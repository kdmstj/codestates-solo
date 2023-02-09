package com.codestates.todo.mapper;

import com.codestates.todo.dto.TodoDto;
import com.codestates.todo.entity.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    Todo TodoPostToTodo(TodoDto.Post todoPostDto);

    TodoDto.Response TodoToTodoResponse(Todo Todo);

    List<TodoDto.Response> TodoListToTodoResponseList(List<Todo> todoList);

    Todo TodoPatchToDo(TodoDto.Patch todoPatch);
}
