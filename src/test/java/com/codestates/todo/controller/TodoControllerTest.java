package com.codestates.todo.controller;


import com.codestates.todo.dto.TodoDto;
import com.codestates.todo.entity.Todo;
import com.codestates.todo.mapper.TodoMapper;
import com.codestates.todo.service.TodoService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private TodoService todoService;

    @MockBean
    TodoMapper todoMapper;

    @Test
    void postTodoTest() throws Exception{
        //given
        TodoDto.Post todoDtoPost = new TodoDto.Post("운동하기");
        TodoDto.Response todoDtoResponse = new TodoDto.Response(1L, "운동하기", 1L, false);

        given(todoMapper.TodoPostToTodo(Mockito.any(TodoDto.Post.class))).willReturn(new Todo());

        given(todoService.createTodo(Mockito.any(Todo.class))).willReturn(new Todo());

        given(todoMapper.TodoToTodoResponse(Mockito.any(Todo.class))).willReturn(todoDtoResponse);

        Gson gson = new Gson();
        String content = gson.toJson(todoDtoPost);

        //when
        ResultActions actions =
                mockMvc.perform(
                        post("/")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
        //then
        actions.andExpect(status().isCreated());
//                .andExpect(jsonPath("$.body.title").value(todoDtoPost.getTitle()));

    }
}
