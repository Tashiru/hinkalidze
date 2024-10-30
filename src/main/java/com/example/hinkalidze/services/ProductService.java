package com.example.hinkalidze.services;

import com.example.hinkalidze.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    private long ID = 0;
    {
        products.add(new Product(++ID,"Pelmeni", "лепёшка из теста с сырной начинкой. В середине делают углубление, куда перед подачей разбивают яйцо. Блюдо традиционно подают горячим.", 200));
        products.add(new Product(++ID,"Хинкали", "с свининой", 175));
    }

    public List<Product> listProducts() {
        return products;
    }

    public void saveProduct(Product product) {
        product.setId(++ID);
        products.add(product);
    }

    public void deleteProduct(Long id){
        products.removeIf(product -> product.getId().equals(id));
    }

    public Product getProductById(Long id){
        for (Product product : products) {
            if (product.getId().equals(id)) return product;
        }
        return null;
    }
}
