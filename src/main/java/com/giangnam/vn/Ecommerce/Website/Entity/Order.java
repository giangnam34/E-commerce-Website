package com.giangnam.vn.Ecommerce.Website.Entity;

import com.giangnam.vn.Ecommerce.Website.Entity.CompositeKey.OrderProductKey;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {
	
	@Id
	private OrderProductKey id;
	
	@ManyToOne
    @MapsId("shopOderId")
    @JoinColumn(name = "order_id")
    private Shop_Order shopOrder;
	
	@ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product_Item productItem;
	
	private int quantity;
	
	private double price;
}