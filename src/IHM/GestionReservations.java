package IHM;

import Utility.BibalExceptions;
import Utility.DBConnection;
import static Utility.Utility.closeStatementResultSet;
import static Utility.Utility.initialiseRequetePreparee;
import static Utility.Utility.showMessageSucces;
import control.ReservationControl;
import control.UsagerControl;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import objets_metiers.Usager;
import static java.lang.Integer.parseInt;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Integer.parseInt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.*;
import objets_metiers.Usager;
/**
 *
 * @author Aymen Souelmi
 */
public class GestionReservations extends javax.swing.JDialog {

    private javax.swing.JButton annulerBouton;
    private javax.swing.JLabel identifiantLabel;
    private javax.swing.JComboBox identifiantUsagerCombo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nomLabel;
    private javax.swing.JLabel nomUsagerLabel;
    private javax.swing.JPanel panAjoutBas;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton validerBouton;

    public GestionReservations(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIdentifiant();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bibliotheque_icone.png")));
    }

    public GestionReservations(Frame parent, boolean modal, String titre) {
        this(parent, modal);
        this.titre = titre;
    }

    
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jPanel1 = new JPanel();
        titleLabel = new JLabel();
        panAjoutBas = new JPanel();
        nomLabel = new JLabel();
        identifiantLabel = new JLabel();
        identifiantUsagerCombo = new JComboBox();
        validerBouton = new JButton();
        annulerBouton = new JButton();
        nomUsagerLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Réserver");

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setName("Gestion personnel");
        jPanel1.setPreferredSize(new Dimension(1162, 608));
        jPanel1.setRequestFocusEnabled(false);

        titleLabel.setFont(new Font("Times New Roman", 1, 36));
        titleLabel.setForeground(new Color(19, 102, 170));
        titleLabel.setText("Choisir Usager");

        panAjoutBas.setBackground(new Color(255, 255, 255));
        panAjoutBas.setBorder(BorderFactory.createTitledBorder(""));

        nomLabel.setFont(new Font("Times New Roman", 1, 14));
        nomLabel.setForeground(new Color(19, 102, 170));
        nomLabel.setText("Nom usager");

        identifiantLabel.setFont(new Font("Times New Roman", 1, 14));
        identifiantLabel.setForeground(new Color(19, 102, 170));
        identifiantLabel.setText("Identifiant ");

        identifiantUsagerCombo.setFont(new Font("Times New Roman", 1, 12));
        identifiantUsagerCombo.setModel(new DefaultComboBoxModel(new String[]{"Choisir identifiant"}));
        identifiantUsagerCombo.setPreferredSize(new Dimension(123, 26));
        identifiantUsagerCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                identifiantUsagerComboActionPerformed(evt);
            }
        });

        validerBouton.setFont(new Font("Times New Roman", 1, 16));
        validerBouton.setText("Valider");
        validerBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                reserver(evt);
            }
        });

        annulerBouton.setFont(new Font("Times New Roman", 1, 16));
        annulerBouton.setText("Annuler");
        annulerBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                annulerBoutonActionPerformed(evt);
            }
        });

        nomUsagerLabel.setFont(new Font("Times New Roman", 1, 18));
        nomUsagerLabel.setText(" ");

        GroupLayout panAjoutBasLayout = new GroupLayout(panAjoutBas);
        panAjoutBas.setLayout(panAjoutBasLayout);
        panAjoutBasLayout.setHorizontalGroup(
                panAjoutBasLayout.createParallelGroup()
                .addGroup(panAjoutBasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, panAjoutBasLayout.createSequentialGroup()
                                        .addGap(0, 169, Short.MAX_VALUE)
                                        .addComponent(annulerBouton)
                                        .addGap(30, 30, 30)
                                        .addComponent(validerBouton))
                                .addGroup(panAjoutBasLayout.createSequentialGroup()
                                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(nomLabel)
                                                .addComponent(identifiantLabel))
                                        .addGap(62, 62, 62)
                                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(identifiantUsagerCombo, 0, 230, Short.MAX_VALUE)
                                                .addComponent(nomUsagerLabel))))
                        .addContainerGap())
        );
        panAjoutBasLayout.setVerticalGroup(
                panAjoutBasLayout.createParallelGroup()
                .addGroup(panAjoutBasLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(identifiantLabel)
                                .addComponent(identifiantUsagerCombo))
                        .addGap(35, 35, 35)
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(nomLabel)
                                .addComponent(nomUsagerLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(annulerBouton)
                                .addComponent(validerBouton))
                        .addGap(5, 5, 5))
        );

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panAjoutBas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(titleLabel)
                        .addGap(18, 18, 18)
                        .addComponent(panAjoutBas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    private void identifiantUsagerComboActionPerformed(ActionEvent evt) {
        try {
            int usagerId = getUsagerID();
            if (usagerId != -1) {
                Usager usager = UsagerControl.findById(usagerId);
                if (null != usager) {
                    String nom = usager.getNom() + " " + usager.getPrenom();
                    nomUsagerLabel.setText(nom);
                }
            }
        } catch (BibalExceptions ex) {
            System.out.println("IHM.Emprunter.identifiantUsagerComboActionPerformed()");
        }
    }

    private void reserver(ActionEvent evt) {

        try {
            int usagerId = getUsagerID();
            if (usagerId != -1) {
                ReservationControl.reserver(usagerId, titre);
                showMessageSucces("Réservation enregistrée");
            } else {
                showMessageSucces("Veuillez choisir un identifiant");
            }
        } catch (BibalExceptions ex) {
            System.out.println("IHM.Reserver.validerBoutonActionPerformed()" + ex.getMessage());
        }
    }

    private int getUsagerID() {
        String id = identifiantUsagerCombo.getSelectedItem().toString();
        if (!id.equals("Choisir identifiant")) {
            return parseInt(id);
        }
        return -1;
    }

    private void setIdentifiant() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            final String SQL_SELECT_ID = "SELECT id FROM usager ";
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(),
                    SQL_SELECT_ID, new Object[0]);
            resultSet = preparedStatement.executeQuery();
            int identifiant;
            while (resultSet.next()) {
                identifiant = resultSet.getInt("id");
                identifiantUsagerCombo.addItem(Integer.toString(identifiant));
            }
        } catch (SQLException | BibalExceptions e) {
            JOptionPane.showMessageDialog(null, "Erreurs d'accès à la base de données",
                    "Erreurs", JOptionPane.ERROR_MESSAGE);
        } finally {
            closeStatementResultSet(preparedStatement, resultSet);
        }
    }

    private void annulerBoutonActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    private String titre;
    
}
