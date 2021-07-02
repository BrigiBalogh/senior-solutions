package streams;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    private List<Order> orders = new ArrayList<>();


    public void saveOrder(Order order){
        orders.add(order);
    }

    public long countOrdersByStatus(String status) {

        return orders.stream()
                .filter(o -> o.getStatus().equals(status))
                .count();
      /*  return orders.stream()
                .map(Order::getStatus)
                .count();*/
    }

    public List<Order> collectOrdersWithProductCategory(String category) {
        return orders.stream()
                .filter(o -> o.getProducts()
                .stream()
                .anyMatch(p -> p.getCategory().equals(category)))
                .collect(Collectors.toList());
    }

    public List<Product> productsOverAmountPrice (int amount) {
        return orders.stream()
                .flatMap(o -> o.getProducts()
                .stream())
                .filter(p -> p.getPrice() > amount)
                .distinct()
                .collect(Collectors.toList());
    }

}
