package IHM;

import Utility.BibalExceptions;
import Utility.ModelTableau;
import static Utility.Utility.showMessageSucces;
import control.EmpruntControl;
import control.ExemplaireControl;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.text.*;
import java.util.ArrayList;
import static javax.swing.JOptionPane.showConfirmDialog;
import objets_metiers.Exemplaire;
import objets_metiers.Livre;
import objets_metiers.Magazine;
import objets_metiers.Oeuvre;

/**
 *
 * @author Aymen Souelmi
 */
public class GestionExemplaires extends JDialog implements MouseListener {

    private JButton afficherButton;
    private JButton ajouterButton;
    private JButton annulerButton;
    private JLabel auteurLabel;
    private JLabel auteurLabelValue;
    private JLabel categorieLabel;
    private JLabel categorieLabelValue;
    private JLabel identifiantLabel;
    private JLabel identifiantLabelValue;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane2;
    private JButton modifierButton;
    private JLabel nbExemplaire;
    private static JLabel nbExemplaireLabelValue;
    private JPanel pan;
    private JPanel panAjoutOeuvre;
    private JPanel panExemplaires;
    private JButton retirerButton;
    public static JTable tableExemplaires;
    private JLabel titleLabel;
    private JLabel titreLabel;
    private JLabel titreLabelValue;
    private JLabel typeOeuvreLabel;
    private JLabel typeOeuvreLabelValue;
    private Object dataLigneSelected[];

    public GestionExemplaires(Frame parent, boolean modal) {
        
          super(parent, modal);
        initComponents();
        tableExemplaires.addMouseListener(this);
        tableExemplaires.addMouseListener(this);
        tableExemplaires.setModel(new ModelTableau(new Object[0][0],
                new String[]{"ID", "Etat"}));
        tableExemplaires.setRowHeight(30);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bibliotheque_icone.png")));
    
    }

    public GestionExemplaires(Frame parent, boolean modal,Oeuvre oeuvre) {
        this(parent, modal);

        identifiantLabelValue.setText(oeuvre.getId() + "");
        titreLabelValue.setText(oeuvre.getTitre());
        categorieLabelValue.setText(oeuvre.getCategorie());
        typeOeuvreLabelValue.setText(oeuvre.getClass().getSimpleName());
        auteurLabelValue.setText(oeuvre.getAuteur());
        nbExemplaireLabelValue.setText(oeuvre.getExamplairesOeuvre().size() + "");
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        jPanel1 = new JPanel();
        panAjoutOeuvre = new JPanel();
        pan = new JPanel();
        jPanel2 = new JPanel();
        typeOeuvreLabel = new JLabel();
        identifiantLabel = new JLabel();
        auteurLabel = new JLabel();
        titreLabel = new JLabel();
        categorieLabel = new JLabel();
        nbExemplaire = new JLabel();
        identifiantLabelValue = new JLabel();
        titreLabelValue = new JLabel();
        categorieLabelValue = new JLabel();
        typeOeuvreLabelValue = new JLabel();
        auteurLabelValue = new JLabel();
        nbExemplaireLabelValue = new JLabel();
        jPanel3 = new JPanel();
        ajouterButton = new JButton();
        modifierButton = new JButton();
        retirerButton = new JButton();
        afficherButton = new JButton();
        annulerButton = new JButton();
        panExemplaires = new JPanel();
        jScrollPane2 = new JScrollPane();
        tableExemplaires = new JTable();
        titleLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion Exemplaire");
        setResizable(false);

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setName("Gestion personnel");
        jPanel1.setPreferredSize(new Dimension(1162, 608));
        jPanel1.setRequestFocusEnabled(false);

        panAjoutOeuvre.setBackground(new Color(255, 255, 255));

        pan.setBackground(new Color(255, 255, 255));
        pan.setBorder(BorderFactory.createTitledBorder(""));

        jPanel2.setBackground(new Color(255, 255, 255));
        jPanel2.setBorder(BorderFactory.createTitledBorder(""));

        typeOeuvreLabel.setFont(new Font("Times New Roman", 1, 14)); 
        typeOeuvreLabel.setForeground(new Color(19, 102, 170));
        typeOeuvreLabel.setText("Type de l'oeuvre");

        identifiantLabel.setFont(new Font("Times New Roman", 1, 14)); 
        identifiantLabel.setForeground(new Color(19, 102, 170));
        identifiantLabel.setText("Identifiant ");

        auteurLabel.setFont(new Font("Times New Roman", 1, 14)); 
        auteurLabel.setForeground(new Color(19, 102, 170));
        auteurLabel.setText("Auteur");

        titreLabel.setFont(new Font("Times New Roman", 1, 14)); 
        titreLabel.setForeground(new Color(19, 102, 170));
        titreLabel.setText("Titre");

        categorieLabel.setFont(new Font("Times New Roman", 1, 14));
        categorieLabel.setForeground(new Color(19, 102, 170));
        categorieLabel.setText("Catégorie");

        nbExemplaire.setFont(new Font("Times New Roman", 1, 14)); 
        nbExemplaire.setForeground(new Color(19, 102, 170));
        nbExemplaire.setText("Nombre \nd'exemplaire");

        identifiantLabelValue.setFont(new Font("Times New Roman", 1, 14)); 
        identifiantLabelValue.setText("1");

        titreLabelValue.setFont(new Font("Times New Roman", 1, 14));
        titreLabelValue.setText("1");

        categorieLabelValue.setFont(new Font("Times New Roman", 1, 14));
        categorieLabelValue.setText("1");

        typeOeuvreLabelValue.setFont(new Font("Times New Roman", 1, 14));
        typeOeuvreLabelValue.setText("1");

        auteurLabelValue.setFont(new Font("Times New Roman", 1, 14));
        auteurLabelValue.setText("1");

        nbExemplaireLabelValue.setFont(new Font("Times New Roman", 1, 14));
        nbExemplaireLabelValue.setText("1");

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup()
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup()
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(identifiantLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(identifiantLabelValue))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup()
                                                .addComponent(titreLabel)
                                                .addComponent(categorieLabel))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup()
                                                .addComponent(categorieLabelValue)
                                                .addComponent(titreLabelValue))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup()
                                .addComponent(typeOeuvreLabel)
                                .addComponent(nbExemplaire)
                                .addComponent(auteurLabel))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup()
                                .addComponent(auteurLabelValue)
                                .addComponent(nbExemplaireLabelValue)
                                .addComponent(typeOeuvreLabelValue))
                        .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup()
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup()
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup()
                                                .addComponent(typeOeuvreLabel)
                                                .addComponent(typeOeuvreLabelValue))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup()
                                                .addComponent(auteurLabel)
                                                .addComponent(auteurLabelValue))
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel2Layout.createParallelGroup()
                                                .addComponent(nbExemplaire)
                                                .addComponent(nbExemplaireLabelValue)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup()
                                                .addComponent(identifiantLabel)
                                                .addComponent(identifiantLabelValue))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup()
                                                .addComponent(titreLabel)
                                                .addComponent(titreLabelValue))
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel2Layout.createParallelGroup()
                                                .addComponent(categorieLabelValue)
                                                .addComponent(categorieLabel))))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new Color(255, 255, 255));
        jPanel3.setBorder(BorderFactory.createTitledBorder(""));

        ajouterButton.setFont(new Font("Times New Roman", 1, 18));
        ajouterButton.setText("Ajouter");
        ajouterButton.setPreferredSize(new Dimension(95, 31));
        ajouterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ajouterButtonActionPerformed(evt);
            }
        });

        modifierButton.setFont(new Font("Times New Roman", 1, 18));
        modifierButton.setText("Modifier");
        modifierButton.setEnabled(false);
        modifierButton.setPreferredSize(new Dimension(95, 31));
        modifierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                modifierButtonActionPerformed(evt);
            }
        });

        retirerButton.setFont(new Font("Times New Roman", 1, 18));
        retirerButton.setText("Retirer");
        retirerButton.setEnabled(false);
        retirerButton.setPreferredSize(new Dimension(95, 31));
        retirerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                retirer(evt);
            }
        });

        afficherButton.setFont(new Font("Times New Roman", 1, 18));
        afficherButton.setText("Afficher");
        afficherButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                afficher(evt);
            }
        });

        annulerButton.setFont(new Font("Times New Roman", 1, 18));
        annulerButton.setText("Annuler");
        annulerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                annulerButtonActionPerformed(evt);
            }
        });

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(ajouterButton)
                                .addComponent(modifierButton)
                                .addComponent(afficherButton)
                                .addComponent(retirerButton)
                                .addComponent(annulerButton))
                        .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup()
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(ajouterButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(afficherButton)
                        .addGap(40, 40, 40)
                        .addComponent(modifierButton)
                        .addGap(38, 38, 38)
                        .addComponent(retirerButton)
                        .addGap(37, 37, 37)
                        .addComponent(annulerButton)
                        .addGap(22, 22, 22))
        );

        panExemplaires.setBackground(new Color(255, 255, 255));
        panExemplaires.setBorder(BorderFactory.createTitledBorder("Exemplaires"));

        jScrollPane2.getViewport().setBackground(Color.white);
        jScrollPane2.setBackground(new Color(255, 255, 255));

        tableExemplaires.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Etat"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tableExemplaires.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableExemplaires.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tableExemplaires);

        GroupLayout panExemplairesLayout = new GroupLayout(panExemplaires);
        panExemplaires.setLayout(panExemplairesLayout);
        panExemplairesLayout.setHorizontalGroup(
                panExemplairesLayout.createParallelGroup()
                .addGroup(panExemplairesLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane2))
        );
        panExemplairesLayout.setVerticalGroup(
                panExemplairesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        GroupLayout panLayout = new GroupLayout(pan);
        pan.setLayout(panLayout);
        panLayout.setHorizontalGroup(
                panLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(panLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(panLayout.createSequentialGroup()
                                        .addComponent(jPanel2)
                                        .addGap(2, 2, 2))
                                .addGroup(panLayout.createSequentialGroup()
                                        .addComponent(jPanel3)
                                        .addGap(5, 5, 5)
                                        .addComponent(panExemplaires))))
        );
        panLayout.setVerticalGroup(
                panLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panLayout.createParallelGroup()
                                .addComponent(jPanel3)
                                .addComponent(panExemplaires))
                        .addGap(2, 2, 2))
        );

        GroupLayout panAjoutOeuvreLayout = new GroupLayout(panAjoutOeuvre);
        panAjoutOeuvre.setLayout(panAjoutOeuvreLayout);
        panAjoutOeuvreLayout.setHorizontalGroup(
                panAjoutOeuvreLayout.createParallelGroup()
                .addComponent(pan)
        );
        panAjoutOeuvreLayout.setVerticalGroup(
                panAjoutOeuvreLayout.createParallelGroup()
                .addGroup(panAjoutOeuvreLayout.createSequentialGroup()
                        .addComponent(pan)
                        .addGap(0, 0, Short.MAX_VALUE))
        );

        titleLabel.setFont(new Font("Times New Roman", 1, 36));
        titleLabel.setForeground(new Color(19, 102, 170));
        titleLabel.setText("Fiche Oeuvre");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(titleLabel)
                        .addContainerGap(234, Short.MAX_VALUE))
                .addComponent(panAjoutOeuvre)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(titleLabel)
                        .addGap(2, 2, 2)
                        .addComponent(panAjoutOeuvre))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                .addComponent(jPanel1)
        );

        pack();
        setLocationRelativeTo(null);
    }                    

    private void ajouterButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        Oeuvre oeuvre = getOeuvreInfos();
        if (null != oeuvre) {
            AjoutExemplaire ajoutExemplaire = new AjoutExemplaire(null, true, oeuvre);
            ajoutExemplaire.setLocationRelativeTo(null);
            ajoutExemplaire.setVisible(true);
        }
    }                                             

    private void annulerButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        this.dispose();
    }                                             

    private void modifierButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               

        Exemplaire exemplaire = getExempalireInfos();
        if (null != exemplaire) {
            ModificationExemplaire modificationExemplaire
                    = new ModificationExemplaire(null, true, exemplaire);
            modificationExemplaire.setLocationRelativeTo(null);
            modificationExemplaire.setVisible(true);
        }
    }                                              

    private void afficher(java.awt.event.ActionEvent evt) {                          
        try {
            Oeuvre oeuvre = getOeuvreInfos();
            ArrayList<Exemplaire> listExemplaires = ExemplaireControl.find(oeuvre);
            if (null != listExemplaires) {
                fillExemplaireJtable(listExemplaires);
            } else {
                showMessageSucces("Aucun exemplaire trouvé");
            }

        } catch (BibalExceptions e) {
            System.out.println("IHM.GestionOeuvre.afficherButtonActionPerformed()");
        }
    }                         

    private void retirer(java.awt.event.ActionEvent evt) {                         
        String message = "\nSupprimer cet exempalaire ?\n"
                + "Cela supprimera aussi tous les emprunts de cet exemplaire\n";

        int reponse = showConfirmDialog(null, message,
                "Avertissement", JOptionPane.OK_CANCEL_OPTION);
        if (reponse == JOptionPane.OK_OPTION) {
            try {
                Exemplaire exemplaire = getExempalireInfos();
                if (null != exemplaire) {
                    EmpruntControl.supprimer(exemplaire);
                    ExemplaireControl.supprimer(exemplaire);
                    ((ModelTableau) tableExemplaires.getModel()).removeRow(tableExemplaires.getSelectedRow());
                    showMessageSucces("L'exemplaire a bien été supprimée");
                    modifierButton.setEnabled(false);
                    retirerButton.setEnabled(false);
                }

            } catch (BibalExceptions ex) {
                System.out.println("IHM.Exemplaires.retirerButtonActionPerformed()" + ex.getMessage());
            }
        }
    }                        

    private Oeuvre getOeuvreInfos() {
        int id = Integer.parseInt(identifiantLabelValue.getText());
        String titre = titreLabelValue.getText();
        String auteur = auteurLabelValue.getText();
        String categorie = categorieLabelValue.getText();
        String typeOeuvre = typeOeuvreLabelValue.getText();
        Oeuvre oeuvre;
        if (typeOeuvre.equals(Magazine.class.getSimpleName())) {
            oeuvre = new Magazine(id, titre, auteur, categorie);
        } else {
            oeuvre = new Livre(id, titre, auteur, categorie);
        }
        return oeuvre;
    }

    private Exemplaire getExempalireInfos() {
        int id = Integer.parseInt(dataLigneSelected[0].toString());
        String etatExemplaire = dataLigneSelected[1].toString();
        return new Exemplaire(id, etatExemplaire);
    }

    private void fillExemplaireJtable(ArrayList<Exemplaire> listExemplaires) {
        String titre[] = new String[]{"ID", "Etat"};
        if (listExemplaires.size() > 0) {
//            pour le findByID si l'objet ajouté est null
            int nbLigne = (null == listExemplaires.get(0)) ? 0 : listExemplaires.size();
            Object data[][] = new Object[nbLigne][titre.length];
            for (int i = 0; i < nbLigne; i++) {
                Exemplaire exemplaire = listExemplaires.get(i);
                data[i][0] = exemplaire.getId();
                data[i][1] = exemplaire.getEtat();
            }
            ModelTableau model = new ModelTableau(data, titre);
            tableExemplaires.setModel(model);
            tableExemplaires.setRowHeight(30);
        } else {
            //afficher tableau vide
            Object data[][] = new Object[1][titre.length];
            ModelTableau model = new ModelTableau(data, titre);
            tableExemplaires.setModel(model);
            tableExemplaires.setRowHeight(1);
        }
        modifierButton.setEnabled(false);
        retirerButton.setEnabled(false);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int row = tableExemplaires.getSelectedRow();
        int nbreCol = tableExemplaires.getColumnCount();

        dataLigneSelected = new Object[nbreCol];
        if (row >= 0) {
            for (int i = 0; i < nbreCol; i++) {
                dataLigneSelected[i] = tableExemplaires.getModel().getValueAt(row, i);
            }
            modifierButton.setEnabled(true);
            retirerButton.setEnabled(true);
        } else {
            modifierButton.setEnabled(false);
            retirerButton.setEnabled(false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    public static void setNbExemplaireLabelValue(String value) {
        GestionExemplaires.nbExemplaireLabelValue.setText(value);
    }

    public static String getNbExemplaireLabelValue() {
        return nbExemplaireLabelValue.getText();
    }}