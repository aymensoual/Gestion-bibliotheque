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
public class Authentification extends JFrame {

    private JButton Connecter;
    private JTextField LoginText;
    private JPasswordField PasseText;
    private JLabel bibliothequeLabel;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;

    public Authentification() {

        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bibliotheque_icone.png")));
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setResizable(false);
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        LoginText = new JTextField();
        PasseText = new JPasswordField();
        Connecter = new JButton();
        jLabel3 = new JLabel();
        bibliothequeLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Authentification");
        setBackground(new Color(255, 255, 255));
        setLocationByPlatform(true);
        setMinimumSize(new Dimension(450, 180));
        setResizable(false);

        jPanel1.setBackground(new Color(255, 255, 255));
        jLabel1.setFont(new Font("Times New Roman", 1, 18));
        jLabel1.setText("Nom d'utilisateur ");
        jLabel2.setFont(new Font("Times New Roman", 1, 18));
        jLabel2.setText("Mot de passe ");
        LoginText.setFont(new Font("Times New Roman", 1, 14));
        PasseText.setFont(new Font("Times New Roman", 1, 14));
        Connecter.setFont(new Font("Times New Roman", 1, 18));
        Connecter.setText("Connecter");
        Connecter.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        Connecter.setHorizontalTextPosition(SwingConstants.CENTER);
        Connecter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ConnecterActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new ImageIcon(getClass().getResource("/images/poly.png")));

        bibliothequeLabel.setFont(new Font("Sonic XBd BT", 1, 40));
        bibliothequeLabel.setForeground(new Color(19, 102, 170));
        bibliothequeLabel.setText("Gestion Bibliothèque");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                )
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(46, 46, 46)))
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(LoginText, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addComponent(PasseText, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                        .addGap(96, 96, 96))
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(82, 82, 82)
                                        .addComponent(bibliothequeLabel))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(217, 217, 217)
                                        .addComponent(jLabel3))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(246, 246, 246)
                                        .addComponent(Connecter)))
                        .addContainerGap(84, Short.MAX_VALUE))
        );

        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(bibliothequeLabel)
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(jLabel1)
                                .addComponent(LoginText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(PasseText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                        .addGap(33, 33, 33)
                        .addComponent(Connecter)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel3)
                )
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

        setSize(new Dimension(636, 500));
        setLocationRelativeTo(null);
    }

    private void ConnecterActionPerformed(ActionEvent evt) {
        Menu m = new Menu();
        this.dispose();
        m.setVisible(true);
    }

    public static void main(String args[]) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Authentification().setVisible(true);
            }
        });
    }

}
