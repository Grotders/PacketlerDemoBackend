package com.oguzcan.paketlerdemo.repository;

import com.oguzcan.paketlerdemo.entities.Packet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacketRepository extends JpaRepository<Packet, Long> {
}
