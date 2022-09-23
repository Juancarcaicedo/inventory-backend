package com.company.Inventory.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.Inventory.model.Category;

public interface ICategoryDao extends CrudRepository<Category, Long> {

}
