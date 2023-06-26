package com.houseowner.property.services;

import com.houseowner.property.DTOs.MessageDTO;
import com.houseowner.property.adapters.databaseAdapter.repository.MessageRepository;
import com.houseowner.property.aggregates.valueObjects.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

    private final MessageRepository messageRepository;


    public MessageService(MessageRepository messageRepository)
    {
        this.messageRepository = messageRepository;
    }


    public void createMessage(MessageDTO messageDTO) {

        Message message = new Message();

        Mono<Message> messageToSave = message.createMessage(messageDTO);

        messageToSave.flatMap(messageRepository::save);
    }
}
