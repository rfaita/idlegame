package com.idle.game.server.service;

/**
 *
 * @author rafael
 */
import com.idle.game.model.mongo.Mail;
import com.idle.game.model.mongo.Reward;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.idle.game.server.util.Destination;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.idle.game.server.repository.MailRepository;

@Service
public class MailService {

    @Autowired
    private SimpMessagingTemplate webSocketMessagingTemplate;

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
                Destination.privateMail(mail.getToUser()),
                mail);

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
        }
    }

    public Reward collectReward(String user, String id) {
        Mail mail = mailRepository.findByIdAndToUserAndCollected(id, user, Boolean.FALSE);
        if (mail != null) {
            mail.setReaded(Boolean.TRUE);
            mail.setCollected(Boolean.TRUE);

            mail = mailRepository.save(mail);

            return mail.getReward();
        }
        return new Reward();
    }

    public void deletePrivateMail(String user, String id) {
        Mail mail = mailRepository.findByIdAndToUser(id, user);
        if (mail != null && mail.getCollected()) {
            mailRepository.delete(mail);
        }
    }

}
