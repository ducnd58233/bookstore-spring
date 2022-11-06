package com.example.demo.modules.user.model;

import com.example.demo.common.BaseEntity;
import com.example.demo.common.UID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static com.example.demo.common.Constants.*;

@Getter @Setter
@Entity
@Table(name="users")
public class UserEntity extends BaseEntity {
    @Column(name="email")
    private String email;

    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @Column(name="password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="phone")
    private String phone;

    @Column(name="role")
    private String role = ROLE_USER;

    @Column(name="status")
    private int status = STATUS_ACTIVE;

    public String getUid() {
        UID val = new UID(this.getId(), DbTypeUser, ShardTypeUser);
        super.setUid(String.valueOf(val));
        return String.valueOf(val);
    }
}
