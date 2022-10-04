package com.oguzcan.paketlerdemo.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "remaining_data")
@Data
public class RemainingData {

    @Id
    private Long userId;

    private Long packetId;
    private double remainingData;
    private double remainingMinutes;
    private double remainingSMS;
    private LocalDate expireDate;
}
