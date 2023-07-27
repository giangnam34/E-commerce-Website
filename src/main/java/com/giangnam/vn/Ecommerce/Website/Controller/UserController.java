package com.giangnam.vn.Ecommerce.Website.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.giangnam.vn.Ecommerce.Website.DTO.UserDTO;
import com.giangnam.vn.Ecommerce.Website.Entity.User;
import com.giangnam.vn.Ecommerce.Website.Repository.UserRepository;
import com.giangnam.vn.Ecommerce.Website.Service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	@PostMapping(value = "/user", consumes = "application/json;charset=UTF-8")
	@CrossOrigin
	public String createNewUser(@RequestBody UserDTO user) {
		String result = userService.createNewUser(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getAddress());
		System.out.println(result);
		System.out.println(user.getPhoneNumber());
		return "hmm";
	}
	
	@GetMapping("/user")
	@CrossOrigin
	public List<User> listAllUser(){
		return userRepository.findAll();
	}
	
	
}
