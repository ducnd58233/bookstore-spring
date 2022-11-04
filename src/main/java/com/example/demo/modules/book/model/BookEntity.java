package com.example.demo.modules.book.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter @Setter
@Entity
@Table(name="books")
public class BookEntity {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="year")
    private Integer year;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private Double price;

    @Column(name="status")
    private int status;

    @CreationTimestamp
    @Column(name="created_at", nullable=false, updatable=false, columnDefinition="TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", columnDefinition="TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;
}
