package com.roche.store.core.web.transfer;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private Double price;
    private Date created;
    private Boolean archived;

}
