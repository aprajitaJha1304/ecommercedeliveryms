package com.example.deliveryms.service;

import com.example.deliveryms.dtos.DeliveryLocationMessageDTO;
import com.example.deliveryms.dtos.DeliveryMessageDTO;
import com.example.deliveryms.entity.Delivery;

// DeliveryService.java
public interface DeliveryService {

    void addOrUpdateDeliveryStatus(DeliveryMessageDTO deliveryDto);

    void addOrUpdateDeliveryLocation(DeliveryLocationMessageDTO deliveryLocDto);

    Delivery getDeliveryInformation(Long deliveryId);

}
