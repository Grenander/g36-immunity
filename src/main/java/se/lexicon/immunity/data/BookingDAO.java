package se.lexicon.immunity.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.immunity.model.entity.Booking;

public interface BookingDAO extends JpaRepository<Booking, String> {
}
