package Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Aymen Souelmi
 */
public final class Utility {
    /**
     * Fermeture du ResultSet
     *
     * @param resultSet
     */
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Echec de la fermeture du ResultSet : " + e.getMessage());
            }
        }
    }

    /**
     * Fermeture du statement
     *
     * @param statement
     */
    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Echec de la fermeture du Statement : " + e.getMessage());
            }
        }
    }

    /**
     * Fermeture de la connexion
     *
     * @param connexion
     */
    public static void closeConnection(Connection connexion) {
        if (connexion != null) {
            try {
                connexion.close();
            } catch (SQLException e) {
                System.out.println("Echec de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }

    /**
     *
     * @param statement
     * @param resultSet
     */
    public static void closeStatementResultSet(Statement statement, ResultSet resultSet) {
        closeStatement(statement);
        closeResultSet(resultSet);
    }

    public static void closeConnectionStatementResultSet(Connection connexion, Statement statement, ResultSet resultSet) {
        closeConnection(connexion);
        closeStatement(statement);
        closeResultSet(resultSet);
    }

    /**
     * Initialisation des requêtes préparées des objets
     *
     * @param connexion
     * @param sql
     * @param objets
     * @return
     * @throws SQLException
     */
    public static PreparedStatement initialiseRequetePreparee(Connection connexion, String sql, Object... objets) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        for (int i = 0; i < objets.length; i++) {
            preparedStatement.setObject(i + 1, objets[i]);
        }
        return preparedStatement;
    }

    /**
     * Méthode utilitaire de conversion d'une chaine en  date 
     * en Date
     * @param dateNais en format dd/MM/yyyy
     * @return
     * @throws BibalExceptions
     */
    public static Date formatDate(String dateNais) throws BibalExceptions {

        if (null != dateNais) {
            try {
                return new SimpleDateFormat("dd/MM/yyyy").parse(dateNais);
            } catch (ParseException e) {
                throw new BibalExceptions("Date non valide ", e.getCause());
            }
        } else {
            throw new BibalExceptions("Merci d'indiquer une date ");
        }
    }

    /**
     * Méthode utilitaire de conversion d'une date en format  yyyy-MM-dd
     *
     * @param date date à convertir
     * @return date au format yyyy-mm-dd
     */
    public static String dateToStr(Date date) {
        if(null == date){
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    
    public static String dateToStrYMD(Date date) {
        if(null == date){
            return null;
        }
        return new SimpleDateFormat("yyyy/MM/dd").format(date);
    }
    
    public static String YMDtoDMY(String dateFormatYMD, String newSeparator) {
        String str[] = dateFormatYMD.split("-");
        return String.join(newSeparator, str[2], str[1], str[0]);
    }
    
    public static String formatMillisToDate(long dateInMilliseconds) {
        //Date date = new Date(dateInMilliseconds);
        return new SimpleDateFormat("dd/MM/yyyy").format(dateInMilliseconds);
        //return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date);
    }
    
    public static void showMessageSucces(String message){
        JOptionPane.showMessageDialog(null, message, 
                "Informations", JOptionPane.INFORMATION_MESSAGE);
    }
}
