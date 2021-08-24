package se.lexicon.immunity.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.immunity.model.entity.ContactInfo;

public interface ContactInfoDAO extends JpaRepository<ContactInfo, String> {
}
