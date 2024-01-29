package com.example.deliveryms.controllers;// DeliveryController.java
import com.example.deliveryms.dtos.DeliveryLocationMessageDTO;
import com.example.deliveryms.dtos.DeliveryMessageDTO;
import com.example.deliveryms.entity.Delivery;
import com.example.deliveryms.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateDeliveryStatus(@RequestBody DeliveryMessageDTO deliveryDto) {
        deliveryService.addOrUpdateDeliveryStatus(deliveryDto);
        return ResponseEntity.ok("Delivery status updated successfully");
    }

    @PostMapping("/updateLocation")
    public ResponseEntity<String> updateDeliveryLocation(@RequestBody DeliveryLocationMessageDTO deliveryLocDto) {
        deliveryService.addOrUpdateDeliveryLocation(deliveryLocDto);
        return ResponseEntity.ok("Delivery location updated successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryInformation(@PathVariable Long id) {
        Delivery delivery = deliveryService.getDeliveryInformation(id);
        if (delivery != null) {
            return ResponseEntity.ok(delivery);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
