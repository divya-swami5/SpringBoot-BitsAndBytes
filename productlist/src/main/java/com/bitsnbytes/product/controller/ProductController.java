package com.bitsnbytes.product.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bitsnbytes.product.dto.ProductDTO;
import com.bitsnbytes.product.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	//get all products
	@GetMapping
	public List<ProductDTO> getAllProducts()
	{
		return service.getAllProducts();
	}
	
	//create product
	@PostMapping
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto)
	{
		ProductDTO createdProduct =  service.createProduct(dto);
		return new ResponseEntity<>(createdProduct , HttpStatus.CREATED);
	}
	
	//update product
	@PutMapping("/{id}")
	public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO)
	{
		return service.updateProduct(id,productDTO);
	}
	
	
	//delete product
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable Long id)
	{
		return service.deleteProduct(id);
	}
	
	//get product by id
	@GetMapping("/{id}")
	public ProductDTO getProductById(@PathVariable Long id)
	{
		return service.getProductById(id);
	}
	

}
