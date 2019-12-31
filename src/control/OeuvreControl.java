package control;

import Utility.BibalExceptions;
import java.util.ArrayList;
import objets_metiers.Oeuvre;
/**
 * 
 * @author Aymen Souelmi
 */
public class OeuvreControl {

    public static void ajouter(Oeuvre oeuvre) throws BibalExceptions {
        //Verifier la validité des informations
        oeuvre.setTitre(oeuvre.getTitre());
        oeuvre.setAuteur(oeuvre.getAuteur());
        oeuvre.setCategorie(oeuvre.getCategorie());

        oeuvre.ajouter(oeuvre);
    }

    public static void modifier(Oeuvre oeuvre) throws BibalExceptions {
        oeuvre.setId(oeuvre.getId());
        oeuvre.setTitre(oeuvre.getTitre());
        oeuvre.setAuteur(oeuvre.getAuteur());
        oeuvre.setCategorie(oeuvre.getCategorie());
        oeuvre.setNbResa(oeuvre.getNbResa());

        oeuvre.modifier(oeuvre);
    }

    public static void supprimer(Oeuvre oeuvre) throws BibalExceptions {
        oeuvre.delete(oeuvre);
    }
    public static Oeuvre findById(int id) throws BibalExceptions {
        Oeuvre oeuvre = new Oeuvre();
        return oeuvre.findById(id);
    }

    public static ArrayList<Oeuvre> findByTitre(String titre) throws BibalExceptions {
        //Oeuvre oeuvre = new Oeuvre();
        return new Oeuvre().findByTitre(titre);
    }
    public static ArrayList<Oeuvre> getListOeuvres() throws BibalExceptions {
        return new Oeuvre().getListOeuvres();
    }
    
}
