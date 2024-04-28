package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Page<Order> fetchAll(Pageable page) {
        return this.orderRepository.findAll(page);
    }

    public List<Order> fetchByUser(User user) {
        return this.orderRepository.findByUser(user);
    }

    public Optional<Order> fetchById(long id) {
        return this.orderRepository.findById(id);
    }

    public void handleUpdateOrder(Order order) {
        Optional<Order> orderOptional = this.fetchById(order.getId());
        if (orderOptional.isPresent()) {
            Order curOrder = orderOptional.get();
            curOrder.setStatus(order.getStatus());
            this.orderRepository.save(curOrder);
        }
    }

    public void handleDeleteOrderById(long id) {
        Optional<Order> orderOptional = this.fetchById(id);
        if (orderOptional.isPresent()) {
            Order curOrder = orderOptional.get();
            List<OrderDetail> orderDetails = curOrder.getOrderDetails();
            for (OrderDetail orderDetail : orderDetails) {
                this.orderDetailRepository.deleteById(orderDetail.getId());
            }
        }

        this.orderRepository.deleteById(id);
    }
}
