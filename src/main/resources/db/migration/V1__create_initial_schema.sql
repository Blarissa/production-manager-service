CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE raw_materials (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    codigo SERIAL,
    name VARCHAR(255) NOT NULL,
    stock_quantity DECIMAL(10,2) NOT NULL
);

CREATE TABLE products (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    codigo SERIAL,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

CREATE TABLE product_compositions (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    product_id UUID REFERENCES products(id) ON DELETE CASCADE,
    material_id UUID REFERENCES raw_materials(id) ON DELETE CASCADE,
    quantity_required DECIMAL(10,2) NOT NULL
);