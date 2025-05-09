﻿# Inventory-Management-System

A Spring Boot application for managing possessions and their reminders.

## Installation Requirements

1. **Java Development Kit (JDK) 11**
   - Download from: https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html
   - Install JDK 11
   - Set JAVA_HOME environment variable:
     - Windows: Control Panel > System > Advanced System Settings > Environment Variables
     - Add new system variable: JAVA_HOME = C:\Program Files\Java\jdk-11
   - Add Java to PATH:
     - Add %JAVA_HOME%\bin to your system PATH

2. **MySQL Server 8.0**
   - Download from: https://dev.mysql.com/downloads/mysql/
   - Run the installer
   - Choose "Developer Default" installation type
   - Set root password during installation (remember this password)
   - Complete the installation
   - Verify MySQL service is running:
     - Windows: Open Services (services.msc) and check if "MySQL80" is running
     - Or run: `net start MySQL80` in Command Prompt

3. **Maven**
   - Download from: https://maven.apache.org/download.cgi
   - Extract the downloaded zip to a directory (e.g., C:\Program Files\Apache\maven)
   - Add Maven to PATH:
     - Add the bin directory (e.g., C:\Program Files\Apache\maven\bin) to your system PATH
   - Verify installation:
     - Open Command Prompt
     - Run: `mvn -version`

## How to Run the Project

1. **Clone the Project**
   ```bash
   git clone https://github.com/bhavana9635/Inventory-Management-System.git
   cd Inventory-Management-System
   ```

2. **Set Up Database**
   - Open Command Prompt
   - Navigate to MySQL bin directory:
     ```bash
     cd C:\Program Files\MySQL\MySQL Server 8.0\bin
     ```
   - Connect to MySQL:
     ```bash
     mysql -u root -p
     ```
   - Enter your root password when prompted
   - Create and set up the database:
     ```sql
     source C:\path\to\your\project\setup_database.sql
     ```

3. **Configure Database Connection**
   - Open `src/main/resources/application.properties` in a text editor
   - Update these lines with your MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/possession_tracker
     spring.datasource.username=root
     spring.datasource.password=your_mysql_password
     ```

4. **Build and Run the Application**
   - Open Command Prompt in the project directory
   - Build the project:
     ```bash
     mvn clean install
     ```
   - Run the application:
     ```bash
     mvn spring-boot:run
     ```
   - Wait for the application to start (you'll see "Started PossessionManagerApplication" in the console)

5. **Access the Application**
   - Open your web browser
   - Go to: http://localhost:8080
   - You should see the application's home page



## Features

- Possession Management
  - Add, edit, and delete possessions
  - Track possession details (name, serial number, category, location, value, etc.)
  - View possession list and details

- Reminder Management
  - Create reminders for possessions
  - Set reminder dates and descriptions
  - Mark reminders as completed

- Duplicate Detection
  - System automatically detects duplicate possessions based on serial numbers
  - View duplicate possession groups



