package com.example.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {

    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue
    private int id;

    @RestResource
    @ElementCollection(fetch = FetchType.EAGER)
    @OneToMany
    private List<Image> images = new ArrayList<>();

    // other attributes....
}