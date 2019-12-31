package IHM;

import static IHM.GestionExemplaires.getNbExemplaireLabelValue;
import static IHM.GestionExemplaires.setNbExemplaireLabelValue;
import Utility.BibalExceptions;
import Utility.DBConnection;
import Utility.ModelTableau;
import static Utility.Utility.closeStatementResultSet;
import static Utility.Utility.initialiseRequetePreparee;
import static Utility.Utility.showMessageSucces;
import control.ExemplaireControl;
import java.awt.Dimension;
import java.awt.Toolkit;
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
 * Interface d'ajout d'un exemplaire dans la base
 *
 * @author Aymen Souelmi
 */
public class AjoutExemplaire extends JDialog {

    private JButton ajouterBouton;
    private JButton annulerBouton;
    private JComboBox etatExemplaireCombo;
    private JLabel etatExemplaireLabel;
    private JFormattedTextField identifiantField;
    private JLabel identifiantLabel;
    private JPanel jPanel1;
    private JPanel panAjoutBas;
    private JLabel titleLabel;

    public AjoutExemplaire(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIdentifiant();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bibliotheque_icone.png")));
    }

    public AjoutExemplaire(java.awt.Frame parent, boolean modal, Oeuvre oeuvre) {
        this(parent, modal);
        this.oeuvre = oeuvre;
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        jPanel1 = new JPanel();
        titleLabel = new JLabel();
        panAjoutBas = new JPanel();
        etatExemplaireLabel = new JLabel();
        identifiantLabel = new JLabel();
        identifiantField = new JFormattedTextField();
        etatExemplaireCombo = new JComboBox();
        ajouterBouton = new JButton();
        annulerBouton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajouter Exemplaire");

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setName("Gestion personnel");
        jPanel1.setPreferredSize(new Dimension(1162, 608));
        jPanel1.setRequestFocusEnabled(false);

        titleLabel.setFont(new Font("Times New Roman", 1, 36));
        titleLabel.setForeground(new Color(19, 102, 170));
        titleLabel.setText("Ajout Exemplaire");

        panAjoutBas.setBackground(new Color(255, 255, 255));
        panAjoutBas.setBorder(BorderFactory.createTitledBorder(""));

        etatExemplaireLabel.setFont(new Font("Times New Roman", 1, 14));
        etatExemplaireLabel.setForeground(new Color(19, 102, 170));
        etatExemplaireLabel.setText("Etat");

        identifiantLabel.setFont(new Font("Times New Roman", 1, 14));
        identifiantLabel.setForeground(new Color(19, 102, 170));
        identifiantLabel.setText("Identifiant ");

        identifiantField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        identifiantField.setHorizontalAlignment(JTextField.CENTER);
        identifiantField.setToolTipText("");
        identifiantField.setEnabled(false);
        identifiantField.setFont(new Font("Times New Roman", 1, 14));
        identifiantField.setPreferredSize(new Dimension(123, 26));

        etatExemplaireCombo.setFont(new Font("Times New Roman", 1, 12));
        etatExemplaireCombo.setModel(new DefaultComboBoxModel(new String[]{"Neuf", "Bon", "Vieux"}));
        etatExemplaireCombo.setPreferredSize(new Dimension(123, 26));

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
                                                .addComponent(identifiantLabel)
                                                .addComponent(etatExemplaireLabel))
                                        .addGap(62, 62, 62)
                                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                                .addComponent(etatExemplaireCombo)
                                                .addComponent(identifiantField)))
                                .addGroup(panAjoutBasLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(annulerBouton)
                                        .addGap(30, 30, 30)
                                        .addComponent(ajouterBouton)))
                        .addContainerGap())
        );
        panAjoutBasLayout.setVerticalGroup(
                panAjoutBasLayout.createParallelGroup()
                .addGroup(panAjoutBasLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(identifiantLabel)
                                .addComponent(identifiantField))
                        .addGap(36, 36, 36)
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(etatExemplaireLabel)
                                .addComponent(etatExemplaireCombo))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(panAjoutBasLayout.createParallelGroup()
                                .addComponent(annulerBouton)
                                .addComponent(ajouterBouton))
                        .addGap(5, 5, 5))
        );

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panAjoutBas)
                        .addContainerGap())
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(titleLabel)
                        .addGap(70, 70, 70))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(titleLabel)
                        .addGap(18, 18, 18)
                        .addComponent(panAjoutBas)
                        .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
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

    private void ajouter(ActionEvent evt) {
      try {
            Exemplaire exemplaire = getExempalireInfos();
            if (null != exemplaire) {
                ExemplaireControl.ajouter(oeuvre, exemplaire.getEtat());
                ((ModelTableau) GestionExemplaires.tableExemplaires.getModel())
                        .addRow(new Object[]{exemplaire.getId(), exemplaire.getEtat()});
                setNbExemplaireLabelValue((parseInt(getNbExemplaireLabelValue()) + 1) + "");
                showMessageSucces("Exemplaire ajouté avec succès");
                setIdentifiant();
            }
        } catch (BibalExceptions e) {
            System.out.println("IHM.AjoutExemplaire.ajouterBoutonActionPerformed()" + e.getMessage());
        }
    }

    private void annulerBoutonActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    /**
     * Récupère les information d'un exemplaire sur l'interface
     *
     * @return un exemplaire
     */
    private Exemplaire getExempalireInfos() {
        int id = parseInt(identifiantField.getText());
        String etatExemplaire = etatExemplaireCombo.getSelectedItem().toString();

        return new Exemplaire(id, etatExemplaire);
    }

    /**
     * Afficher l'identifiant de l'exemplaire qui sera ajouté
     */
    private void setIdentifiant() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            final String SQL_SELECT_ID = "SELECT id FROM exemplaire ORDER BY id DESC LIMIT 1";
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(),
                    SQL_SELECT_ID, new Object[0]);
            resultSet = preparedStatement.executeQuery();
            int identifiant = 1;
            if (resultSet.first()) {
                identifiant = resultSet.getInt("id");
            }
            identifiantField.setText((identifiant + 1) + "");
        } catch (SQLException | BibalExceptions e) {
            showMessageDialog(null, "Erreurs d'accès à la base de données",
                    "Erreurs", JOptionPane.ERROR_MESSAGE);
        } finally {
            closeStatementResultSet(preparedStatement, resultSet);
        }
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    private Oeuvre oeuvre = new Oeuvre();
}
