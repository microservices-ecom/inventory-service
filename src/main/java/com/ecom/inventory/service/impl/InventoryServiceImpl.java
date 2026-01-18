package com.ecom.inventory.service.impl;

import com.ecom.inventory.dto.*;
import com.ecom.inventory.exception.*;
import com.ecom.inventory.mapper.InventoryMapper;
import com.ecom.inventory.model.Inventory;
import com.ecom.inventory.repository.InventoryRepository;
import com.ecom.inventory.service.InventoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;

	@Override
	public InventoryResponse addStock(InventoryRequest request) {
		Inventory inv = Inventory.builder().skuCode(request.getSkuCode()).quantity(request.getQuantity())
				.updatedAt(LocalDateTime.now()).build();

		inventoryRepository.save(inv);
		return InventoryMapper.toResponse(inv);
	}

	@Override
	@Transactional
	public InventoryResponse updateStock(InventoryRequest request) {
		Inventory inv = inventoryRepository.findBySkuCode(request.getSkuCode())
				.orElseThrow(() -> new InventoryNotFoundException("Item not found"));

		inv.setQuantity(request.getQuantity());
		inv.setUpdatedAt(LocalDateTime.now());
		return InventoryMapper.toResponse(inv);
	}

	@Override
	public InventoryResponse getStock(String skuCode) {
		Inventory inv = inventoryRepository.findBySkuCode(skuCode)
				.orElseThrow(() -> new InventoryNotFoundException("Item not found"));
		return InventoryMapper.toResponse(inv);
	}

	@Override
	@Transactional
	public boolean reserveStock(StockReserveRequest request) {
		Inventory inv = inventoryRepository.findBySkuCode(request.getSkuCode())
				.orElseThrow(() -> new InventoryNotFoundException("Item not found"));

		if (inv.getQuantity() < request.getQuantity())
			throw new InsufficientStockException("Not enough stock available");

		inv.setQuantity(inv.getQuantity() - request.getQuantity());
		inv.setUpdatedAt(LocalDateTime.now());
		return true;
	}

	@Override
	@Transactional
	public void releaseStock(String skuCode, Integer qty) {
		Inventory inv = inventoryRepository.findBySkuCode(skuCode)
				.orElseThrow(() -> new InventoryNotFoundException("Item not found"));

		inv.setQuantity(inv.getQuantity() + qty);
		inv.setUpdatedAt(LocalDateTime.now());
	}
}
