package com.company.Inventory.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.Inventory.dao.ICategoryDao;
import com.company.Inventory.model.Category;
import com.company.Inventory.response.CategoryResponseRest;

@Service
public class CategoryServiceImpl implements iCategoryService {

	@Autowired
	private  ICategoryDao categoryDao;
	
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<CategoryResponseRest> search() {
		
		CategoryResponseRest response = new CategoryResponseRest();
		
		try {
			
			List<Category> category =  (List<Category>) categoryDao.findAll();
			
			response.getCategoryRespone().setCategory(category);
			
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		
			
			
		}catch (Exception e) {
			
			response.setMetadata("ERROR", "-1", "ERROR AL CONSULTAR");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}
