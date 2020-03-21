package com.roche.store.core.web.controller;

import com.roche.store.core.domain.Product;
import com.roche.store.core.repository.ProductRepository;
import com.roche.store.core.web.transfer.ProductDto;
import com.roche.store.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductControllerTest {
    private static ProductController mockedProductController;
    private static ProductRepository mockedProductRepository;
    private static ProductMapper mockedProductMapper;

    private static final String TEST_CREATED_DATE_VALUE = "21/03/2020";
    private static Date TEST_CREATED_DATE = null;

    static ProductDto productDto = new ProductDto(null, "Sugar", 4.5D, null, null);
    static Product product = new Product(1l, "Sugar", 4.5D, TEST_CREATED_DATE, false);
    static ProductDto deletedProductDto = new ProductDto(null, "Deleted", 4.5D, null, null);
    static Product deletedProduct = new Product(2l, "Deleted", 4.5D, TEST_CREATED_DATE, true);
    static List<Product> productList = new ArrayList<>();
    static List<Product> productListWithoutDeleted = new ArrayList<>();
    static List<ProductDto> productDtoListWithoutDeleted = new ArrayList<>();
    static List<ProductDto> productDtos = new ArrayList<>();

    static {
        productList.add(product);
        productList.add(deletedProduct);
        productDtos.add(productDto);
        productDtos.add(deletedProductDto);
        productListWithoutDeleted.add(product);
        productDtoListWithoutDeleted.add(productDto);
        try {
            TEST_CREATED_DATE = new SimpleDateFormat("dd/MM/yyyy").parse(TEST_CREATED_DATE_VALUE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    public static void setUpUserControllerInstance() {
        mockedProductRepository = mock(ProductRepository.class);
        mockedProductMapper = mock(ProductMapper.class);
        mockedProductController = new ProductController(mockedProductRepository, mockedProductMapper);
        when(mockedProductMapper.toDto(product)).thenReturn(productDto);
        when(mockedProductMapper.toEntity(productDto)).thenReturn(product);
    }

    @Test
    public void whenCalled_addProduct_AndValidProduct_thenCorrect() {
        when(mockedProductRepository.save(any(Product.class))).thenReturn(product);
        ProductDto result = mockedProductController.addProduct(productDto);

        assertThat(result).isEqualTo(productDto);
    }

    @Test
    public void whenCalled_getAllProduct_AndValidProduct_thenCorrect() {
        when(mockedProductMapper.toProductDTOs(productListWithoutDeleted)).thenReturn(productDtoListWithoutDeleted);
        when(mockedProductRepository.findByArchived()).thenReturn(productListWithoutDeleted);
        List<ProductDto> result = mockedProductController.getAllProduct();

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo(productDto);
    }

    @Test
    public void whenCalled_getAllProductIncludingDeleted_AndValidProduct_thenCorrect() {
        when(mockedProductMapper.toProductDTOs(productList)).thenReturn(productDtos);
        when(mockedProductRepository.findAll()).thenReturn(productList);
        List<ProductDto> result = mockedProductController.getAllProductIncludingDeleted();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0)).isEqualTo(productDto);
        assertThat(result.get(1)).isEqualTo(deletedProductDto);
    }

    @Test
    public void whenCalled_updateProduct_AndValidProduct_thenCorrect() {
        when(mockedProductRepository.save(any(Product.class))).thenReturn(product);
        when(mockedProductRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(product));
        ProductDto result = mockedProductController.addProduct(productDto);

        assertThat(result).isEqualTo(productDto);

        Product modifiedProduct = new Product(1l, "Sugar_modified", 6.5D, TEST_CREATED_DATE, false);
        ProductDto modifiedProductDto = new ProductDto(1L, "Sugar_modified", 6.5D, null, null);
        when(mockedProductMapper.toDto(modifiedProduct)).thenReturn(modifiedProductDto);
        when(mockedProductMapper.toEntity(modifiedProductDto)).thenReturn(modifiedProduct);
        when(mockedProductRepository.save(any(Product.class))).thenReturn(modifiedProduct);
        ProductDto modifiedResult = mockedProductController.updateProduct(1l, modifiedProductDto);
        assertThat(modifiedResult).isEqualTo(modifiedProductDto);
    }

    @Test
    public void whenCalled_deleteProduct_AndValidProduct_thenCorrect() {
        Product deletedProduct = new Product(1l, "Sugar", 6.5D, TEST_CREATED_DATE, true);
        ProductDto deletedProductDto = new ProductDto(1L, "Sugar", 6.5D, null, null);
        when(mockedProductMapper.toDto(deletedProduct)).thenReturn(deletedProductDto);
        when(mockedProductMapper.toEntity(deletedProductDto)).thenReturn(deletedProduct);
        when(mockedProductRepository.save(any(Product.class))).thenReturn(deletedProduct);
        String deletedResult = mockedProductController.deleteProduct(1l);
        assertThat(deletedResult).isEqualTo("Delete was successful");
    }

}
