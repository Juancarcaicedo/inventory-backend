package com.company.Inventory.response;

import java.util.List;
import com.company.Inventory.model.Category;

import lombok.Data;

@Data
public class CategoryResponse {
	
	private List<Category> category;

}
