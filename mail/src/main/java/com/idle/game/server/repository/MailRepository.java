package com.idle.game.server.repository;

import com.idle.game.model.mongo.Mail;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface MailRepository extends MongoRepository<Mail, String> {

    public List<Mail> findAllByToUser(String toUser);

    public List<Mail> findAllByPublicMail(Boolean publicMail);

    public Mail findByIdAndToUser(String id, String toUser);

    public Mail findByIdAndToUserAndCollected(String id, String toUser, Boolean collected);

}
