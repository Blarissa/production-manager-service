package com.production.manager.repository.products;

import java.util.List;

import com.production.manager.model.Product;

import jakarta.persistence.EntityManager;

public class ProductRepository implements IProductRepositoryQuery {

    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findAllByOrderByPriceDesc() {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(Product.class);
        var root = query.from(Product.class);

        query.orderBy(builder.desc(root.get("price")));

        return entityManager.createQuery(query).getResultList();
    }

}
