package com.giangnam.vn.Ecommerce.Website.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giangnam.vn.Ecommerce.Website.DTO.ProductDTO;
import com.giangnam.vn.Ecommerce.Website.Entity.Product;
import com.giangnam.vn.Ecommerce.Website.Entity.Product_Item;
import com.giangnam.vn.Ecommerce.Website.Repository.ProductRepository;
import com.giangnam.vn.Ecommerce.Website.Repository.Product_CategoryRepository;
import com.giangnam.vn.Ecommerce.Website.Repository.Product_ItemRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	Product_ItemRepository product_ItemRepository;

	@Autowired
	Product_CategoryRepository product_CategoryRepository;

	public ProductDTO toDTO(Product_Item product_Item) {
		return new ProductDTO(product_Item.getId(),product_Item.getProduct().getName(),
				product_Item.getProduct().getDescription(), product_Item.getProductImage(),
				product_Item.getProduct().getCategory().getCategoryName(), product_Item.getPrice(),
				product_Item.getQuantityStock());

	}

	public boolean addNewProduct(String name, String type, String description, String image, Integer stock,
			double price) {
		try {
			Product product = new Product(null, null, product_CategoryRepository.findByCategoryName(type), name,
					description, image);
			Product_Item product_Item = new Product_Item(null, null, null, product, null, stock, image, price);
			product.addProductItem(product_Item);
			productRepository.save(product);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String deleteProduct(int id) {
		try {
			product_ItemRepository.deleteById(id);
			return "Xoa San Pham Thanh Cong!!!";
		} catch (Exception e) {
			return e.toString();
		}
	}

	public boolean updateNameProduct(String name) {
		try {
			Product product = productRepository.findByName(name).get(0);
			product.setName(name);
			productRepository.save(product);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateDescriptionProduct(String name, String description) {
		try {
			Product product = productRepository.findByName(name).get(0);
			product.setDescription(description);
			productRepository.save(product);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateImageProduct(String name, String image) {
		try {
			Product product = productRepository.findByName(name).get(0);
			product.setImage(image);
			productRepository.save(product);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Product> findProduct(String name, String description, int page, int productPerPage) {
		List<Product> productList = productRepository
				.findByNameContainingIgnoreCaseAndDescriptionContainingIgnoreCaseOrderByNameAsc(name, description);
		int check = productList.size() % productPerPage == 0 ? productList.size() / productPerPage
				: productList.size() / productPerPage + 1;
		if (page > check)
			page = check;
		return productList.subList(productPerPage * (page - 1), productPerPage * page - 1);
	}

	public List<ProductDTO> findAllProduct() {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		for (Product_Item x : product_ItemRepository.findAll()) {
			result.add(toDTO(x));
		}
		return result;
	}
}
