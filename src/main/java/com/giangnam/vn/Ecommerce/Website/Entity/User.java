package com.giangnam.vn.Ecommerce.Website.Entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(orphanRemoval = true, mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<User_Payment_Method> paymentList;
	
	@OneToMany(orphanRemoval = true, mappedBy="user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Shopping_Cart> cartList;
	
	@OneToMany(orphanRemoval = true, mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Shop_Order> orderList;
	
	@Email
	@Column(unique = true)
	@NotBlank
	private String email;
	
	@Size(min = 10, max = 11)
	private String phoneNumber;
	
	private String address;
	
	@Size(min = 8)
	private String password;
	
	private String role;
	
	
	public void addUserPaymentMethod(User_Payment_Method user_Payment_Method) {
		paymentList.add(user_Payment_Method);
		user_Payment_Method.setUser(this);
	}
	
	public void removeUserPaymentMethod(User_Payment_Method user_Payment_Method) {
		paymentList.remove(user_Payment_Method);
		user_Payment_Method.setUser(null);
	}
	
	public void addShoppingCart(Shopping_Cart shopping_Cart) {
		cartList.add(shopping_Cart);
		shopping_Cart.setUser(this);
	}
	
	public void removeShoppingCart(Shopping_Cart shopping_Cart) {
		cartList.remove(shopping_Cart);
		shopping_Cart.setUser(null);
	}
	
	public void addShopOrder(Shop_Order shop_Order) {
		orderList.add(shop_Order);
		shop_Order.setUser(this);
	}
	
	public void removeShopOrder(Shop_Order shop_Order) {
		orderList.remove(shop_Order);
		shop_Order.setUser(null);
	}
	
}
