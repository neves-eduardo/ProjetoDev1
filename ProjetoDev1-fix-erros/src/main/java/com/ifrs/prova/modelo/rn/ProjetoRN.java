/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifrs.prova.modelo.rn;

import com.ifrs.prova.excecoes.QuebraRegraNegocio;
import com.ifrs.prova.modelo.dao.ProjetoDAO;
import com.ifrs.prova.modelo.entidade.Atividade;
import com.ifrs.prova.modelo.entidade.Projeto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author eduardo
 */
@Component
public class ProjetoRN implements RegraNegocio<Projeto> {

    @Autowired
    ProjetoDAO projetoDAO;

    @Override
    public void validarCadastrar(Projeto entidade) {
        boolean c = false;
        if (entidade.getNome() == null || entidade.getNome().equals("")) {
            throw new QuebraRegraNegocio("3001-:-" + "  Nome é um campo obrigatório");
        } else if (entidade.getLinguagem() == null || entidade.getLinguagem().equals("")) {
            throw new QuebraRegraNegocio("3001-:-" + "  Linguagem é um campo obrigatório");
        } else if (entidade.getHorasPrevistas() == 0) {
            throw new QuebraRegraNegocio("3001-:-" + "  Horas Previstas  é um campo obrigatório");
        } else if (entidade.getHorasExecutadas() != 0) {
            throw new QuebraRegraNegocio("3002-:-" + "  Horas Executadas  não podem ser definidas na criação");
        }
        String[] linguagensAceitas = entidade.getLinguagensAceitas();
        for (String linguagem : linguagensAceitas) {
            if (entidade.getLinguagem().equals(linguagem)) {
                c = true;
            }
        }
        if (c == false) {
            throw new QuebraRegraNegocio("3003-:-" + "  Linguagem inválida");
        }
        
        
        
        
//        else if (entidade.getAtividades().isEmpty()) {
//            throw new QuebraRegraNegocio("  Cadastre uma atividade");
//        }
//       
    }

    @Override
    public void validarAtualizar(Projeto entidadeAtinga, Projeto entidadeNova) {
        if (!entidadeAtinga.getLinguagem().equals(entidadeNova.getLinguagem())) {
            throw new QuebraRegraNegocio("3004-:-" + "  Linguagem não pode ser alterada");
        } else if (entidadeNova.getNome() == null || entidadeNova.getNome().equals("")) {
            throw new QuebraRegraNegocio("3001-:-" + "  Nome é um campo obrigatório");
        } else if (entidadeNova.getLinguagem() == null || entidadeNova.getLinguagem().equals("")) {
            throw new QuebraRegraNegocio("3001-:-" + "  Linguagem é um campo obrigatório");
        } else if (entidadeNova.getHorasPrevistas() == 0) {
            throw new QuebraRegraNegocio("3001-:-" + "  Horas Previstas  é um campo obrigatório");
        } else if (entidadeNova.getHorasExecutadas() != 0) {
            throw new QuebraRegraNegocio("3002-:-" + "  Horas Executadas  não podem ser definidas na atualização");
        }
    }

    @Override
    public void validarExcluir(Projeto entidade) {

    }
    

    
    public void validarHorasPrevistas(Projeto entidade){
        int horasPrevistasTotais=0;
        for (Atividade atividade:entidade.getAtividades()){
        horasPrevistasTotais+=atividade.getHorasPrevistas();
        }
        if (horasPrevistasTotais>entidade.getHorasPrevistas()) {
            throw new QuebraRegraNegocio("4003-:-" + " Soma das horas previstas não pode exceder as horas previstas do projeto");
        }
        
        
    }
    
    
        public void validarHorasPrevistasAtt(Projeto entidade, Atividade attAntiga,Atividade attNova){
        int horasPrevistasTotais= entidade.getHorasPrevistas();
        if (horasPrevistasTotais<(entidade.getHorasPrevistas()+attNova.getHorasPrevistas()-attAntiga.getHorasPrevistas())) {
            throw new QuebraRegraNegocio("4003-:-" + " Soma das horas previstas não pode exceder as horas previstas do projeto");
        }
        
        
    }
    
    public void validarDescricaoAtt(int id,String DescNova,Projeto entidade){
        List <Atividade> atts = entidade.getAtividades();
        for (Atividade atividade:atts) {
            if (atividade.getDescricao().equals(DescNova) && id!=atividade.getId()) {
             throw new QuebraRegraNegocio("4004-:-" + " Descrição de atividades de um projeto não podem ser iguais");
            }
        }
    }

}
