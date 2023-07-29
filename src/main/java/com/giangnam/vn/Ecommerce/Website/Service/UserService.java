package com.giangnam.vn.Ecommerce.Website.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.giangnam.vn.Ecommerce.Website.Entity.Payment_Type;
import com.giangnam.vn.Ecommerce.Website.Entity.Product_Item;
import com.giangnam.vn.Ecommerce.Website.Entity.Shopping_Cart;
import com.giangnam.vn.Ecommerce.Website.Entity.Shopping_Cart_Item;
import com.giangnam.vn.Ecommerce.Website.Entity.User;
import com.giangnam.vn.Ecommerce.Website.Entity.User_Payment_Method;
import com.giangnam.vn.Ecommerce.Website.Entity.CompositeKey.ShoppingProductKey;
import com.giangnam.vn.Ecommerce.Website.Repository.Payment_TypeRepository;
import com.giangnam.vn.Ecommerce.Website.Repository.Product_ItemRepository;
import com.giangnam.vn.Ecommerce.Website.Repository.Shopping_CartRepository;
import com.giangnam.vn.Ecommerce.Website.Repository.Shopping_Cart_ItemRepository;
import com.giangnam.vn.Ecommerce.Website.Repository.UserRepository;
import com.giangnam.vn.Ecommerce.Website.Repository.User_Payment_MethodRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private User_Payment_MethodRepository user_Payment_MethodRepository;
	
	@Autowired
	private Payment_TypeRepository payment_TypeRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private Validation validation;
	
	@Autowired
	private Shopping_CartRepository shopping_CartRepository;
	
	@Autowired
	private Product_ItemRepository product_ItemRepository;
	
	@Autowired
	private Shopping_Cart_ItemRepository shopping_Cart_ItemRepository;
	
	public List<User> findAllUser(){
		return userRepository.findAll();
	}
	
	public User findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public List<User> findByRole(String role){
		return userRepository.findByRole(role);
	}
	
	public List<User> findByAddress(String address){
		return userRepository.findByAddress(address);
	}
	
	public String createNewUser(String email, String password, String firstName, String lastName, String phoneNumber, String address) {
		try {
			if (!validation.isValidPassWord(password)) return "Password is invalid";
			System.out.println(bCryptPasswordEncoder.encode(password));
			User user = new User(email, phoneNumber, address, firstName, lastName, bCryptPasswordEncoder.encode(password), "CUSTOMER");
			userRepository.save(user);
			if (!creatNewCartUser(email)) return "Fail";
			return "Success";
		} catch (Exception ex) {
			return ex.toString();		}
	}
	
	public String deleteUser(String email) {
		try {
			userRepository.delete(userRepository.findByEmail(email));
			return "Xoa Nguoi Dung Thanh Cong!!!";
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	public boolean updateUserPaymentMethod(String email, String value, String provider, String accountnumber,
			Date expiry_date, Boolean is_default) {
		try {
			User_Payment_Method result = findByEmail(email).getPaymentList().stream()
					.filter(paymentMethod -> value.equals(paymentMethod.getPaymentType().getName())).findFirst()
					.orElseGet(() -> {
						User_Payment_Method newPaymentMethod = new User_Payment_Method();
						Payment_Type paymentType = payment_TypeRepository.findAll().stream()
								.filter(paymentType1 -> value.equals(paymentType1.getName())).findFirst().orElse(null);
						paymentType.addUserPaymentMethod(newPaymentMethod);
						return newPaymentMethod;
					});
			result.setAccountNumber(accountnumber);
			result.setExpiryDate(expiry_date);
			result.setIsDefault(is_default);
			user_Payment_MethodRepository.save(result);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public boolean changePasswordUser(String email, String password) {
		try {
			User user = findByEmail(email);
			if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())
					&& validation.isValidPassWord(password)) {
				user.setPassword(bCryptPasswordEncoder.encode(password));
				userRepository.save(user);
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public boolean changePhoneNumberUser(String email, String phoneNumber) {
		try {
			User user = findByEmail(email);
			if (user != null) {
				user.setPhoneNumber(phoneNumber);
				userRepository.save(user);
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public boolean changeAddressUser(String email, String address) {
		try {
			User user = findByEmail(email);
			if (user != null) {
				user.setAddress(address);
				userRepository.save(user);
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public boolean creatNewCartUser(String email) {
		try {
			Shopping_Cart shopping_Cart = new Shopping_Cart();
			shopping_Cart.setUser(findByEmail(email));
			shopping_CartRepository.save(shopping_Cart);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public boolean addnewProductToCartUser(String email, Integer productItemId, int quantity) {
		try {
			User user = findByEmail(email);
			Shopping_Cart shopping_Cart = user.getCartList().get(user.getCartList().size()-1);
			Shopping_Cart_Item shopping_Cart_Item = new Shopping_Cart_Item();
			shopping_Cart_Item.setProductItem(product_ItemRepository.findById(productItemId).get());
			shopping_Cart_Item.setId(new ShoppingProductKey(productItemId,shopping_Cart.getId()));
			shopping_Cart_Item.setShoppingCart(shopping_Cart);
			shopping_Cart.addShoppingCartItem(shopping_Cart_Item);
			shopping_CartRepository.save(shopping_Cart);
			return true;
		} catch(Exception ex) {
			return false;
		}
	}
	
	public boolean updateProductToCartUser(String email, Integer productItemId, int quantity) {
		try {
			User user = findByEmail(email);
			Shopping_Cart shopping_Cart = user.getCartList().get(user.getCartList().size()-1);
			Shopping_Cart_Item shopping_Cart_Item = shopping_Cart_ItemRepository.findById(new ShoppingProductKey(productItemId,shopping_Cart.getId())).get();
			shopping_Cart_Item.setQuantity(quantity);
			shopping_Cart_ItemRepository.save(shopping_Cart_Item);
			return true;
		} catch(Exception ex) {
			return false;
		}
	}
	
	public boolean deleteProductToCartUser(String email, Integer productItemId, int quantity) {
		try {
			User user = findByEmail(email);
			Shopping_Cart shopping_Cart = user.getCartList().get(user.getCartList().size()-1);
			Shopping_Cart_Item shopping_Cart_Item = shopping_Cart_ItemRepository.findById(new ShoppingProductKey(productItemId,shopping_Cart.getId())).get();
			shopping_Cart_ItemRepository.delete(shopping_Cart_Item);
			return true;
		} catch(Exception ex) {
			return false;
		}
	}
	
}
