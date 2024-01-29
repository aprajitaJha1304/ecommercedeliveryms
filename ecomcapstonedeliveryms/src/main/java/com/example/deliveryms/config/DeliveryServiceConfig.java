package com.example.deliveryms.config;

import com.example.deliveryms.dtos.DeliveryStatus;
import com.example.deliveryms.dtos.OrderInfo;
import com.example.deliveryms.entity.Delivery;
import com.example.deliveryms.repo.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.function.Consumer;

@Configuration
public class DeliveryServiceConfig {
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Bean
    public Consumer<OrderInfo> processOrderInfo() {
        return orderInfo -> {
            // Process the received OrderInfo

            System.out.println("Received OrderInfo: " + orderInfo);

            Delivery delivery = new Delivery();
            delivery.setCustomerId(orderInfo.getCustomerId());
            delivery.setOrderInfo(orderInfo);
            delivery.setDeliveryStatus(DeliveryStatus.NOTDISPATCHED); // Assuming default status

            // Save the Delivery entity to the database
            deliveryRepository.save(delivery);
        };
    }
}
