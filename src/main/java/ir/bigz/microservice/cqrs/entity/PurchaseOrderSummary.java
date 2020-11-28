package ir.bigz.microservice.cqrs.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PurchaseOrderSummary {

    @Id
    private String state;
    private Double totalSale;
}
