CREATE TABLE raw_materials (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    stock_quantity DECIMAL(10,2) NOT NULL
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

CREATE TABLE product_compositions (
    product_id INTEGER REFERENCES products(id),
    material_id INTEGER REFERENCES raw_materials(id),
    quantity_required DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (product_id, material_id)
);