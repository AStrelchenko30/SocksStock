package com.skypro.SocksStock.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "colors")
public class Color {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;

@OneToMany(mappedBy = "color")
private List<SocksStock> socksStock;

}
