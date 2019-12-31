package objets_metiers;

import Utility.BibalExceptions;

/**
 * 
 * @author Aymen Souelmi
 */
public class Magazine extends Oeuvre {

    private int lending;

    public Magazine() {
        this.lending = 30;
        this.nbResa = 0;
    }

    public Magazine(String titre, String auteur, String categorie) {
        this();
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
    }

    public Magazine(int id, String titre, String auteur, String categorie) {
        this(titre, auteur, categorie);
        this.id = id;
    }

    public Magazine(int id, String titre, String auteur, String categorie, int nbResa) {
        this(id, titre, auteur, categorie);
        this.nbResa = nbResa;
    }

    public int getLending() {
        return this.lending;
    }

    public void setLending(int lending) throws BibalExceptions {
        if (lending < 30) {
            throw new BibalExceptions("La durée d'emprunt des Magazines "
                    + "doit être superieure ou égale à 30 jours");
        }
        this.lending = lending;
    }

    @Override
    public String toString() {
        return "MAGAZINE{" + super.toString() + "lending=" + lending + '}';
    }

}
