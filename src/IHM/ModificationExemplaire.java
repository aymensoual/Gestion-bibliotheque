package IHM;

import Utility.BibalExceptions;
import Utility.ModelTableau;
import static Utility.Utility.showMessageSucces;
import control.ExemplaireControl;
import java.awt.event.*;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import static java.lang.Integer.parseInt;
import java.text.*;
import objets_metiers.Exemplaire;

/**
 *
 * @author Aymen Souelmi
 */
public class ModificationExemplaire extends JDialog {

    private JButton annulerBouton;
    private JComboBox etatExemplaireCombo;
    private JLabel etatExemplaireLabel;
    private JFormattedTextField identifiantField;
    private JLabel identifiantLabel;
    private JPanel jPanel1;
    private JButton modifierBouton;
    private JPanel panAjoutBas;
    private JLabel titleLabel;

    public ModificationExemplaire(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bibliotheque_icone.png")));
    }

     public ModificationExemplaire(Frame parent, boolean modal, Exemplaire exemplaire) {
        this(parent, modal);
        identifiantField.setText(exemplaire.getId() + "");
        etatExemplaireCombo.setSelectedItem(exemplaire.getEtat());
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
        modifierBouton = new JButton();
        annulerBouton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modification Exemplaire");

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setName("Gestion personnel");
        jPanel1.setPreferredSize(new Dimension(1162, 608));
        jPanel1.setRequestFocusEnabled(false);

        titleLabel.setFont(new Font("Times New Roman", 1, 36));
        titleLabel.setForeground(new Color(19, 102, 170));
        titleLabel.setText("Modification");

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

        modifierBouton.setFont(new Font("Times New Roman", 1, 16));
        modifierBouton.setText("Modifier");
        modifierBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                modifier(evt);
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
                                                .addComponent(etatExemplaireCombo, 0, 233, Short.MAX_VALUE)
                                                .addComponent(identifiantField)))
                                .addGroup(GroupLayout.Alignment.TRAILING, panAjoutBasLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(annulerBouton)
                                        .addGap(30, 30, 30)
                                        .addComponent(modifierBouton)))
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
                                .addComponent(modifierBouton))
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
                        .addGap(94, 94, 94)
                        .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
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

    private void modifier(ActionEvent evt) {
        try {
            Exemplaire exemplaire = getExempalireInfos();
            if (null != exemplaire) {
                ExemplaireControl.modifier(exemplaire);
                ((ModelTableau) GestionExemplaires.tableExemplaires.getModel()).
                        updateRow(new Object[]{exemplaire.getId(), exemplaire.getEtat()},
                        GestionExemplaires.tableExemplaires.getSelectedRow());
                showMessageSucces("Modifications enregistrées avec succès");
            }
        } catch (BibalExceptions e) {
            System.out.println("IHM.ModificationExemplaire.modifierBoutonActionPerformed()");
        }
    }

    private void annulerBoutonActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    private Exemplaire getExempalireInfos() {
        int id = parseInt(identifiantField.getText());
        String etatExemplaire = etatExemplaireCombo.getSelectedItem().toString();

        return new Exemplaire(id, etatExemplaire);
    }
}
