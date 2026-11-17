package app.excercisesgptstream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderAnalizerTest {

    private ArrayList<Order> OrdersList;

    // @InjectMocks
    private OrderAnalizer orderAnalizer;

    @BeforeEach
    void setUp() {
        orderAnalizer = new OrderAnalizer();
        OrdersList = new ArrayList<> (List.of(new Order(1L, List.of(new Product(
                "carros",
                "category",
                200.0)))));
    }

    @Test
    void shouldgetAllProductNames() {
        var result = orderAnalizer.getAllProductNames(OrdersList);
        var expected = List.of("carros");
        assertEquals(expected,result);
    }

    @Test 
    void shouldCallIllegalArgumentsException(){        
        assertThrows(IllegalArgumentException.class, () -> orderAnalizer.getAllProductNames(List.of()));
    }

    @Test 
    void shouldCalculateTotalRevenue(){
        OrdersList.add(new Order(1L, List.of(new Product(
                "carros",
                "category",
                200.0))));
        var result = orderAnalizer.calculateTotalRevenue(OrdersList);
        double expected = 400.0;
        assertEquals(expected,result);
    }



}
