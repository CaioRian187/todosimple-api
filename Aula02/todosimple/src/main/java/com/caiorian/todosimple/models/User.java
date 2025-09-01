package com.caiorian.todosimple.models;

import org.hibernate.usertype.Sized;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity //Transformando em uma entidade
@Table(name = User.TABLE_NAME)  //Criando uma tabela, e agora o spring boot entende que criamos uma tabela que é uma entidade


public class User {
    public static final String TABLE_NAME = "user";

    //Criando os atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //É basicamente o incremento do ID no banco de dados
    @Column(name = "id", unique = true) // unique siginifica unico ou exclusivo
    private Long id; //Lembrando recomendado usar os tipos primitivos do formato de classe(Double,Interger,Long,Float, ...) que ajuda
                     //na questão de quando uma variavel normal acontecer de ficar null não da erro no projeto todo
                    
                     
    @Column(name = "username" , length = 100 , nullable = false , unique = true) 
    @NotNull //Validação de não null
    @NotEmpty //Validação de não vazio
    Sized(min = 2, max = 100) //
    private String username; //Definindo a coluna usuario com o nome, tamanho, não pode ser null, e tem que ser unica


    @Column(name = "password" , length = 60 , nullable = false)
    @NotNull //Validação não null
    @NotEmpty //Validação não vazia
    private String password; //Definindo a coluna senha com o nome, tamanho, não pode ser null


}   
