package com.giangnam.vn.Ecommerce.Website.Entity;

import com.giangnam.vn.Ecommerce.Website.Entity.CompositeKey.OrderProductKey;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Order_Product")
public class Order {
	
	@Id
	private OrderProductKey id;
	
	@ManyToOne
    @MapsId("shopOderId")
    @JoinColumn(name = "orderid")
    private Shop_Order shop_Order;
	
	@ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product_Item productItem;
	
	@Min(value = 0)
	private int quantity;
	
	@Min(value = 0)
	private double price;
}
