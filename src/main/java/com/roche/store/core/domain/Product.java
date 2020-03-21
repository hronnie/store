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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(created, product.created) &&
                Objects.equals(archived, product.archived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, created, archived);
    }

    private Boolean archived;

}
