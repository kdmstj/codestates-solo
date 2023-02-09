package com.codestates.todo.entity;

import lombok.*;

import javax.persistence.*;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @Column(nullable = false)
    private String title;

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoOrder;

    @Column(nullable = false)
    private Boolean completed = false;



}
