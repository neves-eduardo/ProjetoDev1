
package com.ifrs.prova.modelo.entidade;

import java.util.List;
import javax.persistence.*;

@Entity
public class Cliente implements Entidade {
    @Id
    @GeneratedValue(strategy =
          GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String cnpj;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Contato contato;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContatos(Contato contato) {
        this.contato = contato;
    }
}
