package com.example.hinkalidze.repositories;


import com.example.hinkalidze.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Image, Long> {
}
