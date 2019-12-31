package control;

import Utility.BibalExceptions;
import java.util.ArrayList;
import objets_metiers.Exemplaire;
import objets_metiers.Oeuvre;
/**
 * 
 * @author Aymen Souelmi
 */
public class ExemplaireControl {

    public static void ajouter(Oeuvre oeuvre, String etatExemplaire) throws BibalExceptions {
         oeuvre = oeuvre.findById(oeuvre.getId());
        if (null == oeuvre) {
            throw new BibalExceptions("L'oeuvre n'existe pas");
        }
        Exemplaire exemplaire = new Exemplaire();
        exemplaire.ajouter(oeuvre, etatExemplaire);
    }

    public static void modifier(Exemplaire exemplaire) throws BibalExceptions {
        exemplaire.setId(exemplaire.getId());
        exemplaire.setEtat(exemplaire.getEtat());

        exemplaire.modifier(exemplaire);
    }

    public static void supprimer(Exemplaire exemplaire) throws BibalExceptions {
        exemplaire.delete(exemplaire);
    }

    public static Exemplaire findById(int id) throws BibalExceptions {
        Exemplaire exemplaire = new Exemplaire();
        return exemplaire.findById(id);
    }

    public static ArrayList<Exemplaire> findExemplaireDispo(Oeuvre oeuvre) throws BibalExceptions {
        Exemplaire exemplaire = new Exemplaire();
        return exemplaire.findExemplaireDispo(oeuvre);
    }

    public static ArrayList<Exemplaire> find(Oeuvre oeuvre) throws BibalExceptions {
        Exemplaire exemplaire = new Exemplaire();
        return exemplaire.find(oeuvre);
    }
}
