package com.example.demo.modules.authen.model;

import com.example.demo.common.BaseEntity;
import com.example.demo.common.UID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;

import java.util.Collection;
import java.util.Collections;

import static com.example.demo.common.Constants.*;

@Getter @Setter
@Entity
@Table(name="authens")
public class AuthenEntity extends BaseEntity {
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @Transient
    private String role = ROLE_USER;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.getRole()));
    }

    @Column(name="email")
    private String email;

    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    @Column(name="password")
    private String password;

    public String getUid() {
        UID val = new UID(this.getId(), DbTypeUser, ShardTypeUser);
        super.setUid(String.valueOf(val));
        return String.valueOf(val);
    }
}
