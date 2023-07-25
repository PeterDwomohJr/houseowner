package com.houseowner.edge.events.publishers;

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
public class OTPEventPublisher {

    private final ReactivePulsarClient reactivePulsarClient;
    private static final String PULSAR_SERVICE_URL = "pulsar://localhost:6650";
    private static final String TOPIC_NAME = "otp-topic";
    private static final int MAX_IN_FLIGHT = 100;



    public OTPEventPublisher() throws PulsarClientException {
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(PULSAR_SERVICE_URL)
                .build();

        this.reactivePulsarClient = AdaptedReactivePulsarClientFactory.create(pulsarClient);
    }



    public void publishOTPToBroker(OTPCreatedEvent otpCreatedEvent)
    {
        ReactiveMessageSender<OTPCreatedEvent> messageSender = reactivePulsarClient
                .messageSender(Schema.JSON(OTPCreatedEvent.class))
                .cache(AdaptedReactivePulsarClientFactory.createCache())
                .topic(TOPIC_NAME)
                .maxInflight(MAX_IN_FLIGHT)
                .build();

        messageSender.sendOne(MessageSpec.of(otpCreatedEvent)).subscribe();

    }
}
