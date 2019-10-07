
package com.ifrs.prova.controle;

import com.ifrs.prova.modelo.entidade.Atividade;
import com.ifrs.prova.modelo.entidade.Projeto;
import com.ifrs.prova.modelo.servico.Servico;
import com.ifrs.prova.modelo.servico.ProjetoServico;
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
@RequestMapping("/api/projetos")
public class Projetos extends CRUDControle<Projeto> {
        @Autowired
    ProjetoServico servico;
    
    @Override
    public Servico<Projeto> getService() {
        return servico;
    }


    
    @PostMapping("/{idProjeto}/atividades/")
    @ResponseStatus(HttpStatus.CREATED)
    public Atividade cadastrarAtividade(@PathVariable int idProjeto, @RequestBody Atividade atividade) throws Throwable {
        return servico.cadastrarAtividade(idProjeto, atividade);
    }

    @PutMapping("/{idProjeto}/atividades/{idAtividade}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarAtividade(@PathVariable int idProjeto, @PathVariable int idAtividade, @RequestBody Atividade atividade) throws Throwable {
        atividade.setId(idAtividade);
        servico.atualizarAtividade(idProjeto, atividade);
    }

    @DeleteMapping("/{idProjeto}/atividades/{idAtividade}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirAtividade(@PathVariable int idProjeto, @PathVariable int idAtividade) throws Throwable {
        servico.excluirAtividade(idProjeto, idAtividade);
    }

    @GetMapping("/{idProjeto}/atividades/{idAtividade}")
    @ResponseStatus(HttpStatus.OK)
    public Atividade recuperarAtividade(@PathVariable int idProjeto, @PathVariable int idAtividade) throws Throwable {
        return servico.recuperarAtividade(idProjeto, idAtividade);
    }

    
    @GetMapping("/{idProjeto}/atividades/")
    @ResponseStatus(HttpStatus.OK)
    public List<Atividade> listarAtividades(@PathVariable int idProjeto) throws Throwable {
        return servico.listarAtividade(idProjeto);
    }
}

