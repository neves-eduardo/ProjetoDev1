
package com.ifrs.prova.modelo.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Projeto implements Entidade {

    public String[] getLinguagensAceitas() {
        return linguagensAceitas;
    }

    public void setLinguagensAceitas(String[] linguagensAceitas) {
        this.linguagensAceitas = linguagensAceitas;
    }


    @Id
    @GeneratedValue(strategy = 
          GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String linguagem;
    private int horasPrevistas;
    private int horasExecutadas;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atividade> atividades;
    @ManyToOne
    private Cliente cliente;
    @JsonIgnore
    private String[] linguagensAceitas = {"java", "c", "python", "javascript", "php", "objective-c", "delphi", "go", "visual basic", "kotlin"};

    
    
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

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
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

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
        public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
