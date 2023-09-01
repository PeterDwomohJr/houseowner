package com.dwomo.houseowner.events.publisher;


import com.dwomo.houseowner.dto.PropertyEventDTO;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.reactive.client.adapter.AdaptedReactivePulsarClientFactory;
import org.apache.pulsar.reactive.client.api.MessageSpec;
import org.apache.pulsar.reactive.client.api.ReactiveMessageSender;
import org.apache.pulsar.reactive.client.api.ReactivePulsarClient;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    private final ReactivePulsarClient reactivePulsarClient;
    private static final String PULSAR_SERVICE_URL = "pulsar://localhost:6650";
    private static final int MAX_IN_FLIGHT = 100;



    public EventPublisher() throws PulsarClientException {
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(PULSAR_SERVICE_URL)
                .build();

        this.reactivePulsarClient = AdaptedReactivePulsarClientFactory.create(pulsarClient);
    }



    public void publishToBroker(PropertyEventDTO propertyEventDTO, String topic)
    {
        ReactiveMessageSender<PropertyEventDTO> messageSender = reactivePulsarClient
                .messageSender(Schema.JSON(PropertyEventDTO.class))
                .cache(AdaptedReactivePulsarClientFactory.createCache())
                .topic(topic)
                .maxInflight(MAX_IN_FLIGHT)
                .build();

        messageSender.sendOne(MessageSpec.of(propertyEventDTO)).subscribe();
    }
}
