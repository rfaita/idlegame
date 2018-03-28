package com.idle.game.server.service;

/**
 *
 * @author rafael
 */
import com.idle.game.model.Mail;
import com.idle.game.model.Resource;
import com.idle.game.server.dto.Envelope;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idle.game.server.util.Destination;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.idle.game.server.repository.MailRepository;
import java.util.ArrayList;
import com.idle.game.helper.client.resource.UserResourceClient;

@Service
public class MailService {

    @Autowired
    private SimpMessagingTemplate webSocketMessagingTemplate;

    @Autowired
    private UserResourceClient userResourceClient;

    @Autowired
    private MailRepository mailRepository;

    public List<Mail> findAllByToUser(String toUser) {
        return mailRepository.findAllByToUser(toUser);
    }

    public List<Mail> findAllByPublicMail(Boolean publicMail) {
        return mailRepository.findAllByPublicMail(publicMail);
    }

    public void sendPrivateMail(Mail mail) {
        mail = mailRepository.save(mail);

        webSocketMessagingTemplate.convertAndSend(
                Destination.privateMail(mail.getToUserId()),
                mail);

    }

    public void sendPrivateMailError(String user, Envelope<?> env) {
        webSocketMessagingTemplate.convertAndSend(
                Destination.privateMailError(user), env);

    }

    public void sendPublicMail(Mail mail) {

        mail.setPublicMail(Boolean.TRUE);

        mailRepository.save(mail);

    }

    public void markAsReadPrivateMail(String user, String id) {

        Mail mail = mailRepository.findByIdAndToUser(id, user);
        if (mail != null) {
            mail.setReaded(Boolean.TRUE);

            mailRepository.save(mail);

            webSocketMessagingTemplate.convertAndSend(
                    Destination.privateMailUpdate(mail.getToUserId()),
                    mail);

        }
    }

    public void collectReward(String user, String id) {
        Mail mail = mailRepository.findByIdAndToUserAndCollected(id, user, Boolean.FALSE);
        if (mail != null) {

            if (mail.getReward() != null) {
                List<Resource> adds = new ArrayList<>();
                mail.getReward().getRewards().stream().forEach((r) -> {
                    adds.add(new Resource(r.getResource(), r.getValue()));
                });
                userResourceClient.addResources(adds);
            }

            mail.setReaded(Boolean.TRUE);
            mail.setCollected(Boolean.TRUE);

            mail = mailRepository.save(mail);

            webSocketMessagingTemplate.convertAndSend(
                    Destination.privateMailUpdate(mail.getToUserId()),
                    mail);

        }
    }

    public void deletePrivateMail(String user, String id) {
        Mail mail = mailRepository.findByIdAndToUser(id, user);
        if (mail != null && mail.getReaded() && (mail.getReward() == null || mail.getCollected())) {
            mailRepository.delete(mail);

            webSocketMessagingTemplate.convertAndSend(
                    Destination.privateMailDelete(mail.getToUserId()),
                    mail);
        }
    }

}
