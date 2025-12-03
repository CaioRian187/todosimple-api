package com.caiorian.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caiorian.todosimple.models.User;
import com.caiorian.todosimple.repositories.TaskRepository;
import com.caiorian.todosimple.repositories.UserRepository;


@Service
public class UserService {  

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public User findById (Long id){
        Optional<User> user = this.userRepository.findById(id);

        //Retornando o usuario se ele não for vazio, caso contrario lança uma exception personalizada
        return user.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado! Id:" + id + ", Tipo:" + User.class.getName())
        );
    }

    @Transactional //Usar sempre que for manipular dados no bd, Ajuda a realizar a operação completa para não realiza-la pela metade
    public User createUser (User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj); 
    }

    public void delete(Long id){
        findById(id);

        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois hé entidades relacionadas!");
        }
    }
}
