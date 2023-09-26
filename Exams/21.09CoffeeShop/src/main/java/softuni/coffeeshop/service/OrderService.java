package softuni.coffeeshop.service;

import softuni.coffeeshop.model.service.OrderServiceModel;
import softuni.coffeeshop.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    void addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> findAllOrderByPriceDesc();

    void readyOrder(Long id);
}
