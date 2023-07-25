package com.houseowner.edge.events.handlers;

import com.houseowner.edge.dto.OTPCreatedEventDTO;
import com.houseowner.edge.services.OTPRepositoryService;
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
public class OTPEventHandler {

    private final ReactivePulsarClient reactivePulsarClient;
    private final OTPRepositoryService otpRepositoryService;
    private static final String PULSAR_SERVICE_URL = "pulsar://localhost:6650";
    private static final String SUBSCRIPTION_NAME = "local-otp-subscription";
    private static final String TOPIC_NAME = "otp-topic";



    public OTPEventHandler(OTPRepositoryService otpRepositoryService) throws PulsarClientException
    {
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(PULSAR_SERVICE_URL)
                .build();

        this.reactivePulsarClient = AdaptedReactivePulsarClientFactory.create(pulsarClient);

        this.otpRepositoryService = otpRepositoryService;
    }



    public void receiveOTP()
    {
        ReactiveMessageConsumer<OTPCreatedEventDTO> messageConsumer = reactivePulsarClient
                .messageConsumer(Schema.JSON(OTPCreatedEventDTO.class))
                .topic(TOPIC_NAME)
                .subscriptionName(SUBSCRIPTION_NAME)
                .build();

        messageConsumer.consumeMany(messageFlux ->
                        messageFlux.map(message ->
                                MessageResult.acknowledge(message.getMessageId(), message.getValue())))
                .doOnNext(otp -> otpCommand(otp.getPhoneNumber(), otp.getOtpString()))
                .subscribe();
    }


    private void otpCommand(String phoneNumber, String otpString)
    {
        OTPCreatedEventDTO otpCreatedEventDTO = new OTPCreatedEventDTO();

        otpCreatedEventDTO.setPhoneNumber(phoneNumber);
        otpCreatedEventDTO.setOtpString(otpString);

        Mono<OTPCreatedEventDTO> otpCreatedEventDTOMono = Mono.just(otpCreatedEventDTO);

        otpCreatedEventDTOMono.subscribe(System.out::println);

        otpRepositoryService.saveOTP(otpCreatedEventDTOMono).subscribe();

    }
}

