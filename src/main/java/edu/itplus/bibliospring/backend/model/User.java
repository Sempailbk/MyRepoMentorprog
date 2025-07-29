package edu.itplus.bibliospring.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class User extends BaseEntity{
    @Column(name="username",nullable = false,unique = true,length = 25)
    private String username;

    @Column (name="password",nullable = false,unique = true,length = 64)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() {
        return getUUID()+", " +getId()+", "+ getUsername()+", "+ getPassword();
    }


}
