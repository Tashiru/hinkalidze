package com.example.hinkalidze.controllers;

import com.example.hinkalidze.models.Image;
import com.example.hinkalidze.models.Product;
import com.example.hinkalidze.repositories.ImageRepository;
import com.example.hinkalidze.repositories.ProductRepository;
import com.example.hinkalidze.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;


    @GetMapping("/market")
    public String marketPage(@RequestParam(name ="title", required = false) String title, Model model){
        model.addAttribute("active", "active");
        model.addAttribute("products", productService.listProducts(title));
        return "market";
    }

    @GetMapping("/market/product_settings")
    public String productSettingsPage(@RequestParam(name = "title", required = false) String title,
                                      @RequestParam(name = "preview_image_id", required = false) String preview_image_id,
                                      @RequestParam(name = "id", required = false) Long id, Model model, Product product){
        Iterable<Product> products = productService.listProducts();

        model.addAttribute("products", products);

        return "product_settings";
    }

    @PostMapping("/market/product_settings/add")
    public String addProduct(@RequestParam("file1") MultipartFile file1, Product product) throws IOException {
        productService.saveProduct(product, file1);

        return "redirect:/market/product_settings";
    }

    @PostMapping("/market/product_settings/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/market/product_settings";
    }



    @GetMapping("/market/product_settings/edit/{id}")
    public String editProduct(@PathVariable(value = "id") Long id, Product product, Model model) {
        if (productService.getProductById(id) == null){
            return "product_settings";
        }
        model.addAttribute("products_edit", productService.getProductById(id));
        return "edit";
    }

    //Переделать лучше в сервисе дописать норм код с получением данных из форм и перезаписью их в старые атрибуты//
    @PostMapping("/market/product_settings/edit/{id}")
    public String saveEditProduct(@RequestParam("file1") MultipartFile file1
            , @PathVariable(value = "id") Long id, @RequestParam String title, @RequestParam int price, @RequestParam String description ) throws IOException {
    Product product = productService.getProductById(id);
    Image image = productService.getImageById(product.getPreviewImageId());

    product.setDescription(description);
    product.setPrice(price);
    product.setTitle(title);
    file1.getBytes();
    image.setBytes(file1.getBytes());
    image.setName(product.getTitle());
    image.setOriginalFileName(file1.getOriginalFilename());
    image.setContentType(file1.getContentType());
    image.setSize(file1.getSize());
    productRepository.save(product);
    imageRepository.save(image);
//    productService.saveProduct(product, file1);
    return "redirect:/market/product_settings";
    }
}
