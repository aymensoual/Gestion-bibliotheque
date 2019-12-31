package IHM;

import Utility.BibalExceptions;
import Utility.DBConnection;
import Utility.ModelTableau;
import static Utility.Utility.closeStatementResultSet;
import static Utility.Utility.initialiseRequetePreparee;
import static Utility.Utility.showMessageSucces;
import control.OeuvreControl;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import objets_metiers.Livre;
import objets_metiers.Magazine;
import objets_metiers.Oeuvre;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Integer.parseInt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.text.*;
import java.text.*;
import static javax.swing.JOptionPane.showMessageDialog;
import objets_metiers.Exemplaire;
import objets_metiers.Oeuvre;
/**
 *
 * @author Aymen Souelmi
 */
public class AjoutOeuvre extends JDialog {

    private JButton ajouterBouton;
    private JButton annulerBouton;
    private JFormattedTextField auteurField;
    private JLabel auteurLabel;
    private JFormattedTextField categorieField;
    private JLabel categorieLabel;
    private JFormattedTextField identifiantField;
    private JLabel identifiantLabel;
    private JPanel jPanel1;
    private JPanel panAjoutBas;
    private JPanel panAjoutOeuvre;
    private JLabel titleLabel;
    private JFormattedTextField titreField;
    private JLabel titreLabel;
    private JComboBox typeOeuvreCombo;
    private JLabel typeOeuvreLabel;

    public AjoutOeuvre(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIdentifiant();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bibliotheque_icone.png")));

    }

    
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jPanel1 = new JPanel();
        panAjoutOeuvre = new JPanel();
        panAjoutBas = new JPanel();
        typeOeuvreLabel = new JLabel();
        identifiantLabel = new JLabel();
        auteurField = new JFormattedTextField();
        identifiantField = new JFormattedTextField();
        auteurLabel = new JLabel();
        titreLabel = new JLabel();
        titreField = new JFormattedTextField();
        typeOeuvreCombo = new JComboBox();
        categorieLabel = new JLabel();
        categorieField = new JFormattedTextField();
        ajouterBouton = new JButton();
        annulerBouton = new JButton();
        titleLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajout Oeuvre");

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setName("Gestion personnel"); 
        jPanel1.setPreferredSize(new Dimension(1162, 608));
        jPanel1.setRequestFocusEnabled(false);

        panAjoutOeuvre.setBackground(new Color(255, 255, 255));

        panAjoutBas.setBackground(new Color(255, 255, 255));
        panAjoutBas.setBorder(BorderFactory.createTitledBorder(""));

        typeOeuvreLabel.setFont(new Font("Times New Roman", 1, 14)); 
        typeOeuvreLabel.setForeground(new Color(19, 102, 170));
        typeOeuvreLabel.setText("Type de l'oeuvre   ");

        identifiantLabel.setFont(new Font("Times New Roman", 1, 14)); 
        identifiantLabel.setForeground(new Color(19, 102, 170));
        identifiantLabel.setText("Identifiant ");

        auteurField.setPreferredSize(new Dimension(123, 26));

        identifiantField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        identifiantField.setHorizontalAlignment(JTextField.CENTER);
        identifiantField.setToolTipText("");
        identifiantField.setEnabled(false);
        identifiantField.setFont(new Font("Times New Roman", 1, 14)); 
        identifiantField.setPreferredSize(new Dimension(123, 26));

        auteurLabel.setFont(new Font("Times New Roman", 1, 14)); 
        auteurLabel.setForeground(new Color(19, 102, 170));
        auteurLabel.setText("Auteur");

        titreLabel.setFont(new Font("Times New Roman", 1, 14)); 
        titreLabel.setForeground(new Color(19, 102, 170));
        titreLabel.setText("Titre");

        titreField.setPreferredSize(new Dimension(123, 26));

        typeOeuvreCombo.setFont(new Font("Times New Roman", 1, 12)); 
        typeOeuvreCombo.setModel(new DefaultComboBoxModel(new String[]{"Livre", "Magazine"}));
        typeOeuvreCombo.setPreferredSize(new Dimension(123, 26));

        categorieLabel.setFont(new Font("Times New Roman", 1, 14)); 
        categorieLabel.setForeground(new Color(19, 102, 170));
        categorieLabel.setText("Catégorie");

        categorieField.setPreferredSize(new Dimension(123, 26));

        ajouterBouton.setFont(new Font("Times New Roman", 1, 16)); 
        ajouterBouton.setText("Ajouter");
        ajouterBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ajouter(evt);
            }
        });

        annulerBouton.setFont(new Font("Times New Roman", 1, 16)); 
        annulerBouton.setText("Annuler");
        annulerBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                annulerBoutonActionPerformed(evt);
            }
        });

        GroupLayout panAjoutBasLayout = new GroupLayout(panAjoutBas);
        panAjoutBas.setLayout(panAjoutBasLayout);
        panAjoutBasLayout.setHorizontalGroup(
                panAjoutBasLayout.createParallelGroup()
                .addGroup(panAjoutBasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addGroup(panAjoutBasLayout.createSequentialGroup()
                                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                                .addComponent(auteurLabel)
                                                .addComponent(titreLabel)
                                                .addComponent(identifiantLabel)
                                                .addComponent(typeOeuvreLabel)
                                                .addComponent(categorieLabel))
                                        .addGap(62, 62, 62)
                                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                .addComponent(typeOeuvreCombo, 0, 190, Short.MAX_VALUE)
                                                .addComponent(identifiantField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(titreField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(auteurField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(categorieField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(GroupLayout.Alignment.TRAILING, panAjoutBasLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(annulerBouton)
                                        .addGap(34, 34, 34)
                                        .addComponent(ajouterBouton)))
                        .addContainerGap())
        );
        panAjoutBasLayout.setVerticalGroup(
                panAjoutBasLayout.createParallelGroup()
                .addGroup(panAjoutBasLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(typeOeuvreLabel)
                                .addComponent(typeOeuvreCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(identifiantLabel, GroupLayout.Alignment.TRAILING)
                                .addComponent(identifiantField, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(titreLabel)
                                .addComponent(titreField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(auteurLabel)
                                .addComponent(auteurField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(categorieLabel)
                                .addComponent(categorieField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(ajouterBouton)
                                .addComponent(annulerBouton))
                        .addGap(19, 19, 19))
        );

        GroupLayout panAjoutOeuvreLayout = new GroupLayout(panAjoutOeuvre);
        panAjoutOeuvre.setLayout(panAjoutOeuvreLayout);
        panAjoutOeuvreLayout.setHorizontalGroup(
                panAjoutOeuvreLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, panAjoutOeuvreLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panAjoutBas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panAjoutOeuvreLayout.setVerticalGroup(
                panAjoutOeuvreLayout.createParallelGroup()
                .addGroup(panAjoutOeuvreLayout.createSequentialGroup()
                        .addComponent(panAjoutBas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
        );

        titleLabel.setFont(new Font("Times New Roman", 1, 36)); 
        titleLabel.setForeground(new Color(19, 102, 170));
        titleLabel.setText("Ajout Oeuvre");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panAjoutOeuvre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(titleLabel)
                        .addGap(2, 2, 2)
                        .addComponent(panAjoutOeuvre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 468, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ajouter(ActionEvent evt) {//GEN-FIRST:event_ajouter

        try {
            Oeuvre oeuvre = getOeuvreInfos();
            OeuvreControl.ajouter(oeuvre);
            ((ModelTableau) GestionOeuvresExemplaires.tableListeOeuvre.getModel())
                    .addRow(
                            new Object[]{oeuvre.getId(), oeuvre.getTitre(), oeuvre.getAuteur(),
                                oeuvre.getCategorie(), oeuvre.getClass().getSimpleName(), 0, 0});
            setIdentifiant();
            clearField();
            showMessageSucces("Oeuvre ajoutée avec succès");
        } catch (BibalExceptions e) {
            System.out.println("IHM.AjoutOeuvre.ajouterBoutonActionPerformed()");
        }
    }//GEN-LAST:event_ajouter

    private Oeuvre getOeuvreInfos() {
        int identifiant = Integer.parseInt(identifiantField.getText());
        String titre = titreField.getText();
        String auteur = auteurField.getText();
        String categorie = categorieField.getText();
        String typeOeuvre = typeOeuvreCombo.getSelectedItem().toString();

        if (typeOeuvre.equals(Magazine.class.getSimpleName())) {
            return new Magazine(identifiant, titre, auteur, categorie);
        }
        return new Livre(identifiant, titre, auteur, categorie);
    }

    private void annulerBoutonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_annulerBoutonActionPerformed
        this.dispose();
    }//GEN-LAST:event_annulerBoutonActionPerformed

    private void clearField() {
        titreField.setText("");
        auteurField.setText("");
        categorieField.setText("");
    }

    private void setIdentifiant() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            final String SQL_SELECT_ID = "SELECT id FROM oeuvre ORDER BY id DESC LIMIT 1";
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(),
                    SQL_SELECT_ID, new Object[0]);
            resultSet = preparedStatement.executeQuery();
            int identifiant = 1;
            if (resultSet.first()) {
                identifiant = resultSet.getInt("id");
            }
            identifiantField.setText((identifiant + 1) + "");
        } catch (SQLException | BibalExceptions e) {
            JOptionPane.showMessageDialog(null, "Erreurs d'accès à la base de données",
                    "Erreurs", JOptionPane.ERROR_MESSAGE);
        } finally {
            closeStatementResultSet(preparedStatement, resultSet);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    
    // End of variables declaration//GEN-END:variables
}
