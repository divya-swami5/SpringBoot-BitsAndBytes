package com.bitsnbytes.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitsnbytes.product.dto.CategoryDTO;
import com.bitsnbytes.product.entity.Category;
import com.bitsnbytes.product.mapper.CategoryMapper;
import com.bitsnbytes.product.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repo;
	//create category
	public CategoryDTO createCategory(CategoryDTO categoryDTO)
	{
		Category category = CategoryMapper.toCategoryEntity(categoryDTO);
		category = repo.save(category);
		return CategoryMapper.toCategoryDTO(category);
	}
	
	//get all category
	public List<CategoryDTO> getAllCategories() {
		
		return repo.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();
	}

	//get category by id
	public CategoryDTO getCategoryById(Long id) {
		 Category category = repo.findById(id).orElseThrow(()->new RuntimeException("Category not found"));
		 return CategoryMapper.toCategoryDTO(category);
	}

	public String deleteCategory(Long id) {
		repo.deleteById(id);
		return "Category deleted successfully";
	}
	
	
	
	//delete category
}
