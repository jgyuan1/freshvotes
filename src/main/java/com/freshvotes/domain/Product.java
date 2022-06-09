package com.freshvotes.domain;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    private Long id;
    private String name;
    private User user;
    private Set<Feature> features = new HashSet<>();
    private Boolean published;

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // CascadeType:All means that if you delete a product
    // all the associated features will also be deleted
    //Alert: be careful if the feature also use cascade all
    // the whole database may be deleted.
    // What do we use inside of a feature to point back to a product
    // Inside of the Feature, we use the word product to map back to the product
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "product")
    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString(){
        return "productId = " + id + " ,productName = " + name +" ,isPublished = " + published;
    }
}


