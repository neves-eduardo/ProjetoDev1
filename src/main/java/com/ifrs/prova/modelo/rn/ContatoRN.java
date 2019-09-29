
package com.ifrs.prova.modelo.rn;

import com.ifrs.prova.excecoes.QuebraRegraNegocio;
import com.ifrs.prova.modelo.dao.ContatoDAO;
import com.ifrs.prova.modelo.entidade.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ContatoRN implements RegraNegocio<Contato>{
    @Autowired
    ContatoDAO contatoDAO;

    @Override
    public void validarCadastrar(Contato entidade) {
       if(entidade.getNome()==null) {
            throw new QuebraRegraNegocio("2001-:-" + "  Campo Nome é obrigatório"); 
       } else if (entidade.getEmail()==null){
            throw new QuebraRegraNegocio("2001-:-" + "  Campo E-mail é um campo obrigatório");
        } else if (!entidade.getEmail().contains("@")){
            throw new QuebraRegraNegocio("2002-:-" + "  Campo E-mail deve ser um email válido");
        }
    }

    @Override
    public void validarAtualizar(Contato entidadeAtinga, Contato entidadeNova) {
               if(entidadeNova.getNome()==null) {
            throw new QuebraRegraNegocio("2001-:-" + "  Campo Nome é obrigatório"); 
       } else if (entidadeNova.getEmail()==null){
            throw new QuebraRegraNegocio("2001-:-" + "  Campo E-mail é um campo obrigatório");
        } else if (entidadeNova.getEmail().contains("@")){
            throw new QuebraRegraNegocio("2002-:-" + "  Campo E-mail deve ser um email válido");
        }
    }

    @Override
    public void validarExcluir(Contato entidade) {
        
    }


    
}
