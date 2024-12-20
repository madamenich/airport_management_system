-- Drop existing tables if they exist (clean start)
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS ticket CASCADE;
DROP TABLE IF EXISTS passenger CASCADE;
DROP TABLE IF EXISTS flight CASCADE;
DROP TABLE IF EXISTS airport CASCADE;

-- Users Table
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       enabled BOOLEAN NOT NULL,
                       roles TEXT NOT NULL
);

-- -- User Roles Table
-- CREATE TABLE user_roles (
--                             user_id BIGINT NOT NULL,
--                             role VARCHAR(50) NOT NULL,
--                             FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
-- );


-- Airport Table
CREATE TABLE airport (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL UNIQUE,
                         code VARCHAR(10) NOT NULL UNIQUE,
                         location VARCHAR(255) NOT NULL,
                         country VARCHAR(100) NOT NULL
);

-- Flight Table
CREATE TABLE flight (
                        id SERIAL PRIMARY KEY,
                        flight_number VARCHAR(50) NOT NULL UNIQUE,
                        departure_time TIMESTAMP NOT NULL,
                        arrival_time TIMESTAMP NOT NULL,
                        departure_airport_id BIGINT NOT NULL,
                        arrival_airport_id BIGINT NOT NULL,
                        FOREIGN KEY (departure_airport_id) REFERENCES airport (id) ON DELETE CASCADE,
                        FOREIGN KEY (arrival_airport_id) REFERENCES airport (id) ON DELETE CASCADE
);

-- Passenger Table
CREATE TABLE passenger (
                           id SERIAL PRIMARY KEY,
                           first_name VARCHAR(100) NOT NULL,
                           last_name VARCHAR(100) NOT NULL,
                           email VARCHAR(255) NOT NULL UNIQUE,
                           phone_number VARCHAR(20) NOT NULL UNIQUE,
                           passport_number VARCHAR(50) NOT NULL UNIQUE
);

-- Ticket Table
CREATE TABLE ticket (
                        id SERIAL PRIMARY KEY,
                        ticket_number VARCHAR(50) NOT NULL UNIQUE,
                        flight_id BIGINT NOT NULL,
                        passenger_id BIGINT NOT NULL,
                        seat_number VARCHAR(10) NOT NULL,
                        price NUMERIC(10, 2) NOT NULL,
                        user_id BIGINT NOT NULL,
                        FOREIGN KEY (flight_id) REFERENCES flight (id) ON DELETE CASCADE,
                        FOREIGN KEY (passenger_id) REFERENCES passenger (id) ON DELETE CASCADE,
                        FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
