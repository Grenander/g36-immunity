package se.lexicon.immunity.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.immunity.model.entity.Address;

public interface AddressDAO extends JpaRepository<Address, String> {
}
