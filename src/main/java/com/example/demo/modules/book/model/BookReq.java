package com.example.demo.modules.book.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookReq {
    private Long id;
    private String title;
    private Integer year;
    private String description;
    private Double price;
    private int status;
}
