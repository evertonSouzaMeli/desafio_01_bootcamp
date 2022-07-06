package com.example.desafio_01_digital_house.controller;

import com.example.desafio_01_digital_house.entity.Pedido;
import com.example.desafio_01_digital_house.entity.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private Pedido pedido;

    public PedidoController(Pedido pedido) {
        this.pedido = pedido;
    }

    @GetMapping
    public ResponseEntity<Pedido> getPedido(){
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/single")
    public ResponseEntity<Pedido> addSingleProduct(@RequestBody Produto produto) {
        pedido.addProduct(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/list")
    public ResponseEntity<Pedido> addListProduct(@RequestBody List<Produto> produtos) {
        pedido.addProduct(produtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/finalizar_compra")
    public ResponseEntity<Pedido> finishPurchase() throws CloneNotSupportedException {
        Pedido pedidoFinal = this.pedido.clone();
        this.pedido = new Pedido();
        return ResponseEntity.ok(pedidoFinal);
    }
}
