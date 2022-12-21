UPDATE products 
SET product_name = 'potato', product_price = 5.5, product_discount = 10, product_discount_quantity = 10
WHERE product_id = 1;

UPDATE products 
SET product_name = 'chocolate', product_price = 3.1, product_discount = 5, product_discount_quantity = 5
WHERE product_id = 2;

UPDATE products 
SET product_name = 'milk', product_price = 1.8, product_discount = 0, product_discount_quantity = 0
WHERE product_id = 3;

UPDATE products 
SET product_name = 'cake', product_price = 12, product_discount = 5, product_discount_quantity = 2
WHERE product_id = 4;

UPDATE products 
SET product_name = 'cheaps', product_price = 3.9, product_discount = 5, product_discount_quantity = 2
WHERE product_id = 5;


UPDATE products_list
SET product_id = 1, quantity = 6
WHERE product_id = 1;

UPDATE products_list
SET product_id = 2, quantity = 3
WHERE product_id = 2;

UPDATE products_list
SET product_id = 1, quantity = 1
WHERE product_id = 1;

UPDATE products_list
SET product_id = 3, quantity = 2
WHERE product_id = 3;

UPDATE products_list
SET product_id = 2, quantity = 3
WHERE product_id = 2;

UPDATE discount_card
SET number_card = 1234, discount = 6, active = true
WHERE discount_card_id = 1;