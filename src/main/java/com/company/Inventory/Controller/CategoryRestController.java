package com.company.Inventory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.Inventory.Services.iCategoryService;
import com.company.Inventory.model.Category;
import com.company.Inventory.response.CategoryResponseRest;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")

public class CategoryRestController {
	@Autowired
	private  iCategoryService service;
	
	/**
	 * get all the categories
	 * @return
	 */
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> SearchCategories(){
		
		ResponseEntity<CategoryResponseRest> response = service.search();
		return response;
		
	}
	
	/**
	 * get categories by id
	 * @param id
	 * @return
	 */
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> SearchCategoriesById(@PathVariable Long id){
		
		ResponseEntity<CategoryResponseRest> response = service.searchById(id);
		return response;
		
	}
	
	/**
	 * Save categories
	 * @param category
	 * @return
	 */
	
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> Save(@RequestBody Category category){
		
		ResponseEntity<CategoryResponseRest> response = service.save(category);
		return response;
		
	}
	/**
	 *  Actualiza la categoria
	 * @param category
	 * @param id
	 * @return
	 */
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest>update(@RequestBody Category category, @PathVariable Long id){
		
		ResponseEntity<CategoryResponseRest> response = service.update(category, id);
		return response;
		
	}
	/**
	 * Delete categories
	 * @param id
	 * @return
	 */
	
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest>delete(@PathVariable Long id){
		
		ResponseEntity<CategoryResponseRest> response = service.deleteById(id);
		return response;
		
	}
	


}
