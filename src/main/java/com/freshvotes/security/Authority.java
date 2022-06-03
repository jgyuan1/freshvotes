package com.freshvotes.security;

import com.freshvotes.domain.User;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serial;
@Entity
public class Authority implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = 1272548942962614584L;
    private Long id;
    private String authority;
    private User user;

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne()
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
