package seedu.pill.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class OrderTest {
    private Order purchaseOrder;
    private Order dispenseOrder;
    private final String testNotes = "Test order notes";

    @BeforeEach
    void setUp() {
        purchaseOrder = new Order(Order.OrderType.PURCHASE, testNotes);
        dispenseOrder = new Order(Order.OrderType.DISPENSE, testNotes);
    }

    @Test
    void constructor_createsOrderWithCorrectInitialState() {
        // Test purchase order
        assertNotNull(purchaseOrder.getId(), "Order ID should not be null");
        assertEquals(Order.OrderType.PURCHASE, purchaseOrder.getType(), "Order type should be PURCHASE");
        assertEquals(Order.OrderStatus.PENDING, purchaseOrder.getStatus(), "Initial status should be PENDING");
        assertEquals(testNotes, purchaseOrder.getNotes(), "Notes should match constructor argument");
        assertTrue(purchaseOrder.getItems().isEmpty(), "Initial items list should be empty");
        assertNull(purchaseOrder.getFulfillmentTime(), "Initial fulfillment time should be null");

        // Test creation timestamp
        LocalDateTime now = LocalDateTime.now();
        long timeDiff = ChronoUnit.SECONDS.between(purchaseOrder.getCreationTime(), now);
        assertTrue(Math.abs(timeDiff) <= 1, "Creation time should be within 1 second of current time");

        // Test dispense order
        assertEquals(Order.OrderType.DISPENSE, dispenseOrder.getType(), "Order type should be DISPENSE");
    }

    @Test
    void addItem_addsItemsCorrectly() {
        // Add single item
        purchaseOrder.addItem("Aspirin", 100);
        List<OrderItem> items = purchaseOrder.getItems();
        assertEquals(1, items.size(), "Should have one item");
        OrderItem item = items.get(0);
        assertEquals("Aspirin", item.getItemName(), "Item name should match");
        assertEquals(100, item.getQuantity(), "Item quantity should match");

        // Add multiple items
        purchaseOrder.addItem("Bandages", 50);
        items = purchaseOrder.getItems();
        assertEquals(2, items.size(), "Should have two items");
        item = items.get(1);
        assertEquals("Bandages", item.getItemName(), "Second item name should match");
        assertEquals(50, item.getQuantity(), "Second item quantity should match");
    }

    @Test
    void getItems_returnsCopy() {
        purchaseOrder.addItem("Aspirin", 100);
        List<OrderItem> items1 = purchaseOrder.getItems();
        List<OrderItem> items2 = purchaseOrder.getItems();

        assertNotSame(items1, items2, "getItems should return a new copy of the list each time");
        assertEquals(items1, items2, "Lists should contain the same items");
    }

    @Test
    void fulfill_updatesOrderStatusAndTime() {
        purchaseOrder.fulfill();

        assertEquals(Order.OrderStatus.FULFILLED, purchaseOrder.getStatus(),
                "Status should be FULFILLED after fulfilling");
        assertNotNull(purchaseOrder.getFulfillmentTime(),
                "Fulfillment time should be set");

        LocalDateTime now = LocalDateTime.now();
        long timeDiff = ChronoUnit.SECONDS.between(purchaseOrder.getFulfillmentTime(), now);
        assertTrue(Math.abs(timeDiff) <= 1,
                "Fulfillment time should be within 1 second of current time");
    }

    @Test
    void cancel_updatesOrderStatus() {
        purchaseOrder.cancel();

        assertEquals(Order.OrderStatus.CANCELLED, purchaseOrder.getStatus(),
                "Status should be CANCELLED after cancelling");
        assertNull(purchaseOrder.getFulfillmentTime(),
                "Fulfillment time should still be null after cancelling");
    }

    @Test
    void multipleStatusChanges_handleCorrectly() {
        // Test cancel then fulfill
        Order order1 = new Order(Order.OrderType.PURCHASE, "Test order 1");
        order1.cancel();
        order1.fulfill();
        assertEquals(Order.OrderStatus.FULFILLED, order1.getStatus(),
                "Should allow fulfilling a cancelled order");

        // Test fulfill then cancel
        Order order2 = new Order(Order.OrderType.PURCHASE, "Test order 2");
        order2.fulfill();
        order2.cancel();
        assertEquals(Order.OrderStatus.CANCELLED, order2.getStatus(),
                "Should allow cancelling a fulfilled order");
    }

    @Test
    void orderEquality_idBasedComparison() {
        Order order1 = new Order(Order.OrderType.PURCHASE, "Test order");
        Order order2 = new Order(Order.OrderType.PURCHASE, "Test order");

        assertNotEquals(order1.getId(), order2.getId(),
                "Different orders should have different IDs");
    }
}