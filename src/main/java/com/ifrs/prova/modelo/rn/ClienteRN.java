
package com.ifrs.prova.modelo.rn;
import com.ifrs.prova.excecoes.QuebraRegraNegocio;
import com.ifrs.prova.modelo.dao.ClienteDAO;
import com.ifrs.prova.modelo.entidade.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ClienteRN  implements RegraNegocio<Cliente>{
    @Autowired
    ClienteDAO clienteDAO;

    @Override
    public void validarCadastrar(Cliente entidade) {
     if(entidade.getNome()==null) {
        throw new QuebraRegraNegocio("1001-:-" + "  Nome é um campo obrigatório"); 
       } else if(entidade.getCnpj()==null) {
        throw new QuebraRegraNegocio("1001-:-" + " Cnpj é um campo obrigatório"); 
       } else if(entidade.getCnpj()==null||entidade.getCnpj().length()!=14) {
       throw new QuebraRegraNegocio("1001-:-" + " Cnpj deve ter 14 caracteres");}
    }

    @Override
    public void validarAtualizar(Cliente entidadeAtinga, Cliente entidadeNova) {
   
    }

    @Override
    public void validarExcluir(Cliente entidade) {
        
    }
    
    
    
}
