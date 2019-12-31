package objets_metiers;

import Utility.BibalExceptions;

/**
 * 
 * @author Aymen Souelmi
 */
public class Livre extends Oeuvre {
    
    private int lending;
    
    public Livre() {
        this.lending = 10;
        this.nbResa = 0;
    }
    
    public Livre(String titre, String auteur, String categorie) {
        this();
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
    }
    
    public Livre(int id, String titre, String auteur, String categorie) {
        this(titre, auteur, categorie);
        this.id = id;
    }

    public Livre(int id, String titre, String auteur, String categorie, int nbResa) {
        this(id, titre, auteur, categorie);
        this.nbResa = nbResa;
    }
    
    public int getLending() {
        return this.lending;
    }
    
    public void setLending(int lending) throws BibalExceptions {
        if (lending < 10) {
            throw new BibalExceptions("La durée d'emprunt des livres "
                    + "doit être superieure ou égale à 10 jours");
        }
        this.lending = lending;
    }
    
    @Override
    public String toString() {
        return "LIVRE{" + super.toString() + "lending=" + lending + '}';
    }
}
