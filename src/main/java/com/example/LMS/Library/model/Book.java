package com.example.LMS.Library.model;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String category;

    // --- Puthusa add panna Rate (Price) ---
    private Double price;
    // --------------------------------------

    private String status; // 'AVAILABLE' or 'ISSUED'

    // Generate Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    // --- Price Getters and Setters ---
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    // ---------------------------------

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}