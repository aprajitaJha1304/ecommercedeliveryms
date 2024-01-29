package com.example.deliveryms.service;// DeliveryServiceImpl.java
import com.example.deliveryms.dtos.DeliveryLocationMessageDTO;
import com.example.deliveryms.dtos.DeliveryMessageDTO;
import com.example.deliveryms.dtos.DeliveryStatus;
import com.example.deliveryms.entity.Delivery;
import com.example.deliveryms.exceptions.NoSuchDeliveryFoundFoundException;
import com.example.deliveryms.repo.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private StreamBridge streamBridge;

    @Override
    public void addOrUpdateDeliveryStatus(DeliveryMessageDTO deliveryDto) {

        Delivery delivery= deliveryRepository.findById(Long.valueOf(deliveryDto.getDeliveryID())).orElseThrow(()->new NoSuchDeliveryFoundFoundException("No corresponding delivery found for the order"));
        delivery.setDeliveryStatus(DeliveryStatus.valueOf(deliveryDto.getStatus()));
        deliveryRepository.save(delivery);

        Message<DeliveryMessageDTO> message= MessageBuilder.withPayload(deliveryDto)
                .setHeader(KafkaHeaders.KEY,deliveryDto.getOrderID().getBytes())
                .build();


        // Send to Kafka topic using Stream Bridge
        streamBridge.send("deliveries-out", message);
    }

    @Override
    public void addOrUpdateDeliveryLocation(DeliveryLocationMessageDTO deliveryLocDto) {
        Delivery delivery= deliveryRepository.findById(Long.valueOf(deliveryLocDto.getDeliveryID())).orElseThrow(()->new NoSuchDeliveryFoundFoundException("No corresponding delivery found for the order"));
        delivery.setDeliveryAgentLocation("Updated Location");
        deliveryRepository.save(delivery);

        Message<DeliveryLocationMessageDTO> message= MessageBuilder.withPayload(deliveryLocDto)
                .setHeader(KafkaHeaders.KEY,deliveryLocDto.getOrderID().getBytes())
                .build();


        // Send to Kafka topic using Stream Bridge
        streamBridge.send("delivery-locations-out", message);
    }

    @Override
    public Delivery getDeliveryInformation(Long deliveryId) {
        // Retrieve delivery information from the database
        return deliveryRepository.findById(deliveryId).orElse(null);
    }
}
