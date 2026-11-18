package app.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class InventoryServiceTest {

    @Test
    void shouldCalculateStockForMultipleProducts() {
        List<StockMovement> movements = List.of(
                new StockMovement("LAPTOP", "IN", 10),
                new StockMovement("MOUSE", "IN", 20),
                new StockMovement("LAPTOP", "OUT", 3),
                new StockMovement("MOUSE", "OUT", 5),
                new StockMovement("LAPTOP", "IN", 2));

        InventoryService inventoryService = new InventoryService();
        var result = inventoryService.calculateStock(movements);

        assertEquals(9, result.get("LAPTOP"));
        assertEquals(15, result.get("MOUSE"));
    }

    @Test
    void shouldThrowExceptionWhenStockBecomesNegative() {
        List<StockMovement> movements = List.of(
                new StockMovement("LAPTOP", "IN", -10),
                new StockMovement("MOUSE", "IN", 20),
                new StockMovement("LAPTOP", "OUT", 3),
                new StockMovement("MOUSE", "OUT", 5),
                new StockMovement("LAPTOP", "IN", 2));

        InventoryService inventoryService = new InventoryService();
        assertThrows(IllegalStateException.class, () -> inventoryService.calculateStock(movements));

    }

    @Test 
    void shouldReturnEmptyMapWhenMovementsAreEmpty(){
        List<StockMovement> movements = List.of();
        InventoryService inventoryService = new InventoryService();
        assertEquals(Map.of(),inventoryService.calculateStock(movements));
    }

}
