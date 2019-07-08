package com.hcl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hcl.dto.Product;

public class ProductRowMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setCategory(rs.getString("category"));
		product.setName(rs.getString("name"));
		product.setBrand(rs.getString("brand"));
		product.setModel(rs.getString("model"));
		product.setPrice(rs.getDouble("price"));
		return product;
	}
}
