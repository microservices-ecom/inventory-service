package com.ecom.inventory.service;

import com.ecom.inventory.dto.*;

public interface InventoryService {
	InventoryResponse addStock(InventoryRequest request);

	InventoryResponse updateStock(InventoryRequest request);

	InventoryResponse getStock(String skuCode);

	boolean reserveStock(StockReserveRequest request);

	void releaseStock(String skuCode, Integer qty);
}
