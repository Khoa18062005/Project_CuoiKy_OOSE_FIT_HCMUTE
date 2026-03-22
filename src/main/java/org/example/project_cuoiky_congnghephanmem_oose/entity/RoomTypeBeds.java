package org.example.project_cuoiky_congnghephanmem_oose.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RoomTypeBeds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomTypeBeds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "typeID")
    private RoomTypes roomType;

    @ManyToOne
    @JoinColumn(name = "bedID")
    private Beds bed;
}