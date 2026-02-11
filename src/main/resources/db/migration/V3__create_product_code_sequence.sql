CREATE SEQUENCE IF NOT EXISTS product_code_sequence
    START WITH 1000
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM pg_constraint 
        WHERE conname = 'uk_product_code'
    ) THEN
        ALTER TABLE products ADD CONSTRAINT uk_product_code UNIQUE (code);
    END IF;
END $$;

DO $$
DECLARE
    product_record RECORD;
BEGIN
    FOR product_record IN 
        SELECT id FROM products WHERE code IS NULL
    LOOP
        UPDATE products 
        SET code = nextval('product_code_sequence')
        WHERE id = product_record.id;
    END LOOP;
END $$;
