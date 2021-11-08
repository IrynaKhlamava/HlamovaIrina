package com.company.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role extends AEntity implements GrantedAuthority {

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleValue value;

    @Override
    public String getAuthority() {
        return getValue().toString();
    }

    public enum RoleValue {
        USER, ADMIN;
    }

    public Role() {
    }

    public Role(RoleValue value) {
        this.value = value;
    }

    public RoleValue getValue() {
        return value;
    }

    public void setValue(RoleValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Role{" +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return value == role.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
