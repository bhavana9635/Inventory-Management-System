-- Drop existing tables if they exist
DROP TABLE IF EXISTS reminders;
DROP TABLE IF EXISTS possessions;

-- Create possessions table
CREATE TABLE possessions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    serial_number VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL,
    value DECIMAL(10,2) NOT NULL,
    purchase_date DATE NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- Create reminders table
CREATE TABLE reminders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    reminder_date TIMESTAMP NOT NULL,
    completed BOOLEAN DEFAULT FALSE,
    possession_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (possession_id) REFERENCES possessions(id) ON DELETE CASCADE
);

-- Create indexes
CREATE INDEX idx_possession_serial ON possessions(serial_number);
CREATE INDEX idx_reminder_date ON reminders(reminder_date);
CREATE INDEX idx_reminder_completed ON reminders(completed);

-- Insert sample possessions
INSERT INTO possessions (name, serial_number, category, location, value, purchase_date, description, created_at, updated_at) VALUES
('MacBook Pro', 'MBP2023-001', 'Electronics', 'Home Office', 1999.99, '2023-01-15', '13-inch M2 Pro', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('iPhone 14 Pro', 'IPH2023-001', 'Electronics', 'Personal', 999.99, '2023-02-20', '256GB Space Gray', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Samsung QLED TV', 'TV2023-001', 'Electronics', 'Living Room', 1299.99, '2023-03-10', '55-inch 4K', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Herman Miller Chair', 'HM2023-001', 'Furniture', 'Home Office', 1499.99, '2023-04-05', 'Aeron Chair', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Breville Coffee Maker', 'CM2023-001', 'Appliances', 'Kitchen', 299.99, '2023-05-12', 'Barista Express', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sony WH-1000XM4', 'HP2023-001', 'Electronics', 'Personal', 349.99, '2023-06-18', 'Wireless Headphones', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('iPad Pro', 'IPAD2023-001', 'Electronics', 'Personal', 799.99, '2023-07-22', '12.9-inch M2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Dyson V15', 'VC2023-001', 'Appliances', 'Home', 749.99, '2023-08-30', 'Cordless Vacuum', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert duplicate possessions
INSERT INTO possessions (name, serial_number, category, location, value, purchase_date, description, created_at, updated_at) VALUES
('MacBook Pro', 'MBP2023-001', 'Electronics', 'Work Office', 1999.99, '2023-01-15', '13-inch M2 Pro (Work)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('iPhone 14 Pro', 'IPH2023-001', 'Electronics', 'Backup', 999.99, '2023-02-20', '256GB Space Gray (Backup)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Samsung QLED TV', 'TV2023-001', 'Electronics', 'Bedroom', 1299.99, '2023-03-10', '55-inch 4K (Bedroom)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Herman Miller Chair', 'HM2023-001', 'Furniture', 'Work Office', 1499.99, '2023-04-05', 'Aeron Chair (Work)', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert sample reminders
INSERT INTO reminders (title, description, reminder_date, completed, possession_id, created_at) VALUES
('MacBook Maintenance', 'Schedule annual maintenance for MacBook Pro', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 30 DAY), false, 1, CURRENT_TIMESTAMP),
('iPhone Backup', 'Backup iPhone data to iCloud', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 7 DAY), false, 2, CURRENT_TIMESTAMP),
('TV Firmware Update', 'Check for TV firmware updates', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 14 DAY), false, 3, CURRENT_TIMESTAMP),
('Chair Adjustment', 'Adjust chair height and settings', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 3 DAY), false, 4, CURRENT_TIMESTAMP),
('Coffee Maker Cleaning', 'Clean coffee maker and descale', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 60 DAY), false, 5, CURRENT_TIMESTAMP),
('Headphones Battery Check', 'Check battery health of headphones', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 45 DAY), false, 6, CURRENT_TIMESTAMP),
('iPad Update', 'Update iPad to latest iOS version', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 15 DAY), false, 7, CURRENT_TIMESTAMP),
('Vacuum Filter Cleaning', 'Clean vacuum filters and check for blockages', DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 90 DAY), false, 8, CURRENT_TIMESTAMP); 