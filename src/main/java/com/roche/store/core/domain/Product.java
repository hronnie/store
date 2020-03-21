package com.roche.store.core.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name is mandatory")
    @Basic(optional = false)
    private String name;

    @NotNull(message = "Price is mandatory")
    private Double price;

    @CreatedDate
    private Date created;

    private Boolean archived;

}
