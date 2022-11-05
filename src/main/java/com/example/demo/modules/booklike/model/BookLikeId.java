package com.example.demo.modules.booklike.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BookLikeId implements Serializable {
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @Column(name="book_id")
    private Integer bookRid;

    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @Column(name="user_id")
    private Integer userRid;
}
