DROP TABLE IF EXISTS items;

CREATE TABLE IF NOT EXISTS items(
    id serial primary key,
    description text,
    created timestamp,
    done boolean
);

SELECT * FROM items;