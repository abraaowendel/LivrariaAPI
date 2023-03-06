package com.api.livraria.dto;

import com.api.livraria.entities.Publisher;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class PublisherDTO implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;

    @NotBlank(message = "Nome da editora não pode ser vazio ou nulo.")
    private String name;

    public PublisherDTO() {
    }

    public PublisherDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public PublisherDTO(Publisher entity) {
        id = entity.getId();
        name = entity.getName();
    }

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
}
