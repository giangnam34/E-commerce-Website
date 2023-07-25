package com.giangnam.vn.Ecommerce.Website.Entity;

import java.util.Date;
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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shop_Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "shipping_method_id")
	@JsonBackReference
	private Shipping_Method shippingMethod;
	
	@OneToMany(orphanRemoval = true, mappedBy = "shop_Order")
	@JsonManagedReference
	private List<Order> orderList;
	
	private Date orderDate;
	
	@NotNull
	private String shipping_address;
	
	@Min(value = 0)
	private int orderTotal;
	
	private String orderStatus;
	
	public void addOrder(Order order) {
		orderList.add(order);
		order.setShop_Order(this);
	}
	
	public void removeOrder(Order order) {
		orderList.remove(order);
		order.setShop_Order(null);
	}
}
