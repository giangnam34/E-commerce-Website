package com.giangnam.vn.Ecommerce.Website.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giangnam.vn.Ecommerce.Website.Entity.Order;
import com.giangnam.vn.Ecommerce.Website.Entity.Shop_Order;
import com.giangnam.vn.Ecommerce.Website.Entity.CompositeKey.OrderProductKey;

@Repository
public interface Shop_OrderRepository  extends JpaRepository<Shop_Order, Integer >{

}
