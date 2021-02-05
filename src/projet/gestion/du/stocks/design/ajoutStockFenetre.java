/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks.design;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import projet.gestion.du.stocks.dao.FournisseursDAO;
import projet.gestion.du.stocks.dao.VaccinsDAO;

/**
 *
 * @author Kalictong
 */
public class ajoutStockFenetre extends javax.swing.JDialog {

    /**
     * Créer une nouvelle fenêtre ajouteStockFenetre
     *
     * @param parent La fenêtre parent
     * @param modal Le model de la fênetre
     */
    public ajoutStockFenetre(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        //Chargement des vaccins de la base de données dans l'applications
        VaccinsDAO.getListeVaccins().forEach(vaccin -> {
            TypeVaccins.addItem(vaccin.getTypeVaccin());
        });

        FournisseursDAO.getListeFounisseurs().forEach(fournisseur -> {
            NomFournisseur.addItem(fournisseur.getNomFournisseur());
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Quantiter = new javax.swing.JTextField();
        TypeVaccins = new javax.swing.JComboBox<>();
        NomFournisseur = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ajouter du stocks");

        jLabel2.setText("Type :");

        jLabel3.setText("Fournisseur :");

        jLabel4.setText("Quantité :");

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutStocks(evt);
            }
        });

        TypeVaccins.setEditable(true);

        NomFournisseur.setEditable(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Quantiter, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                    .addComponent(NomFournisseur, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TypeVaccins, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel1)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TypeVaccins, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NomFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Quantiter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Methode permettant l'ajout du stocks lors du click sur le boutton ok
     *
     * @param evt
     */
    private void AjoutStocks(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjoutStocks
        String oublie = "";
        int label = 0;

        //Vérification label sont bien remplie et avec les bonnes données
        if (TypeVaccins.getSelectedItem() == null || TypeVaccins.getSelectedItem().toString().isBlank()) {
            oublie += "Type de vaccin, ";
            label++;
        }
        if (NomFournisseur.getSelectedItem() == null || NomFournisseur.getSelectedItem().toString().isBlank()) {
            oublie += "Nom du fournisseur, ";
            label++;
        }

        if (Quantiter.getText().isBlank() || !Pattern.matches("[0-9]+", Quantiter.getText())) {
            oublie += "quantité ";
            label++;
        }

        if (!oublie.isEmpty() && label == 1) {
            JOptionPane.showMessageDialog(null, oublie + " est incorrect");
        } else if (!oublie.isEmpty() && label > 1) {
            JOptionPane.showMessageDialog(null, oublie + " sont incorrect");
        } else {
            try {
                //Ajoute la quantité
                String typeVaccin = TypeVaccins.getSelectedItem().toString();
                String nomFournisseur = NomFournisseur.getSelectedItem().toString();
                int quantiter = Integer.valueOf(Quantiter.getText());

                VaccinsDAO.MiseAJourListeVaccins(typeVaccin, nomFournisseur, quantiter);

                JOptionPane.showMessageDialog(null, "Ajout du stocks réussi.");

                this.dispose();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ajout du stocks échouer, veuillez réessayer.");
            } catch (Exception ex) {
                Logger.getLogger(ajoutStockFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_AjoutStocks

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ajoutStockFenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ajoutStockFenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ajoutStockFenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ajoutStockFenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ajoutStockFenetre dialog = new ajoutStockFenetre(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> NomFournisseur;
    private javax.swing.JTextField Quantiter;
    private javax.swing.JComboBox<String> TypeVaccins;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
