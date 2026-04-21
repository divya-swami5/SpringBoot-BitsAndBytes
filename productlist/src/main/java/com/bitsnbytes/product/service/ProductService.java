package com.bitsnbytes.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitsnbytes.product.dto.ProductDTO;
import com.bitsnbytes.product.entity.Category;
import com.bitsnbytes.product.entity.Product;
import com.bitsnbytes.product.mapper.ProductMapper;
import com.bitsnbytes.product.repository.CategoryRepository;
import com.bitsnbytes.product.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	//create product
	public ProductDTO createProduct(ProductDTO productDTO) {
		
		Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(()->new RuntimeException("Category not found"));
		
		Product product = ProductMapper.toProductEntity(productDTO, category);
		
		product= productRepository.save(product);
		
		return ProductMapper.toProductDTO(product);
	}

	//get all products
	public List<ProductDTO> getAllProducts() {
		List<ProductDTO> productDTOList = productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
		return productDTOList;
	}

	//get product by Id
	public ProductDTO getProductById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
		
		return ProductMapper.toProductDTO(product);
	}

	//update product
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		Product product = productRepository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
		Category category =  categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(()->new RuntimeException("Category not found"));
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setCategory(category);
		productRepository.save(product);
		
		return ProductMapper.toProductDTO(product);
	}

	//delete product
	public String deleteProduct(Long id) {
		productRepository.deleteById(id);
		return "Product deleted successfully";
	}
	
	

}
