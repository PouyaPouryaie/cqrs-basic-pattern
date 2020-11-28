package ir.bigz.microservice.cqrs.service.data;

import com.github.javafaker.Faker;
import ir.bigz.microservice.cqrs.entity.Product;
import ir.bigz.microservice.cqrs.entity.PurchaseOrder;
import ir.bigz.microservice.cqrs.entity.User;
import ir.bigz.microservice.cqrs.repository.ProductRepository;
import ir.bigz.microservice.cqrs.repository.PurchaseOrderRepository;
import ir.bigz.microservice.cqrs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@ConditionalOnProperty(name = "app.generator.enabled", havingValue = "true")
public class DataGenerator implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        createUser(faker);
        createProduct(faker);
//        IntStream.range(0, 10)
//                .forEach(i -> createOrder(faker));
        createOrder(faker);
    }

    private void createOrder(Faker faker){

        List<Product> products = this.productRepository.findAll();
        List<User> users = this.userRepository.findAll();

        List<PurchaseOrder> purchaseOrderList = IntStream.range(0, 100_000).mapToObj(i -> {
            int userIndex = faker.number().numberBetween(0, 10000);
            int prodIndex = faker.number().numberBetween(0, 1000);
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setUserId(users.get(userIndex).getId());
            purchaseOrder.setProductId(products.get(prodIndex).getId());
            purchaseOrder.setOrderDate(
                    faker.date().between(createDateFromString("2000-01-01"),
                            createDateFromString("2020-10-20")));
            return purchaseOrder;
        }).collect(Collectors.toList());
        purchaseOrderRepository.saveAll(purchaseOrderList);
    }

    private void createUser(Faker faker){
        List<User> userList = IntStream.range(0, 10000)
                .mapToObj(i -> {
                    User user = new User();
                    user.setFirstName(faker.name().firstName());
                    user.setLastName(faker.name().lastName());
                    user.setState(faker.address().stateAbbr());
                    return user;
                }).collect(Collectors.toList());
        userRepository.saveAll(userList);
    }

    private void createProduct(Faker faker){
        List<Product> productList = IntStream.range(0, 1000)
                .mapToObj(i -> {
                    Product product = new Product();
                    product.setDescription(faker.book().title());
                    product.setPrice(faker.number().numberBetween(1, 200));
                    return product;
                }).collect(Collectors.toList());

        productRepository.saveAll(productList);
    }

    private Date createDateFromString(String date){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Date result = null;
        try {
            result = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
