package objets_metiers;

import Utility.BibalExceptions;
import Utility.DBConnection;
import static Utility.Utility.closeStatement;
import static Utility.Utility.closeStatementResultSet;
import static Utility.Utility.dateToStr;
import static Utility.Utility.initialiseRequetePreparee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Aymen Souelmi
 */
public class Usager {

    private int id;
    private String nom;
    private String prenom;
    private Date dateNais;
    private String sexe;
    private String adresse;
    private String tel;
    private Vector<Emprunt> empruntsUsager = new Vector<Emprunt>();
    private Vector<Reservation> reservationsUsager = new Vector<Reservation>();

    public Usager() {
    }

    public Usager(String nom, String prenom, Date dateNais,
            String sexe, String adresse, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNais = dateNais;
        this.sexe = sexe;
        this.adresse = adresse;
        this.tel = tel;

    }

    public Usager(int id, String nom, String prenom, Date dateNais,
            String sexe, String adresse, String tel) {
        this(nom, prenom, dateNais, sexe, adresse, tel);
        this.id = id;

    }

    public Usager(Usager usager) {
        this(usager.id, usager.nom, usager.prenom, usager.dateNais,
                usager.sexe, usager.adresse, usager.tel);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws BibalExceptions {
        if (id <= 0) {
            throw new BibalExceptions("Identifiant Usager non valide !");
        }
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) throws BibalExceptions {
        if (null != nom) {
            if (nom.length() < 2) {
                throw new BibalExceptions("Le nom de l'usager doit contenir "
                        + "au moins 3 caractères");
            }
        } else {
            throw new BibalExceptions("Merci de saisir le nom de l'usager");
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) throws BibalExceptions {
        if (null != prenom) {
            if (prenom.length() < 3) {
                throw new BibalExceptions("Le prénom de l'usager doit contenir "
                        + "au moins 3 caractères");
            }
        } else {
            throw new BibalExceptions("Merci de saisir le prénom de l'usager");
        }
        this.prenom = prenom;
    }

    public Date getDateNais() {
        return dateNais;
    }

    public void setDateNais(Date dateNais) {
        this.dateNais = dateNais;
    }

    public String getSexe() {
        return this.sexe;
    }

    public void setSexe(String sexe) throws BibalExceptions {
        if (null == sexe) {
            throw new BibalExceptions("Merci de préciser le sexe de l'usager");
        }
        this.sexe = sexe;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) throws BibalExceptions {
        if (null != adresse) {
            if (adresse.length() < 5) {
                throw new BibalExceptions("L'adresse doit contenir "
                        + "au moins 10 caractères");
            }
        } else {
            throw new BibalExceptions("Merci de renseigner l'adresse de l'usager");
        }
        this.adresse = adresse;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) throws BibalExceptions {
        if (null != tel) {
            if (tel.length() > 8) {
                throw new BibalExceptions("Le numero de téléphone doit se composer \n"
                        + "au moins de 8 chiffres");
            }
        } else {
            throw new BibalExceptions("Merci de saisir un numéro de téléphone ");
        }
        this.tel = tel;
    }

    public void ajouter(Usager usager) throws BibalExceptions {
        final String SQL_INSERT = "INSERT INTO usager "
                + "(Nom, Prenom, DateNais, Sexe, Adresse,Tel ) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            String formatedDateNais = dateToStr(usager.getDateNais());
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), SQL_INSERT,
                    usager.getNom().toUpperCase(), usager.getPrenom(),
                    formatedDateNais, usager.getSexe(),
                    usager.getAdresse(), usager.getTel());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new BibalExceptions("Erreurs lors de l'ajout d'un usager");
            }

        } catch (SQLException | BibalExceptions e) {
            throw new BibalExceptions("Erreurs lors de l'ajout d'un usager", e.getCause());
        } finally {
            closeStatement(preparedStatement);
        }
    }

    public void modifier(Usager usager) throws BibalExceptions {
        final String SQL_UPDATE = "UPDATE usager "
                + " SET Nom = ?, Prenom = ?, DateNais = ?, Sexe = ?, Adresse = ?,Tel = ? "
                + "WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            String formatedDateNais = dateToStr(usager.getDateNais());
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), SQL_UPDATE,
                    usager.getNom().toUpperCase(), usager.getPrenom(),
                    formatedDateNais, usager.getSexe(),
                    usager.getAdresse(), usager.getTel(),
                    usager.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new BibalExceptions("Echec de la mise à jour de l'usager");
            }
        } catch (SQLException | BibalExceptions e) {
            throw new BibalExceptions("Erreurs lors de la mise à jour de l'usager ", e.getCause());
        } finally {
            closeStatement(preparedStatement);
        }
    }

    public void delete(Usager usager) throws BibalExceptions {
        final String SQL_DELETE_BY_ID = "DELETE FROM usager WHERE id = ? ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), SQL_DELETE_BY_ID,
                    usager.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new BibalExceptions("Echec de la suppression de l'usager");
            }
        } catch (SQLException | BibalExceptions e) {
            throw new BibalExceptions("Erreurs lors de la suppression de l'usager ", e.getCause());
        } finally {
            closeStatement(preparedStatement);
        }
    }

    /**
     * Methode qui donnne la liste de tous les usagers
     *
     * @return
     * @throws BibalExceptions
     */
    public ArrayList<Usager> getListUsagers() throws BibalExceptions {
        final String SQL_SELECT = "SELECT * FROM usager ORDER BY id";
        return find(SQL_SELECT, new Object[0]);
    }

    /**
     * Chercher un usager par id
     *
     * @param id
     * @return
     * @throws BibalExceptions
     */
    public Usager findById(int id) throws BibalExceptions {
        final String SQL_SELECT_BY_ID = "SELECT * FROM usager WHERE id = ?";
        ArrayList<Usager> usagers = find(SQL_SELECT_BY_ID, id);
        return (usagers == null) ? null : usagers.get(0);
    }

    /**
     * Chercher les usager par nom
     *
     * @param nom
     * @return
     * @throws BibalExceptions
     */
    public ArrayList<Usager> findByNom(String nom) throws BibalExceptions {
        final String SQL_SELECT_BY_NOM = "SELECT * FROM usager WHERE Nom = ?";
        ArrayList<Usager> usagers = find(SQL_SELECT_BY_NOM, nom);
        return (usagers == null) ? null : usagers;
    }

    private ArrayList<Usager> find(String sql, Object... objets) throws BibalExceptions {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Usager> listUsagers = new ArrayList<>();

        try {
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), sql, objets);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listUsagers.add(mappingUsager(resultSet));
            }
        } catch (SQLException e) {
            throw new BibalExceptions("Aucun enregistrement trouvé " + e.getMessage());
        } finally {
            closeStatementResultSet(preparedStatement, resultSet);
        }

        return listUsagers;
    }

    private static Usager mappingUsager(ResultSet resultSet) throws SQLException {

        Usager usager = new Usager();
        try {
            usager.setId(resultSet.getInt("id"));
            usager.setNom(resultSet.getString("nom"));
            usager.setPrenom(resultSet.getString("prenom"));
            usager.setDateNais(resultSet.getDate("dateNais"));
            usager.setSexe(resultSet.getString("sexe"));
            usager.setAdresse(resultSet.getString("adresse"));
            usager.setTel(resultSet.getString("tel"));
        } catch (BibalExceptions e) {
            System.out.println(e.getMessage());
        }
        return usager;
    }

    @Override
    public String toString() {
        return "Usager{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom
                + ", dateNais=" + dateNais + ", sexe=" + sexe + ", adresse="
                + adresse + ", tel=" + tel + '}';
    }

}
