--Widok Dostępnych Produktów - Pokazuje wszystkie produkty obecnie dostępne w lodówce.
CREATE VIEW available_products AS
SELECT p.name, p.category, f.status, f.added_date
FROM product p
         JOIN fridge f ON p.id = f.product_id
WHERE f.status = 'available';

--Widok Przepisów i Ich Składników - Umożliwia szybkie przeglądanie, jakie produkty są potrzebne do każdego przepisu.
CREATE VIEW recipe_ingredients AS
SELECT r.name AS recipe_name, p.name AS product_name, rp.quantity, rp.unit
FROM recipe r
         JOIN recipe_product rp ON r.id = rp.recipe_id
         JOIN product p ON rp.product_id = p.id;


--Trigger Aktualizujący Status Produktu - Automatycznie aktualizuje status produktu w lodówce na "used" po użyciu go w przepisie.
CREATE OR REPLACE FUNCTION update_product_status()
RETURNS TRIGGER AS $$
BEGIN
UPDATE fridge
SET status = 'used'
WHERE product_id = NEW.product_id;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION get_all_recipes_for_fridge()
RETURNS TABLE(recipe_id INT, recipe_name TEXT) AS $$
BEGIN
RETURN QUERY
SELECT
    r.id AS recipe_id,
    r.name AS recipe_name
FROM
    recipe r
WHERE NOT EXISTS (
    SELECT 1
    FROM recipe_product rp
             LEFT JOIN (
        SELECT product_id, SUM(quantity) AS total_quantity
        FROM fridge
        WHERE status = 'available'
        GROUP BY product_id
    ) f ON rp.product_id = f.product_id
    WHERE
            rp.recipe_id = r.id
      AND (f.total_quantity IS NULL OR f.total_quantity < rp.quantity)
);
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_update_status
    AFTER INSERT ON recipe_product
    FOR EACH ROW
    EXECUTE FUNCTION update_product_status();

--Procedura Dodawania Produktu do Lodówki - Umożliwia dodanie produktu do lodówki z jednoczesnym sprawdzeniem i aktualizacją stanu.
CREATE OR REPLACE PROCEDURE add_product_to_fridge(product_name TEXT, product_category TEXT, quantity NUMERIC)
LANGUAGE plpgsql AS $$
BEGIN
INSERT INTO product (name, category)
VALUES (product_name, product_category);

INSERT INTO fridge (product_id, quantity, status)
VALUES (currval('product_id_seq'), quantity, 'available');
END;
$$;