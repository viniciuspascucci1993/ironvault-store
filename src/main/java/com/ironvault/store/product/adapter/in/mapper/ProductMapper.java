package com.ironvault.store.product.adapter.in.mapper;

import com.ironvault.store.product.adapter.in.dto.request.CreateProductRequest;
import com.ironvault.store.product.adapter.in.dto.request.VariantRequest;
import com.ironvault.store.product.adapter.in.dto.response.ProductResponse;
import com.ironvault.store.product.domain.model.Product;
import com.ironvault.store.product.domain.port.in.CreateProductUseCase;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(Product product);
    default List<CreateProductUseCase.VariantInput> toVariantInputs(List<VariantRequest> variants) {
        return variants.stream()
                .map(v -> new CreateProductUseCase.VariantInput(v.getSize(), v.getPrice(), v.getStock()))
                .toList();
    }

}
