package com.ironvault.store.product.adapter.in.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    @NotBlank
    private String name;
    private String description;
    private String imageUrl;

    @NotEmpty
    @Valid
    private List<VariantRequest> variants;
}
