package com.softech.netika.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softech.netika.model.User;
import com.softech.netika.service.UserService;
import com.softech.netika.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserController {
@Autowired
private UserService userService;
@ModelAttribute("user")
public UserRegistrationDto userRegistrationDto() {
	return new UserRegistrationDto();
}
@GetMapping
public String showRegistrationForm(Model model) {
	return "registration";
}
@PostMapping
public String registerUserAccount(@ModelAttribute("user")@Valid UserRegistrationDto userDto,BindingResult result) {
	User existing=userService.findByEmail(userDto.getEmail());
	if(existing!=null) {
		result.rejectValue("email",null, "There is already a account registered with that email");
		
	}
	if(result.hasErrors()) {
		return "registration";
	}
	userService.Save(userDto);
	return "redirect:/registration/success";
}
}
