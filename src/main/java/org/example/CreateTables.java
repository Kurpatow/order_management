package org.example;

public class CreateTables {
    /*
    CREATE TABLE customers (
        customerId SERIAL PRIMARY KEY,
        firstName VARCHAR(50),
        lastName VARCHAR(50),
        email VARCHAR(100)
    );
    CREATE TABLE orders (
        orderId SERIAL PRIMARY KEY,
        customerId INT,
        orderDate DATE,
        FOREIGN KEY (customerId) REFERENCES customers(customerId)
    );
    CREATE TABLE order_items (
        orderId INT,
        productId INT,
        quantity INT,
        PRIMARY KEY (orderId, productId),
        FOREIGN KEY (orderId) REFERENCES orders(orderId)
    );

    INSERT INTO customers (firstName, lastName, email) VALUES
        ('Emma', 'Taylor', 'emma.taylor@example.com'),
        ('Liam', 'Anderson', 'liam.anderson@example.com'),
        ('Olivia', 'Thomas', 'olivia.thomas@example.com'),
        ('Noah', 'Jackson', 'noah.jackson@example.com'),
        ('Ava', 'White', 'ava.white@example.com'),
        ('Ethan', 'Harris', 'ethan.harris@example.com'),
        ('Sophia', 'Martin', 'sophia.martin@example.com'),
        ('Mason', 'Thompson', 'mason.thompson@example.com'),
        ('Isabella', 'Garcia', 'isabella.garcia@example.com'),
        ('Lucas', 'Martinez', 'lucas.martinez@example.com');


    INSERT INTO orders (customerId, orderDate) VALUES
        (1, '2026-04-01'),
        (2, '2026-04-02'),
        (3, '2026-04-03'),
        (4, '2026-04-04'),
        (5, '2026-04-05'),
        (6, '2026-04-06'),
        (7, '2026-04-07'),
        (8, '2026-04-08'),
        (9, '2026-04-09'),
        (10, '2026-04-10');

    INSERT INTO order_items (orderId, productId, quantity) VALUES
        (2, 106, 3),
        (5, 101, 2),
        (3, 107, 5),
        (1, 108, 1),
        (4, 102, 4),
        (2, 109, 2),
        (5, 110, 3),
        (3, 104, 1),
        (1, 105, 4),
        (4, 103, 2);
    */
}
