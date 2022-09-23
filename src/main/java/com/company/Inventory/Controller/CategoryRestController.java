package com.company.Inventory.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.Inventory.Services.iCategoryService;
import com.company.Inventory.response.CategoryResponseRest;

@RestController
@RequestMapping("/api/v1")

public class CategoryRestController {
	@Autowired
	private  iCategoryService service;
	
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> SearchCategories(){
		
		ResponseEntity<CategoryResponseRest> response = service.search();
		return response;
		
	}

}
