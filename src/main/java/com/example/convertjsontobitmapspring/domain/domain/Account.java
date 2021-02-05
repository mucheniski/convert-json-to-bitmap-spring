package com.example.convertjsontobitmapspring.domain.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Account {

    @EqualsAndHashCode.Include
    @Id
    private Long id;

    private String name;
    private BigDecimal balance;

}