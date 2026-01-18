package com.ecom.inventory.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockReserveRequest {
    private String skuCode;
    private Integer quantity;
    private String orderId;
}
