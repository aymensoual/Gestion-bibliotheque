package IHM;

import Utility.BibalExceptions;
import Utility.DBConnection;
import Utility.ModelTableau;
import static Utility.Utility.closeStatementResultSet;
import static Utility.Utility.initialiseRequetePreparee;
import static Utility.Utility.showMessageSucces;
import control.EmpruntControl;
import control.ExemplaireControl;
import control.OeuvreControl;
import control.ReservationControl;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import objets_metiers.Emprunt;
import objets_metiers.Exemplaire;
import objets_metiers.Livre;
import objets_metiers.Magazine;
import objets_metiers.Oeuvre;
import objets_metiers.Reservation;
import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 *
 * @author Aymen Souelmi
 */
public class GestionReservationsEmprunts extends JFrame {

    private JPanel EmpruntPanel;
    private JButton ReservationButton;
    private JPanel ReservationPanel;
    private JButton affEmpButton;
    private JButton affExempButton;
    private JButton affResaButton;
    private JButton annulerResaButton;
    private JLabel auteurLabel1;
    private JLabel auteurLabelValue;
    private JLabel categorieLabel1;
    private JLabel categorieLabelValue;
    private JButton emprunterButton;
    private JButton empruntsButton;
    private JComboBox<String> identifiantComboBox;
    private JLabel identifiantLabel1;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane5;
    private JPanel mainPanel;
    private JPanel menuEmpPanel;
    private JPanel menuResaPanel;
    private JLabel nbExemplaire1;
    private JLabel nbExemplaireLabelValue;
    private JPanel panEmpCours;
    private JPanel panExemplaires;
    private JPanel panResaCours;
    private JButton rendreButton;
    private JButton reserverButton;
    private JButton retourButton;
    public static JTable tableEmpCours;
    public static JTable tableExemplaires;
    public static JTable tableResaCours;
    private JLabel titleResaEmpLabel;
    private JLabel titreLabel1;
    private JLabel titreLabelValue;
    private JLabel typeOeuvreLabel1;
    private JLabel typeOeuvreLabelValue;


    public GestionReservationsEmprunts() {
        initComponents();
        setIdentifiant();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bibliotheque_icone.png")));
        tableExemplaires.setModel(new ModelTableau(new Object[0][0],
                new String[]{"ID", "Etat"}));
        tableExemplaires.setRowHeight(30);

        tableEmpCours.setModel(new ModelTableau(new Object[0][0],
                new String[]{"ID", "Exemplaire", "Usager",
                    "Date Emprunt", "Date Retour"}));
        tableEmpCours.setRowHeight(30);

        tableResaCours.setModel(new ModelTableau(new Object[0][0],
                new String[]{"ID", "Oeuvre", "Usager",
                    "Date Réservation"}));
        tableResaCours.setRowHeight(30);

        tableExemplaires.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int row = tableExemplaires.getSelectedRow();
                int nbreCol = tableExemplaires.getColumnCount();

                dataLigneSelectedExemplaires = new Object[nbreCol];
                if (row >= 0) {
                    for (int i = 0; i < nbreCol; i++) {
                        dataLigneSelectedExemplaires[i] = tableExemplaires.getModel().getValueAt(row, i);
                    }
                    emprunterButton.setEnabled(true);
                } else {
                    emprunterButton.setEnabled(false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        tableEmpCours.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int row = tableEmpCours.getSelectedRow();
                int nbreCol = tableEmpCours.getColumnCount();

                dataLigneSelectedEmprunts = new Object[nbreCol];
                if (row >= 0) {
                    for (int i = 0; i < nbreCol; i++) {
                        dataLigneSelectedEmprunts[i] = tableEmpCours.getModel().getValueAt(row, i);
                    }
                    rendreButton.setEnabled(true);
                } else {
                    rendreButton.setEnabled(false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        tableResaCours.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int row = tableResaCours.getSelectedRow();
                int nbreCol = tableResaCours.getColumnCount();

                dataLigneSelectedReservations = new Object[nbreCol];
                if (row >= 0) {
                    for (int i = 0; i < nbreCol; i++) {
                        dataLigneSelectedReservations[i] = tableResaCours.getModel().getValueAt(row, i);
                    }
                    annulerResaButton.setEnabled(true);
                } else {
                    annulerResaButton.setEnabled(false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    
    @SuppressWarnings("unchecked")

    private void initComponents() {

        jLabel1 = new JLabel();
        jPanel2 = new JPanel();
        mainPanel = new JPanel();
        EmpruntPanel = new JPanel();
        panEmpCours = new JPanel();
        jScrollPane1 = new JScrollPane();
        tableEmpCours = new JTable();
        menuEmpPanel = new JPanel();
        rendreButton = new JButton();
        emprunterButton = new JButton();
        affEmpButton = new JButton();
        affExempButton = new JButton();
        panExemplaires = new JPanel();
        jScrollPane2 = new JScrollPane();
        tableExemplaires = new JTable();
        ReservationPanel = new JPanel();
        panResaCours = new JPanel();
        jScrollPane5 = new JScrollPane();
        tableResaCours = new JTable();
        menuResaPanel = new JPanel();
        annulerResaButton = new JButton();
        reserverButton = new JButton();
        affResaButton = new JButton();
        jPanel1 = new JPanel();
        empruntsButton = new JButton();
        ReservationButton = new JButton();
        retourButton = new JButton();
        titleResaEmpLabel = new JLabel();
        jPanel3 = new JPanel();
        typeOeuvreLabel1 = new JLabel();
        identifiantLabel1 = new JLabel();
        auteurLabel1 = new JLabel();
        titreLabel1 = new JLabel();
        categorieLabel1 = new JLabel();
        nbExemplaire1 = new JLabel();
        titreLabelValue = new JLabel();
        categorieLabelValue = new JLabel();
        typeOeuvreLabelValue = new JLabel();
        auteurLabelValue = new JLabel();
        nbExemplaireLabelValue = new JLabel();
        identifiantComboBox = new JComboBox<>();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion Réservations et Oeuvres");
        setBackground(new Color(255, 255, 255));
        setResizable(false);

        jPanel2.setBackground(new Color(255, 255, 255));

        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setLayout(new CardLayout());

        EmpruntPanel.setBackground(new Color(255, 255, 255));

        panEmpCours.setBackground(new Color(255, 255, 255));
        panEmpCours.setBorder(BorderFactory.createTitledBorder("Emprunts en cours des exempalaires de l'oeuvre"));

        jScrollPane1.getViewport().setBackground(Color.white);
        jScrollPane1.setBackground(new Color(255, 255, 255));

        tableEmpCours.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Exemplaire", "Usager", "Date emprunt", "Date Retour"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tableEmpCours.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableEmpCours.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableEmpCours);

        GroupLayout panEmpCoursLayout = new GroupLayout(panEmpCours);
        panEmpCours.setLayout(panEmpCoursLayout);
        panEmpCoursLayout.setHorizontalGroup(
                panEmpCoursLayout.createParallelGroup()
                .addGroup(panEmpCoursLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
        );
        panEmpCoursLayout.setVerticalGroup(
                panEmpCoursLayout.createParallelGroup()
                .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        );

        menuEmpPanel.setBackground(new Color(255, 255, 255));
        menuEmpPanel.setBorder(BorderFactory.createTitledBorder(""));

        rendreButton.setFont(new Font("Times New Roman", 1, 18)); 
        rendreButton.setText("Rendre");
        rendreButton.setEnabled(false);
        rendreButton.setPreferredSize(new Dimension(95, 31));
        rendreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rendre(evt);
            }
        });

        emprunterButton.setFont(new Font("Times New Roman", 1, 18)); 
        emprunterButton.setText("Emprunter");
        emprunterButton.setEnabled(false);
        emprunterButton.setPreferredSize(new Dimension(95, 31));
        emprunterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                emprunter(evt);
            }
        });

        affEmpButton.setFont(new Font("Times New Roman", 1, 18)); 
        affEmpButton.setText("Afficher emprunts");
        affEmpButton.setEnabled(false);
        affEmpButton.setPreferredSize(new Dimension(95, 31));
        affEmpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                affEmpButtonActionPerformed(evt);
            }
        });

        affExempButton.setFont(new Font("Times New Roman", 1, 18)); 
        affExempButton.setText("Afficher exemplaires");
        affExempButton.setEnabled(false);
        affExempButton.setPreferredSize(new Dimension(95, 31));
        affExempButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                affExempButtonActionPerformed(evt);
            }
        });

        GroupLayout menuEmpPanelLayout = new GroupLayout(menuEmpPanel);
        menuEmpPanel.setLayout(menuEmpPanelLayout);
        menuEmpPanelLayout.setHorizontalGroup(
                menuEmpPanelLayout.createParallelGroup()
                .addGroup(menuEmpPanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(menuEmpPanelLayout.createParallelGroup()
                                .addComponent(affEmpButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(emprunterButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(menuEmpPanelLayout.createParallelGroup()
                                .addComponent(rendreButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addComponent(affExempButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70))
        );
        menuEmpPanelLayout.setVerticalGroup(
                menuEmpPanelLayout.createParallelGroup()
                .addGroup(menuEmpPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(menuEmpPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(rendreButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(emprunterButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(menuEmpPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(affEmpButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(affExempButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
        );

        panExemplaires.setBackground(new Color(255, 255, 255));
        panExemplaires.setBorder(BorderFactory.createTitledBorder("Exemplaires Disponibles"));

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
                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
        );
        panExemplairesLayout.setVerticalGroup(
                panExemplairesLayout.createParallelGroup()
                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        GroupLayout EmpruntPanelLayout = new GroupLayout(EmpruntPanel);
        EmpruntPanel.setLayout(EmpruntPanelLayout);
        EmpruntPanelLayout.setHorizontalGroup(
                EmpruntPanelLayout.createParallelGroup()
                .addGroup(EmpruntPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(EmpruntPanelLayout.createParallelGroup()
                                .addComponent(menuEmpPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(EmpruntPanelLayout.createSequentialGroup()
                                        .addComponent(panEmpCours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panExemplaires, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        .addGap(2, 2, 2))
        );
        EmpruntPanelLayout.setVerticalGroup(
                EmpruntPanelLayout.createParallelGroup()
                .addGroup(EmpruntPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(menuEmpPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(EmpruntPanelLayout.createParallelGroup()
                                .addComponent(panEmpCours, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panExemplaires, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );

        mainPanel.add(EmpruntPanel, "panel1");

        ReservationPanel.setBackground(new Color(255, 255, 255));

        panResaCours.setBackground(new Color(255, 255, 255));
        panResaCours.setBorder(BorderFactory.createTitledBorder("Réservations en cours"));

        jScrollPane5.getViewport().setBackground(Color.white);
        jScrollPane5.setBackground(new Color(255, 255, 255));

        tableResaCours.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Oeuvre", "Usager", "Date Reservation"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tableResaCours.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tableResaCours);

        GroupLayout panResaCoursLayout = new GroupLayout(panResaCours);
        panResaCours.setLayout(panResaCoursLayout);
        panResaCoursLayout.setHorizontalGroup(
                panResaCoursLayout.createParallelGroup()
                .addGroup(panResaCoursLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane5))
        );
        panResaCoursLayout.setVerticalGroup(
                panResaCoursLayout.createParallelGroup()
                .addComponent(jScrollPane5, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        );

        menuResaPanel.setBackground(new Color(255, 255, 255));
        menuResaPanel.setBorder(BorderFactory.createTitledBorder(""));

        annulerResaButton.setFont(new Font("Times New Roman", 1, 18)); 
        annulerResaButton.setText("Annuler");
        annulerResaButton.setEnabled(false);
        annulerResaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                annulerResaButtonActionPerformed(evt);
            }
        });

        reserverButton.setFont(new Font("Times New Roman", 1, 18)); 
        reserverButton.setText("Réserver");
        reserverButton.setEnabled(false);
        reserverButton.setPreferredSize(new Dimension(95, 31));
        reserverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                reserverButtonActionPerformed(evt);
            }
        });

        affResaButton.setFont(new Font("Times New Roman", 1, 18)); 
        affResaButton.setText("Afficher ");
        affResaButton.setEnabled(false);
        affResaButton.setPreferredSize(new Dimension(95, 31));
        affResaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                affResaButtonActionPerformed(evt);
            }
        });

        GroupLayout menuResaPanelLayout = new GroupLayout(menuResaPanel);
        menuResaPanel.setLayout(menuResaPanelLayout);
        menuResaPanelLayout.setHorizontalGroup(
                menuResaPanelLayout.createParallelGroup()
                .addGroup(menuResaPanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(reserverButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(affResaButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(annulerResaButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))
        );
        menuResaPanelLayout.setVerticalGroup(
                menuResaPanelLayout.createParallelGroup()
                .addGroup(menuResaPanelLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(menuResaPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(annulerResaButton)
                                .addComponent(reserverButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(affResaButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(39, Short.MAX_VALUE))
        );

        GroupLayout ReservationPanelLayout = new GroupLayout(ReservationPanel);
        ReservationPanel.setLayout(ReservationPanelLayout);
        ReservationPanelLayout.setHorizontalGroup(
                ReservationPanelLayout.createParallelGroup()
                .addGap(0, 788, Short.MAX_VALUE)
                .addGroup(ReservationPanelLayout.createParallelGroup()
                        .addGroup(ReservationPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(ReservationPanelLayout.createParallelGroup()
                                        .addComponent(menuResaPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panResaCours, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        ReservationPanelLayout.setVerticalGroup(
                ReservationPanelLayout.createParallelGroup()
                .addGap(0, 414, Short.MAX_VALUE)
                .addGroup(ReservationPanelLayout.createParallelGroup()
                        .addGroup(ReservationPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(menuResaPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(panResaCours, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()))
        );

        mainPanel.add(ReservationPanel, "panel2");

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setBorder(BorderFactory.createTitledBorder(""));

        empruntsButton.setFont(new Font("Times New Roman", 1, 18)); 
        empruntsButton.setText("Emprunts");
        empruntsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                empruntsButtonActionPerformed(evt);
            }
        });

        ReservationButton.setFont(new Font("Times New Roman", 1, 18)); 
        ReservationButton.setText("Réservations");
        ReservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ReservationButtonActionPerformed(evt);
            }
        });

        retourButton.setFont(new Font("Times New Roman", 1, 18)); 
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
                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(empruntsButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ReservationButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(retourButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(empruntsButton)
                        .addGap(90, 90, 90)
                        .addComponent(ReservationButton)
                        .addGap(90, 90, 90)
                        .addComponent(retourButton)
                        .addContainerGap(143, Short.MAX_VALUE))
        );

        titleResaEmpLabel.setFont(new Font("Times New Roman", 1, 36)); 
        titleResaEmpLabel.setForeground(new Color(19, 102, 170));
        titleResaEmpLabel.setText("Gestion des Réservations et Emprunts");

        jPanel3.setBackground(new Color(255, 255, 255));

        typeOeuvreLabel1.setFont(new Font("Times New Roman", 1, 14)); 
        typeOeuvreLabel1.setForeground(new Color(19, 102, 170));
        typeOeuvreLabel1.setText("Type de l'oeuvre");

        identifiantLabel1.setFont(new Font("Times New Roman", 1, 14)); 
        identifiantLabel1.setForeground(new Color(19, 102, 170));
        identifiantLabel1.setText("Identifiant ");

        auteurLabel1.setFont(new Font("Times New Roman", 1, 14)); 
        auteurLabel1.setForeground(new Color(19, 102, 170));
        auteurLabel1.setText("Auteur");

        titreLabel1.setFont(new Font("Times New Roman", 1, 14)); 
        titreLabel1.setForeground(new Color(19, 102, 170));
        titreLabel1.setText("Titre");

        categorieLabel1.setFont(new Font("Times New Roman", 1, 14)); 
        categorieLabel1.setForeground(new Color(19, 102, 170));
        categorieLabel1.setText("Catégorie");

        nbExemplaire1.setFont(new Font("Times New Roman", 1, 14)); 
        nbExemplaire1.setForeground(new Color(19, 102, 170));
        nbExemplaire1.setText("Nombre \nd'exemplaire");

        titreLabelValue.setFont(new Font("Times New Roman", 1, 14)); 
        titreLabelValue.setText(" ");

        categorieLabelValue.setFont(new Font("Times New Roman", 1, 14)); 
        categorieLabelValue.setText(" ");

        typeOeuvreLabelValue.setFont(new Font("Times New Roman", 1, 14)); 
        typeOeuvreLabelValue.setText(" ");

        auteurLabelValue.setFont(new Font("Times New Roman", 1, 14)); 
        auteurLabelValue.setText(" ");

        nbExemplaireLabelValue.setFont(new Font("Times New Roman", 1, 14)); 
        nbExemplaireLabelValue.setText(" ");

        identifiantComboBox.setFont(new Font("Tahoma", 1, 12)); 
        identifiantComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Choix ID"}));
        identifiantComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                identifiantComboBoxActionPerformed(evt);
            }
        });

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup()
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(titreLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(titreLabelValue))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(categorieLabel1, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(categorieLabelValue)))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(identifiantLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(identifiantComboBox, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup()
                                .addComponent(typeOeuvreLabel1)
                                .addComponent(nbExemplaire1)
                                .addComponent(auteurLabel1))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup()
                                .addComponent(auteurLabelValue)
                                .addComponent(nbExemplaireLabelValue)
                                .addComponent(typeOeuvreLabelValue))
                        .addGap(195, 195, 195))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup()
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(typeOeuvreLabel1)
                                .addComponent(identifiantLabel1)
                                .addComponent(typeOeuvreLabelValue)
                                .addComponent(identifiantComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(titreLabel1)
                                .addComponent(titreLabelValue)
                                .addComponent(auteurLabel1)
                                .addComponent(auteurLabelValue))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(categorieLabelValue)
                                .addComponent(nbExemplaire1)
                                .addComponent(nbExemplaireLabelValue)
                                .addComponent(categorieLabel1))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup()
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup()
                                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(titleResaEmpLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(126, 126, 126))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup()
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titleResaEmpLabel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup()
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void ReservationButtonActionPerformed(ActionEvent evt) {
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "panel2");
        String identitfiant = identifiantComboBox.getSelectedItem().toString();
        if (!identitfiant.equals("Choix ID")) {
            reserverButton.setEnabled(true);
        } else {
            reserverButton.setEnabled(false);
        }

    }

    private void empruntsButtonActionPerformed(ActionEvent evt) {
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "panel1");

        String identitfiant = identifiantComboBox.getSelectedItem().toString();
        if (!identitfiant.equals("Choix ID")) {
            emprunterButton.setEnabled(true);
        } else {
            emprunterButton.setEnabled(false);
            rendreButton.setEnabled(false);
            annulerResaButton.setEnabled(false);
        }
    }

    private void retourButtonActionPerformed(ActionEvent evt) {
        Menu m = new Menu();
        this.dispose();
        m.setVisible(true);
    }

    private void affEmpButtonActionPerformed(ActionEvent evt) {
        try {
            String identitfiant = identifiantComboBox.getSelectedItem().toString();
            if (!identitfiant.equals("Choix ID")) {
                Oeuvre oeuvre = getOeuvreInfos();
                ArrayList<Emprunt> listEmprunts = EmpruntControl.findEmprunts(oeuvre);
                if (null != listEmprunts) {
                    fillEmpruntsJtable(listEmprunts);
                } else {
                    showMessageSucces("Aucun emprunt en cours de cette oeuvre trouvé");
                    String titre[] = new String[]{"ID", "Exemplaire", "Usager",
                        "Date Emprunt", "Date Retour"};
                    Object data[][] = new Object[1][titre.length];
                    ModelTableau model = new ModelTableau(data, titre);
                    tableEmpCours.setModel(model);
                    tableEmpCours.setRowHeight(1);
                }
            }

        } catch (BibalExceptions ex) {
            System.out.println("IHM.GestionReservationsEmprunts.affEmpButtonActionPerformed()");
        }
    }

    private void identifiantComboBoxActionPerformed(ActionEvent evt) {

        String identitfiant = identifiantComboBox.getSelectedItem().toString();
        if (!identitfiant.equals("Choix ID")) {
            try {
                int id = parseInt(identitfiant);
                Oeuvre oeuvre = OeuvreControl.findById(id);
                titreLabelValue.setText(oeuvre.getTitre());
                categorieLabelValue.setText(oeuvre.getCategorie());
                typeOeuvreLabelValue.setText(oeuvre.getClass().getSimpleName());
                auteurLabelValue.setText(oeuvre.getAuteur());
                nbExemplaireLabelValue.setText(oeuvre.getExamplairesOeuvre().size() + "");
            } catch (BibalExceptions ex) {
                System.out.println("IHM.GestionReservationsEmprunts.identifiantComboBoxActionPerformed()");
            }
            affEmpButton.setEnabled(true);
            affExempButton.setEnabled(true);
            emprunterButton.setEnabled(true);
            reserverButton.setEnabled(true);
            affResaButton.setEnabled(true);
        } else {
            titreLabelValue.setText("");
            categorieLabelValue.setText("");
            typeOeuvreLabelValue.setText("");
            auteurLabelValue.setText("");
            nbExemplaireLabelValue.setText("");
            affEmpButton.setEnabled(false);
            affExempButton.setEnabled(false);
            emprunterButton.setEnabled(false);
            rendreButton.setEnabled(false);
            reserverButton.setEnabled(false);
            affResaButton.setEnabled(false);

        }
        rendreButton.setEnabled(false);
        //vider les tables
        tableExemplaires.setModel(new ModelTableau(new Object[0][0],
                new String[]{"ID", "Etat"}));
        tableExemplaires.setRowHeight(30);

        tableEmpCours.setModel(new ModelTableau(new Object[0][0],
                new String[]{"ID", "Exemplaire", "Usager",
                    "Date Emprunt", "Date Retour"}));
        tableResaCours.setRowHeight(30);

        tableResaCours.setModel(new ModelTableau(new Object[0][0],
                new String[]{"ID", "Oeuvre", "Usager",
                    "Date Réservation"}));
        tableResaCours.setRowHeight(30);

    }

    private void affExempButtonActionPerformed(ActionEvent evt) {
        try {
            String identitfiant = identifiantComboBox.getSelectedItem().toString();
            if (!identitfiant.equals("Choix ID")) {
                Oeuvre oeuvre = getOeuvreInfos();
                ArrayList<Exemplaire> listExemplaires = ExemplaireControl.findExemplaireDispo(oeuvre);
                if (null != listExemplaires) {
                    fillExemplaireJtable(listExemplaires);
                } else {
                    String titre[] = new String[]{"ID", "Etat"};
                    Object data[][] = new Object[1][titre.length];
                    ModelTableau model = new ModelTableau(data, titre);
                    tableExemplaires.setModel(model);
                    tableExemplaires.setRowHeight(1);
                    showMessageSucces("Aucun exemplaire disponible trouvé");
                }
            }
        } catch (BibalExceptions e) {
            System.out.println("IHM.GestionOeuvre.afficherButtonActionPerformed()");
        }
    }

    private void rendre(ActionEvent evt) {
        try {
            int oeuvreId = getOeuvreID();
            if (oeuvreId != -1 && tableEmpCours.getSelectedRow() >= 0) {
                int idExemplaire = parseInt(dataLigneSelectedEmprunts[1].toString());
                int idOeuvre = parseInt(identifiantComboBox.getSelectedItem().toString());
                EmpruntControl.rendre(dataLigneSelectedEmprunts[2].toString(), idOeuvre, idExemplaire);
                ((ModelTableau) tableEmpCours.getModel()).removeRow(tableEmpCours.getSelectedRow());
                showMessageSucces("Exemplaire Rendu");
            }
        } catch (BibalExceptions e) {
            System.out.println("IHM.GestionReservationsEmprunts.rendreButtonActionPerformed()");
        }
    }

    private void emprunter(ActionEvent evt) {

        String titre = titreLabelValue.getText();
        GestionEmprunts emprunter = new GestionEmprunts(this, true, titre);
        emprunter.setLocationRelativeTo(null);
        emprunter.setVisible(true);
    }

    private void affResaButtonActionPerformed(ActionEvent evt) {
        try {
            String identitfiant = identifiantComboBox.getSelectedItem().toString();
            if (!identitfiant.equals("Choix ID")) {
                Oeuvre oeuvre = getOeuvreInfos();
                ArrayList<Reservation> listReservations = ReservationControl.findByReservation(oeuvre);
                if (null != listReservations) {
                    fillResaJtable(listReservations);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Aucune réservation en cours de cette oeuvre trouvée", "Informations",
                            JOptionPane.INFORMATION_MESSAGE);
                    String titre[] = new String[]{"ID", "Exemplaire", "Usager",
                        "Date Emprunt", "Date Retour"};
                    Object data[][] = new Object[1][titre.length];
                    ModelTableau model = new ModelTableau(data, titre);
                    tableResaCours.setModel(model);
                    tableResaCours.setRowHeight(1);
                }
            }

        } catch (BibalExceptions e) {
            System.out.println("IHM.GestionReservationsEmprunts.affEmpButtonActionPerformed()");
        }
    }

    private void reserverButtonActionPerformed(ActionEvent evt) {
        String titre = titreLabelValue.getText();
        GestionReservations reserver = new GestionReservations(this, true, titre);
        reserver.setLocationRelativeTo(null);
        reserver.setVisible(true);
    }

    private void annulerResaButtonActionPerformed(ActionEvent evt) {
        try {
            int oeuvreId = getOeuvreID();
            if (oeuvreId != -1 && tableResaCours.getSelectedRow() >= 0) {
                int idUsager = parseInt(dataLigneSelectedReservations[2].toString());
                ReservationControl.annuler(idUsager, titreLabelValue.getText());
                ((ModelTableau) tableResaCours.getModel()).removeRow(tableResaCours.getSelectedRow());
                showMessageSucces("Réservation annulée ");
            }

        } catch (BibalExceptions e) {
            System.out.println("IHM.GestionReservationsEmprunts.annulerResaButtonActionPerformed()");
        }
    }

    private int getOeuvreID() {
        String id = identifiantComboBox.getSelectedItem().toString();
        if (!id.equals("Choisir identifiant")) {
            return parseInt(id);
        }
        return -1;
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
    }

    private void fillEmpruntsJtable(ArrayList<Emprunt> listEmprunts) {
        String titre[] = new String[]{"ID", "Exemplaire", "Usager",
            "Date Emprunt", "Date Retour"};
        if (listEmprunts.size() > 0) {
//            pour le findByID si l'objet ajouté est null
            int nbLigne = (null == listEmprunts.get(0)) ? 0 : listEmprunts.size();
            Object data[][] = new Object[nbLigne][titre.length];
            for (int i = 0; i < nbLigne; i++) {
                Emprunt emprunt = listEmprunts.get(i);
                data[i][0] = emprunt.getId();
                data[i][1] = emprunt.getExemplairesEmprunt().getId();
                data[i][2] = emprunt.getUsagerEmprunt().getNom();
                data[i][3] = emprunt.getDateEmprunt();
                data[i][4] = emprunt.getDateRetourPrevu();

            }
            ModelTableau model = new ModelTableau(data, titre);
            tableEmpCours.setModel(model);
            tableEmpCours.setRowHeight(30);
        } else {
            //afficher tableau vide
            Object data[][] = new Object[1][titre.length];
            ModelTableau model = new ModelTableau(data, titre);
            tableEmpCours.setModel(model);
            tableEmpCours.setRowHeight(1);
        }
    }

    private void fillResaJtable(ArrayList<Reservation> listReservations) {
        String titre[] = new String[]{"ID", "Oeuvre", "Usager",
            "Date Réservation"};
        if (listReservations.size() > 0) {
            int nbLigne = (null == listReservations.get(0)) ? 0 : listReservations.size();
            Object data[][] = new Object[nbLigne][titre.length];
            for (int i = 0; i < nbLigne; i++) {
                Reservation reservation = listReservations.get(i);
                data[i][0] = reservation.getId();
                data[i][1] = reservation.getOeuvresReservation().getId();
                data[i][2] = reservation.getUsagerReservation().getId();
                data[i][3] = reservation.getDateReservation();

            }
            ModelTableau model = new ModelTableau(data, titre);
            tableResaCours.setModel(model);
            tableResaCours.setRowHeight(30);
        } else {
            //afficher tableau vide
            Object data[][] = new Object[1][titre.length];
            ModelTableau model = new ModelTableau(data, titre);
            tableResaCours.setModel(model);
            tableResaCours.setRowHeight(1);
        }
    }

    private void setIdentifiant() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            final String SQL_SELECT_ID = "SELECT id FROM oeuvre ";
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(),
                    SQL_SELECT_ID, new Object[0]);
            resultSet = preparedStatement.executeQuery();
            int identifiant;
            while (resultSet.next()) {
                identifiant = resultSet.getInt("id");
                identifiantComboBox.addItem(Integer.toString(identifiant));
            }
        } catch (SQLException | BibalExceptions e) {
            showMessageDialog(null, "Erreurs d'accès à la base de données",
                    "Erreurs", JOptionPane.ERROR_MESSAGE);
        } finally {
            closeStatementResultSet(preparedStatement, resultSet);
        }
    }

    private Oeuvre getOeuvreInfos() {
        int id = parseInt(identifiantComboBox.getSelectedItem().toString());
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

    private Object dataLigneSelectedExemplaires[];
    private Object dataLigneSelectedEmprunts[];
    private Object dataLigneSelectedReservations[];
    // Variables declaration - do not modify                     

}
