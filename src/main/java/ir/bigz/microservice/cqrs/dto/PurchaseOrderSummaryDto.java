package ir.bigz.microservice.cqrs.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseOrderSummaryDto {

    private String state;
    private double totalSale;
}
