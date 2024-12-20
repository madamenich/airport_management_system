-- Insert Users
INSERT INTO users (username, password, enabled, roles) VALUES
    ('admin', '$2a$12$qnmomvI8koyKANgqxzvdH.Gb4VZSIQ2xAWxTGPuVGXh2BGY4zb692', true, '["ROLE_ADMIN", "ROLE_USER"]');




-- Assign Roles to Users
-- INSERT INTO user_roles (user_id, role) VALUES
--                                            (1, 'ADMIN'),
--                                            (1, 'USER'),
--                                            (2, 'USER');



-- Insert Default Airports
INSERT INTO airport (name, code, location, country) VALUES
                                                        ('Phnom Penh International Airport', 'PNH', 'Phnom Penh', 'Cambodia'),
                                                        ('Siem Reap International Airport', 'REP', 'Siem Reap', 'Cambodia');

-- Insert Default Flights
INSERT INTO flight (flight_number, departure_time, arrival_time, departure_airport_id, arrival_airport_id) VALUES
                                                                                                               ('PNH123', '2024-12-01 08:00:00', '2024-12-01 10:00:00', 1, 2),
                                                                                                               ('REP456', '2024-12-01 18:00:00', '2024-12-01 20:00:00', 2, 1);

-- Insert Default Passengers
INSERT INTO passenger (first_name, last_name, email, phone_number, passport_number) VALUES
                                                                                        ('John', 'Doe', 'john.doe@example.com', '+123456789', 'A12345678'),
                                                                                        ('Jane', 'Smith', 'jane.smith@example.com', '+987654321', 'B87654321');

-- Insert Default Tickets
INSERT INTO ticket (ticket_number, flight_id, passenger_id, seat_number, price, user_id) VALUES
                                                                                             ('TICK123', 1, 1, 'A1', 100.0, 1),
ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq');
CREATE SEQUENCE users_id_seq START 1;
ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq');
                                                                                   ('TICK456', 2, 2, 'B2', 120.0, 2);
