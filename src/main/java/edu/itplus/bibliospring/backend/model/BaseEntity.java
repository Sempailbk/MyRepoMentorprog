package edu.itplus.bibliospring.backend.model;

public class BaseEntity extends AbstractModel{
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
