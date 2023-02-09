package com.codestates.todo.service;

import com.codestates.exception.BusinessException;
import com.codestates.exception.ErrorCode;
import com.codestates.todo.entity.Todo;
import com.codestates.todo.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo){

        //가장 큰 todoOrder 를 조회하여 1을 더한 후 저장
        todo.setTodoOrder(getLargestTodoOrder()+1);

        return todoRepository.save(todo);


    }

    public List<Todo> getTodoList() {

        return todoRepository.findAll();
    }

    public Todo getTodo(long todoId) {

        return verifyExistId(todoId);
    }



    public Todo updateTodo(long todoId, Todo todo) {

        Todo findTodo = verifyExistId(todoId);
        Optional.ofNullable(todo.getTitle())
                        .ifPresent(findTodo::setTitle);

        Optional.ofNullable(todo.getCompleted())
                .ifPresent(findTodo::setCompleted);

        Optional.ofNullable(todo.getTodoOrder())
                .ifPresent(findTodo::setTodoOrder);

        return todoRepository.save(findTodo);
    }

    public void deleteTodoList(){
        todoRepository.deleteAll();
    }

    public void deleteTodo(long todoId){
        verifyExistId(todoId);
        todoRepository.deleteById(todoId);

        //DB에 저장되어 있는 Todo가 있을 경우,
        if(todoRepository.count()!=0){
            //삭제한 todoId 보다 큰 값을 가진 Todo의 todoOrder -1을 더한 후 수정
            updateOrder(todoId);
        }
    }

    private Todo verifyExistId(long todoId) {

        Optional<Todo> optionalTodo = todoRepository.findById(todoId);

        return optionalTodo.orElseThrow(() ->
                new BusinessException(ErrorCode.TODO_NOT_FOUND));
    }

    private long getLargestTodoOrder(){
        List<Todo> todoList = todoRepository.findAll(Sort.by(Sort.Direction.DESC, "todoOrder"));

        long largestTodoOrder = 0;
        if(!(todoList.size() ==0)){
            largestTodoOrder = todoList.get(0).getTodoOrder();
        }

        return largestTodoOrder;
    }


    private void updateOrder(long todoId){

        List<Todo> todoList = todoRepository.findAll(Sort.by(Sort.Direction.DESC, "todoId"));
        long last_todoId = todoList.get(0).getTodoId();

        //가장 큰 todoId 부터 삭제한 todoId 까지 updateOrder 수행
        while(last_todoId > todoId){
            Optional<Todo> OptionalTodo = todoRepository.findById(last_todoId);
            if(OptionalTodo.isPresent()){
                Todo todo = OptionalTodo.get();
                long todoOrder = todo.getTodoOrder();
                todo.setTodoOrder(--todoOrder);
                updateTodo(last_todoId, todo);
            }
            last_todoId--;
        }


    }
}
