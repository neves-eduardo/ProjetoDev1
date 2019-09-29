/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifrs.prova.modelo.rn;

import com.ifrs.prova.excecoes.QuebraRegraNegocio;
import com.ifrs.prova.modelo.dao.ProjetoDAO;
import com.ifrs.prova.modelo.entidade.Projeto;
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
        if(entidade.getNome()==null) {
            throw new QuebraRegraNegocio("3001-:-" + "  Nome é um campo obrigatório");
       } else if (entidade.getLinguagem()==null){
            throw new QuebraRegraNegocio("3001-:-" + "  Linguagem é um campo obrigatório");
        } else if (entidade.getHorasPrevistas()== 0){
            throw new QuebraRegraNegocio("3001-:-" + "  Horas Previstas  é um campo obrigatório");
        } else if (entidade.getHorasExecutadas()!= 0){
            throw new QuebraRegraNegocio("3002-:-" + "  Horas Executadas  não podem ser definidas na criação");
        } else if (entidade.getAtividades().isEmpty()) {
            throw new QuebraRegraNegocio("  Cadastre uma atividade");
        }
       
    }

    @Override
    public void validarAtualizar(Projeto entidadeAtinga, Projeto entidadeNova) {
       if(!entidadeAtinga.getLinguagem().equals(entidadeNova.getLinguagem())) {
            throw new QuebraRegraNegocio("3004-:-" + "  Linguagem não pode ser alterada"); 
       }       else  if(entidadeNova.getNome()==null) {
            throw new QuebraRegraNegocio("3003-:-" + "  Nome é um campo obrigatório"); 
       } else if (entidadeNova.getLinguagem()==null){
            throw new QuebraRegraNegocio("3003-:-" + "  Linguagem é um campo obrigatório");
        } else if (entidadeNova.getHorasPrevistas()== 0){
            throw new QuebraRegraNegocio("3003-:-" + "  Horas Previstas  é um campo obrigatório");
        } else if (entidadeNova.getHorasExecutadas()!= 0){
            throw new QuebraRegraNegocio("3002-:-" + "  Horas Executadas  não podem ser definidas na atualização");}
    }

    @Override
    public void validarExcluir(Projeto entidade) {
        
    }
    
    
}
