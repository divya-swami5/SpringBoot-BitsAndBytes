 package com.bitsnbytes.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitsnbytes.product.dto.CategoryDTO;
import com.bitsnbytes.product.entity.Category;
import com.bitsnbytes.product.exception.CategoryAlreadyExistsException;
import com.bitsnbytes.product.mapper.CategoryMapper;
import com.bitsnbytes.product.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repo;
	//create category
	public CategoryDTO createCategory(CategoryDTO categoryDTO)
	{
		Optional<Category> optionalCategory = repo.findByName(categoryDTO.getName());
		if(optionalCategory.isPresent())
		{
			throw new CategoryAlreadyExistsException("Category " + categoryDTO.getName()+" already exists");
		}
		
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
