
package com.ifrs.prova.modelo.servico;
import com.ifrs.prova.excecoes.NaoEncontrado;
import com.ifrs.prova.modelo.entidade.Cliente;
import com.ifrs.prova.modelo.dao.ClienteDAO;
import com.ifrs.prova.modelo.dao.ContatoDAO;
import com.ifrs.prova.modelo.dao.ProjetoDAO;
import com.ifrs.prova.modelo.entidade.Contato;
import com.ifrs.prova.modelo.entidade.Projeto;
import com.ifrs.prova.modelo.rn.ClienteRN;
import com.ifrs.prova.modelo.rn.ContatoRN;
import com.ifrs.prova.modelo.rn.ProjetoRN;
import com.ifrs.prova.modelo.rn.RegraNegocio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public class ClienteServico extends Servico<Cliente> {
    @Autowired
    ClienteDAO clienteDAO;
    @Autowired
    ContatoDAO contatoDAO;
    @Autowired
    ClienteRN clienteRN;
        @Autowired
    ContatoRN contatoRN;
    
    @Override
    public CrudRepository<Cliente, Integer> getDAO() {
        return clienteDAO;
    }

    @Override
    public RegraNegocio<Cliente> getRegraNegocio() {
        return null;
    }

    @Override
    public Cliente cadastrar(Cliente entidade) {
        if(!entidade.getContatos().isEmpty()) {
            for(Contato contato : entidade.getContatos()){
            contatoRN.validarCadastrar(contato);
            }
        }
        clienteRN.validarCadastrar(entidade);
        return super.cadastrar(entidade); 
    }
    
        public Contato cadastrarContato(int idCliente, Contato contato) throws Throwable {
        Cliente cliente = this.recuperar(idCliente);
        contato.setId(0);
        contatoRN.validarCadastrar(contato);
        Contato contatoBanco = contatoDAO.save(contato);
        cliente.getContatos().add(contatoBanco);
        clienteDAO.save(cliente);
        return contatoBanco;
    }

    public Contato recuperarContato(int idCliente, int idContato) throws Throwable {
        Cliente cliente= this.recuperar(idCliente);
        List<Contato> contatos = cliente.getContatos();
        for (Contato contato : contatos) {
            if (contato.getId() == idContato) {
                return contato;
            }
        }
        throw new NaoEncontrado("id " + idContato + " não foi encontrada");
    }

    public void atualizarContato(int idCliente, Contato contato) throws Throwable {
        this.recuperarContato(idCliente, contato.getId());
        contatoRN.validarAtualizar(this.recuperarContato(idCliente, contato.getId()), contato);
        contatoDAO.save(contato);
    }

    public List<Contato> listarContatos (int idCliente) throws Throwable {
        return this.recuperar(idCliente).getContatos();
    }
    
        public void excluirContato(int idCliente, int idContato) throws Throwable {
        Cliente cliente = this.recuperar(idCliente);
        List<Contato> contatos = cliente.getContatos();
        for (Contato contato : contatos) {
            if (contato.getId() == idContato) {
                cliente.getContatos().remove(contato);
                clienteDAO.save(cliente);
                return;
            }
        }
        throw new NaoEncontrado("id " + idContato + " não foi encontrada");
    }
    
}
