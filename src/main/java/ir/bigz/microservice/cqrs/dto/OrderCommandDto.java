package ir.bigz.microservice.cqrs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCommandDto {

    private int userIndex;
    private int productIndex;
}
