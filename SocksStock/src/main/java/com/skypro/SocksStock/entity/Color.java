package com.skypro.SocksStock.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "colors")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @OneToMany(mappedBy = "color")
    @ToString.Exclude
    private List<SocksStock> socksStock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Color color = (Color) o;
        return id != null && Objects.equals(id, color.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
