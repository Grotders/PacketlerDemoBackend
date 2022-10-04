package com.oguzcan.paketlerdemo.repository;

import com.oguzcan.paketlerdemo.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
