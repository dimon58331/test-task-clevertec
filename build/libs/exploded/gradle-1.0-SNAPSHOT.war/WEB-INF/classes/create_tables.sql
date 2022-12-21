CREATE TABLE products(
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(15) NOT NULL,
    product_price FLOAT NOT NULL,
    product_discount FLOAT,
    product_discount_quantity INT
);

CREATE TABLE discount_card(
    discount_card_id SERIAL PRIMARY KEY,
    number_card INT NOT NULL UNIQUE,
    discount FLOAT,
    active BOOLEAN NOT NULL  
);

CREATE TABLE products_list(
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    quantity INT NOT NULL
);