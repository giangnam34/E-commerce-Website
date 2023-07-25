package com.giangnam.vn.Ecommerce.Website.Entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product_Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(orphanRemoval = true, mappedBy = "productItem")
	@JsonManagedReference
	private List<Shopping_Cart_Item> shoppingCartItemList;
	
	@OneToMany(orphanRemoval = true, mappedBy = "productItem")
	@JsonManagedReference
	private List<Order> orderList;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@JsonBackReference
	private Product product;
	
	@ManyToMany
	private Set<Variation_Option> variationOption;
	
	@Min(value = 0)
	private int quantityStock;
	
	private String productImage;
	
	@Min(value = 0)
	private double price;
	
	public void addShoppingCartItem(Shopping_Cart_Item shopping_Cart_Item) {
		shoppingCartItemList.add(shopping_Cart_Item);
		shopping_Cart_Item.setProductItem(this);
	}
	
	public void removeShoppingCartItem(Shopping_Cart_Item shopping_Cart_Item) {
		shoppingCartItemList.remove(shopping_Cart_Item);
		shopping_Cart_Item.setProductItem(null);
	}
	
	public void addOrder(Order order) {
		orderList.add(order);
		order.setProductItem(this);
	}
	
	public void removeOrder(Order order) {
		orderList.remove(order);
		order.setProductItem(null);
	}
}
