package com.example.desafio_01_digital_house.repository;

import com.example.desafio_01_digital_house.entity.Produto;
import com.example.desafio_01_digital_house.helper.FileMapperHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProdutoRepository implements BaseRepository<Integer, Produto> {
    private final ObjectMapper mapper = FileMapperHelper.getObjectMapper();
    private final String PATH = FileMapperHelper.getPATH_PRODUTO();
    private final ArrayList produtos = new ArrayList(FileMapperHelper.getFileProducts());

    @Override
    public List<Produto> getAll() {
        return produtos;
    }

    @Override
    public Produto getById(Integer key) {
        return getAll().stream()
                .filter(produto -> produto.getId().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public void save(Produto value) throws IOException {
        try {
            value.setId(produtos.size() + 1);
            produtos.add(value);
            mapper.writeValue(new File(PATH), this.produtos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(List<Produto> produtos)  {
        produtos.forEach(produto -> {
            try {
                save(produto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public List<Produto> getAllProductsFilteredByCategory(String categoria) {
       return getAll().stream()
               .filter(produto -> produto.getCategoria().equalsIgnoreCase(categoria))
               .collect(Collectors.toList());
    }

    public List<Produto> getAllAvaliableProducts(){
        return getAll().stream()
                .filter(produto -> produto.getQuantidade() > 0)
                .collect(Collectors.toList());
    }

    public List<Produto> getAllProductsFilteredByCategoryAndFreeShipping(String categoria) {
        return getAllProductsFilteredByCategory(categoria).stream()
                .filter(Produto::getFreteGratis)
                .collect(Collectors.toList());
    }

    public List<Produto> getAllProductsFilteredByFreeShippingAndPrestige(String avaliacao){
        return getAll().stream()
                .filter(produto -> produto.getAvaliacao().equals(avaliacao))
                .filter(Produto::getFreteGratis)
                .collect(Collectors.toList());
    }

    public List<Produto> getAllProductsOrderedByName(String order){
        if(order.equalsIgnoreCase("asc"))
            return getAll().stream()
                    .sorted(Comparator.comparing(Produto::getNome))
                    .collect(Collectors.toList());

        else if(order.equalsIgnoreCase("desc"))
            return getAll().stream()
                    .sorted(Comparator.comparing(Produto::getNome).reversed())
                    .collect(Collectors.toList());

        return getAll();
    }

    public List<Produto> getAllProductsOrderedByPrice(String order){
        if(order.equalsIgnoreCase("asc"))
            return getAll().stream()
                    .sorted(Comparator.comparing(Produto::getPreco))
                    .collect(Collectors.toList());

        else if(order.equalsIgnoreCase("desc"))
            return getAll().stream()
                    .sorted(Comparator.comparing(Produto::getPreco).reversed())
                    .collect(Collectors.toList());

        return getAll();
    }
}
