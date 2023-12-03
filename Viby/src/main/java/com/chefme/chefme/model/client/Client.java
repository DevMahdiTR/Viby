package com.chefme.chefme.model.client;

import com.chefme.chefme.model.point.Point;
import com.chefme.chefme.model.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "clients")
public class Client  extends UserEntity {


    @OneToMany
    private List<Point> points = new ArrayList<Point>();



}
