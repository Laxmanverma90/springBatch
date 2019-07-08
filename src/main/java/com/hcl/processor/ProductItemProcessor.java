package com.hcl.processor;

import org.springframework.batch.item.ItemProcessor;

import com.hcl.dto.Product;

public class ProductItemProcessor implements ItemProcessor<Product, Product>{//<input, output>

	@Override
	public Product process(Product product) throws Exception {
		return product;
	}

}
