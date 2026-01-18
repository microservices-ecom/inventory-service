package com.ecom.inventory.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest {
	@NotBlank
	private String skuCode;

	@Min(0)
	private Integer quantity;
}
