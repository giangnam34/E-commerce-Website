package com.giangnam.vn.Ecommerce.Website.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

	@GetMapping("/hello")
	public String test() {
		return "Hello";
	}
}
