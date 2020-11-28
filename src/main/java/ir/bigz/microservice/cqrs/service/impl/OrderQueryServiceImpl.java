package ir.bigz.microservice.cqrs.service.impl;

import ir.bigz.microservice.cqrs.dto.PurchaseOrderSummaryDto;
import ir.bigz.microservice.cqrs.entity.PurchaseOrderSummary;
import ir.bigz.microservice.cqrs.repository.PurchaseOrderSummaryRepository;
import ir.bigz.microservice.cqrs.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("OrderQueryServiceImpl")
public class OrderQueryServiceImpl implements OrderQueryService {

    @Autowired
    private PurchaseOrderSummaryRepository purchaseOrderSummaryRepository;

    @Override
    public List<PurchaseOrderSummaryDto> getSaleSummaryGroupByState() {

        return purchaseOrderSummaryRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseOrderSummaryDto getSaleSummaryByState(String state) {
        return purchaseOrderSummaryRepository.findByState(state)
                .map(this::entityToDto)
                .orElseGet(() -> new PurchaseOrderSummaryDto(state, 0));

    }

    @Override
    public double getTotalSale() {
        return purchaseOrderSummaryRepository.findAll()
                .stream()
                .mapToDouble(PurchaseOrderSummary::getTotalSale).sum();
    }

    private PurchaseOrderSummaryDto entityToDto(PurchaseOrderSummary purchaseOrderSummary) {
        PurchaseOrderSummaryDto dto = new PurchaseOrderSummaryDto();
        dto.setState(purchaseOrderSummary.getState());
        dto.setTotalSale(purchaseOrderSummary.getTotalSale());
        return dto;
    }
}
