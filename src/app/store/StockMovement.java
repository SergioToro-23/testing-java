package app.store;

public record StockMovement(
    String productId,
    String type,
    int quantity
) {}