
package com.ifrs.prova.modelo.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Atividade implements Entidade{
   @Id
   @GeneratedValue(strategy = 
          GenerationType.IDENTITY)
    private int id;
   private String descricao;
   private int horasPrevistas;
   private int horasExecutadas;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getHorasPrevistas() {
        return horasPrevistas;
    }

    public void setHorasPrevistas(int horasPrevistas) {
        this.horasPrevistas = horasPrevistas;
    }

    public int getHorasExecutadas() {
        return horasExecutadas;
    }

    public void setHorasExecutadas(int horasExecutadas) {
        this.horasExecutadas = horasExecutadas;
    }

}
