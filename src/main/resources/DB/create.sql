CREATE DATABASE org_news_portal;
\c org_news_portal;

CREATE TABLE IF NOT EXISTS users (
 id SERIAL PRIMARY KEY,
 name VARCHAR,
 role VARCHAR,
 position VARCHAR
);

CREATE TABLE IF NOT EXISTS news (
 id SERIAL PRIMARY KEY,
 title VARCHAR,
 content VARCHAR,
 depaertment_id INTEGER
);

CREATE TABLE IF NOT EXISTS department (
 id SERIAL PRIMARY KEY,
 name VARCHAR,
 description VARCHAR,
 number_of_employees INTEGER
);

CREATE TABLE IF NOT EXISTS departments_users (
 id SERIAL PRIMARY KEY,
 depaertment_id INTEGER,
 user_id INTEGER
);

CREATE DATABASE org_news_portal_test WITH TEMPLATE org_news_portal;