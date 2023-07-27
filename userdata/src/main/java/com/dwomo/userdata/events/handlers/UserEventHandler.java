package com.dwomo.userdata.events.handlers;

import com.dwomo.userdata.dto.ConsumeTopicRequestDTO;
import com.dwomo.userdata.dto.UserCreatedEventDTO;
import com.dwomo.userdata.services.UserService;
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
public class UserEventHandler {

    private final ReactivePulsarClient reactivePulsarClient;
    private final UserService userService;
    private static final String PULSAR_SERVICE_URL = "pulsar://localhost:6650";



    public UserEventHandler(UserService userService) throws PulsarClientException {
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(PULSAR_SERVICE_URL)
                .build();

        this.reactivePulsarClient = AdaptedReactivePulsarClientFactory.create(pulsarClient);

        this.userService = userService;
    }




    public void ConsumeTopic(ConsumeTopicRequestDTO consumeTopicRequestDTO)
    {
        ReactiveMessageConsumer<UserCreatedEventDTO> messageConsumer = reactivePulsarClient
                .messageConsumer(Schema.JSON(UserCreatedEventDTO.class))
                .topic(consumeTopicRequestDTO.getTopicName())
                .subscriptionName(consumeTopicRequestDTO.getSubscriptionName())
                .build();

        messageConsumer.consumeMany(messageFlux ->
                        messageFlux.map(message ->
                                MessageResult.acknowledge(message.getMessageId(), message.getValue())))
                .doOnNext(this::messageCommand)
                .subscribe();
    }


    private void messageCommand(UserCreatedEventDTO userCreatedEventDTO)
    {
        Mono<UserCreatedEventDTO> userCreatedEventDTOMono = Mono.just(userCreatedEventDTO);

        userCreatedEventDTOMono.subscribe(System.out::println);

        userService.save(userCreatedEventDTOMono).subscribe();

    }
}
