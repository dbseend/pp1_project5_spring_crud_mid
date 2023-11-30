package com.pp1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public String boardList(Model model) {
        model.addAttribute("list", boardService.getBoardList());
        return "list";
    }

    @GetMapping("/add")
    public String addPost() {
        return "addpostform";
    }

    @PostMapping("/addok")
    public String addPostOK(BoardVO vo) {
        if (boardService.insertBoard(vo) != 0) {
            System.out.println("데이터 추가 성공");
        } else {
            System.out.println("데이터 추가 실패");
        }

        return "redirect:list";
    }

    @GetMapping("/editform/{id}")
    public String editPost(@PathVariable int id, Model model) {
        BoardVO boardVO = boardService.getBoard(id);
        model.addAttribute("update", boardVO);
        return "editform";
    }

    @PostMapping("/editok")
    public String editPostOk(BoardVO vo) {
        if (boardService.updateBoard(vo) != 0) {
            System.out.println("데이터 수정 성공");
        } else {
            System.out.println("데이터 수정 실패");
        }

        return "redirect:list";
    }

    @GetMapping("/deleteok/{id}")
    public String deletePost(@PathVariable int id) {
        if (boardService.deleteBoard(id) != 0) {
            System.out.println("데이터 삭제 성공");
        } else {
            System.out.println("데이터 삭제 실패");
        }

        return "redirect:../list";
    }
}
