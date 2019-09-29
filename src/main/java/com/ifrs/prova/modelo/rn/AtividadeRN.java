
package com.ifrs.prova.modelo.rn;
import com.ifrs.prova.excecoes.QuebraRegraNegocio;
import com.ifrs.prova.modelo.dao.AtividadeDAO;
import com.ifrs.prova.modelo.dao.ClienteDAO;
import com.ifrs.prova.modelo.entidade.Atividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AtividadeRN implements RegraNegocio<Atividade>{
    @Autowired
    AtividadeDAO atividadeDAO;

    @Override
    public void validarCadastrar(Atividade entidade) {
        if(entidade.getDescricao()==null) {
            throw new QuebraRegraNegocio("4001-:-" + "  Campo Descrição é obrigatório"); 
       } else if (entidade.getHorasPrevistas()==0){
            throw new QuebraRegraNegocio("4001-:-" + "  Campo Horas previstas  é um campo obrigatório");
        } else if (entidade.getHorasExecutadas()!= 0){
            throw new QuebraRegraNegocio("4002-:-" + "  Horas Executadas  não podem ser definidas na criação");
        }
    }

    @Override
    public void validarAtualizar(Atividade entidadeAtinga, Atividade entidadeNova) {
        if(entidadeNova.getDescricao()==null) {
            throw new QuebraRegraNegocio("4001-:-" + "  Campo Descrição é obrigatório"); 
       } else if (entidadeNova.getHorasPrevistas()==0){
            throw new QuebraRegraNegocio("4001-:-" + "  Campo Horas previstas  é um campo obrigatório");
        }
    }

    @Override
    public void validarExcluir(Atividade entidade) {
       
    }
    
}
