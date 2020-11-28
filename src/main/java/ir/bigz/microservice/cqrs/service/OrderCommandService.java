package ir.bigz.microservice.cqrs.service;

import org.springframework.stereotype.Service;

@Service
public interface OrderCommandService {

    void createOrder(int userIndex, int productIndex);
    void cancelOrder(long orderId);
}