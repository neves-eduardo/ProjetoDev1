/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifrs.prova.modelo.dao;

import com.ifrs.prova.modelo.entidade.Atividade;
import com.ifrs.prova.modelo.entidade.Projeto;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author eduardo
 */
public interface ProjetoDAO extends CrudRepository<Projeto, Integer> {
    
}
