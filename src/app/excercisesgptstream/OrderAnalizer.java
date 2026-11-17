package app.excercisesgptstream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderAnalizer {

    public List<String> getAllProductNames(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("Orders list cannot be null");
        }
        return orders.stream()
                .flatMap(streamOrder -> streamOrder.products().stream())
                .map(product -> product.name())
                .toList();
    }
    
    public double calculateTotalRevenue(List<Order> orders){
        
        return orders.stream()
                .flatMap(streamOrder -> streamOrder.products().stream())
                .mapToDouble(product -> product.price())
                .sum();
    }

    /*
     * public Map<String,List<Product>> groupByCategory(List<Order> orders){
     * 
     * }
     * 
     * public Map<String,Long> countProductsByCategory(List<Order> orders){
     * 
     * }
     */
}
