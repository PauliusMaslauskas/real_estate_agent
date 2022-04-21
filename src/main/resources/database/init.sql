CREATE TABLE IF NOT EXISTS property (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    price REAL,
    description VARCHAR(255),
    city VARCHAR(255),
    country VARCHAR(255)
);