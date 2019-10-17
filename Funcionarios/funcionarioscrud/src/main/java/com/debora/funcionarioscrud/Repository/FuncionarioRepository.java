package com.debora.funcionarioscrud.Repository;

import com.debora.funcionarioscrud.Models.Funcionario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Débora Siqueira
 */
/**
 * Interface que extende os métodos de CRUD
 * Notação repository indica o uso do padrão repository
 */
@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long>{
    
}