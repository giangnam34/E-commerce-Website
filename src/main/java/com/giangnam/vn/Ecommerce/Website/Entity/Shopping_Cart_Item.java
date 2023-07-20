package com.giangnam.vn.Ecommerce.Website.Entity;

import com.giangnam.vn.Ecommerce.Website.Entity.CompositeKey.ShoppingProductKey;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Entity
public class Shopping_Cart_Item {

	@EmbeddedId
	private ShoppingProductKey id;
	
	@ManyToOne
    @MapsId("shoppingId")
    @JoinColumn(name = "shopping_id")
    Shopping_Cart shoppingCart;
	
	@ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product_Item productItem;
	
	@Min(value = 0)
	private int quantity;
}
