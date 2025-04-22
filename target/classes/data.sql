-- Clear existing data
DELETE FROM reminders;
DELETE FROM possessions;

-- Reset auto-increment counters
ALTER TABLE reminders AUTO_INCREMENT = 1;
ALTER TABLE possessions AUTO_INCREMENT = 1;

-- Insert sample possessions
INSERT INTO possessions (name, serial_number, category, location, value, purchase_date, description, created_at, updated_at) VALUES
('MacBook Pro', 'MBP2023-001', 'Electronics', 'Home Office', 1999.99, '2023-01-15', '13-inch M2 Pro', NOW(), NOW()),
('iPhone 14 Pro', 'IPH2023-001', 'Electronics', 'Personal', 999.99, '2023-02-20', '256GB Space Gray', NOW(), NOW()),
('Samsung QLED TV', 'TV2023-001', 'Electronics', 'Living Room', 1299.99, '2023-03-10', '55-inch 4K', NOW(), NOW()),
('Herman Miller Chair', 'HM2023-001', 'Furniture', 'Home Office', 1499.99, '2023-04-05', 'Aeron Chair', NOW(), NOW()),
('Breville Coffee Maker', 'CM2023-001', 'Appliances', 'Kitchen', 299.99, '2023-05-12', 'Barista Express', NOW(), NOW()),
('Sony WH-1000XM4', 'HP2023-001', 'Electronics', 'Personal', 349.99, '2023-06-18', 'Wireless Headphones', NOW(), NOW()),
('iPad Pro', 'IPAD2023-001', 'Electronics', 'Personal', 799.99, '2023-07-22', '12.9-inch M2', NOW(), NOW()),
('Dyson V15', 'VC2023-001', 'Appliances', 'Home', 749.99, '2023-08-30', 'Cordless Vacuum', NOW(), NOW());

-- Insert duplicate possessions (same serial numbers, different locations)
INSERT INTO possessions (name, serial_number, category, location, value, purchase_date, description, created_at, updated_at) VALUES
('MacBook Pro', 'MBP2023-001', 'Electronics', 'Work Office', 1999.99, '2023-01-15', '13-inch M2 Pro (Work)', NOW(), NOW()),
('iPhone 14 Pro', 'IPH2023-001', 'Electronics', 'Backup', 999.99, '2023-02-20', '256GB Space Gray (Backup)', NOW(), NOW()),
('Samsung QLED TV', 'TV2023-001', 'Electronics', 'Bedroom', 1299.99, '2023-03-10', '55-inch 4K (Bedroom)', NOW(), NOW()),
('Herman Miller Chair', 'HM2023-001', 'Furniture', 'Work Office', 1499.99, '2023-04-05', 'Aeron Chair (Work)', NOW(), NOW());

-- Insert sample reminders
INSERT INTO reminders (title, description, reminder_date, completed, possession_id, created_at) VALUES
('MacBook Maintenance', 'Schedule annual maintenance for MacBook Pro', DATE_ADD(NOW(), INTERVAL 30 DAY), false, 1, NOW()),
('iPhone Backup', 'Backup iPhone data to iCloud', DATE_ADD(NOW(), INTERVAL 7 DAY), false, 2, NOW()),
('TV Firmware Update', 'Check for TV firmware updates', DATE_ADD(NOW(), INTERVAL 14 DAY), false, 3, NOW()),
('Chair Adjustment', 'Adjust chair height and settings', DATE_ADD(NOW(), INTERVAL 3 DAY), false, 4, NOW()),
('Coffee Maker Cleaning', 'Clean coffee maker and descale', DATE_ADD(NOW(), INTERVAL 60 DAY), false, 5, NOW()),
('Headphones Battery Check', 'Check battery health of headphones', DATE_ADD(NOW(), INTERVAL 45 DAY), false, 6, NOW()),
('iPad Update', 'Update iPad to latest iOS version', DATE_ADD(NOW(), INTERVAL 15 DAY), false, 7, NOW()),
('Vacuum Filter Cleaning', 'Clean vacuum filters and check for blockages', DATE_ADD(NOW(), INTERVAL 90 DAY), false, 8, NOW());

-- Insert duplicate iPhone 13s
INSERT INTO possessions (name, category, serial_number, location, value, purchase_date, description) 
VALUES ('iPhone 13', 'Electronics', 'IP13-2024-001', 'Home Office', 999.99, '2024-01-15', 'Black 128GB');

INSERT INTO possessions (name, category, serial_number, location, value, purchase_date, description) 
VALUES ('iPhone 13', 'Electronics', 'IP13-2024-001', 'Bedroom', 999.99, '2024-01-15', 'Black 128GB');

-- Insert duplicate MacBook Pros
INSERT INTO possessions (name, category, serial_number, location, value, purchase_date, description) 
VALUES ('MacBook Pro', 'Electronics', 'MBP-2024-002', 'Home Office', 1299.99, '2024-02-01', 'M2 Pro 14-inch');

INSERT INTO possessions (name, category, serial_number, location, value, purchase_date, description) 
VALUES ('MacBook Pro', 'Electronics', 'MBP-2024-002', 'Living Room', 1299.99, '2024-02-01', 'M2 Pro 14-inch');

-- Insert duplicate AirPods
INSERT INTO possessions (name, category, serial_number, location, value, purchase_date, description) 
VALUES ('AirPods Pro', 'Electronics', 'APP-2024-003', 'Car', 249.99, '2024-03-01', '2nd Generation');

INSERT INTO possessions (name, category, serial_number, location, value, purchase_date, description) 
VALUES ('AirPods Pro', 'Electronics', 'APP-2024-003', 'Gym Bag', 249.99, '2024-03-01', '2nd Generation'); 