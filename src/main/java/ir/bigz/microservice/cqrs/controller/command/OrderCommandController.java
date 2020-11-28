package ir.bigz.microservice.cqrs.controller.command;

import ir.bigz.microservice.cqrs.dto.OrderCommandDto;
import ir.bigz.microservice.cqrs.service.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("po")
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "true")
public class OrderCommandController {

    @Autowired
    @Qualifier("OrderCommandServiceImpl")
    private OrderCommandService orderCommandService;

    @PostMapping("/sale")
    public void placeOrder(@RequestBody OrderCommandDto dto){
        this.orderCommandService.createOrder(dto.getUserIndex(), dto.getProductIndex());
    }

    @PutMapping("/cancel-order/{orderId}")
    public void cancelOrder(@PathVariable long orderId){
        this.orderCommandService.cancelOrder(orderId);
    }
}
