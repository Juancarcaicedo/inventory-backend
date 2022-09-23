package com.company.Inventory.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="category")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4774126192193448186L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private long id;
	private String name;
	private String description;
	

}

