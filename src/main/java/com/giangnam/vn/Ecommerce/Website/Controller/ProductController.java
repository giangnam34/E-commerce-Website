package com.giangnam.vn.Ecommerce.Website.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giangnam.vn.Ecommerce.Website.Entity.User;
import com.giangnam.vn.Ecommerce.Website.Entity.User_Payment_Method;
import com.giangnam.vn.Ecommerce.Website.Repository.User_Payment_MethodRepository;
import com.giangnam.vn.Ecommerce.Website.Service.UserService;

@RestController
public class ProductController {

	@Autowired
	private UserService userService;
	
}
