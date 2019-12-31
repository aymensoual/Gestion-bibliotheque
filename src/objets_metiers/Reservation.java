package objets_metiers;

import Utility.BibalExceptions;
import Utility.DBConnection;
import static Utility.Utility.YMDtoDMY;
import static Utility.Utility.closeStatement;
import static Utility.Utility.closeStatementResultSet;
import static Utility.Utility.dateToStr;
import static Utility.Utility.initialiseRequetePreparee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Aymen Souelmi
 */
public class Reservation {

    private int id;
    private Date dateReservation;
    private Date dateAnnulation;
    private Usager usagerReservation;
    private Oeuvre oeuvresReservation;
//    static final String SQL_SELECT_JOINTURE= "SELECT reservation.*, o.titre, u.nom,"
//                + " u.prenom FROM reservation, oeuvre o, usager u"
//                + " WHERE OeuvreID = o.id"
//                + " AND UsagerID = u.id"
//                + " AND ";
    public Reservation() {
        this.dateReservation = Date.from(Instant.now());
        this.dateAnnulation = null;
        this.usagerReservation = new Usager();
        this.oeuvresReservation = new Oeuvre();
    }

    public Reservation(Usager usager, Oeuvre oeuvre, Date dateJour) {
        this.usagerReservation = usager;
        this.oeuvresReservation = oeuvre;
        this.dateReservation = dateJour;
        this.dateAnnulation = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws BibalExceptions {
        if (id <= 0) {
            throw new BibalExceptions("Identifiant Reservation non valide !");
        }
        this.id = id;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Date getDateAnnulation() {
        return dateAnnulation;
    }

    public void setDateAnnulation(Date dateAnnulation) {
        this.dateAnnulation = dateAnnulation;
    }

    public Oeuvre getOeuvresReservation() {
        return oeuvresReservation;
    }

    public void setOeuvresReservation(Oeuvre oeuvresReservation) {
        this.oeuvresReservation = oeuvresReservation;
    }

    public Usager getUsagerReservation() {
        return usagerReservation;
    }

    public void setUsagerReservation(Usager usagerReservation) {
        this.usagerReservation = usagerReservation;
    }

    public void reserver(Usager usager, Oeuvre oeuvre, Date dateJour) throws BibalExceptions {
        Reservation reservation = findByReservation(usager, oeuvre);
        if (null != reservation) {
            throw new BibalExceptions("Vous avez déjà réservé l'oeuvre '" + oeuvre.getTitre()
                    + "'\n le '" + YMDtoDMY(reservation.getDateReservation().toString(),"-") + "'");
        }
        final String SQL_INSERT = "INSERT INTO Reservation "
                + "(OeuvreID, UsagerID, dateReservation, DateAnnulation) "
                + "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        String formatedDateJour = dateToStr(dateJour);
        try {
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), SQL_INSERT,
                    oeuvre.getId(), usager.getId(),
                    formatedDateJour, null);
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new BibalExceptions("Echec lors de l'enregistrement de la réservation");
            }
        } catch (SQLException e) {
            throw new BibalExceptions("Erreurs lors de l'enregistrement de la réservation", e.getCause());
        } catch (BibalExceptions e) {
            throw new BibalExceptions("Erreurs lors de l'enregistrement de la réservation ", e.getCause());
        } finally {
            closeStatement(preparedStatement);
        }
    }

    public void annuler(Usager usager, Oeuvre oeuvre, Reservation reservation) throws BibalExceptions {
        final String SQL_UPDATE_RES = "UPDATE reservation SET DateAnnulation = ?"
                + " WHERE OeuvreID = ? AND UsagerID = ?";
        PreparedStatement preparedStatement = null;
        try {
            String formatedDateAnnulation = dateToStr(reservation.getDateAnnulation());
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), SQL_UPDATE_RES,
                    formatedDateAnnulation, oeuvre.getId(),
                    usager.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new BibalExceptions("Echec de l'annulation de la réservation");
            }
        } catch (SQLException | BibalExceptions e) {
            throw new BibalExceptions("Erreurs lors de  l'annulation de la réservation", e.getCause());
        } finally {
            closeStatement(preparedStatement);
        }
    }

    public Reservation findById(int id) throws BibalExceptions {
        final String SQL_SELECT_BY_ID = "SELECT reservation.*, o.titre, u.nom,"
                + " u.prenom FROM reservation, oeuvre o, usager u"
                + " WHERE OeuvreID = o.id"
                + " AND UsagerID = u.id"
                + " AND reservation.id = ?";
        ArrayList<Reservation> reservations = find(SQL_SELECT_BY_ID, id);
        return reservations.isEmpty() ? null : reservations.get(0);
    }

    public ArrayList<Reservation> findByDateReservaton(Date dateRes) throws BibalExceptions {
        final String SQL_SELECT_BY_DATE_RES = "SELECT reservation.*, o.titre, u.nom,"
                + " u.prenom FROM reservation, oeuvre o, usager u"
                + " WHERE OeuvreID = o.id"
                + " AND UsagerID = u.id"
                + " AND dateReservation = ?"
                + " AND dateAnnulation IS NULL";
        String formatedDateRes = dateToStr(dateRes);
        ArrayList<Reservation> reservations = find(SQL_SELECT_BY_DATE_RES, formatedDateRes);
        return reservations;
    }

    
    public Reservation findByReservation(Usager usager, Oeuvre oeuvre) throws BibalExceptions {
        final String SQL_SELECT_BY_ID_OEUVRE_USAGER = "SELECT reservation.*, o.titre, u.nom,"
                + " u.prenom FROM reservation, oeuvre o, usager u"
                + " WHERE OeuvreID = o.id"
                + " AND UsagerID = u.id"
                + " AND UsagerID = ?"
                + " AND OeuvreID = ? "
                + " AND DateAnnulation IS NULL";
        ArrayList<Reservation> reservations
                = find(SQL_SELECT_BY_ID_OEUVRE_USAGER, usager.getId(), oeuvre.getId());
        return reservations.isEmpty() ? null : reservations.get(0);
    }

    public ArrayList<Reservation> findByReservation(Oeuvre oeuvre) throws BibalExceptions {
        final String SQL_SELECT_BY_ID_OEUVRE = "SELECT reservation.*, o.titre, u.nom,"
                + " u.prenom FROM reservation, oeuvre o, usager u"
                + " WHERE OeuvreID = o.id"
                + " AND UsagerID = u.id"
                + " AND OeuvreID = ? "
                + " AND DateAnnulation IS NULL";
        ArrayList<Reservation> reservations
                = find(SQL_SELECT_BY_ID_OEUVRE, oeuvre.getId());
        return (reservations == null || reservations.isEmpty()) ? null : reservations;
    }
    private ArrayList<Reservation> find(String sql, Object... objets) throws BibalExceptions {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Reservation> listReservations = new ArrayList<>();

        try {
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(), sql, objets);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listReservations.add(mappingReservation(resultSet));
            }
        } catch (SQLException e) {
            throw new BibalExceptions("Aucun enregistrement trouvé " + e.getMessage());
        } finally {
            closeStatementResultSet(preparedStatement, resultSet);
        }
        return listReservations;
    }

    private static Reservation mappingReservation(ResultSet resultSet) throws SQLException {

        Reservation reservation = new Reservation();
        try {
            reservation.id = resultSet.getInt("id");
            reservation.dateReservation = resultSet.getDate("dateReservation");
            reservation.dateAnnulation = resultSet.getDate("DateAnnulation");
            reservation.oeuvresReservation.setId(resultSet.getInt("OeuvreID"));
            reservation.oeuvresReservation.setTitre(resultSet.getString("Titre"));
            reservation.usagerReservation.setId(resultSet.getInt("UsagerID"));
            reservation.usagerReservation.setNom(resultSet.getString("nom"));
            reservation.usagerReservation.setPrenom(resultSet.getString("prenom"));
        } catch (BibalExceptions e) {
            System.out.println(e.getMessage());
        }
        return reservation;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", dateReservation=" + dateReservation + ", dateAnnulation=" + dateAnnulation + ", usagerReservation=" + usagerReservation + ", oeuvresReservation=" + oeuvresReservation + "}\n";
    }

}
