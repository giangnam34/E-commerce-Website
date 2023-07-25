package com.giangnam.vn.Ecommerce.Website.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shopping_Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable=false)
	@JsonBackReference
	private User user; 
	
	@OneToMany(orphanRemoval = true, mappedBy = "shoppingCart")
	@JsonManagedReference
	private List<Shopping_Cart_Item> shoppingCartItemList;
	
	public void addShoppingCartItem(Shopping_Cart_Item shopping_Cart_Item) {
		shoppingCartItemList.add(shopping_Cart_Item);
		shopping_Cart_Item.setShoppingCart(this);
	}
	
	public void removeShoppingCartItem(Shopping_Cart_Item shopping_Cart_Item) {
		shoppingCartItemList.remove(shopping_Cart_Item);
		shopping_Cart_Item.setShoppingCart(null);
	}
}
