package com.company.Inventory.Services;

import org.springframework.http.ResponseEntity;

import com.company.Inventory.response.CategoryResponseRest;

public interface iCategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	public ResponseEntity<CategoryResponseRest> searchById(Long Id);

}
