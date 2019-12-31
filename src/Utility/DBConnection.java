package Utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *@author Aymen Souelmi
 * 
 * Cette classe se charge de lire les informations de configuration depuis le
 * fichier 'DatabaseConfig.properties' et fournir une connexion à la base de
 * données 
 */

public class DBConnection {

    private static final String FICHIER_PROPERTIES = "Utility/DatabaseConfig.properties";
    private static final String PROPERTY_URL = "SGBD.URL";
    private static final String PROPERTY_DRIVER = "SGBD.DRIVER";
    private static final String PROPERTY_NOM_USER = "SGBD.USER";
    private static final String PROPERTY_PASSWORD = "SGBD.PASSWORD";

    /**
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance connexion à la
     * base de données
     *
     * @return
     * @throws SQLException
     * @throws BibalExceptions
     */
    public static Connection getConnection() throws SQLException, BibalExceptions {

        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;

        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

        if (null == fichierProperties) {
            throw new BibalExceptions("Le fichier properties '"
                    + FICHIER_PROPERTIES.toUpperCase() + "' est introuvable\n");
        }
        /**
         * Chargement du fichier de configuration de la BD
         */
        try {
            properties.load(fichierProperties);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            nomUtilisateur = properties.getProperty(PROPERTY_NOM_USER);
            motDePasse = properties.getProperty(PROPERTY_PASSWORD);
        } catch (IOException ex) {
            throw new BibalExceptions("Impossible de charger le fichier properties '"
                    + FICHIER_PROPERTIES.toUpperCase() + "'\n", ex.getCause());
        }
        /**
         * Chargement du Driver
         */
        try {
            Class.forName(driver);
        } catch (Exception e) {
            throw new BibalExceptions("Le driver " + PROPERTY_DRIVER.toUpperCase()
                    + " est introuvable\n", e.getCause());
        }

        return DriverManager.getConnection(url, nomUtilisateur, motDePasse);
    }

}
