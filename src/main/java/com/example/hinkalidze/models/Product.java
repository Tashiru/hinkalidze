package com.example.hinkalidze.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private int price;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "product")//  //


    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dataOfCreated;

    public Product() {
    }

    @PrePersist
    private void init() {
        dataOfCreated = LocalDateTime.now();
    }

    public void addImageToProduct(Image image) {
        image.setProduct(this);
        images.add(image);
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public List<Image> getImages() {
        return this.images;
    }

    public Long getPreviewImageId() {
        return this.previewImageId;
    }

    public LocalDateTime getDataOfCreated() {
        return this.dataOfCreated;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setPreviewImageId(Long previewImageId) {
        this.previewImageId = previewImageId;
    }

    public void setDataOfCreated(LocalDateTime dataOfCreated) {
        this.dataOfCreated = dataOfCreated;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Product)) return false;
        final Product other = (Product) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        if (this.getPrice() != other.getPrice()) return false;
        final Object this$images = this.getImages();
        final Object other$images = other.getImages();
        if (this$images == null ? other$images != null : !this$images.equals(other$images)) return false;
        final Object this$previewImageId = this.getPreviewImageId();
        final Object other$previewImageId = other.getPreviewImageId();
        if (this$previewImageId == null ? other$previewImageId != null : !this$previewImageId.equals(other$previewImageId))
            return false;
        final Object this$dataOfCreated = this.getDataOfCreated();
        final Object other$dataOfCreated = other.getDataOfCreated();
        if (this$dataOfCreated == null ? other$dataOfCreated != null : !this$dataOfCreated.equals(other$dataOfCreated))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Product;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        result = result * PRIME + this.getPrice();
        final Object $images = this.getImages();
        result = result * PRIME + ($images == null ? 43 : $images.hashCode());
        final Object $previewImageId = this.getPreviewImageId();
        result = result * PRIME + ($previewImageId == null ? 43 : $previewImageId.hashCode());
        final Object $dataOfCreated = this.getDataOfCreated();
        result = result * PRIME + ($dataOfCreated == null ? 43 : $dataOfCreated.hashCode());
        return result;
    }

    public String toString() {
        return "Product(id=" + this.getId() + ", title=" + this.getTitle() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ", images=" + this.getImages() + ", previewImageId=" + this.getPreviewImageId() + ", dataOfCreated=" + this.getDataOfCreated() + ")";
    }
}
