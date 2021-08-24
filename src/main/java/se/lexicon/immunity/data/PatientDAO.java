package se.lexicon.immunity.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.immunity.model.entity.Patient;

public interface PatientDAO extends JpaRepository<Patient, String> {
}
