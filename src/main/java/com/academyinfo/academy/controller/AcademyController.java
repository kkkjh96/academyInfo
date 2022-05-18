package com.academyinfo.academy.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.academyinfo.academy.dto.AcademyRequestDto;

public interface AcademyController {
	@GetMapping("/add")
	public String add(Model model);
	public String add(AcademyRequestDto academyRequestDto);
	public String detail(@PathVariable("no") int no, Model model);
	public String edit(@PathVariable("no") int no, Model model);
	public String update(AcademyRequestDto academyDTO);
	public String delete(@PathVariable("no") int no);
}
