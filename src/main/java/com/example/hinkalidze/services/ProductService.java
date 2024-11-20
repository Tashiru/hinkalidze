package com.example.hinkalidze.services;

import com.example.hinkalidze.models.Image;
import com.example.hinkalidze.models.Product;
import com.example.hinkalidze.repositories.ImageRepository;
import com.example.hinkalidze.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;


    public List<Product> listProducts(String title) {
        if (title != null){
            return productRepository.findByTitle(title);
        }
        return productRepository.findAll();
    }

    public Iterable<Product> listProducts() {
        return productRepository.findAll();
    }

    public Iterable<Image> listImages(){
        return imageRepository.findAll();

    }



    public void saveProduct(Product product, MultipartFile file1) throws IOException {

        Image image1 = new Image();

        if (file1.getSize() != 0){
            image1 = toImageEntity(file1, product);
            image1.setPreviewImage(true);

            product.addImageToProduct(image1);
        }
        log.info("Saved new Product. Title: {};", product.getTitle());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());

        productRepository.save(product);
    }

    public Image toImageEntity(MultipartFile file, Product product) throws IOException {
        Image image = new Image();
        image.setName(product.getTitle());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());

        return image;
    }



    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id){

        return productRepository.findById(id).orElse(null);
    }
    public Image getImageById(Long id){
        return imageRepository.findById(id).orElse(null);
    }
}
