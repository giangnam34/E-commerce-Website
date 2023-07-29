package com.giangnam.vn.Ecommerce.Website.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.giangnam.vn.Ecommerce.Website.DTO.ProductDTO;
import com.giangnam.vn.Ecommerce.Website.Service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product")
	@CrossOrigin
	List<ProductDTO> getAllProduct(){
		return productService.findAllProduct();
	}
	
	@DeleteMapping("/product/{id}")
	@CrossOrigin
	public String deleteProduct(@PathVariable int id) {
		String result = productService.deleteProduct(id);
		System.out.println(result);
		return result;
	}
	
}
