package IHM;

import Utility.BibalExceptions;
import Utility.ModelTableau;
import static Utility.Utility.showMessageSucces;
import control.OeuvreControl;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import static javax.swing.JOptionPane.showConfirmDialog;
import javax.swing.table.*;
import objets_metiers.Livre;
import objets_metiers.Magazine;
import objets_metiers.Oeuvre;

/**
 *
 * @author Aymen Souelmi
 */
public class GestionOeuvresExemplaires extends JFrame implements MouseListener {

    private JButton RecherchBouton;
    private JComboBox RecherchCombo;
    private JFormattedTextField RecherchField;
    private JLabel RecherchParLabel;
    private JButton afficherButton;
    private JButton ajouterBouton;
    private JButton exemplaireButton;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JButton modifierBouton;
    private JPanel panListeOeuvre;
    private JPanel panRecherch;
    private JButton retourButton;
    private JButton supprimerButton;
    public static JTable tableListeOeuvre;

    public GestionOeuvresExemplaires() {

        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setResizable(false);
        tableListeOeuvre.addMouseListener(this);
        tableListeOeuvre.addMouseListener(this);
        tableListeOeuvre.setModel(new ModelTableau(new Object[0][0],
                new String[]{"ID", "Titre", "Auteur", "Catégorie", "Type", "Exemplaires", "Réservations"}));
        tableListeOeuvre.setRowHeight(30);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bibliotheque_icone.png")));
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        jPanel1 = new JPanel();
        panRecherch = new JPanel();
        RecherchParLabel = new JLabel();
        RecherchField = new JFormattedTextField();
        RecherchCombo = new JComboBox();
        RecherchBouton = new JButton();
        panListeOeuvre = new JPanel();
        jScrollPane1 = new JScrollPane();
        tableListeOeuvre = new JTable();
        jPanel2 = new JPanel();
        retourButton = new JButton();
        modifierBouton = new JButton();
        ajouterBouton = new JButton();
        exemplaireButton = new JButton();
        supprimerButton = new JButton();
        afficherButton = new JButton();
        jLabel1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion des oeuvres et des Exemplaires");
        setResizable(false);

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setName("Gestion personnel");
        jPanel1.setPreferredSize(new Dimension(1162, 608));
        jPanel1.setRequestFocusEnabled(false);

        panRecherch.setBackground(new Color(255, 255, 255));
        panRecherch.setBorder(BorderFactory.createTitledBorder("Rechercher"));

        RecherchParLabel.setText("Rechercher par  ");

        RecherchField.setHorizontalAlignment(JTextField.CENTER);
        RecherchField.setPreferredSize(new Dimension(130, 26));

        RecherchCombo.setModel(new DefaultComboBoxModel(new String[]{"Identifiant", "Titre", "Tout afficher"}));
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
                        .addGap(118, 118, 118)
                        .addGroup(panRecherchLayout.createParallelGroup()
                                .addGroup(panRecherchLayout.createSequentialGroup()
                                        .addComponent(RecherchField)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(RecherchBouton))
                                .addGroup(panRecherchLayout.createSequentialGroup()
                                        .addComponent(RecherchParLabel)
                                        .addGap(41, 41, 41)
                                        .addComponent(RecherchCombo)))
                        .addContainerGap(93, Short.MAX_VALUE))
        );
        panRecherchLayout.setVerticalGroup(
                panRecherchLayout.createParallelGroup()
                .addGroup(panRecherchLayout.createSequentialGroup()
                        .addGroup(panRecherchLayout.createParallelGroup()
                                .addComponent(RecherchParLabel)
                                .addComponent(RecherchCombo))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(panRecherchLayout.createParallelGroup()
                                .addComponent(RecherchField)
                                .addComponent(RecherchBouton))
                        .addContainerGap())
        );

        panListeOeuvre.setBackground(new Color(255, 255, 255));
        panListeOeuvre.setBorder(BorderFactory.createTitledBorder("Liste des Oeuvres"));

        jScrollPane1.getViewport().setBackground(Color.white);
        jScrollPane1.setBackground(new Color(255, 255, 255));

        tableListeOeuvre.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Titre", "Auteur", "Catégorie", "Type", "Exemplaires", "Réservations"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tableListeOeuvre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableListeOeuvre.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableListeOeuvre);
        if (tableListeOeuvre.getColumnModel().getColumnCount() > 0) {
            tableListeOeuvre.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        GroupLayout panListeOeuvreLayout = new GroupLayout(panListeOeuvre);
        panListeOeuvre.setLayout(panListeOeuvreLayout);
        panListeOeuvreLayout.setHorizontalGroup(
                panListeOeuvreLayout.createParallelGroup()
                .addGroup(panListeOeuvreLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1)
                        .addGap(0, 0, 0))
        );
        panListeOeuvreLayout.setVerticalGroup(
                panListeOeuvreLayout.createParallelGroup()
                .addComponent(jScrollPane1)
        );

        jPanel2.setBackground(new Color(255, 255, 255));
        jPanel2.setBorder(BorderFactory.createTitledBorder(""));

        retourButton.setFont(new Font("Times New Roman", 1, 18));
        retourButton.setText("Retour");
        retourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                retourButtonActionPerformed(evt);
            }
        });

        modifierBouton.setFont(new Font("Times New Roman", 1, 18));
        modifierBouton.setText("Modifier");
        modifierBouton.setEnabled(false);
        modifierBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                modifierBoutonActionPerformed(evt);
            }
        });

        ajouterBouton.setFont(new Font("Times New Roman", 1, 18));
        ajouterBouton.setText("Ajouter");
        ajouterBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ajouterBoutonActionPerformed(evt);
            }
        });

        exemplaireButton.setFont(new Font("Times New Roman", 1, 18));
        exemplaireButton.setText("Exemplaire");
        exemplaireButton.setEnabled(false);
        exemplaireButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exemplaireButtonActionPerformed(evt);
            }
        });

        supprimerButton.setFont(new Font("Times New Roman", 1, 18));
        supprimerButton.setText("Supprimer");
        supprimerButton.setEnabled(false);
        supprimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                supprimerButtonActionPerformed(evt);
            }
        });

        afficherButton.setFont(new Font("Times New Roman", 1, 18));
        afficherButton.setText("Afficher");
        afficherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                afficherButtonActionPerformed(evt);
            }
        });

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup()
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup()
                                .addComponent(exemplaireButton)
                                .addComponent(retourButton)
                                .addComponent(modifierBouton)
                                .addComponent(ajouterBouton)
                                .addComponent(supprimerButton)
                                .addComponent(afficherButton))
                        .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup()
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ajouterBouton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(afficherButton)
                        .addGap(43, 43, 43)
                        .addComponent(modifierBouton)
                        .addGap(47, 47, 47)
                        .addComponent(supprimerButton)
                        .addGap(45, 45, 45)
                        .addComponent(exemplaireButton)
                        .addGap(45, 45, 45)
                        .addComponent(retourButton)
                        .addGap(33, 33, 33))
        );

        jLabel1.setFont(new Font("Times New Roman", 1, 36));
        jLabel1.setForeground(new Color(19, 102, 170));
        jLabel1.setText("Gestion des Oeuvres et Exemplaires");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(panRecherch)
                                .addComponent(panListeOeuvre))
                        .addGap(3, 3, 3))
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel1)
                        .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(panRecherch)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panListeOeuvre))
                                .addComponent(jPanel2))
                        .addGap(3, 3, 3))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new Dimension(837, 584));
        setLocationRelativeTo(null);
    }

    private void retourButtonActionPerformed(ActionEvent evt) {
        Menu m = new Menu();
        this.dispose();
        m.setVisible(true);
    }

    private void RecherchBoutonActionPerformed(ActionEvent evt) {
        String rechPar = RecherchCombo.getSelectedItem().toString();
        String textARechercher = RecherchField.getText().trim();
        if (textARechercher.length() > 0) {
            ArrayList<Oeuvre> listOeuvres;
            try {
                switch (rechPar) {
                    case "Identifiant":
                        int identifiant = parseInt(textARechercher);
                        Oeuvre oeuvre = OeuvreControl.findById(identifiant);
                        fillOeuvreJtable(new ArrayList<Oeuvre>() {
                            {
                                add(oeuvre);
                            }
                        });
                        break;
                    case "Titre":
                        listOeuvres = OeuvreControl.findByTitre(textARechercher);
                        fillOeuvreJtable(listOeuvres);
                        break;
                    case "Tout afficher":
                        listOeuvres = OeuvreControl.getListOeuvres();
                        fillOeuvreJtable(listOeuvres);
                        break;
                }
            } catch (BibalExceptions e) {
                System.out.println("IHM.GestionUsager.RecherchBoutonActionPerformed() : Erreurs");
            } catch (NumberFormatException e) {
                showMessageSucces("Identifiant non valide");
            }
        }
    }

    private void RecherchComboActionPerformed(ActionEvent evt) {

        if (RecherchCombo.getSelectedItem().equals("Tout afficher")) {
            try {
                ArrayList<Oeuvre> listOeuvres = OeuvreControl.getListOeuvres();
                fillOeuvreJtable(listOeuvres);
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

    private void ajouterBoutonActionPerformed(ActionEvent evt) {

        AjoutOeuvre ajoutOeuvre = new AjoutOeuvre(this, true);
        ajoutOeuvre.setLocationRelativeTo(null);
        ajoutOeuvre.setVisible(true);
    }

    private void modifierBoutonActionPerformed(ActionEvent evt) {
        Oeuvre oeuvre = getOeuvreInfos();
        if (null != oeuvre) {
            ModificationOeuvre modificationOeuvre = new ModificationOeuvre(this, true, oeuvre);
            modificationOeuvre.setLocationRelativeTo(null);
            modificationOeuvre.setVisible(true);
        }
    }

    private void afficherButtonActionPerformed(ActionEvent evt) {
        try {
            ArrayList<Oeuvre> listOeuvres = OeuvreControl.getListOeuvres();
            fillOeuvreJtable(listOeuvres);
        } catch (BibalExceptions ex) {
            System.out.println("IHM.GestionOeuvre.afficherButtonActionPerformed()");
        }
    }

    private void supprimerButtonActionPerformed(ActionEvent evt) {

 try {
            Oeuvre oeuvre = getOeuvreInfos();
            String message;
            if (null != oeuvre) {
                if (oeuvre.getExamplairesOeuvre().size() > 0) {
                    message = "Vous ne pouvez pas supprimer cette oeuvre\n"
                            + " Elle a " + oeuvre.getExamplairesOeuvre().size() + " exemplaire"
                            + (oeuvre.getExamplairesOeuvre().size() > 1 ? "s" : "");
                    showMessageSucces(message);
                } else {
                    message = "Cette oeuvre a " + oeuvre.getExamplairesOeuvre().size() + " exemplaire\n"
                            + "\n Voulez-vous la supprimer ?";
                    int reponse = showConfirmDialog(null, message,
                            "Avertissement", JOptionPane.OK_CANCEL_OPTION);
                    if (reponse == JOptionPane.OK_OPTION) {
                        OeuvreControl.supprimer(oeuvre);
                        ((ModelTableau) tableListeOeuvre.getModel()).removeRow(tableListeOeuvre.getSelectedRow());
                        showMessageSucces("L'Oeuvre a bien été supprimée");

                    }
                }
            }

        } catch (BibalExceptions ex) {
            System.out.println("IHM.GestionOeuvresExemplaires.supprimerButtonActionPerformed()");
        }
    }

    private void exemplaireButtonActionPerformed(ActionEvent evt) {
        Oeuvre oeuvre = getOeuvreInfos();
        if (null != oeuvre) {
            GestionExemplaires exemplaires = new GestionExemplaires(this, true, getOeuvreInfos());
            exemplaires.setLocationRelativeTo(null);
            exemplaires.setVisible(true);
        }
    }

    private Oeuvre getOeuvreInfos() {
        Oeuvre oeuvre = null;
        if (tableListeOeuvre.getSelectedRow() != -1) {
            int id = parseInt(dataLigneSelected[0].toString());
            String titre = dataLigneSelected[1].toString();
            String auteur = dataLigneSelected[2].toString();
            String categorie = dataLigneSelected[3].toString();
            String typeOeuvre = dataLigneSelected[4].toString();
            int nbExemplaire = parseInt(dataLigneSelected[5].toString());
            int nbResa = parseInt(dataLigneSelected[6].toString());

            if (typeOeuvre.equals(Magazine.class.getSimpleName())) {
                oeuvre = new Magazine(id, titre, auteur, categorie, nbResa);
            } else {
                oeuvre = new Livre(id, titre, auteur, categorie, nbResa);
            }
            oeuvre.getExamplairesOeuvre().setSize(nbExemplaire);
        }
        return oeuvre;

    }

    private void fillOeuvreJtable(ArrayList<Oeuvre> listOeuvres) {
        String titre[] = new String[]{"ID", "Titre",
            "Auteur", "Catégorie", "Type", "Exemplaires", "Réservations"};
        if (listOeuvres.size() > 0) {
//            pour le findByID si l'objet ajouté est null
            int nbLigne = (null == listOeuvres.get(0)) ? 0 : listOeuvres.size();
            Object data[][] = new Object[nbLigne][titre.length];
            for (int i = 0; i < nbLigne; i++) {
                Oeuvre oeuvre = listOeuvres.get(i);
                data[i][0] = oeuvre.getId();
                data[i][1] = oeuvre.getTitre();
                data[i][2] = oeuvre.getAuteur();
                data[i][3] = oeuvre.getCategorie();
                data[i][4] = oeuvre.getClass().getSimpleName();
                data[i][5] = oeuvre.getExamplairesOeuvre().size();
                data[i][6] = oeuvre.getNbResa();
            }
            ModelTableau model = new ModelTableau(data, titre);
            tableListeOeuvre.setModel(model);
            tableListeOeuvre.setRowHeight(30);
        } else {
            //afficher tableau vide
            Object data[][] = new Object[1][titre.length];
            ModelTableau model = new ModelTableau(data, titre);
            tableListeOeuvre.setModel(model);
            tableListeOeuvre.setRowHeight(1);
        }
        modifierBouton.setEnabled(false);
        supprimerButton.setEnabled(false);
        exemplaireButton.setEnabled(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int row = tableListeOeuvre.getSelectedRow();
        int nbreCol = tableListeOeuvre.getColumnCount();

        dataLigneSelected = new Object[nbreCol];
        if (row >= 0) {
            for (int i = 0; i < nbreCol; i++) {
                dataLigneSelected[i] = tableListeOeuvre.getModel().getValueAt(row, i);
            }
            modifierBouton.setEnabled(true);
            supprimerButton.setEnabled(true);
            exemplaireButton.setEnabled(true);
        } else {
            modifierBouton.setEnabled(false);
            supprimerButton.setEnabled(false);
            exemplaireButton.setEnabled(true);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private Object dataLigneSelected[];
}
