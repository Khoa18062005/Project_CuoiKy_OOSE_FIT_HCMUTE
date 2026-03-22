package org.example.project_cuoiky_congnghephanmem_oose.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "RoomTypes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeID;
    private String typeName;
    private double priceRoom;
    private int occupancy;

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    private List<RoomTypeBeds> roomTypeBeds;

    public List<Rooms> getRoomsByType() {
        return null;
    }
}