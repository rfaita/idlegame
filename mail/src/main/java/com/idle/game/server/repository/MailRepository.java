package com.idle.game.server.repository;

import com.idle.game.model.Mail;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafael
 */
@Repository
public interface MailRepository extends MongoRepository<Mail, String> {

    public List<Mail> findAllByToUserId(String toUserId);

    public List<Mail> findAllByPublicMail(Boolean publicMail);

    public Mail findByIdAndToUserId(String id, String toUserId);

    public Mail findByIdAndToUserIdAndCollected(String id, String toUserId, Boolean collected);

}
