package ir.bigz.microservice.cqrs;

import ir.bigz.microservice.cqrs.service.OrderQueryService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CqrsApplicationTests {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderQueryService orderQueryService;

	@Test
	void contextLoads() {
	}


	@Test
	public void invokeAOP(){
		orderQueryService.getSaleSummaryGroupByState();
	}

}
