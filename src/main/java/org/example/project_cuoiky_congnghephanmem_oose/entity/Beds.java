package org.example.project_cuoiky_congnghephanmem_oose.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Beds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bedID;
    private String bedName;
    private int capacity;
}