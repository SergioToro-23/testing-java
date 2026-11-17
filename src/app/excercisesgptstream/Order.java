package app.excercisesgptstream;

import java.util.List;

public record Order(
    Long id,
    List<Product> products
) {}
