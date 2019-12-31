package IHM;

import Utility.BibalExceptions;
import Utility.DBConnection;
import Utility.ModelTableau;
import static Utility.Utility.YMDtoDMY;
import static Utility.Utility.closeStatementResultSet;
import static Utility.Utility.dateToStr;
import static Utility.Utility.formatDate;
import static Utility.Utility.initialiseRequetePreparee;
import static Utility.Utility.showMessageSucces;
import control.UsagerControl;
import java.awt.Color;
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
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.*;
import objets_metiers.Usager;
import java.text.*;
import javax.swing.text.*;

/**
 *
 * @author Aymen Souelmi
 */
public class GestionUsager extends JFrame implements MouseListener {

    private JButton RecherchBouton;
    private JComboBox RecherchCombo;
    private JFormattedTextField RecherchField;
    private JLabel RecherchParLabel;
    private JFormattedTextField adresseField;
    private JButton ajouterBouton;
    private JButton annulerBouton;
    private JComboBox civiliteCombo;
    private JLabel civiliteLabel;
    private JLabel dateLabel;
    private org.jdesktop.swingx.JXDatePicker dateNaisPicker;
    private JFormattedTextField identifiantField;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JLabel matriculeLabel;
    private JButton modifierBouton;
    private JFormattedTextField nomField;
    private JLabel nomLabel;
    private JPanel panAjoutBas;
    private JPanel panAjoutUsager;
    private JPanel panListeUsager;
    private JPanel panRecherch;
    private JFormattedTextField prenomField;
    private JLabel prenomLabel;
    private JLabel prenomLabel1;
    private JButton retourButton;
    private JButton supprimerButton;
    private JTable tableListeUsager;
    private JFormattedTextField telField;
    private JLabel telLabel;

    public GestionUsager() {

          initComponents();
        setResizable(false);
        setIdentifiant();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bibliotheque_icone.png")));

        tableListeUsager.addMouseListener(this);
        tableListeUsager.setModel(new ModelTableau(new Object[0][0],
                new String[]{"Identifiant", "Nom","Prénom", "Date de naissance", "Sexe", "Téléphone", "Adresse"}));
        tableListeUsager.setRowHeight(30);
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        jPanel1 = new JPanel();
        panRecherch = new JPanel();
        RecherchParLabel = new JLabel();
        RecherchField = new JFormattedTextField();
        RecherchCombo = new JComboBox();
        RecherchBouton = new JButton();
        panListeUsager = new JPanel();
        jScrollPane1 = new JScrollPane();
        tableListeUsager = new JTable();
        panAjoutUsager = new JPanel();
        panAjoutBas = new JPanel();
        civiliteLabel = new JLabel();
        matriculeLabel = new JLabel();
        prenomField = new JFormattedTextField();
        identifiantField = new JFormattedTextField();
        prenomLabel = new JLabel();
        telField = new JFormattedTextField();
        nomLabel = new JLabel();
        telLabel = new JLabel();
        dateLabel = new JLabel();
        nomField = new JFormattedTextField();
        civiliteCombo = new JComboBox();
        prenomLabel1 = new JLabel();
        adresseField = new JFormattedTextField();
        dateNaisPicker = new org.jdesktop.swingx.JXDatePicker();
        annulerBouton = new JButton();
        ajouterBouton = new JButton();
        modifierBouton = new JButton();
        supprimerButton = new JButton();
        jLabel1 = new JLabel();
        retourButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion Usagers");
        setPreferredSize(new Dimension(1162, 608));
        setResizable(false);

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setName("Gestion personnel");
        jPanel1.setPreferredSize(new Dimension(1162, 608));
        jPanel1.setRequestFocusEnabled(false);

        panRecherch.setBackground(new Color(255, 255, 255));
        panRecherch.setBorder(BorderFactory.createTitledBorder("Rechercher"));

        RecherchParLabel.setText("Rechercher par  ");

        RecherchField.setHorizontalAlignment(JTextField.CENTER);
        RecherchField.setPreferredSize(new Dimension(123, 26));

        RecherchCombo.setModel(new DefaultComboBoxModel(new String[]{"Identifiant", "Nom", "Tout afficher"}));
        RecherchCombo.setPreferredSize(new Dimension(123, 26));
        RecherchCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RecherchComboActionPerformed(evt);
            }
        });

        RecherchBouton.setText("Rechercher");
        RecherchBouton.setPreferredSize(new Dimension(47, 26));
        RecherchBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RecherchBoutonActionPerformed(evt);
            }
        });

        GroupLayout panRecherchLayout = new GroupLayout(panRecherch);
        panRecherch.setLayout(panRecherchLayout);
        panRecherchLayout.setHorizontalGroup(
                panRecherchLayout.createParallelGroup()
                .addGroup(panRecherchLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(panRecherchLayout.createParallelGroup()
                                .addGroup(panRecherchLayout.createSequentialGroup()
                                        .addComponent(RecherchParLabel)
                                        .addGap(41, 41, 41)
                                        .addComponent(RecherchCombo))
                                .addGroup(panRecherchLayout.createSequentialGroup()
                                        .addComponent(RecherchField)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(RecherchBouton)))
                        .addContainerGap(184, Short.MAX_VALUE))
        );
        panRecherchLayout.setVerticalGroup(
                panRecherchLayout.createParallelGroup()
                .addGroup(panRecherchLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panRecherchLayout.createParallelGroup()
                                .addComponent(RecherchParLabel)
                                .addComponent(RecherchCombo))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panRecherchLayout.createParallelGroup()
                                .addComponent(RecherchField)
                                .addComponent(RecherchBouton))
                        .addContainerGap(25, Short.MAX_VALUE))
        );

        panListeUsager.setBackground(new Color(255, 255, 255));
        panListeUsager.setBorder(BorderFactory.createTitledBorder("Liste des Usagers"));

        jScrollPane1.getViewport().setBackground(Color.white);
        jScrollPane1.setBackground(new Color(255, 255, 255));

        tableListeUsager.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Identifiant", "Nom", "Prénom", "Date de naissance", "Sexe", "Téléphone", "Adresse"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tableListeUsager.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableListeUsager.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableListeUsager);

        GroupLayout panListeUsagerLayout = new GroupLayout(panListeUsager);
        panListeUsager.setLayout(panListeUsagerLayout);
        panListeUsagerLayout.setHorizontalGroup(
                panListeUsagerLayout.createParallelGroup()
                .addGroup(panListeUsagerLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1)
                        .addGap(0, 0, 0))
        );
        panListeUsagerLayout.setVerticalGroup(
                panListeUsagerLayout.createParallelGroup()
                .addComponent(jScrollPane1)
        );

        panAjoutUsager.setBackground(new Color(255, 255, 255));
        panAjoutUsager.setBorder(BorderFactory.createTitledBorder("Ajout Usager"));

        panAjoutBas.setBackground(new Color(255, 255, 255));
        panAjoutBas.setBorder(BorderFactory.createTitledBorder(""));

        civiliteLabel.setFont(new Font("Times New Roman", 1, 14));
        civiliteLabel.setForeground(new Color(19, 102, 170));
        civiliteLabel.setText("Civilité : ");

        matriculeLabel.setFont(new Font("Times New Roman", 1, 14));
        matriculeLabel.setForeground(new Color(19, 102, 170));
        matriculeLabel.setText("Identifiant ");

        prenomField.setPreferredSize(new Dimension(123, 26));
        identifiantField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        identifiantField.setHorizontalAlignment(JTextField.CENTER);
        identifiantField.setToolTipText("");
        identifiantField.setEnabled(false);
        identifiantField.setFont(new Font("Times New Roman", 1, 14));
        identifiantField.setPreferredSize(new Dimension(123, 26));

        prenomLabel.setFont(new Font("Times New Roman", 1, 14));
        prenomLabel.setForeground(new Color(19, 102, 170));
        prenomLabel.setText("Prénom  ");

        try {
            telField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##-###-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        
        
        telField.setHorizontalAlignment(JTextField.CENTER);
        telField.setToolTipText("");
        telField.setMinimumSize(new Dimension(4, 22));
        telField.setPreferredSize(new Dimension(123, 26));
        telField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                telFieldActionPerformed(evt);
            }
        });

        nomLabel.setFont(new Font("Times New Roman", 1, 14));
        nomLabel.setForeground(new Color(19, 102, 170));
        nomLabel.setText("Nom  ");

        telLabel.setFont(new Font("Times New Roman", 1, 14));
        telLabel.setForeground(new Color(19, 102, 170));
        telLabel.setText("Téléphone  ");

        dateLabel.setFont(new Font("Times New Roman", 1, 14));
        dateLabel.setForeground(new Color(19, 102, 170));
        dateLabel.setText("Date de naissance  ");

        nomField.setPreferredSize(new Dimension(123, 26));

        civiliteCombo.setFont(new Font("Times New Roman", 1, 12));
        civiliteCombo.setModel(new DefaultComboBoxModel(new String[]{"M", "Mme"}));
        civiliteCombo.setPreferredSize(new Dimension(123, 26));

        prenomLabel1.setFont(new Font("Times New Roman", 1, 14));
        prenomLabel1.setForeground(new Color(19, 102, 170));
        prenomLabel1.setText("Adresse");

        adresseField.setPreferredSize(new Dimension(123, 26));

        dateNaisPicker.setFormats("dd/MM/yyyy");
        dateNaisPicker.setPreferredSize(new Dimension(123, 26));

        GroupLayout panAjoutBasLayout = new GroupLayout(panAjoutBas);
        panAjoutBas.setLayout(panAjoutBasLayout);
        panAjoutBasLayout.setHorizontalGroup(
                panAjoutBasLayout.createParallelGroup()
                .addGroup(panAjoutBasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(telLabel)
                                .addComponent(dateLabel)
                                .addComponent(prenomLabel)
                                .addComponent(nomLabel)
                                .addComponent(matriculeLabel)
                                .addComponent(civiliteLabel)
                                .addComponent(prenomLabel1))
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addGroup(panAjoutBasLayout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                .addComponent(civiliteCombo)
                                                .addComponent(identifiantField)
                                                .addComponent(nomField)
                                                .addComponent(prenomField)
                                                .addComponent(telField)
                                                .addComponent(adresseField))
                                        .addContainerGap(49, Short.MAX_VALUE))
                                .addGroup(panAjoutBasLayout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(dateNaisPicker, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panAjoutBasLayout.setVerticalGroup(
                panAjoutBasLayout.createParallelGroup()
                .addGroup(panAjoutBasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(civiliteLabel)
                                .addComponent(civiliteCombo))
                        .addGap(26, 26, 26)
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(matriculeLabel)
                                .addComponent(identifiantField))
                        .addGap(26, 26, 26)
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(nomLabel)
                                .addComponent(nomField))
                        .addGap(31, 31, 31)
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(prenomLabel)
                                .addComponent(prenomField))
                        .addGap(29, 29, 29)
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(dateLabel)
                                .addComponent(dateNaisPicker))
                        .addGap(31, 31, 31)
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(prenomLabel1)
                                .addComponent(adresseField))
                        .addGap(39, 39, 39)
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(telField)
                                .addComponent(telLabel))
                        .addContainerGap(27, Short.MAX_VALUE))
        );

        annulerBouton.setFont(new Font("Times New Roman", 1, 16));
        annulerBouton.setText("Annuler");
        annulerBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                annulerBoutonActionPerformed(evt);
            }
        });

        ajouterBouton.setFont(new Font("Times New Roman", 1, 16));
        ajouterBouton.setText("Ajouter");
        ajouterBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ajouter(evt);
            }
        });

        modifierBouton.setFont(new Font("Times New Roman", 1, 16));
        modifierBouton.setText("Modifier");
        modifierBouton.setEnabled(false);
        modifierBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                modifier(evt);
            }
        });

        supprimerButton.setFont(new Font("Times New Roman", 1, 16));
        supprimerButton.setText("Supprimer");
        supprimerButton.setEnabled(false);
        supprimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                supprimer(evt);
            }
        });

        GroupLayout panAjoutUsagerLayout = new GroupLayout(panAjoutUsager);
        panAjoutUsager.setLayout(panAjoutUsagerLayout);
        panAjoutUsagerLayout.setHorizontalGroup(
                panAjoutUsagerLayout.createParallelGroup()
                .addComponent(panAjoutBas)
                .addGroup(panAjoutUsagerLayout.createSequentialGroup()
                        .addComponent(annulerBouton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(supprimerButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifierBouton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ajouterBouton)
                        .addGap(10, 10, 10))
        );
        panAjoutUsagerLayout.setVerticalGroup(
                panAjoutUsagerLayout.createParallelGroup()
                .addGroup(panAjoutUsagerLayout.createSequentialGroup()
                        .addComponent(panAjoutBas)
                        .addGap(29, 29, 29)
                        .addGroup(panAjoutUsagerLayout.createParallelGroup()
                                .addComponent(ajouterBouton)
                                .addComponent(modifierBouton)
                                .addComponent(annulerBouton)
                                .addComponent(supprimerButton)))
        );

        jLabel1.setFont(new Font("Times New Roman", 1, 36));
        jLabel1.setForeground(new Color(19, 102, 170));
        jLabel1.setText("Gestion Des Usagers");

        retourButton.setText("Retour");
        retourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                retourButtonActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(panAjoutUsager, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(retourButton)))
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(jLabel1)
                                .addComponent(panRecherch)
                                .addComponent(panListeUsager)))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(retourButton)
                                .addComponent(jLabel1))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(panRecherch)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(panListeUsager))
                                .addComponent(panAjoutUsager))
                        .addContainerGap(16, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel1)
                        .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel1)
                        .addGap(0, 0, 0))
        );

        setSize(new Dimension(1178, 589));
        setLocationRelativeTo(null);
    }

   private void annulerBoutonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        clearField();
        modifierBouton.setEnabled(false);
        supprimerButton.setEnabled(false);
        ajouterBouton.setEnabled(true);
        setIdentifiant();

    }                                             

    private void retourButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        Menu m = new Menu();
        this.dispose();
        m.setVisible(true);
    }                                            

    private void modifier(java.awt.event.ActionEvent evt) {                          

        try {
            Usager usager = getUsagerInfos();
            UsagerControl.modifier(usager);
            setIdentifiant();
            clearField();
            ((ModelTableau) tableListeUsager.getModel()).updateRow(
                    new Object[]{usager.getId(), usager.getNom(), usager.getPrenom(),
                        dateToStr(usager.getDateNais()), usager.getSexe(),
                        usager.getTel(), usager.getAdresse()},
                    tableListeUsager.getSelectedRow());
            modifierBouton.setEnabled(false);
            supprimerButton.setEnabled(false);
            ajouterBouton.setEnabled(true);
            showMessageSucces("Les modifications ont été enregistrées");
        } catch (BibalExceptions e) {
            System.out.println("IHM.GestionUsager.modifierBoutonActionPerformed()");
        }
    }                         

    private void supprimer(java.awt.event.ActionEvent evt) {                           
        try {
            Usager usager = getUsagerInfos();
            UsagerControl.supprimer(usager);
            showMessageSucces("L'enregistrement a bien été supprimé");
            setIdentifiant();
            clearField();
            ((ModelTableau) tableListeUsager.getModel()).removeRow(tableListeUsager.getSelectedRow());
            modifierBouton.setEnabled(false);
            supprimerButton.setEnabled(false);
            ajouterBouton.setEnabled(true);
        } catch (BibalExceptions e) {
            showMessageSucces("Impossible de supprimer cet usager");
        }
    }                          

    private void RecherchBoutonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        String rechPar = RecherchCombo.getSelectedItem().toString();
        String textARechercher = RecherchField.getText().trim();
        if (textARechercher.length() > 0) {
            ArrayList<Usager> listUsagers;
            try {
                switch (rechPar) {
                    case "Identifiant":
                        int identifiant = parseInt(textARechercher);
                        Usager usager = UsagerControl.findById(identifiant);
                        fillUsagerJtable(new ArrayList<Usager>() {
                            {
                                add(usager);
                            }
                        });
                        break;
                    case "Nom":
                        listUsagers = UsagerControl.findByNom(textARechercher);
                        fillUsagerJtable(listUsagers);
                        break;
                    case "Tout afficher":
                        listUsagers = UsagerControl.getListUsagers();
                        fillUsagerJtable(listUsagers);
                        break;
                }
            } catch (BibalExceptions e) {
                System.out.println("IHM.GestionUsager.RecherchBoutonActionPerformed() : Erreurs");
            } catch (NumberFormatException e) {
                showMessageSucces("Identifiant non valide");
            }
        }
    }                                              

    private void RecherchComboActionPerformed(java.awt.event.ActionEvent evt) {                                              

        if (RecherchCombo.getSelectedItem().equals("Tout afficher")) {
            try {
                ArrayList<Usager> listUsagers = UsagerControl.getListUsagers();
                fillUsagerJtable(listUsagers);
                RecherchBouton.setEnabled(false);
                RecherchField.setEnabled(false);
            } catch (BibalExceptions e) {
                System.out.println("IHM.GestionUsager.RecherchComboActionPerformed() : Erreurs");
            }
        } else {
            RecherchBouton.setEnabled(true);
            RecherchField.setEnabled(true);
        }
    }                                             

    private void ajouter(java.awt.event.ActionEvent evt) {                         
        try {
            Usager usager = getUsagerInfos();
            UsagerControl.ajouter(usager);
            setIdentifiant();
            clearField();
            ((ModelTableau) tableListeUsager.getModel()).addRow(
                    new Object[]{usager.getId(), usager.getNom(), usager.getPrenom(),
                        dateToStr(usager.getDateNais()), usager.getSexe(),
                        usager.getTel(), usager.getAdresse()});
            showMessageSucces("Usager ajouté avec succès");
        } catch (BibalExceptions e) {
            System.out.println("IHM.GestionUsager.ajouterBoutonActionPerformed()");
        }
    }                        

    private void telFieldActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private Usager getUsagerInfos() {
        int identifiant = parseInt(identifiantField.getText());
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        Date dateNais = dateNaisPicker.getDate();
        String sexe = civiliteCombo.getSelectedItem().toString().equals("M") ? "Masculin" : "Féminin";
        String adresse = adresseField.getText();
        String tel = telField.getText().replace("-", "").trim();
        return new Usager(identifiant, nom, prenom, dateNais, sexe, adresse, tel);
    }

    private void fillUsagerJtable(ArrayList<Usager> listUsagers) {
        String titre[] = new String[]{"Identifiant", "Nom",
            "Prénom", "Date de naissance", "Sexe", "Téléphone", "Adresse"};
        if (listUsagers.size() > 0) {
            int nbLigne = (null == listUsagers.get(0)) ? 0 : listUsagers.size();
            Object data[][] = new Object[nbLigne][titre.length];
            for (int i = 0; i < nbLigne; i++) {
                Usager usager = listUsagers.get(i);
                data[i][0] = usager.getId();
                data[i][1] = usager.getNom();
                data[i][2] = usager.getPrenom();
                data[i][3] = usager.getDateNais();
                data[i][4] = usager.getSexe();
                data[i][5] = usager.getTel();
                data[i][6] = usager.getAdresse();
            }
            ModelTableau model = new ModelTableau(data, titre);
            tableListeUsager.setModel(model);
            tableListeUsager.setRowHeight(30);
        } else {
            //afficher tableau vide
            Object data[][] = new Object[1][titre.length];
            ModelTableau model = new ModelTableau(data, titre);
            tableListeUsager.setModel(model);
            tableListeUsager.setRowHeight(1);
        }
    }

    private void clearField() {
        nomField.setText("");
        prenomField.setText("");
        adresseField.setText("");
        telField.setText("");
    }

    private void setIdentifiant() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            final String SQL_SELECT_ID = "SELECT id FROM usager ORDER BY id DESC LIMIT 1";
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(),
                    SQL_SELECT_ID, new Object[0]);
            resultSet = preparedStatement.executeQuery();
            int identifiant = 1;
            if (resultSet.first()) {
                identifiant = resultSet.getInt("id");
            }
            identifiantField.setText((identifiant + 1) + "");
        } catch (SQLException | BibalExceptions e) {
            JOptionPane.showMessageDialog(null, "Erreurs d'accès à la base de données" +e.getMessage(),
                    "Erreurs", JOptionPane.ERROR_MESSAGE);
        } finally {
            closeStatementResultSet(preparedStatement, resultSet);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int row = tableListeUsager.getSelectedRow();
        int nbreCol = tableListeUsager.getColumnCount();

        Object dataLigneSelected[] = new Object[nbreCol];
        if (row >= 0) {
            for (int i = 0; i < nbreCol; i++) {
                dataLigneSelected[i] = tableListeUsager.getModel().getValueAt(row, i);
            }
            identifiantField.setText(dataLigneSelected[0].toString());
            nomField.setText(dataLigneSelected[1].toString());
            prenomField.setText(dataLigneSelected[2].toString());
            try {
                dateNaisPicker.setDate(formatDate(YMDtoDMY(dataLigneSelected[3].toString(), "/")));
            } catch (BibalExceptions ex) {
                System.out.println("IHM.GestionUsager.mouseReleased()");
            }
            String sexe = dataLigneSelected[4].toString();
            String civilite = sexe.equalsIgnoreCase("Masculin") ? "M" : "Mme";
            civiliteCombo.setSelectedItem(civilite);
            telField.setText(dataLigneSelected[5].toString().replace(" ", ""));
            adresseField.setText(dataLigneSelected[6].toString());
            modifierBouton.setEnabled(true);
            supprimerButton.setEnabled(true);
            ajouterBouton.setEnabled(false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }}