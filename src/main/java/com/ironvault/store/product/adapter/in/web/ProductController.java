package com.ironvault.store.product.adapter.in.web;

import com.ironvault.store.product.adapter.in.dto.request.CreateProductRequest;
import com.ironvault.store.product.adapter.in.dto.response.ProductResponse;
import com.ironvault.store.product.adapter.in.mapper.ProductMapper;
import com.ironvault.store.product.domain.model.Product;
import com.ironvault.store.product.domain.port.in.CreateProductUseCase;
import com.ironvault.store.product.domain.port.in.GetAllProductsUseCase;
import com.ironvault.store.product.domain.port.in.GetProductByIdUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final ProductMapper mapper;

    public ProductController(CreateProductUseCase createProductUseCase,
                             GetProductByIdUseCase getProductByIdUseCase,
                             GetAllProductsUseCase getAllProductsUseCase,
                             ProductMapper mapper) {
        this.createProductUseCase = createProductUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody CreateProductRequest request,
            HttpServletRequest httpRequest) {
        String merchantIdAttr = (String) httpRequest.getAttribute("merchantId");
        if (merchantIdAttr == null) {
            throw new IllegalStateException("merchantId not found in authentication token");
        }
        UUID merchantId = UUID.fromString(merchantIdAttr);

        Product product = createProductUseCase.execute(
                merchantId,
                request.getName(),
                request.getDescription(),
                request.getImageUrl(),
                mapper.toVariantInputs(request.getVariants())
        );

        return ResponseEntity.status(201).body(mapper.toResponse(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable("id") UUID id) {
        Product product = getProductByIdUseCase.getById(id);
        return ResponseEntity.ok(mapper.toResponse(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll(HttpServletRequest httpRequest) {
        String merchantIdAttr = (String) httpRequest.getAttribute("merchantId");
        if (merchantIdAttr == null) {
            throw new IllegalStateException("merchantId not found in authentication token");
        }
        UUID merchantId = UUID.fromString(merchantIdAttr);

        List<ProductResponse> products = getAllProductsUseCase.getByMerchantId(merchantId)
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(products);
    }
}
