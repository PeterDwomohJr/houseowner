package com.houseowner.property.services;

import com.houseowner.property.DTOs.MessageDTO;
import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.DTOs.UpdateMessageDTO;
import com.houseowner.property.adapters.databaseAdapter.repository.MessageRepository;
import com.houseowner.property.adapters.databaseAdapter.repository.PropertyRepository;
import com.houseowner.property.aggregates.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final PropertyService propertyService;
    @Autowired
    private PropertyRepository propertyRepository;


    public MessageService(MessageRepository messageRepository, PropertyService propertyService)
    {
        this.messageRepository = messageRepository;
        this.propertyService = propertyService;
    }


    public void createMessage(MessageDTO messageDTO) {

        Message message = new Message();

        Mono<Message> messageToSave = message.createMessage(messageDTO);

        // get the property which is associated with the message
        Mono<PropertyDTO> propertyMono = propertyService.getProperty(messageDTO.getPropertyId());

        // set the newly created message to the property object


        // save the newly created message in its own repository

        // save the property back into the repository

    }


    public Mono<Message> getMessage(String id)
    {
        return messageRepository.findById(id);
    }



    public Flux<Message> listMessages()
    {
        return messageRepository.findAll();
    }

    public void updateMessage(UpdateMessageDTO updateMessageDTO)
    {
        // retrieve the property which is associated with the message from the repository
        Mono<PropertyDTO> propertyMono = propertyService.getProperty(updateMessageDTO.getPropertyId());

       // retrieve the message which has been saved and is to updated from the repository
        Mono<Message> messageMono = messageRepository.findById(updateMessageDTO.getMessageId());

        // update the message with the new message content
        messageMono.flatMap(message -> {

            message.updateMessage(updateMessageDTO.getNewContent());
            return messageMono;
        });

        // set the new message content to the property object


        // save the updated message back into the repository
       messageMono.flatMap(messageRepository::save);

        // save the updated property back into the repository

    }


    public void deleteMessage(String id)
    {
        // soft delete the message object
        Mono<Message> messageMono = messageRepository.findById(id);
        messageMono.flatMap(message -> {
            message.setDeleted(true);
            return messageMono;
        });

        // save the updated message back into the repository
        messageMono.flatMap(messageRepository::save);
    }

}
