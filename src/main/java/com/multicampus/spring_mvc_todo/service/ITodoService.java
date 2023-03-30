package com.multicampus.spring_mvc_todo.service;

import com.multicampus.spring_mvc_todo.dto.PageRequestDTO;
import com.multicampus.spring_mvc_todo.dto.PageResponseDTO;
import com.multicampus.spring_mvc_todo.dto.TodoDTO;

import java.util.List;

public interface ITodoService {
    List<TodoDTO> selectAll();

    TodoDTO view(String tno);

    int insert(TodoDTO dto);

    int modify(TodoDTO dto);

    int remove(String tno);

    PageResponseDTO<TodoDTO>  search(PageRequestDTO pageRequestDTO);

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);


}


