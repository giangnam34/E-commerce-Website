package com.giangnam.vn.Ecommerce.Website.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private int id;
	private String name;
	private String description;
	private String image;
	private String categoryName;
	private double price;
	private int quantityStock;
	
}
