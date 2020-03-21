package com.roche.store.mapper;

import com.roche.store.core.domain.Product;
import com.roche.store.core.web.transfer.ProductDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductMapperTest {
    private ProductMapper mapper
            = Mappers.getMapper(ProductMapper.class);

    private static final String TEST_CREATED_DATE_VALUE = "21/03/2020";
    private static Date TEST_CREATED_DATE = null;

    static {
        try {
            TEST_CREATED_DATE = new SimpleDateFormat("dd/MM/yyyy").parse(TEST_CREATED_DATE_VALUE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenSourceToDestination_whenMaps_thenCorrect() {
        Product source = new Product(1l, "Sugar", 4.5D, TEST_CREATED_DATE, false);
        ProductDto destination = mapper.toDto(source);

        assertThat(source.getId()).isEqualTo(destination.getId());
        assertThat(source.getName()).isEqualTo(destination.getName());
        assertThat(source.getPrice()).isEqualTo(destination.getPrice());
        assertThat(source.getCreated()).isEqualTo(destination.getCreated());
        assertThat(source.getArchived()).isEqualTo(destination.getArchived());
    }

    @Test
    public void givenDestinationToSource_whenMaps_thenCorrect() {
        ProductDto source = new ProductDto(1l, "Sugar", 4.5D, TEST_CREATED_DATE, true);
        Product destination = mapper.toEntity(source);

        assertThat(source.getId()).isEqualTo(destination.getId());
        assertThat(source.getName()).isEqualTo(destination.getName());
        assertThat(source.getPrice()).isEqualTo(destination.getPrice());
        assertThat(source.getCreated()).isEqualTo(destination.getCreated());
        assertThat(source.getArchived()).isEqualTo(destination.getArchived());
    }

    @Test
    public void givenDestinationListToSourceList_whenMaps_thenCorrect() {
        Product sourceEntity = new Product(1l, "Sugar", 4.5D, TEST_CREATED_DATE, true);
        List<Product> source = new ArrayList<>();
        source.add(sourceEntity);
        List<ProductDto> destination = mapper.toProductDTOs(source);

        assertThat(source.get(0).getId()).isEqualTo(destination.get(0).getId());
        assertThat(source.get(0).getName()).isEqualTo(destination.get(0).getName());
        assertThat(source.get(0).getPrice()).isEqualTo(destination.get(0).getPrice());
        assertThat(source.get(0).getCreated()).isEqualTo(destination.get(0).getCreated());
        assertThat(source.get(0).getArchived()).isEqualTo(destination.get(0).getArchived());
    }
}
