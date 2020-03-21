package com.roche.store.core.web.controller;

import com.roche.store.core.domain.Product;
import com.roche.store.core.repository.ProductRepository;
import com.roche.store.core.web.transfer.ProductDto;
import com.roche.store.mapper.ProductMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping("/product/list")
    @ApiOperation(value="Lists all Products NOT included the deleted Products")
    public List<ProductDto> getAllProduct() {
        return productMapper.toProductDTOs((List<Product>) productRepository.findByArchived());
    }

    @GetMapping("/product/listall")
    @ApiOperation(value="Lists all Products included the deleted Products")
    public List<ProductDto> getAllProductIncludingDeleted() {
        return productMapper.toProductDTOs((List<Product>) productRepository.findAll());
    }

    @PostMapping("/product")
    @ApiOperation(value="Creates a new Product")
    public ProductDto addProduct(@RequestBody @ApiParam("Product data") ProductDto productDto) {
        productDto.setArchived(false);
        productDto.setCreated(new Date());
        Product result = productRepository.save(productMapper.toEntity(productDto));
        return productMapper.toDto(result);
    }

    @PatchMapping("/product/{id}")
    @ApiOperation(value="Updates an existing Product")
    public ProductDto updateProduct(@ApiParam(value = "Product data Id", required = true, example = "123") @PathVariable("id") long id,
            @RequestBody @ApiParam("Product data") ProductDto productDto) {
        Product toUpdate = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        toUpdate.setName(productDto.getName());
        toUpdate.setPrice(productDto.getPrice());
        Product result = productRepository.save(toUpdate);
        return productMapper.toDto(result);
    }

    @DeleteMapping("/product/{id}")
    @ApiOperation(value="Deletes a Product with soft delete")
    public String deleteProduct(@PathVariable("id") @ApiParam(value = "Product data", required = true, example = "123") long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        product.setArchived(true);
        Product result = productRepository.save(product);
        if (result.getArchived()) {
            return "Delete was successful";
        }
        return "Delete was NOT successful";
    }
}
