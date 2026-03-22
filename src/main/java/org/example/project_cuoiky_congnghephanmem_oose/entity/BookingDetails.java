package org.example.project_cuoiky_congnghephanmem_oose.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "BookingDetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private double subTotal;

    @ManyToOne
    @JoinColumn(name = "bookingID")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "roomID")
    private Rooms room;

    public double calculateSubTotal() {
        return 0;
    }
}