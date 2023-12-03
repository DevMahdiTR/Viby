package com.chefme.chefme.model.point;


import com.chefme.chefme.model.business.Business;
import com.chefme.chefme.model.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "points")
public class Point {

    @SequenceGenerator(
            name = "points_sequence",
            sequenceName = "points_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "points_sequence")
    @Id
    @Column(name = "id" ,unique = true , nullable = false)
    private long id;

    @Column(name = "total_points" , nullable = false)
    private long totalPoints;

    @OneToOne
    private Business business;

    @OneToOne
    private Client client;

    public Point(long totalPoints, Business business, Client client) {
        this.totalPoints = totalPoints;
        this.business = business;
        this.client = client;
    }
}
