package io.swagger.services.dto;

import java.util.Objects;

public abstract class BaseDTO {

    private Long id;

    // Default constructor
    public BaseDTO() {}

    // Constructor with id
    public BaseDTO(Long id) {
        this.id = id;
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDTO baseDTO = (BaseDTO) o;
        return Objects.equals(id, baseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseDTO{" +
                "id=" + id +
                '}';
    }
}
