/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifrs.prova.modelo.servico;

import com.ifrs.prova.excecoes.NaoEncontrado;
import com.ifrs.prova.modelo.dao.AtividadeDAO;
import com.ifrs.prova.modelo.entidade.Cliente;
import com.ifrs.prova.modelo.dao.ClienteDAO;
import com.ifrs.prova.modelo.dao.ProjetoDAO;
import com.ifrs.prova.modelo.entidade.Atividade;
import com.ifrs.prova.modelo.entidade.Projeto;
import com.ifrs.prova.modelo.rn.AtividadeRN;
import com.ifrs.prova.modelo.rn.ProjetoRN;
import com.ifrs.prova.modelo.rn.RegraNegocio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author eduardo
 */
@Component
public class ProjetoServico extends Servico<Projeto> {

    @Autowired
    ProjetoDAO projetoDAO;
    @Autowired
    ClienteDAO clienteDAO;
    @Autowired
    AtividadeDAO atividadeDAO;
    @Autowired
    ProjetoRN projetoRN;
    @Autowired
    AtividadeRN atividadeRN;

    @Override
    public CrudRepository<Projeto, Integer> getDAO() {
        return projetoDAO;
    }

    @Override
    public RegraNegocio<Projeto> getRegraNegocio() {
        return projetoRN;
    }

    @Override
    public Projeto cadastrar(Projeto entidade) {
//        if(entidade.getAtividades()!=null) {
//            for(Atividade atividade : entidade.getAtividades()){
//                atividadeRN.validarCadastrar(atividade);
//            }}
        projetoRN.validarCadastrar(entidade);
        entidade.setCliente(buscaCliente(entidade.getCliente()));

        return super.cadastrar(entidade);
    }

    @Override
    public void atualizar(Projeto entidade) {
//        if(!entidade.getAtividades().isEmpty()) {
//            for(Atividade atividade : entidade.getAtividades()){
//                atividadeRN.validarCadastrar(atividade);
//            }}
        projetoRN.validarAtualizar(entidade, entidade);
        entidade.setCliente(buscaCliente(entidade.getCliente()));
        super.atualizar(entidade); //To change body of generated methods, choose Tools | Templates.
    }

    private Cliente buscaCliente(Cliente cliente) {
        Optional<Cliente> clienteBuscado = clienteDAO.findById(cliente.getId());
        if (clienteBuscado.isPresent()) {
            return clienteBuscado.get();
        } else {
            throw new NaoEncontrado("Clientenão encontrado");
        }
    }

    public Atividade cadastrarAtividade(int idProjeto, Atividade atividade) throws Throwable {
        Projeto projeto = this.recuperar(idProjeto);
        atividade.setId(0);
        atividadeRN.validarCadastrar(atividade);
        projetoRN.validarDescricaoAtt(atividade.getId(), atividade.getDescricao(), this.recuperar(idProjeto));
        Atividade atividadeBanco = atividadeDAO.save(atividade);

        projeto.getAtividades().add(atividadeBanco);
        projetoRN.validarHorasPrevistas(projeto);
        this.somaHoras(projeto);
        projetoDAO.save(projeto);
        return atividadeBanco;
    }

    public Atividade recuperarAtividade(int idProjeto, int idAtividade) throws Throwable {
        Projeto projeto = this.recuperar(idProjeto);
        List<Atividade> atividades = projeto.getAtividades();
        for (Atividade atividade : atividades) {
            if (atividade.getId() == idAtividade) {
                return atividade;
            }
        }
        throw new NaoEncontrado("id " + idAtividade + " não foi encontrada");
    }

    public void atualizarAtividade(int idProjeto, Atividade atividade) throws Throwable {
        Projeto projeto = this.recuperar(idProjeto);
        this.recuperarAtividade(idProjeto, atividade.getId());
        projetoRN.validarDescricaoAtt(atividade.getId(), atividade.getDescricao(), this.recuperar(idProjeto));
        atividadeRN.validarAtualizar(this.recuperarAtividade(idProjeto, atividade.getId()), atividade);

        
        
        //rojetoRN.validarHorasPrevistas(projeto);
        projetoRN.validarHorasPrevistasAtt(projeto, this.recuperarAtividade(idProjeto, atividade.getId()), atividade);
        //this.somaHoras(projeto);
        atividadeDAO.save(atividade);
        Projeto proj2 = this.recuperar(idProjeto);
        this.somaHoras(proj2);
        projetoDAO.save(proj2);
    }

    public List<Atividade> listarAtividade(int idProjeto) throws Throwable {
        return this.recuperar(idProjeto).getAtividades();
    }

    public void excluirAtividade(int idProjeto, int idAtividade) throws Throwable {
        Projeto projeto = this.recuperar(idProjeto);
        List<Atividade> atividades = projeto.getAtividades();
        for (Atividade atividade : atividades) {
            if (atividade.getId() == idAtividade) {
                projeto.getAtividades().remove(atividade);
                this.somaHoras(projeto);
                projetoDAO.save(projeto);
                return;
            }
        }
        throw new NaoEncontrado("id " + idAtividade + " não foi encontrada");
    }

    public void somaHoras(Projeto entidade) {
        List<Atividade> atividades = entidade.getAtividades();
        int somaHoras = 0;
        for (Atividade at : atividades) {
            somaHoras += at.getHorasExecutadas();
        }
        entidade.setHorasExecutadas(somaHoras);
    }
}
