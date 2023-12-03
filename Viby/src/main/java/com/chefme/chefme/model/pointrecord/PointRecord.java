package com.chefme.chefme.model.pointrecord;


import com.chefme.chefme.model.business.Business;
import com.chefme.chefme.model.client.Client;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "points_records")
public class PointRecord {


    @SequenceGenerator(
            name = "points_records_sequence",
            sequenceName = "points_records_sequence",
            allocationSize = 1
    )
    @GeneratedValue
    @Id
    @Column(name = "id" , unique = true , nullable = false)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "points_to_transmit" , nullable = false)
    private long pointsToTransmit;

    @Column(name = "is_collected" , nullable = false)
    private boolean isCollected = false;

    @OneToOne
    private Business business;

    @OneToOne
    private Client client;
}
