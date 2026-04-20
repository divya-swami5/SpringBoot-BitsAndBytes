package com.bitsnbytes.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitsnbytes.product.dto.CategoryDTO;
import com.bitsnbytes.product.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	private CategoryService service;
	public CategoryController(CategoryService service) {
		super();
		this.service = service;
	}
	//get all categories
	//create category
	@PostMapping
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO dto)
	{
		return  new ResponseEntity<>(service.createCategory(dto),HttpStatus.CREATED);
	}
	//get category by id
	//delete category
	
	

}
