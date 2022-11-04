package com.example.demo.modules.book.model;

import com.example.demo.common.BaseEntity;
import com.example.demo.common.UID;
import lombok.*;
import javax.persistence.*;

import static com.example.demo.common.Constants.DbTypeBook;
import static com.example.demo.common.Constants.ShardTypeBook;

@Getter @Setter
@Entity
@Table(name="books")
public class BookEntity extends BaseEntity {
    @Column(name="title")
    private String title;

    @Column(name="year")
    private Integer year;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private Double price;

    public String getUid() {
        System.out.println(this.getId());
        UID val = new UID(this.getId(), DbTypeBook, ShardTypeBook);
        super.setUid(String.valueOf(val));
        return String.valueOf(val);
    }
}
