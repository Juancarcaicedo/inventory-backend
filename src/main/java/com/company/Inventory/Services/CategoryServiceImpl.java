package com.company.Inventory.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchById(Long Id) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			
			Optional<Category> category = categoryDao.findById(Id);
			
			if(category.isPresent()) {
				list.add(category.get());
				response.getCategoryRespone().setCategory(list);
				response.setMetadata("OK", "00", "Categoria  encontrada");
			}else {
				response.setMetadata("ERROR", "-1", "Categoria NO encontrada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			
			response.setMetadata("ERROR", "-1", "ERROR AL CONSULTAR POR ID");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> save(Category category) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			Category categorySaved = categoryDao.save(category);
			if(categorySaved!=null) {
				list.add(categorySaved);
				response.getCategoryRespone().setCategory(list);
				response.setMetadata("ok", "00", "Categoria   Guardada");
				
			}else {
				
				response.setMetadata("ERROR", "-1", "Categoria NO  Guardada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
			
		}catch (Exception e) {
			
			response.setMetadata("ERROR", "-1", "ERROR AL  GUARDAR CATEGORIA ");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			
				Optional<Category> categorySearch = categoryDao.findById(id);
				
				if(categorySearch.isPresent()) {
					// se procedera a actualizar el registro
					categorySearch.get().setName(category.getName());
					categorySearch.get().setDescription(category.getDescription());

					
					Category categoryToUpdate = categoryDao.save(categorySearch.get());
					
					if(categoryToUpdate !=null) {
						list.add(categoryToUpdate);
						response.getCategoryRespone().setCategory(list);
						response.setMetadata("ok", "00", "Categoria   Actualizada");
					
					}else {
						response.setMetadata("ERROR", "-1", "Categoria NO  Actualizada");
						return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
						
					}
				}else {
					response.setMetadata("ERROR", "-1", "Categoria NO  encontrada");
					return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
				}
			
			
		}catch (Exception e) {
			
			response.setMetadata("ERROR", "-1", "ERROR AL  Actualizar la CATEGORIA ");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	
	public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
		CategoryResponseRest response = new CategoryResponseRest();

		try {
			categoryDao.deleteById(id);
			response.setMetadata("Respuesta OK", "00", "Registro eliminado");
			
			
			
		}catch (Exception e) {
			
			response.setMetadata("ERROR", "-1", "ERROR AL  Eliminar");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

}
