package com.houseowner.edge.events.publishers;

import com.houseowner.edge.dto.DTO;
import com.houseowner.edge.events.Domain.OTPCreatedEvent;
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



    public void publishToBroker(DTO otpCreatedEvent, String topic)
    {
        ReactiveMessageSender<DTO> messageSender = reactivePulsarClient
                .messageSender(Schema.JSON(DTO.class))
                .cache(AdaptedReactivePulsarClientFactory.createCache())
                .topic(topic)
                .maxInflight(MAX_IN_FLIGHT)
                .build();

        messageSender.sendOne(MessageSpec.of(otpCreatedEvent)).subscribe();
    }
}
