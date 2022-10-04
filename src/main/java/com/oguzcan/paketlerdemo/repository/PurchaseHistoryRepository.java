package com.oguzcan.paketlerdemo.repository;

import com.oguzcan.paketlerdemo.entities.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {

}
