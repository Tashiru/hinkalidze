package com.example.hinkalidze.controllers;

import com.example.hinkalidze.models.Product;
import com.example.hinkalidze.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/market")
    public String marketPage(@RequestParam(name ="title", required = false) String title, Model model){
        model.addAttribute("active", "active");
        model.addAttribute("products", productService.listProducts(title));
        return "market";
    }
    @GetMapping("/market/{id}")
    public String infoProduct(@PathVariable (value = "id") Long id, Model model) {
        model.addAttribute("products", productService.getProductById(id));
        return "product-info";
    }

    @GetMapping("/market/product_settings")
    public String productSettingsPage(@RequestParam(name ="title", required = false) String title, Model model){
        model.addAttribute("products", productService.listProducts(title));
        return "product_settings";
    }

    @PostMapping("/market/product_settings/add")
    public String addProduct(Product product){
        productService.saveProduct(product);
        return "redirect:/market/product_settings";
    }

    @PostMapping("/market/product_settings/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/market/product_settings";
    }
}
