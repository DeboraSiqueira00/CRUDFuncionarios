package com.debora.funcionarioscrud.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author Débora Siqueira
 */

 /**
  * Classe que representa o modelo de negocio assim como uma entidade no banco de dados
  * A notação Data da biblioteca lombok que deixa implicito os getters e setters
  * A notação Entity representa que a classe é uma entidade do banco de dados
  */

@Data
@Entity
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFuncionario;
    private String nome;
    private String sobrenome;
    private String email;
    private long PIS;

}