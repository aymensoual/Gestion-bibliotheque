package control;

import Utility.BibalExceptions;
import static Utility.Utility.formatDate;
import static Utility.Utility.formatMillisToDate;
import java.util.ArrayList;
import objets_metiers.Emprunt;
import objets_metiers.Exemplaire;
import objets_metiers.Oeuvre;
import objets_metiers.Usager;
/**
 * 
 * @author Aymen Souelmi
 */
public class EmpruntControl {

    public static void emprunter(int id, String titre) throws BibalExceptions {
        Usager usager = new Usager();
        usager = usager.findById(id);
        if (null == usager) {
            throw new BibalExceptions("L'usager n'est pas enregistré");
        }
        Oeuvre oeuvre = new Oeuvre();
        ArrayList<Oeuvre> oeuvres = oeuvre.findByTitre(titre);
        oeuvre = (oeuvres == null) ? null : oeuvres.get(0);
        if (null == oeuvre) {
            throw new BibalExceptions("L'oeuvre n'existe pas");
        }
        
        Exemplaire exemplaire = new Exemplaire();
        ArrayList<Exemplaire> exemplaires = exemplaire.findExemplaireDispo(oeuvre);
        exemplaire = (exemplaires == null) ? null : exemplaires.get(0);
        if(null == exemplaire){
            throw new BibalExceptions("Aucun exemplaire de l'oeuvre '" 
                    +oeuvre.getTitre()+"' n'est disponible\n");
        }
        
        String dateJour = formatMillisToDate(System.currentTimeMillis());
        Emprunt emprunt = new Emprunt();
        emprunt.emprunter(usager, oeuvre, exemplaire, formatDate(dateJour));
        //Vérifier si l'oeuvre est reserver par l'usager si oui annuler
        //la réservation
        if(null != ReservationControl.findByReservation(usager, oeuvre)){
          ReservationControl.annuler(id, titre);
        }
    }
    
    public static void rendre(Emprunt emprunt) throws BibalExceptions {
        emprunt.Rendre(emprunt);
    }
    
    public static void rendre(String nom, int idOeuvre, int idExemplaire) throws BibalExceptions {
        Usager usager = new Usager();
        ArrayList<Usager> usagers = usager.findByNom(nom);
        usager = (usagers == null) ? null : usagers.get(0);
        
        if (null == usager) {
            throw new BibalExceptions("L'usager n'est pas enregistré");
        }
        Oeuvre oeuvre = new Oeuvre();
        oeuvre = oeuvre.findById(idOeuvre);
        if (null == oeuvre) {
            throw new BibalExceptions("L'oeuvre n'existe pas");
        }
        Exemplaire exemplaire = new Exemplaire();
        exemplaire = exemplaire.find(oeuvre, idExemplaire);
        if (null == exemplaire) {
            throw new BibalExceptions("L'exemplaire n'existe pas");
        }
        Emprunt emprunt = new Emprunt().find(usager, exemplaire.getId(), oeuvre);
        if (null == exemplaire) {
            throw new BibalExceptions("L'emprunt n'existe pas");
        }
        emprunt.Rendre(emprunt);
    }
    
    public static ArrayList<Emprunt> findEmprunts(Oeuvre oeuvre) throws BibalExceptions {
        Emprunt emprunt = new Emprunt();
        return emprunt.findEmprunts(oeuvre);
    }
    
    public static void supprimer(Exemplaire exemplaire) throws BibalExceptions {
        new Emprunt().delete(exemplaire);
    }

}
