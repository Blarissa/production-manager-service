package com.production.manager.repository.products;

import java.util.List;

import com.production.manager.model.Product;

public interface IProductRepositoryQuery {
    List<Product> findAllByOrderByPriceDesc();
}
