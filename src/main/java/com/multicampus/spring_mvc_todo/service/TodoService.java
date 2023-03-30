package com.multicampus.spring_mvc_todo.service;

import com.multicampus.spring_mvc_todo.dao.ITodoDAO;
import com.multicampus.spring_mvc_todo.dto.PageRequestDTO;
import com.multicampus.spring_mvc_todo.dto.PageResponseDTO;
import com.multicampus.spring_mvc_todo.dto.TodoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements ITodoService {

    @Autowired
    private ITodoDAO dao;

    @Override
    public List<TodoDTO> selectAll() {

        return dao.selectAllDAO();
    }
    @Override
    public TodoDTO view(String tno) {

        return dao.viewDAO(tno);
    }

    @Override
    public int insert(TodoDTO dto) {
        return dao.insertDAO(dto);
    }

    @Override
    public int modify(TodoDTO dto) {

        return dao.modifyDAO(dto);
    }

    @Override
    public int remove(String tno) {

        return dao.removeDAO(tno);
    }

    @Override
//    public List<TodoDTO> search(PageRequestDTO pageRequestDTO) {
    public PageResponseDTO<TodoDTO> search(PageRequestDTO pageRequestDTO) {
        return new PageResponseDTO<TodoDTO>(pageRequestDTO, dao.searchDAO(pageRequestDTO), dao.getCount(pageRequestDTO));
//        return dao.searchDAO(pageRequestDTO);

//        List<TodoDTO> dtoList = dao.getCount(pageRequestDTO);
//        int total = dao.getCount(pageRequestDTO);
////        int total = dtoList.size();
//        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO> withAll()
////                .dtoList(dtoList)
//                .total(total)
//                .pageRequestDTO(pageRequestDTO)
//                .build();
//        return pageResponseDTO;
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        List<TodoDTO> dtoList = dao.selectList(pageRequestDTO);
        int total = dao.getCount(pageRequestDTO);

        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO> withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;
    }

}
