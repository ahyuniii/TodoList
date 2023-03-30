package com.multicampus.spring_mvc_todo.dao;

import com.multicampus.spring_mvc_todo.dto.PageRequestDTO;
import com.multicampus.spring_mvc_todo.dto.PageResponseDTO;
import com.multicampus.spring_mvc_todo.dto.TodoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ITodoDAO {
    public List<TodoDTO> selectAllDAO();
    public TodoDTO viewDAO(String tno);
    public int insertDAO(TodoDTO dto);
    public int modifyDAO(TodoDTO dto);
    public int removeDAO(String tno);

    public List<TodoDTO> searchDAO(PageRequestDTO pageRequestDTO);
    public List<TodoDTO> selectList(PageRequestDTO pageRequestDTO);
    public int searchCountDAO(PageRequestDTO pageRequestDTO);
    public int getCount(PageRequestDTO pageRequestDTO);

}
