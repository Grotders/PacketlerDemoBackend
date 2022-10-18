package com.oguzcan.paketlerdemo.entities;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer extends User{


    @Embedded
    private PersonalInformation personalInformation;

    @Embedded
    private PacketBalance packetBalance;

    @ManyToOne
    @JoinColumn(name = "packet_id")
    private Packet packet;

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public PacketBalance getPacketBalance() {
        return packetBalance;
    }

    public void setPacketBalance(PacketBalance packetBalance) {
        this.packetBalance = packetBalance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "personalInformation=" + personalInformation +
                '}';
    }
}
