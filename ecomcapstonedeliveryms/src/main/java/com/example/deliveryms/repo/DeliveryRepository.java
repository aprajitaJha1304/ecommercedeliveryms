package com.example.deliveryms.repo;// DeliveryRepository.java
import com.example.deliveryms.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    // Add custom query methods if needed
}
