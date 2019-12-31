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

/**
 * 
 * @author Diallo & Janati
 */
public class Exemplaire {

    private int id;
    private String etat;
    private Vector<Emprunt> empruntsExamplaire = new Vector<Emprunt>();
    private Oeuvre oeuvresExamplaire = new Oeuvre();

    public Exemplaire() {
    }

    public Exemplaire(int id, String etat) {
        this.id = id;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws BibalExceptions {
        if (id <= 0) {
            throw new BibalExceptions("Identifiant Oeuvre non valide !");
        }
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Oeuvre getOeuvresExamplaire() {
        return oeuvresExamplaire;
    }

    public void setOeuvresExamplaire(Oeuvre oeuvresExamplaire) {
        this.oeuvresExamplaire = oeuvresExamplaire;
    }

    public void ajouter(Oeuvre oeuvre, String etatExemplaire) throws BibalExceptions {
        final String SQL_INSERT = "INSERT INTO exemplaire "
                + "( OeuvreID, Etat) VALUES ( ?, ? )";
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), SQL_INSERT,
                    oeuvre.getId(), etatExemplaire);
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new BibalExceptions("Echec d'ajout de l'exemplaire");
            }

        } catch (SQLException | BibalExceptions e) {
            throw new BibalExceptions("Erreurs lors de l'ajout de l'exemplaire " + e.getMessage(), e.getCause());
        } finally {
            closeStatement(preparedStatement);
        }
    }

    public void modifier(Exemplaire exemplaire) throws BibalExceptions {
        final String SQL_UPDATE = "UPDATE exemplaire "
                + " SET etat = ? WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), SQL_UPDATE,
                    exemplaire.getEtat(), exemplaire.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new BibalExceptions("Echec de la mise à jour de l'exemplaire");
            }
        } catch (SQLException e) {
            throw new BibalExceptions("Erreurs lors de la mise à jour de l'exemplaire ", e.getCause());
        } finally {
            closeStatement(preparedStatement);
        }
    }

    public void delete(Exemplaire exemplaire) throws BibalExceptions {
        final String SQL_DELETE_BY_ID = "DELETE FROM exemplaire WHERE id = ? ";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), SQL_DELETE_BY_ID,
                    exemplaire.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new BibalExceptions("Echec de la suppression de l'exemplaire");
            }
        } catch (SQLException | BibalExceptions e) {
            throw new BibalExceptions("Erreurs lors de la suppression de l'exemplaire ", e.getCause());
        } finally {
            closeStatement(preparedStatement);
        }
    }

    public ArrayList<Exemplaire> getAll() throws BibalExceptions {
        final String SQL_SELECT = "SELECT * FROM exemplaire ORDER BY id";
        return find(SQL_SELECT, new Object[0]);
    }

    public Exemplaire findById(int id) throws BibalExceptions {
        final String SQL_SELECT_BY_ID = "SELECT e.*, o.Titre, o.Auteur"
                + " FROM exemplaire e, oeuvre o"
                + " WHERE e.OeuvreID = o.id"
                + " AND e.id = ?";
        ArrayList<Exemplaire> exemplaires = find(SQL_SELECT_BY_ID, id);
        return exemplaires.isEmpty() ? null : exemplaires.get(0);
    }

    /**
     * Trouver les exemplaires disponibles d'une oeuvre C'est-à-dire les
     * exempalaires rendus ou non encore empruntés
     *
     * @param oeuvre l'oeuvre
     * @return Un ArrayList des Exemplaires
     * @throws BibalExceptions
     */
    public ArrayList<Exemplaire> findExemplaireDispo(Oeuvre oeuvre) throws BibalExceptions {
        final String SQL_SELECT_BY_ID_IDOEUVRE = " SELECT e.*, o.Titre, o.Auteur"
                + " FROM exemplaire e, oeuvre o"
                + " WHERE e.OeuvreID = o.id"
                + " AND e.OeuvreID = ?"
                + " AND e.id NOT IN (SELECT ExemplaireId FROM emprunt "
                + "              WHERE DateRetourEffective IS NULL )";
        ArrayList<Exemplaire> exemplaires = find(SQL_SELECT_BY_ID_IDOEUVRE, oeuvre.getId());
        return exemplaires.isEmpty() ? null : exemplaires;
    }

    public ArrayList<Exemplaire> find(Oeuvre oeuvre) throws BibalExceptions {
        final String SQL_SELECT_BY_ID_IDOEUVRE = " SELECT e.*, o.Titre, o.Auteur"
                + " FROM exemplaire e, oeuvre o"
                + " WHERE e.OeuvreID = o.id"
                + " AND e.OeuvreID = ?";
        ArrayList<Exemplaire> exemplaires = find(SQL_SELECT_BY_ID_IDOEUVRE, oeuvre.getId());
        return exemplaires.isEmpty() ? null : exemplaires;
    }

    public Exemplaire find(Oeuvre oeuvre, int idExemplaire) throws BibalExceptions {
        final String SQL_SELECT_BY_ID_IDOEUVRE = " SELECT e.*, o.Titre, o.Auteur"
                + " FROM exemplaire e, oeuvre o"
                + " WHERE e.OeuvreID = o.id"
                + " AND e.OeuvreID = ?"
                + " AND e.id = ?";
        ArrayList<Exemplaire> exemplaires = find(SQL_SELECT_BY_ID_IDOEUVRE,
                oeuvre.getId(), idExemplaire);
        return exemplaires.isEmpty() ? null : exemplaires.get(0);
    }

    private ArrayList<Exemplaire> find(String sql, Object... objets) throws BibalExceptions {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Exemplaire> listExemplaires = new ArrayList<>();

        try {
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), sql, objets);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listExemplaires.add(mappingExamplaire(resultSet));
            }
        } catch (SQLException e) {
            throw new BibalExceptions("Aucun enregistrement trouvé " + e.getMessage());
        } finally {
            closeStatementResultSet(preparedStatement, resultSet);
        }
        return listExemplaires;
    }

    private static Exemplaire mappingExamplaire(ResultSet resultSet) throws SQLException {

        Exemplaire exemplaire = new Exemplaire();
        try {
            exemplaire.setId(resultSet.getInt("id"));
            exemplaire.setEtat(resultSet.getString("etat"));
            exemplaire.oeuvresExamplaire.setId(resultSet.getInt("OeuvreID"));
            exemplaire.oeuvresExamplaire.setTitre(resultSet.getString("Titre"));
            exemplaire.oeuvresExamplaire.setAuteur(resultSet.getString("Auteur"));
        } catch (BibalExceptions e) {
            System.out.println(e.getMessage());
        }
        return exemplaire;
    }

    @Override
    public String toString() {
        return "Exemplaire{" + "id=" + id + ", empruntsExamplaire=" + empruntsExamplaire + ", oeuvresExamplaire=(" + oeuvresExamplaire + ")}\n";
    }

}
