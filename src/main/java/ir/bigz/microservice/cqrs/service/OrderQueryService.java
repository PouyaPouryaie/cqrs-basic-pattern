package ir.bigz.microservice.cqrs.service;

import ir.bigz.microservice.cqrs.dto.PurchaseOrderSummaryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderQueryService {
    List<PurchaseOrderSummaryDto> getSaleSummaryGroupByState();
    PurchaseOrderSummaryDto getSaleSummaryByState(String state);
    double getTotalSale();
}
