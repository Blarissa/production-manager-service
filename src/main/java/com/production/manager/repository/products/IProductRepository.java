package com.production.manager.repository.products;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.production.manager.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID>, IProductRepositoryQuery {
}
