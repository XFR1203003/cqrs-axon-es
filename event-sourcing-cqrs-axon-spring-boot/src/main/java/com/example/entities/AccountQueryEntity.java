package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountQueryEntity {

    @Id
    private String id;

    private double accountBalance;

    private String currency;

    private String status;

}
