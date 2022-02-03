package com.koreait.springbootboard.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired private BoardService service;

    //화면띄우기 용도
    @GetMapping("/list")
    public void list() {}
}
