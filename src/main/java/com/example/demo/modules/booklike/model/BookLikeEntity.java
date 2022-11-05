package com.example.demo.modules.booklike.model;

import com.example.demo.common.UID;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

import static com.example.demo.common.Constants.DbTypeBook;
import static com.example.demo.common.Constants.ShardTypeBook;

@Getter @Setter
@Entity
@Table(name="book_likes")
public class BookLikeEntity {
    @EmbeddedId
    private BookLikeId bookLikeId;

    @Transient
    private String userId;

    @Transient
    private String bookId;

    @CreationTimestamp
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'hh:mm:ss")
    @Column(name="created_at", nullable=false, updatable=false, columnDefinition="TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    public String getUserId() {
        UID val = new UID(bookLikeId.getUserRid(), DbTypeBook, ShardTypeBook);
        this.setUserId(String.valueOf(val));
        return String.valueOf(val);
    }

    public String getBookId() {
        UID val = new UID(bookLikeId.getBookRid(), DbTypeBook, ShardTypeBook);
        this.setBookId(String.valueOf(val));
        return String.valueOf(val);
    }
}
