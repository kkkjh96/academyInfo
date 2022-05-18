package com.academyinfo.board.freeboard.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.academyinfo.board.freeboard.dto.BoardRequestDto;

public interface BoardController {
	public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum);
	// public String write();
	public String write(Model model);
	// public String write(@RequestParam(value="id") String id, Model model);
    public String detail(@PathVariable("no") int no, Model model);
    public String edit(@PathVariable("no") int no, Model model);
    public String update(BoardRequestDto boardDTO);
    public String delete(@PathVariable("no") int no);
    // public String search(@RequestParam(value="keyword") String keyword, @RequestParam(value="page", defaultValue = "1") Integer pageNum, Model model);
    // public String search(@RequestParam(value="keyword") String keyword, @RequestParam(value="page", defaultValue = "1") Integer pageNum, @RequestParam(value="filter") String filter, Model model);
    public String search(@RequestParam(value="keyword", defaultValue="") String keyword, @RequestParam(value="page", defaultValue = "1") Integer pageNum, @RequestParam(value="filter", defaultValue="title") String filter, Model model);
    public String updateReport(@PathVariable("no") int no, Model model);
}
