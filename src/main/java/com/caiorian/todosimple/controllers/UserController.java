package com.caiorian.todosimple.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.caiorian.todosimple.models.User;
import com.caiorian.todosimple.models.User.CreateUser;
import com.caiorian.todosimple.models.User.UpdateUser;
import com.caiorian.todosimple.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")                 // Utiliza o pathVariable porque ele é uma variavel que a rota recebe
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = this.userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Validated(CreateUser.class) // Realizar as validações que foram colocadas no model (NotNull, NotEmpyt e Size)
    public ResponseEntity<Void> create(@Valid @RequestBody User obj){
        this.userService.createUser(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(obj.getId()).toUri();
        // É basicamente um builder que vai pegar o contexto da requisição atual que estamo rodando, adicionar o path que é o id e
        // faz um build expandido adicionando o getId() na variavel path("{id}"
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(UpdateUser.class)
    public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id){
        obj.setId(id);
        obj = this.userService.update(obj);

        return ResponseEntity.noContent().build();
        //noContent() Significa não retornando nenhum dado e retorna status 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();

    }
}   
