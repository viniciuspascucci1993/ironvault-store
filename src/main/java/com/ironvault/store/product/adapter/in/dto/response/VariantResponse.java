package com.ironvault.store.product.adapter.in.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariantResponse {

    private UUID id;
    private Integer size;
    private BigDecimal price;
    private Integer stock;

}
