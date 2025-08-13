-- This migration only runs in development profile
-- Seeds initial users for development/testing purposes
-- Note: Both users use password 'password' for simplicity
INSERT INTO users (username, phone_number, password, user_type, enabled, created_date, updated_date) VALUES
('admin', '1234567890', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'ADMIN', true, NOW(), NOW()),
('user', '0987654321', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'USER', true, NOW(), NOW())
ON DUPLICATE KEY UPDATE
    phone_number = VALUES(phone_number),
    password = VALUES(password),
    user_type = VALUES(user_type),
    enabled = VALUES(enabled),
    updated_date = NOW(); 