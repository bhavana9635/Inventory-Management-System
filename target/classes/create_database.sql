-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS possession_tracker
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- Use the database
USE possession_tracker;

-- Grant privileges to the user
GRANT ALL PRIVILEGES ON possession_tracker.* TO 'root'@'localhost';
FLUSH PRIVILEGES;

-- Create possessions table
CREATE TABLE IF NOT EXISTS possessions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100),
    location VARCHAR(255),
    value DECIMAL(10, 2),
    purchase_date DATE,
    serial_number VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create reminders table
CREATE TABLE IF NOT EXISTS reminders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    reminder_date DATETIME NOT NULL,
    completed BOOLEAN DEFAULT FALSE,
    possession_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (possession_id) REFERENCES possessions(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create indexes
CREATE INDEX idx_possession_serial ON possessions(serial_number);
CREATE INDEX idx_reminder_date ON reminders(reminder_date);
CREATE INDEX idx_reminder_completed ON reminders(completed);
CREATE INDEX idx_possession_id ON reminders(possession_id); 