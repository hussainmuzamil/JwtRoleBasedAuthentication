package com.example.task1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;

@Entity
@Data
@Table(name = "delivery")
public class Delivery {
    @Id
    private int stopNo;
    private String trackingId;
    private Double time;
    private String arrival;
    private String timeWind;
    private String customerAddress;
    private String postal;
    private String signature;
    private String customer;
    private Double latitude;
    private Double longitude;
    private String place;
    private Double cubicFt;
    private String zone;
}
