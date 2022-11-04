package com.example.demo.modules.book.model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookReq {
    private Long id;
    private String title;
    private int year;
    private String description;
    private double price;
    private int status;
}
