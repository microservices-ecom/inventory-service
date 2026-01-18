package com.ecom.inventory.mapper;


import com.ecom.inventory.dto.InventoryResponse;
import com.ecom.inventory.model.Inventory;

public class InventoryMapper {

	public static InventoryResponse toResponse(Inventory inv) {
		return InventoryResponse.builder().id(inv.getId()).skuCode(inv.getSkuCode()).quantity(inv.getQuantity())
				.build();
	}
}
