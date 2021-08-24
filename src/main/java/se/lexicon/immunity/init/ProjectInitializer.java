package se.lexicon.immunity.init;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.immunity.data.PatientDAO;
import se.lexicon.immunity.data.PremisesDAO;
import se.lexicon.immunity.model.demo.Gender;
import se.lexicon.immunity.model.entity.Address;
import se.lexicon.immunity.model.entity.ContactInfo;
import se.lexicon.immunity.model.entity.Patient;
import se.lexicon.immunity.model.entity.Premises;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class ProjectInitializer {

    private final PatientDAO patientDAO;

    private final PremisesDAO premisesDAO;


    public ProjectInitializer(PatientDAO patientDAO, PremisesDAO premisesDAO) {
        this.patientDAO = patientDAO;
        this.premisesDAO = premisesDAO;
    }

    @PostConstruct
    @Transactional
    public void initialize(){
        if(patientDAO.count() == 0){
            createBerit();
        }
        if(premisesDAO.count() == 0){
            createPremises();
        }
    }

    public void createPremises(){
        Premises norr = new Premises(
                "Vårdcentral Norr"
        );
        Premises soder = new Premises(
                "Vårdcentral Söder"
        );

        Address address = new Address(
                "Norrgatan 1",
                "35236",
                "Byhåla"
        );

        Address address2 = new Address(
                "Södergatan 1",
                "34236",
                "Byhåla"
        );

        norr.setAddress(address);
        soder.setAddress(address2);

        premisesDAO.save(norr);
        premisesDAO.save(soder);
    }

    public void createBerit(){
        Patient berit = new Patient(
                "1954-10-12 2562",
                "Berit",
                "Andersson",
                LocalDate.parse("1954-10-12"),
                Gender.FEMALE
        );
        ContactInfo contactInfo = new ContactInfo(
                "berit.andersson@gmail.com",
                "070-1234567"
        );
        berit.setContactInfo(contactInfo);
        patientDAO.save(berit);
    }

}
