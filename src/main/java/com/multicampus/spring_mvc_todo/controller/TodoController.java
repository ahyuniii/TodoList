package com.multicampus.spring_mvc_todo.controller;

import com.multicampus.spring_mvc_todo.dto.PageRequestDTO;
import com.multicampus.spring_mvc_todo.dto.TodoDTO;
import com.multicampus.spring_mvc_todo.service.ITodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class TodoController {

    @Autowired
    private ITodoService todoService;

    @RequestMapping("/")
    public @ResponseBody String root() throws Exception {
        return "Todo List 확인하기";
    }
//    @RequestMapping("/list")
//    public String list(Model model) throws Exception {
//        model.addAttribute("list", todoService.selectAll());
//        return "list";
//    }

    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
//        if(bindingResult.hasErrors()){
//            pageRequestDTO = pageRequestDTO.builder().build();
//        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
        model.addAttribute("responseDTO", todoService.search(pageRequestDTO));
    }


    @GetMapping("/view")
    public String view(HttpServletRequest request, Model model) throws Exception {
        String tno = request.getParameter("tno");
        model.addAttribute("dto", todoService.view(tno));
        return "view";
    }

    @GetMapping("/register")
    public String register() throws Exception {
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerProcess(TodoDTO dto) throws Exception {
        int nResult = todoService.insert(dto);
        return "redirect:list";
    }


    @GetMapping("/modify")
    public String modify(HttpServletRequest request, Model model) throws Exception{
        String tno = request.getParameter("tno");
        model.addAttribute("dto", todoService.view(tno));
        return "modify";
    }

    @PostMapping("/modify")
    public String modifyProcess(TodoDTO dto) throws Exception{

        if(dto.getFinished() == null) {
            dto.setFinished("0");
        } else {
            dto.setFinished("1");
        }
        int nResult = todoService.modify(dto);
        return "redirect:list";
    }

    @GetMapping("/remove")
    public String remove(HttpServletRequest request, Model model) throws Exception {
        String tno = request.getParameter("tno");
        model.addAttribute("dto", todoService.remove(tno));
        return "redirect:list";
    }

//    @GetMapping("/search")
//    public String search(PageRequestDTO pageRequestDTO, Model model) {
//        model.addAttribute("pageRequestDTO", todoService.search(pageRequestDTO));
//        return "list";
//    }
}
