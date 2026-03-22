package org.example.project_cuoiky_congnghephanmem_oose.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "Rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomID;
    private String roomNumber;
    private String status;
    private String description;

    @ManyToOne
    @JoinColumn(name = "typeID")
    private RoomTypes roomType;

    public boolean isAvailable(Date checkin, Date checkout) {
        return false;
    }

    public void updateStatus(String status) {
    }

    public void updateInfo() {
    }
}