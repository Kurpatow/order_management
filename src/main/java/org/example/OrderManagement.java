package org.example;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.example.models.Customer;
import org.example.models.Order;
import org.example.models.OrderItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class OrderManagement {

    public static final QueryRunner queryRunner = new QueryRunner();

    static void main(String[] args) {
        String jdbcUrl ="jdbc:postgresql://localhost:5432/testDb";
        String username ="postgres";
        String password ="postgres";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            List<Order> orders = queryRunner.query(connection, "SELECT * FROM orders", new BeanListHandler<>(Order.class));
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Order Date: " + order.getOrderDate());
                System.out.println("Customer: " + getCustomerInfo(connection, order.getCustomerId()));
                System.out.println("Order Items:");
                List<OrderItem> orderItems = getOrderItems(connection, order.getOrderId());
                for (OrderItem item : orderItems) {
                    System.out.println("  Product ID: " + item.getProductId());
                    System.out.println("  Quantity: " + item.getQuantity());
                }
                System.out.println("---------------");
            }
            String customerLastName = "Nik";
            List<Order> ordersByLastName = getOrdersByCustomerLastName(connection, customerLastName);
            System.out.println("Orders by Last Name '" + customerLastName + "':");
            for (Order order : ordersByLastName) {
                System.out.println("Order ID: " + order.getOrderId());
                System.out.println("Order Date: " + order.getOrderDate());
                System.out.println("Customer: " + getCustomerInfo(connection, order.getCustomerId()));
                System.out.println("------------");
            }

            int updateRows = updateOrderItemQuantity(connection,1,101,5);
            System.out.println(updateRows + " rows updated.");

            int newOrderId = insertNewOrder(connection, 2);
            System.out.println("New Order ID: " + newOrderId);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getCustomerInfo(Connection connection, int customerId) throws SQLException {
        Customer customer = queryRunner.query(connection, "SELECT * FROM customers WHERE customerId=?", new BeanHandler<>(Customer.class), customerId);
        return customer.getFirstName() + " " + customer.getLastName();
    }

    private static List<OrderItem> getOrderItems(Connection connection, int orderId) throws SQLException {
        return queryRunner.query(connection, "SELECT * FROM order_items WHERE orderId=?", new BeanListHandler<>(OrderItem.class), orderId);
    }

    private static List<Order> getOrdersByCustomerLastName(Connection connection, String lastName) throws SQLException {
        String sql = "SELECT * FROM orders o JOIN customers c ON o.customerId = c.customerId WHERE c.lastName=?";
        return queryRunner.query(connection, sql, new BeanListHandler<>(Order.class), lastName);
    }

    private static int updateOrderItemQuantity(Connection connection, int orderId, int productId, int newQuantity) throws SQLException {
        String sql = "UPDATE order_items SET quantity = ? WHERE orderId = ? AND productId = ?";
        return queryRunner.update(connection, sql, newQuantity, orderId, productId);
    }

    private static int insertNewOrder(Connection connection, int customerId) throws SQLException {
        String sql = "INSERT INTO orders (customerId, orderDate) VALUES (?, CURRENT_TIMESTAMP) RETURNING orderId";
        return queryRunner.query(connection, sql, new ScalarHandler<>(), customerId);
    }
}
