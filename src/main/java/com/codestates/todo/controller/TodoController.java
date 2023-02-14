package com.codestates.todo.controller;


import com.codestates.todo.dto.TodoDto;
import com.codestates.todo.entity.Todo;
import com.codestates.todo.mapper.TodoMapper;
import com.codestates.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @GetMapping("/")
    public String helloWorld(){
        return "To-do Application! made by eunseo ";
    }

    @PostMapping
    public ResponseEntity postTodo(@RequestBody TodoDto.Post todoPost){

        //todoPost -> todo
        Todo todo = todoMapper.TodoPostToTodo(todoPost);

        Todo createdTodo = todoService.createTodo(todo);

        //createdTodo -> todoResponse
        TodoDto.Response todoResponse = todoMapper.TodoToTodoResponse(createdTodo);


        return new ResponseEntity<>(todoResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getTodoList(){

        List<Todo> todoList = todoService.getTodoList();
        List<TodoDto.Response> todoResponseList = todoMapper.TodoListToTodoResponseList(todoList);

        return new ResponseEntity<>(todoResponseList, HttpStatus.OK);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") Long todoId){

        Todo todo = todoService.getTodo(todoId);
        TodoDto.Response todoResponse = todoMapper.TodoToTodoResponse(todo);

        return new ResponseEntity<>(todoResponse, HttpStatus.OK);
    }

    @PatchMapping("/{todo-id}")
    public ResponseEntity patchTodo(@RequestBody TodoDto.Patch todoPatch , @PathVariable("todo-id") long todoId) {

        Todo todo = todoMapper.TodoPatchToDo(todoPatch);

        Todo patchedTodo = todoService.updateTodo(todoId, todo);

        return new ResponseEntity(patchedTodo, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deleteTodoList(){

        todoService.deleteTodoList();
        return new ResponseEntity(HttpStatus.OK);

    }

    @DeleteMapping("{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") long todoId){
        todoService.deleteTodo(todoId);
        return new ResponseEntity(HttpStatus.OK);
    }




}
