
package com.ifrs.prova.controle;

import com.ifrs.prova.modelo.entidade.Contato;
import com.ifrs.prova.modelo.entidade.Cliente;
import com.ifrs.prova.modelo.entidade.Cliente;
import com.ifrs.prova.modelo.servico.ClienteServico;
import com.ifrs.prova.modelo.servico.Servico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/clientes")
public class Clientes extends CRUDControle<Cliente> {
    @Autowired
    ClienteServico servico;
    
    @Override
    public Servico<Cliente> getService() {
        return servico;
    }
        @PostMapping("/{idCliente}/contatos")
    @ResponseStatus(HttpStatus.CREATED)
    public Contato cadastrarContato(@PathVariable int idCliente, @RequestBody Contato atividade) throws Throwable {
        return servico.cadastrarContato(idCliente, atividade);
    }

    @PutMapping("/{idCliente}/contatos/{idContato}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarContato(@PathVariable int idCliente, @PathVariable int idContato, @RequestBody Contato atividade) throws Throwable {
        atividade.setId(idContato);
        servico.atualizarContato(idCliente, atividade);
    }

    @DeleteMapping("/{idCliente}/contatos/{idContato}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirContato(@PathVariable int idCliente, @PathVariable int idContato) throws Throwable {
        servico.excluirContato(idCliente, idContato);
    }

    @GetMapping("/{idCliente}/contatos/{idContato}")
    @ResponseStatus(HttpStatus.OK)
    public Contato recuperarContato(@PathVariable int idCliente, @PathVariable int idContato) throws Throwable {
        return servico.recuperarContato(idCliente, idContato);
    }

    
    @GetMapping("/{idCliente}/contatos/")
    @ResponseStatus(HttpStatus.OK)
    public List<Contato> listarContatos(@PathVariable int idCliente) throws Throwable {
        return servico.listarContatos(idCliente);
    }
    
}
