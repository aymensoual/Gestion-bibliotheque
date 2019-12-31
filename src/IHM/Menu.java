package IHM;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Aymen Souelmi
 */
public class Menu extends JFrame {

    private JButton Deconnecter;
    private JButton GestionOeuvresExamplaires;
    private JButton GestionReservationsEmprunts;
    private JButton GestionUsager;
    private JLabel bibliothequeLabel;
    private JPanel jPanel1;

    public Menu() {

        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bibliotheque_icone.png")));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new JPanel();
        Deconnecter = new JButton();
        GestionReservationsEmprunts = new JButton();
        GestionUsager = new JButton();
        GestionOeuvresExamplaires = new JButton();
        bibliothequeLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");
        setMinimumSize(new Dimension(410, 410));
        setResizable(false);

        jPanel1.setBackground(new Color(255, 255, 255));

        Deconnecter.setBackground(new Color(225, 242, 242));
        Deconnecter.setFont(new Font("Candara", 1, 20));
        Deconnecter.setForeground(new Color(19, 130, 176));
        Deconnecter.setText("Déconnecter");
        Deconnecter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DeconnecterActionPerformed(evt);
            }
        });

        GestionReservationsEmprunts.setBackground(new Color(225, 242, 242));
        GestionReservationsEmprunts.setFont(new Font("Candara", 1, 20));
        GestionReservationsEmprunts.setForeground(new Color(19, 130, 176));
        GestionReservationsEmprunts.setText("Gestion Reservations et Emprunts");
        GestionReservationsEmprunts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                GestionReservationsEmpruntsActionPerformed(evt);
            }
        });

        GestionUsager.setBackground(new Color(225, 242, 242));
        GestionUsager.setFont(new Font("Candara", 1, 20));
        GestionUsager.setForeground(new Color(19, 130, 176));
        GestionUsager.setText("Gestion Des Usagers");
        GestionUsager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                GestionUsagerActionPerformed(evt);
            }
        });

        GestionOeuvresExamplaires.setBackground(new Color(225, 242, 242));
        GestionOeuvresExamplaires.setFont(new Font("Candara", 1, 20));
        GestionOeuvresExamplaires.setForeground(new Color(19, 130, 176));
        GestionOeuvresExamplaires.setText("Gestion Oeuvres et Examplaires");
        GestionOeuvresExamplaires.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                GestionOeuvresExamplairesActionPerformed(evt);
            }
        });

        bibliothequeLabel.setFont(new Font("Times New Roman", 1, 48));
        bibliothequeLabel.setForeground(new Color(19, 102, 170));
        bibliothequeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bibliothequeLabel.setText("Gestion Bibliothèque");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(38, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(bibliothequeLabel)
                                        .addGap(37, 37, 37))
                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                .addComponent(Deconnecter, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(GestionReservationsEmprunts)
                                                .addComponent(GestionUsager, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(GestionOeuvresExamplaires, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE))
                                        .addGap(97, 97, 97))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bibliothequeLabel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GestionUsager, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(GestionOeuvresExamplaires, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(GestionReservationsEmprunts, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(Deconnecter, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addComponent(jPanel1)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1)
                )
        );

        pack();
    }

    private void GestionUsagerActionPerformed(ActionEvent evt) {
        GestionUsager gu = new GestionUsager();
        this.dispose();
        gu.setVisible(true);
    }

    private void DeconnecterActionPerformed(ActionEvent evt) {
        Authentification a = new Authentification();
        this.dispose();
        a.setVisible(true);
    }

    private void GestionOeuvresExamplairesActionPerformed(ActionEvent evt) {
        GestionOeuvresExemplaires goe = new GestionOeuvresExemplaires();
        this.dispose();
        goe.setVisible(true);

    }

    private void GestionReservationsEmpruntsActionPerformed(ActionEvent evt) {
        GestionReservationsEmprunts goe = new GestionReservationsEmprunts();
        this.dispose();
        goe.setVisible(true);
    }

}
