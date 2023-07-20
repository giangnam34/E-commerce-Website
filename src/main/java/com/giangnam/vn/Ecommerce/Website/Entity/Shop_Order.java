package com.giangnam.vn.Ecommerce.Website.Entity;

import java.util.Date;
import java.util.List;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shop_Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "shipping_method_id")
	private Shipping_Method shippingMethod;
	
	@OneToMany(mappedBy = "shopOrder")
	private List<Order> orderList;
	
	@OneToMany(mappedBy = "shopOrder")
	private List<User_Payment_Method> paymentList;
	
	private Date orderDate;
	
	@NotNull
	private String shipping_address;
	
	@Min(value = 0)
	private int orderTotal;
	
	private String orderStatus;
}
