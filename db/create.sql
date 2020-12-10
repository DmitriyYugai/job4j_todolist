DROP TABLE IF EXISTS items_categories;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS users;


CREATE TABLE IF NOT EXISTS users(
    id serial primary key,
    name text,
    email text UNIQUE,
    password text
);

CREATE TABLE IF NOT EXISTS categories (
    id serial primary key,
    name text
);

CREATE TABLE IF NOT EXISTS items(
    id serial primary key,
    description text,
    created timestamp,
    done boolean,
    user_id integer references users(id)
);

INSERT INTO categories(name) VALUES ('Multithreading');
INSERT INTO categories(name) VALUES ('Servlets');
INSERT INTO categories(name) VALUES ('Hibernate');
INSERT INTO categories(name) VALUES ('Spring');

SELECT * FROM items;

SELECT * FROM users;

SELECT * FROM categories;