package com.prodev.spring5.webapp.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne
    private Organization parent;


    @OneToMany
    private Set<Organization> children = new HashSet<>();



    public Organization(String name) {
        this.name = name;
    }


    public Organization(String name, Organization parent) {
        this.name = name;
        this.parent = parent;
    }

    public Organization() {

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

    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = parent;
    }

    public Set<Organization> getChildren() {
        return children;
    }

    public void setChildren(Set<Organization> children) {
        this.children = children;
    }

    public void addChild(Organization children) {
        this.children.add(children);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                '}';
    }
}
