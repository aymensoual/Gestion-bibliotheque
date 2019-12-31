package objets_metiers;

import Utility.BibalExceptions;
import Utility.DBConnection;
import static Utility.Utility.closeStatement;
import static Utility.Utility.closeStatementResultSet;
import static Utility.Utility.initialiseRequetePreparee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Stream;

/**
 * 
 * @author Aymen Souelmi
 */
public class Oeuvre {

    protected int id;
    protected String titre;
    protected String auteur;
    protected String categorie;
    protected int nbResa;
    protected Vector<Exemplaire> examplairesOeuvre = new Vector<>();
    protected Vector<Reservation> reservationsOeuvre = new Vector<>();

    public int getId() {
        return id;
    }

    public void setId(int id) throws BibalExceptions {
        if (id <= 0) {
            throw new BibalExceptions("Identifiant Oeuvre non valide !");
        }
        this.id = id;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) throws BibalExceptions {
        if (null != categorie) {
            if (categorie.length() < 3) {
                throw new BibalExceptions("Le nom de la catégorie doit contenir "
                        + "au moins 3 caractères");
            }
        } else {
            throw new BibalExceptions("Merci de saisir une catégorie ");
        }
        this.categorie = categorie;
    }

    public String getAuteur() {
        return this.auteur;
    }

    public void setAuteur(String auteur) throws BibalExceptions {
        if (null != auteur) {
            if (auteur.length() < 3) {
                throw new BibalExceptions("Le nom de l'auteur doit avoir "
                        + "au moins 3 caractères");
            }
        } else {
            throw new BibalExceptions("Merci de saisir un nom ");
        }
        this.auteur = auteur;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) throws BibalExceptions {
        if (null != titre) {
            if (titre.length() < 3) {
                throw new BibalExceptions("Le titre doit avoir "
                        + "au moins 3 caractères");
            }
        } else {
            throw new BibalExceptions("Merci de saisir le titre de l'oeuvre ");
        }
        this.titre = titre;
    }

    public int getNbResa() {
        return this.nbResa;
    }

    public void setNbResa(int nbResa) {
        this.nbResa = nbResa;
    }

    public Vector<Exemplaire> getExamplairesOeuvre() {
        return examplairesOeuvre;
    }

    public void setExamplairesOeuvre(Vector<Exemplaire> examplairesOeuvre) {
        this.examplairesOeuvre = examplairesOeuvre;
    }

    public Vector<Reservation> getReservationsOeuvre() {
        return reservationsOeuvre;
    }

    public void setReservationsOeuvre(Vector<Reservation> reservationsOeuvre) {
        this.reservationsOeuvre = reservationsOeuvre;
    }

    //Verifie si l'oeuvre n'est pas déjà ajoutée
    // c'est-à-dire la même oeuvre avec le même auteur
    private boolean oeuvreExiste(Oeuvre oeuvre) throws BibalExceptions {
        ArrayList<Oeuvre> oeuvres = findByTitre(oeuvre.getTitre());
        if (null == oeuvres) {
            return false;
        }
        Stream<Oeuvre> filterOeuvre
                = oeuvres.stream().filter((oeuvre1) -> (oeuvre.getTitre().equals(oeuvre1.getTitre())
                        && oeuvre.getAuteur().equals(oeuvre1.getAuteur())));
        return filterOeuvre.findFirst().isPresent();
    }

    public void ajouter(Oeuvre oeuvre) throws BibalExceptions {

        if (oeuvreExiste(oeuvre)) {
            throw new BibalExceptions("Cette oeuvre est déjà ajoutée");
        }
        final String SQL_INSERT = "INSERT INTO oeuvre "
                + "(Titre, Auteur, Categorie, NbResa, Lending,TypeOeuvre ) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {

            String typeOeuvre = oeuvre.getClass().getSimpleName();
            int lending = 10;
            if (typeOeuvre.equals(Magazine.class.getSimpleName())) {
                lending = 30;
            }
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), SQL_INSERT,
                    oeuvre.getTitre(), oeuvre.getAuteur(),
                    oeuvre.getCategorie(), oeuvre.getNbResa(),
                    lending, typeOeuvre);
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new BibalExceptions("Echec d'ajout de l'oeuvre");
            }

        } catch (SQLException | BibalExceptions e) {
            throw new BibalExceptions("Erreurs lors de l'ajout de l'oeuvre", e.getCause());
        } finally {
            closeStatement(preparedStatement);
        }
    }

    public void modifier(Oeuvre oeuvre) throws BibalExceptions {
        final String SQL_UPDATE = "UPDATE oeuvre "
                + " SET Titre = ?, Auteur = ?, Categorie = ?,TypeOeuvre = ? "
                + "WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            String typeOeuvre = oeuvre.getClass().getSimpleName();
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), SQL_UPDATE,
                    oeuvre.getTitre(), oeuvre.getAuteur(),
                    oeuvre.getCategorie(), typeOeuvre,
                    oeuvre.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new BibalExceptions("Echec de la mise à jour de l'oeuvre");
            }
        } catch (SQLException e) {
            throw new BibalExceptions("Erreurs lors de la mise à jour de l'oeuvre ", e.getCause());
        } finally {
            closeStatement(preparedStatement);
        }
    }

    /**
     * Retourne la liste de toutes les oeuvres
     *
     * @return
     * @throws BibalExceptions
     */
    public ArrayList<Oeuvre> getListOeuvres() throws BibalExceptions {
        final String SQL_SELECT = "SELECT o.*, COUNT(e.oeuvreID) AS nbExemplaire FROM oeuvre o"
                + " LEFT JOIN exemplaire e"
                + " ON o.id = e.oeuvreID"
                + " GROUP BY o.id"
                + " ORDER BY o.id DESC";
        return find(SQL_SELECT, new Object[0]);
    }

    /**
     * Rechercher une oeuvre par id
     *
     * @param id
     * @return
     * @throws BibalExceptions
     */
    public Oeuvre findById(int id) throws BibalExceptions {
        final String SQL_SELECT_BY_ID = "SELECT o.*, COUNT(e.oeuvreID) AS nbExemplaire FROM oeuvre o"
                + " LEFT JOIN exemplaire e"
                + " ON o.id = e.oeuvreID"
                + " WHERE o.id = ?"
                + " GROUP BY o.id"
                + " ORDER BY o.id DESC";
        ArrayList<Oeuvre> oeuvres = find(SQL_SELECT_BY_ID, id);
        return oeuvres.isEmpty() ? null : oeuvres.get(0);
    }

//    public static Oeuvre findByTitre(String titre) throws BibalExceptions {
//        final String SQL_SELECT_BY_TITRE_OEUVRE = "SELECT * FROM oeuvre WHERE titre = ?";
//        ArrayList<Oeuvre> oeuvres = find(SQL_SELECT_BY_TITRE_OEUVRE, titre);
//        return oeuvres.isEmpty() ? null : oeuvres.get(0);
//    }
    public ArrayList<Oeuvre> findByTitre(String titre) throws BibalExceptions {
        
        final String SQL_SELECT_BY_TITRE_OEUVRE = "SELECT o.*, COUNT(e.oeuvreID)"
                + " AS nbExemplaire FROM oeuvre o"
                + " LEFT JOIN exemplaire e"
                + " ON o.id = e.oeuvreID"
                + " WHERE o.titre = ?"
                + " GROUP BY o.id"
                + " ORDER BY o.id DESC";
        ArrayList<Oeuvre> oeuvres = find(SQL_SELECT_BY_TITRE_OEUVRE, titre);
        return (oeuvres == null) ? null : oeuvres;
    }

    private ArrayList<Oeuvre> find(String sql, Object... objets) throws BibalExceptions {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Oeuvre> listOeuvres = new ArrayList<>();

        try {
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), sql, objets);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listOeuvres.add(mappingUsager(resultSet));
            }
        } catch (SQLException e) {
            throw new BibalExceptions("Aucune oeuvre trouvée " + e.getMessage());
        } finally {
            closeStatementResultSet(preparedStatement, resultSet);
        }

        return listOeuvres;
    }

    private static Oeuvre mappingUsager(ResultSet resultSet) throws SQLException {
        Oeuvre oeuvre = null;
        try {
            String typeOeuvre = resultSet.getString("TypeOeuvre");
            if (typeOeuvre.equals(Magazine.class.getSimpleName())) {
                oeuvre = new Magazine();
                ((Magazine) oeuvre).setLending(resultSet.getInt("Lending"));
            } else {
                oeuvre = new Livre();
                ((Livre) oeuvre).setLending(resultSet.getInt("Lending"));
            }
            oeuvre.setId(resultSet.getInt("id"));
            oeuvre.setTitre(resultSet.getString("Titre"));
            oeuvre.setCategorie(resultSet.getString("Categorie"));
            oeuvre.setAuteur(resultSet.getString("Auteur"));
            oeuvre.setNbResa(resultSet.getInt("NbResa"));
            //pour savoir le nombre d'exemplaire on defini la taille du vector 
            oeuvre.examplairesOeuvre.setSize(resultSet.getInt("nbExemplaire"));
        } catch (BibalExceptions e) {
            System.out.println(e.getMessage());
        }
        return oeuvre;
    }
    
    public void delete(Oeuvre oeuvre) throws BibalExceptions {
        final String SQL_DELETE_BY_ID = "DELETE FROM oeuvre WHERE id = ? ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), SQL_DELETE_BY_ID,
                    oeuvre.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new BibalExceptions("Echec de la suppression de l'oeuvre");
            }
        } catch (SQLException | BibalExceptions e) {
            throw new BibalExceptions("Erreurs lors de la suppression de l'oeuvre ", e.getCause());
        } finally {
            closeStatement(preparedStatement);
        }
    }
    
    //trouver les oeuvres dont aucun exemplaire n'est disponible
    /**
     * Vérifie si l'oeuvre n'a aucun examplaire disponible, si oui elle peut
     * donc être réservée
     *
     *
     * @return true si on peut la réservée false sinon
     * @throws BibalExceptions
     */
//    public boolean estReservable() throws BibalExceptions {
//        final String SQL_SELECT_OEUVRE_SANS_EXEMP = "SELECT * FROM oeuvre o"
//                + " WHERE o.id NOT IN ( "
//                + "    SELECT e.oeuvreID FROM exemplaire e"
//                + "    LEFT JOIN emprunt emp"
//                + "    ON e.id = emp.exemplaireID"
//                + "    WHERE emp.ExemplaireID IS NULL"
//                + "    OR dateRetourEffective IS NOT NULL"
//                + "    ORDER BY e.id)";
//        ArrayList<Oeuvre> oeuvres = find(SQL_SELECT_OEUVRE_SANS_EXEMP, new Object[0]);
//        if (oeuvres.isEmpty()) {
//            return false;
//        }
//        Stream<Oeuvre> filterOeuvre
//                = oeuvres.stream().filter((oeuvre1) -> (this.getId() == oeuvre1.getId()));
//
//        return filterOeuvre.findFirst().isPresent();
//    }

    @Override
    public String toString() {
        return "id=" + id + ", titre=" + titre + ", auteur=" + auteur + ", categorie=" + categorie + ", nbResa=" + nbResa + " ";
    }

}
