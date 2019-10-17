package com.debora.funcionarioscrud.dto;

import lombok.Data;

/**
 * @author Débora Siqueira
 */
/**
 * Classe DTO para transferencia de dados 
 */
@Data
public class DTOFuncionario {

    private String nome;
    private String sobrenome;
    private String email;
    private long PIS;

}