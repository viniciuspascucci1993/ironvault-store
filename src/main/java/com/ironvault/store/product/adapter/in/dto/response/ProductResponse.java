package com.ironvault.store.product.adapter.in.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private UUID id;
    private UUID merchantId;
    private String name;
    private  String description;
    private String imageUrl;
    private boolean active;
    private LocalDateTime createdAt;
}
