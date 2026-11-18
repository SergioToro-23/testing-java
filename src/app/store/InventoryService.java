package app.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService {

    public Map<String, Integer> calculateStock(List<StockMovement> movements) {
        if (movements == null || movements.isEmpty()) {
            return Map.of();
        }

        Map<String, Integer> storage = new HashMap<>();

        for (StockMovement stockMovement : movements) {

            String productId = stockMovement.productId();
            String type = stockMovement.type();
            int quantity = stockMovement.quantity();

            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be positive");
            }
            if ("IN".equals(type)) {
                storage.merge(productId, quantity, Integer::sum);

            } else if ("OUT".equals(type)) {

                int currentStock = storage.getOrDefault(productId, 0);

                if (currentStock < quantity) {
                    throw new IllegalStateException("Insufficient stock");
                }

                storage.merge(productId, -quantity, Integer::sum);

            } else {
                throw new IllegalArgumentException("Invalid movement type");
            }
        }

        return storage;
    }
}