package com.example.demo;

import java.util.UUID;

import org.springframework.content.commons.repository.ContentStore;
import org.springframework.content.rest.StoreRestResource;
import org.springframework.stereotype.Component;

//@Component
@StoreRestResource(path = "products")
public interface ImageContentStore extends ContentStore<Image, UUID> {
}