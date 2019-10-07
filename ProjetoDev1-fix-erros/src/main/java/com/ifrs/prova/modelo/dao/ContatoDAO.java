/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifrs.prova.modelo.dao;

import com.ifrs.prova.modelo.entidade.Atividade;
import com.ifrs.prova.modelo.entidade.Contato;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author eduardo
 */
public interface ContatoDAO extends CrudRepository<Contato, Integer> {
    
}
