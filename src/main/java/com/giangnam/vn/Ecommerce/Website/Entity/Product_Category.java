package com.giangnam.vn.Ecommerce.Website.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product_Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(orphanRemoval = true, mappedBy = "category")
	@JsonManagedReference
	private List<Product> productList;
	
	@OneToMany(orphanRemoval = true, mappedBy = "category")
	@JsonManagedReference
	private List<Variation> variationList;
	
	@NotNull
	private String categoryName;
	
	public void addProduct(Product product) {
		productList.add(product);
		product.setCategory(this);
	}
	
	public void removeProduct(Product product) {
		productList.remove(product);
		product.setCategory(null);
	}
	
	public void addVariation(Variation variation) {
		variationList.add(variation);
		variation.setCategory(this);
	}
	
	public void removeVariation(Variation variation) {
		variationList.remove(variation);
		variation.setCategory(null);
	}
}
