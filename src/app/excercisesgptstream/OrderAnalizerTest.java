package app.excercisesgptstream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderAnalizerTest {

    private ArrayList<Order> OrdersList;

    // @InjectMocks
    private OrderAnalizer orderAnalizer;

    @BeforeEach
    void setUp() {
        orderAnalizer = new OrderAnalizer();
        OrdersList = new ArrayList<>(List.of(new Order(1L, List.of(new Product(
                "tesla",
                "cars",
                200.0))),
                new Order(1L, List.of(new Product(
                        "ford",
                        "cars",
                        300.0)))));
    }

    @Test
    void shouldgetAllProductNames() {
        var result = orderAnalizer.getAllProductNames(OrdersList);
        var expected = List.of("tesla", "ford");
        assertEquals(expected, result);
    }

    @Test
    void shouldCallIllegalArgumentsException() {
        assertThrows(IllegalArgumentException.class, () -> orderAnalizer.getAllProductNames(List.of()));
    }

    @Test
    void shouldCalculateTotalRevenue() {
        OrdersList.add(new Order(1L, List.of(new Product(
                "chevy",
                "cars",
                200.0))));
        var result = orderAnalizer.calculateTotalRevenue(OrdersList);
        double expected = 400.0;
        assertEquals(expected, result);
    }

    @Test
    void shouldGroupByCategory() {

        OrdersList.add(new Order(1L, List.of(new Product(
                "boeing747",
                "planes",
                400.0))));

        var result = orderAnalizer.groupByCategory(OrdersList);
        Map<String, List<Product>> expected = Map.of(
                "cars", List.of(new Product(
                        "tesla",
                        "cars",
                        200.0),
                        new Product(
                                "ford",
                                "cars",
                                300.0)),
                "planes", List.of(new Product(
                        "boeing747",
                        "planes",
                        400.0))

        );
        assertEquals(expected, result);
    }

}
