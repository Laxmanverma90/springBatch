package com.hcl.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String category;
	private String name;
	private String brand;
	private String model;
	private double price;

}
