package com.houseowner.property.aggregates.valueObjects;

import com.houseowner.property.DTOs.MessageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("message")
@Component
public class Message {

    @Id
    private String PropertyId;
    private String content;
    private String senderName;
    private String receiverName;
    private LocalDateTime timestamp;
    private LocalDateTime dateModified;
    private boolean deleted;


    /**
     *
     * @param messageDTO
     * @return
     */
    public Mono<Message> createMessage(MessageDTO messageDTO) {

        Message message = new Message();

        message.setContent(messageDTO.getContent());
        message.setSenderName(messageDTO.getSenderName());
        message.setReceiverName(messageDTO.getReceiverName());
        message.setTimestamp(LocalDateTime.now());
        message.setPropertyId(messageDTO.getPropertyId());
        message.setDeleted(messageDTO.isDeleted());

        return Mono.just(message);
    }


    public void editMessage(String newContent) {

        this.content = content;
        this.dateModified = LocalDateTime.now();
    }

    /**
     * The default deletion is soft deletion.
     */
    public void delete() {

        this.deleted = true;
    }
}
