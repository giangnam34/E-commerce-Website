package com.giangnam.vn.Ecommerce.Website.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy="user")
	private List<User_Payment_Method> paymentList;
	
	@OneToMany(mappedBy="user")
	private List<Shopping_Cart> cartList;
	
	@OneToMany(mappedBy = "user")
	private List<Shop_Order> orderList;
	
	@Email
	private String email;
	
	@Size(min = 10, max = 11)
	private String phoneNumber;
	
	private String address;
	
	@Size(min = 8)
	private String password;
	
	private String role;
	
	
}
