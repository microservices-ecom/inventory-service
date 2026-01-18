package com.ecom.inventory.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String skuCode;

	@Column(nullable = false)
	private Integer quantity;

	@Version
	private Long version;

	private LocalDateTime updatedAt;
}
