package com.ecom.inventory.controller;


import com.ecom.inventory.dto.*;
import com.ecom.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

	private final InventoryService inventoryService;

	@PostMapping
	public ResponseEntity<?> addStock(@RequestBody InventoryRequest req) {
		return ResponseEntity.ok(inventoryService.addStock(req));
	}

	@PutMapping
	public ResponseEntity<?> updateStock(@RequestBody InventoryRequest req) {
		return ResponseEntity.ok(inventoryService.updateStock(req));
	}

	@GetMapping("/{skuCode}")
	public ResponseEntity<?> getStock(@PathVariable String skuCode) {
		return ResponseEntity.ok(inventoryService.getStock(skuCode));
	}

	@PostMapping("/reserve")
	public ResponseEntity<?> reserve(@RequestBody StockReserveRequest req) {
		return ResponseEntity.ok(inventoryService.reserveStock(req));
	}

	@PostMapping("/release/{skuCode}/{qty}")
	public ResponseEntity<?> release(@PathVariable String skuCode, @PathVariable Integer qty) {
		inventoryService.releaseStock(skuCode, qty);
		return ResponseEntity.ok().build();
	}
}
