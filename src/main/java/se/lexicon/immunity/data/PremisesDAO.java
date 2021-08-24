package se.lexicon.immunity.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.immunity.model.entity.Premises;

public interface PremisesDAO extends JpaRepository<Premises, String> {
}
