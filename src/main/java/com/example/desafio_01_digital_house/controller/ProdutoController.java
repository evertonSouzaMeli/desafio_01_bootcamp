package com.example.desafio_01_digital_house.controller;

import com.example.desafio_01_digital_house.entity.Produto;
import com.example.desafio_01_digital_house.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        return ResponseEntity.ok(produtoRepository.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(produtoRepository.getById(id));
    }

    @GetMapping("/disponivel")
    public ResponseEntity<List<Produto>> getAllAvaliableProducts(){
        return ResponseEntity.ok(produtoRepository.getAllAvaliableProducts());
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<Produto>> getAllProductsFilteredByCategory(@RequestParam String categoria){
        return ResponseEntity.ok(produtoRepository.getAllProductsFilteredByCategory(categoria));
    }

    @GetMapping("/categoria/frete_gratis")
    public ResponseEntity<List<Produto>> getAllProductsFilteredByCategoryAndFreeShipping(@RequestParam String categoria){
        return ResponseEntity.ok(produtoRepository.getAllProductsFilteredByCategoryAndFreeShipping(categoria));
    }

    @GetMapping("/frete_gratis")
    public ResponseEntity<List<Produto>> getAllProductsFilteredByFreeShippingAndPrestige(@RequestParam String avaliacao){
        return ResponseEntity.ok(produtoRepository.getAllProductsFilteredByFreeShippingAndPrestige(avaliacao));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Produto>> getAllProductsOrderedByName(@RequestParam String order){
        return ResponseEntity.ok(produtoRepository.getAllProductsOrderedByName(order));
    }

    @GetMapping("/preco")
    public ResponseEntity<List<Produto>> getAllProductsOrderedByPrice(@RequestParam String order){
        return ResponseEntity.ok(produtoRepository.getAllProductsOrderedByPrice(order));
    }

    @PostMapping("/single")
    public ResponseEntity<Produto> save(@RequestBody Produto produto) throws IOException {
        produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/list")
    public ResponseEntity<Produto> save(@RequestBody List<Produto> produto) {
        produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
