
package com.ifrs.prova.modelo.servico;

import com.ifrs.prova.excecoes.NaoEncontrado;
import com.ifrs.prova.modelo.entidade.Entidade;
import com.ifrs.prova.modelo.rn.RegraNegocio;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


public abstract class Servico<T extends Entidade> {

    public abstract CrudRepository<T, Integer> getDAO();
    public abstract RegraNegocio<T> getRegraNegocio();

    public T cadastrar(T entidade) {
        entidade.setId(0);
        if(getRegraNegocio()!=null)
            getRegraNegocio().validarCadastrar(entidade);
        T save = getDAO().save(entidade);
        return save;
    }

    public Iterable<T> listar() {
        return getDAO().findAll();
    }

    public void atualizar(T entidade) {
        if(getRegraNegocio()!=null) {
            T entidadeAntiga = getDAO().findById(entidade.getId()).get();
            getRegraNegocio().validarAtualizar(entidadeAntiga, entidade);
        }
        getDAO().save(entidade);
    }

    public T recuperar(int id) throws Throwable {
        Optional<T> optional = getDAO().findById(id);
        if (!optional.isPresent()) {
            throw new NaoEncontrado("id " + id + " não foi encontrada");
        }
        return optional.get();
    }

    public void excluir(int id) throws Throwable {
        if (!getDAO().existsById(id)) {
            throw new NaoEncontrado("id " + id + " não foi encontrada");
        }
        if(getRegraNegocio()!=null)
            getRegraNegocio().validarExcluir(recuperar(id));
        getDAO().deleteById(id);
    }

}

