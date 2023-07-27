package com.dwomo.propertydata.events.handlers;

import com.dwomo.propertydata.dto.ConsumeTopicRequestDTO;
import com.dwomo.propertydata.dto.PropertyCreatedEventDTO;
import com.dwomo.propertydata.services.PropertyService;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.reactive.client.adapter.AdaptedReactivePulsarClientFactory;
import org.apache.pulsar.reactive.client.api.MessageResult;
import org.apache.pulsar.reactive.client.api.ReactiveMessageConsumer;
import org.apache.pulsar.reactive.client.api.ReactivePulsarClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PropertyEventHandler {

    private final ReactivePulsarClient reactivePulsarClient;
    private final PropertyService propertyService;
    private static final String PULSAR_SERVICE_URL = "pulsar://localhost:6650";



    public PropertyEventHandler(PropertyService propertyService) throws PulsarClientException {
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(PULSAR_SERVICE_URL)
                .build();

        this.reactivePulsarClient = AdaptedReactivePulsarClientFactory.create(pulsarClient);

        this.propertyService = propertyService;
    }




    public void consumeTopic(ConsumeTopicRequestDTO consumeTopicRequestDTO)
    {
        ReactiveMessageConsumer<PropertyCreatedEventDTO> messageConsumer = reactivePulsarClient
                .messageConsumer(Schema.JSON(PropertyCreatedEventDTO.class))
                .topic(consumeTopicRequestDTO.getTopicName())
                .subscriptionName(consumeTopicRequestDTO.getSubscriptionName())
                .build();

        messageConsumer.consumeMany(messageFlux ->
                        messageFlux.map(message ->
                                MessageResult.acknowledge(message.getMessageId(), message.getValue())))
                .doOnNext(this::messageCommand)
                .subscribe();
    }


    private void messageCommand(PropertyCreatedEventDTO propertyCreatedEventDTO)
    {
        Mono<PropertyCreatedEventDTO> propertyCreatedEventDTOMono = Mono.just(propertyCreatedEventDTO);

        propertyCreatedEventDTOMono.subscribe(System.out::println);

        propertyService.save(propertyCreatedEventDTOMono).subscribe();

    }
}
