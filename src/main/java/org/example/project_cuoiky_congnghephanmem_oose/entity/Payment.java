package org.example.project_cuoiky_congnghephanmem_oose.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentID;
    private double amount;
    private String method;
    private LocalDateTime paymentDate;
    private String transactionCode;
    private String status;

    @ManyToOne
    @JoinColumn(name = "bookingID")
    private Booking booking;

    public boolean processPayment() {
        return false;
    }

    public void sendConfirmationEmail() {
    }

    public boolean refund() {
        return false;
    }
}