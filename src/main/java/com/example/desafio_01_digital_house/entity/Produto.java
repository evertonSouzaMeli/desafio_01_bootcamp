package com.example.desafio_01_digital_house.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Produto {
    private Integer id;
    private String nome;
    private String categoria;
    private String marca;
    private BigDecimal preco;
    private Integer quantidade;
    private Boolean freteGratis;
    private String avaliacao;
}
