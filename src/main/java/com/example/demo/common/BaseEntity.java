package com.example.demo.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter @Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Transient
    private String uid;

    @Column(name="status")
    private int status = 1;

    @CreationTimestamp
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'hh:mm:ss")
    @Column(name="created_at", nullable=false, updatable=false, columnDefinition="TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'hh:mm:ss")
    @Column(name="updated_at", columnDefinition="TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime updatedAt;
}
