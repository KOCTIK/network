package Polytechnic.network.service;

import Polytechnic.network.domain.Message;
import Polytechnic.network.domain.User;
import Polytechnic.network.domain.Views;
import Polytechnic.network.dto.EventType;
import Polytechnic.network.dto.MessagePageDto;
import Polytechnic.network.dto.ObjectType;
import Polytechnic.network.repo.MessageRepo;
import Polytechnic.network.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.BiConsumer;

@Service
public class MessageService {
    private final MessageRepo messageRepo;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageService(MessageRepo messageRepo, WsSender wsSender) {
        this.messageRepo = messageRepo;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.FullMessage.class);
    }

    public void delete(Message message) {
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }

    public Message update(Message messageFromDb, Message message) {

        messageFromDb.setText(message.getText());

        Message updatedMessage = messageRepo.save(messageFromDb);

        wsSender.accept(EventType.UPDATE, updatedMessage);

        return updatedMessage;
    }

    public Message create(Message message, User user) {
        message.setCreationDate(LocalDateTime.now());
        message.setAuthor(user);
        Message updatedMessage = messageRepo.save(message);

        wsSender.accept(EventType.CREATE, updatedMessage);

        return updatedMessage;
    }

    public MessagePageDto findAll(Pageable pageable) {
        Page<Message> page = messageRepo.findAll(pageable);
        return new MessagePageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }
}
