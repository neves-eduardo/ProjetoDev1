
package com.ifrs.prova.modelo.rn;

public interface RegraNegocio<T> {
    public void validarCadastrar(T entidade);
    public void validarAtualizar(T entidadeAtinga, T entidadeNova);
    public void validarExcluir(T entidade);
}
