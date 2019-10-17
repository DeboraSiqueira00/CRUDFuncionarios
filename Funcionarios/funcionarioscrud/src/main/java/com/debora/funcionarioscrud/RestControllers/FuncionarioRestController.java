package com.debora.funcionarioscrud.RestControllers;

import java.util.Optional;

import com.debora.funcionarioscrud.Models.Funcionario;
import com.debora.funcionarioscrud.Repository.FuncionarioRepository;
import com.debora.funcionarioscrud.dto.DTOFuncionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Débora Siqueira
 */
/**
 * Classe controller com as requisições http
 * A notação crossOrigin libera o acesso as requisições para o front
 * A notação restController que indica que a classe é uma controller
 * A notação RequestMapping define a url de requisição
 * 
 */
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/Funcionario")

public class FuncionarioRestController {

    @Autowired
    private FuncionarioRepository rf;
/**
 * Método que retorna todos os funcionarios cadastrados
 * @return todos os funcionários ou emite um aviso que não há funcionarios
 */
    @GetMapping(value = "")
    public ResponseEntity<Object> httpGetFuncionarios() {
        if (rf.count() > 0) {
            Iterable<Funcionario> todosFuncionarios = rf.findAll();
            return ResponseEntity.ok(todosFuncionarios);
        } else {
            return ResponseEntity.badRequest().body("Não há funcionarios cadastrados");
        }

    }
/**
 * Método que retorna um funcionário especifico 
 * @param id do funcionario requisitado
 * @return o funcionario desejado
 */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> httpGetFuncionario(@PathVariable long id) {
        Optional<Funcionario> funcionario = rf.findById(id);
        if (funcionario.isPresent()) {
            return ResponseEntity.ok(funcionario);

        } else {
            return ResponseEntity.badRequest().body("Não há funcionarios cadastrados com o id =" + id);
        }

    }
/**
 * Método para cadastro do funcionario
 * @param funcionario desejado para cadastro
 * @return o funcionario cadastrado
 */
    @PostMapping
    public ResponseEntity<Funcionario> httpPostFuncionario(@RequestBody DTOFuncionario funcionario) {
        Funcionario nf = new Funcionario();
        nf.setEmail(funcionario.getEmail());
        nf.setNome(funcionario.getNome());
        nf.setSobrenome(funcionario.getSobrenome());
        nf.setPIS(funcionario.getPIS());
        Funcionario funcionarioCriado = rf.save(nf);
        return ResponseEntity.ok(funcionarioCriado);

    }
/**
 * Método para excluir um funcionário
 * @param id do funcionario 
 * @return o funcionario deletado ou aviso que não há funcionario com o id requisitado
 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> httpDeleteFuncionario(@PathVariable long id) {
        Optional<Funcionario> funcionario = rf.findById(id);
        if (funcionario.isPresent()) {
            rf.deleteById(id);
            return ResponseEntity.ok(funcionario);
        } else {
            return ResponseEntity.badRequest().body("Não há funcionarios com esse ID");
        }

    }
/**
 * Método para atualizar um fincionario
 * @param id do funcionario 
 * @param funcionario atualizar os dados novos
 * @return atualizado ou emite uma mensagem caso não encontrado
 */
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> httpPutFuncionario(@PathVariable long id,
            @RequestBody DTOFuncionario funcionario) {
        return rf.findById(id).map(funcionarioAtualizado -> {
            funcionarioAtualizado.setEmail(funcionario.getEmail());
            funcionarioAtualizado.setNome(funcionario.getNome());
            funcionarioAtualizado.setSobrenome(funcionario.getSobrenome());
            funcionarioAtualizado.setPIS(funcionario.getPIS());
            Funcionario atualizado = rf.save(funcionarioAtualizado);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());

    }
}