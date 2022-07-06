package com.example.desafio_01_digital_house.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Pedido implements Cloneable {
    private List<Produto> produtos;
    private LocalDateTime dataAberto;
    private BigDecimal precoFinal;
    private Boolean aberto;

    public Pedido(){
        this.produtos = new ArrayList();
        this.dataAberto = LocalDateTime.now();
        this.precoFinal = BigDecimal.ZERO;
        this.aberto = true;
    }

    public void addProduct(List<Produto> produtos) {
        produtos.forEach(this::addProduct);
    }

    public void addProduct(Produto produto) {
        if(produto == null)
            throw new RuntimeException("Produto não pode ser nulo");

        this.produtos.add(produto);

        this.precoFinal = BigDecimal.valueOf(this.produtos.stream()
                .map(p -> p.getPreco().multiply(BigDecimal.valueOf(p.getQuantidade())))
                .mapToDouble(BigDecimal::doubleValue).sum());
    }

    @Override
    public Pedido clone() throws CloneNotSupportedException {
        return (Pedido) super.clone();
    }
}
