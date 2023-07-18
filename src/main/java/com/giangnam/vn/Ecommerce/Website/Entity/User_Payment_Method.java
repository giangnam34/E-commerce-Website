package com.giangnam.vn.Ecommerce.Website.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User_Payment_Method {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="payment_type_id")
	private Payment_Type paymentType;
	
	@ManyToOne
	@JoinColumn(name="payment_method_id")
	private Shop_Order shopOrder;
	
	private String provider;
	
	private String accountNumber;
	
	private Date expiryDate;
	
	private Boolean isDefault;
}
