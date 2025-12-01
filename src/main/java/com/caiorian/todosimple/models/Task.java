package com.caiorian.todosimple.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tasks")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique = true)
    private Long id;

    @ManyToOne  //Define que varias tarefas será de um usuario
    @JoinColumn(name = "user_id", nullable = false, updatable = false) //Relacionando com o id da tabela user
    private User user;

    @Column(name="description", length = 255, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String description;

    public Task() {
    }

    public Task(Long id, User user, String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }


    @Override
    public boolean equals(Object obj){
        if (obj == this){
            return true;
        }
         if (obj == null){
            return false;
        }
        if (!(obj instanceof Task)){
            return false;
        }

        Task other = (Task) obj;

        if (this.id == null){
            if (other.id != null){
                return false;
            }
            else if (!this.id.equals(other.id)){
                return false;
            }

            return Objects.equals(this.id, other.id) && Objects.equals(this.user, other.user) && Objects.equals(this.description, other.description);
        }
        return false;

}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    

}
