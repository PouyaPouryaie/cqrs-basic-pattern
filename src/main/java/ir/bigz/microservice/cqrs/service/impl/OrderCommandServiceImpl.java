package ir.bigz.microservice.cqrs.service.impl;

import ir.bigz.microservice.cqrs.entity.Product;
import ir.bigz.microservice.cqrs.entity.PurchaseOrder;
import ir.bigz.microservice.cqrs.entity.User;
import ir.bigz.microservice.cqrs.repository.ProductRepository;
import ir.bigz.microservice.cqrs.repository.PurchaseOrderRepository;
import ir.bigz.microservice.cqrs.repository.UserRepository;
import ir.bigz.microservice.cqrs.service.OrderCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Component("OrderCommandServiceImpl")
@Slf4j
public class OrderCommandServiceImpl implements OrderCommandService {

    private static final long ORDER_CANCELLATION_WINDOW = 30;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    private List<User> users;
    private List<Product> products;

    @PostConstruct
    private void init(){
        this.users = this.userRepository.findAll();
        this.products = this.productRepository.findAll();
    }

    @Override
    public void createOrder(int userIndex, int productIndex) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(this.products.get(productIndex-1).getId());
        purchaseOrder.setUserId(this.users.get(userIndex-1).getId());
        this.purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public void cancelOrder(long orderId) {

        log.info("order find is" + purchaseOrderRepository.findById(orderId).get().toString());
        this.purchaseOrderRepository.findById(orderId)
                .ifPresent(purchaseOrder -> {
                    LocalDate orderDate = LocalDate.ofInstant(purchaseOrder.getOrderDate().toInstant(), ZoneId.systemDefault());
                    if(Duration.between(orderDate.atStartOfDay(), LocalDate.now().atStartOfDay()).toDays() <= ORDER_CANCELLATION_WINDOW){
                        this.purchaseOrderRepository.deleteById(orderId);
                        //additional logic to issue refund
                    }
                });
    }
}
