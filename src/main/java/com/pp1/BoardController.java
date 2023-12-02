package com.pp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/post")
    public String boardList(Model model) {
        List<BoardVO> boardList = boardService.getBoardList();
        model.addAttribute("post", boardList);
        return "post";
    }

    @GetMapping("/view/{id}")
    public String readBoard(@PathVariable int id, Model model) {
        BoardVO boardVO = boardService.getBoard(id);
        model.addAttribute("post", boardVO);
        return "view";
    }

    @GetMapping("/add")
    public String addPost() {
        return "addform";
    }

    @PostMapping("/addok")
    public String addPostOK(BoardVO vo) {
        if (boardService.insertBoard(vo) != 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }

        return "redirect:post";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable int id, Model model) {
        BoardVO boardVO = boardService.getBoard(id);
        System.out.println(boardVO);
        model.addAttribute("u", boardVO);
        return "editform";
    }

    @PostMapping("/editok")
    public String editPostOk(BoardVO vo) {
        if (boardService.updateBoard(vo) != 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }

        //왜 redirect 사용해?
        return "redirect:post";
    }

    @GetMapping("/deleteok/{id}")
    public String deletePost(@PathVariable int id) {
        if (boardService.deleteBoard(id) != 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }

        return "redirect:../post";
    }
}
