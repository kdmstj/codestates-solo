package com.codestates.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TodoDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post{

        private String title;

    }

    @Getter
    @AllArgsConstructor
    public static class Response{

        private Long todoId;
        private String title;
        private Long todoOrder;
        private Boolean completed;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        private String title;
        private Long todoOrder;
        private Boolean completed;
    }


}
