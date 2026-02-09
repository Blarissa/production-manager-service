-- Add timestamps to raw_materials
ALTER TABLE raw_materials ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE raw_materials ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Rename codigo to code in raw_materials
ALTER TABLE raw_materials DROP COLUMN codigo;
ALTER TABLE raw_materials ADD COLUMN code SERIAL;

-- Add timestamps to products
ALTER TABLE products ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE products ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Rename codigo to code in products
ALTER TABLE products DROP COLUMN codigo;
ALTER TABLE products ADD COLUMN code SERIAL;

-- Add timestamps to product_compositions
ALTER TABLE product_compositions ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE product_compositions ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
