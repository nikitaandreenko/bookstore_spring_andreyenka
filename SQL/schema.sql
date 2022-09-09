/*
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_items;
*/

CREATE TABLE IF NOT EXISTS books
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    book_name VARCHAR(75) NOT NULL,
    author VARCHAR(30),
    isbn VARCHAR(75) NOT NULL,
    price NUMERIC(6,2) NOT NULL,
    pages INTEGER NOT NULL,
    binding VARCHAR(30) NOT NULL,
    year_publishing INTEGER,
    book_language VARCHAR(30) NOT NULL,
    availability VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    user_age INTEGER NOT NULL,
    email VARCHAR(100) NOT NULL,
    user_role VARCHAR(100) NOT NULL,
    life_cycle VARCHAR(30) NOT NULL DEFAULT 'active'
);

CREATE TABLE IF NOT EXISTS orders
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users NOT NULL,
    total_cost NUMERIC(8,2) NOT NULL,
    status VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS order_items
(
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT REFERENCES orders NOT NULL,
    book_id BIGINT REFERENCES books NOT NULL,
    quantity INTEGER NOT NULL,
    price NUMERIC(6,2) NOT NULL
);


