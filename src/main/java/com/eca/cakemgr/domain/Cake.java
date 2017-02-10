package com.eca.cakemgr.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cake implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String desc;

    @Column(nullable = false, length = 1000)
    private String image;

    protected Cake() {
    }

    public Cake(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cake cake = (Cake) o;

        if (!getId().equals(cake.getId())) return false;
        if (!getTitle().equals(cake.getTitle())) return false;
        if (!getDesc().equals(cake.getDesc())) return false;
        return getImage().equals(cake.getImage());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getDesc().hashCode();
        result = 31 * result + getImage().hashCode();
        return result;
    }

}