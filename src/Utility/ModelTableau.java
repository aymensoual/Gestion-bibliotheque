package Utility;

import java.util.Arrays;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aymen Souelmi
 */
public class ModelTableau extends AbstractTableModel {

    private Object[][] data;
    private String[] title;

    //Constructeur
    public ModelTableau(Object[][] data, String[] title) {
        this.data = data;
        this.title = title;
    }

    @Override
    public int getColumnCount() {
        return this.title.length;
    }

    @Override
    public int getRowCount() {
        return this.data.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return this.data[row][col];
    }

    @Override
    public String getColumnName(int col) {
        return this.title[col];
    }
    //Retourne vrai si la cellule est éditable : celle-ci sera donc éditable

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void removeRow(int row) {
        int nbLigne = 0;
        int nbCol = title.length;
        Object resultat[][] = new Object[data.length - 1][nbCol];
        for (int i = 0; i < data.length; i++) {
            if (i != row) {
                //copy de toutes les colonnes de la ligne
                resultat[nbLigne++] = Arrays.copyOfRange(data[i], 0, nbCol);
            }
        }
        //nouvel table à afficher
        data = resultat;
        //Notifier la table
        fireTableRowsDeleted(row, row);
    }

    public void addRow(Object values[]) {
        int nbLigne = data.length;
        int nbCol = title.length;
        if (nbLigne == 0) {
            data = new Object[1][nbCol];
            data[0] = Arrays.copyOfRange(values, 0, nbCol);
            nbLigne++;
        } else {
            Object resultat[][] = new Object[nbLigne + 1][nbCol];
//            ajout de la ligne au debut de la table
            resultat[0] = Arrays.copyOfRange(values, 0, nbCol);
            for (int i = 0; i < nbLigne; i++) {
                resultat[i + 1] = Arrays.copyOfRange(data[i], 0, nbCol);
            }
            data = resultat;
        }
        fireTableRowsInserted(0, 0);
    }

    public void updateRow(Object values[], int row) {
        data[row] = Arrays.copyOfRange(values, 0, title.length);
        fireTableRowsUpdated(row, row);
    }

}
