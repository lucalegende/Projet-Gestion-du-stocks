/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks.design;

import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import projet.gestion.du.stocks.Classe.Commandes;
import projet.gestion.du.stocks.Classe.Fournisseurs;
import projet.gestion.du.stocks.Classe.Vaccins;
import projet.gestion.du.stocks.dao.CommandesDAO;
import projet.gestion.du.stocks.dao.FournisseursDAO;
import projet.gestion.du.stocks.dao.VaccinsDAO;

/**
 *
 * @author Kalic
 */
public class stockFenetre extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     * @throws java.sql.SQLException
     */
    public stockFenetre() throws SQLException {
        initComponents();
        
        refreshTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableVaccins = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableFournisseurs = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableCommandes = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButtonValidate = new javax.swing.JButton();
        jButtonAnnuler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Stock de vaccin contre le Covid-19");

        jTableVaccins.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type de vaccin", "Nombre commadés", "Nombre disponible", "Total en stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int row, int column){return false;}
        });
        jScrollPane4.setViewportView(jTableVaccins);

        jButton5.setText("Ajouter un stock");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutStockFenetre(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Fournisseurs");

        jTableFournisseurs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Nombre commandés", "Nombre disponible", "Total en stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int row, int column){return false;}
        });
        jScrollPane5.setViewportView(jTableFournisseurs);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Commandes");

        jTableCommandes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom du client", "Fournisseur", "Type de vaccin", "Nombre commandés"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int row, int column){return false;}
        });
        jTableCommandes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCommandesMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableCommandes);

        jButton6.setText("Ajouter une commande");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjoutCommandeFenetre(evt);
            }
        });

        jButtonValidate.setText("Valider la commande");
        jButtonValidate.setEnabled(false);
        jButtonValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValiderCommande(evt);
            }
        });

        jButtonAnnuler.setText("Annuler la commande");
        jButtonAnnuler.setEnabled(false);
        jButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnnulerCommande(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane5)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane6)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 559, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jButtonValidate, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonValidate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Méthode qui annule une commande
     * @param evt 
     */
    private void AnnulerCommande(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnnulerCommande
        Collection CommandeSelectionner = ((DefaultTableModel) jTableCommandes.getModel()).getDataVector().elementAt(jTableCommandes.getSelectedRow());
        int IdCommandes = (Integer) CommandeSelectionner.iterator().next();
        
        try {
            CommandesDAO.AnnulerUneCommande(IdCommandes);
            JOptionPane.showMessageDialog(null, "Annulation de la commande réussi.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Annulation de la commande échouer.");
        }
        
        try {
            refreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(stockFenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AnnulerCommande

    /**
     * Méthode qui affiche la fentre pour l'ajout de stocks
     * @param evt 
     */
    @SuppressWarnings("empty-statement")
    private void AjoutStockFenetre(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjoutStockFenetre
        
        JDialog jfrm2= new ajoutStockFenetre(this, rootPaneCheckingEnabled);
        jfrm2.setModal(true);
        jfrm2.setVisible(true);
        jfrm2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        try {
            refreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(stockFenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_AjoutStockFenetre

    /**
     * Méthode qui affiche la fentre pour l'ajout de commande
     * @param evt 
     */
    private void AjoutCommandeFenetre(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjoutCommandeFenetre
        if (VaccinsDAO.getListeVaccins().size() > 0) {
            JDialog jfrm2= new ajoutCommandeFenetre(this, rootPaneCheckingEnabled);
            jfrm2.setModal(true);
            jfrm2.setVisible(true);
            jfrm2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            try {
                refreshTable();
            } catch (SQLException ex) {
                Logger.getLogger(stockFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //On affiche un texte si aucun vaccin n'existe et donc on ne peut pas commander
            JOptionPane.showMessageDialog(null, "Vous ne pouvez pas commander car vous n'avez pas ajouté de vaccin.");
        }
    }//GEN-LAST:event_AjoutCommandeFenetre

    /**
     * Méthode qui permet de rendre utilisable les bouttona valider et annuler
     * @param evt 
     */
    private void jTableCommandesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCommandesMouseClicked
        jButtonValidate.setEnabled(true);
        jButtonAnnuler.setEnabled(true);  
    }//GEN-LAST:event_jTableCommandesMouseClicked

    /**
     * Méthode qui valide une commande
     * @param evt 
     */
    private void ValiderCommande(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValiderCommande
        Collection CommandeSelectionner = ((DefaultTableModel) jTableCommandes.getModel()).getDataVector().elementAt(jTableCommandes.getSelectedRow());
        int IdCommandes = (Integer) CommandeSelectionner.iterator().next();
        
        try {
            CommandesDAO.ValiderUneCommande(IdCommandes);
            JOptionPane.showMessageDialog(null, "Validation de la commande réussi.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Validation de la commande échouer.");
        }
        
        try {
            refreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(stockFenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ValiderCommande

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(stockFenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new stockFenetre().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(stockFenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void refreshTable() throws SQLException{
        // Mise a jour des données
        //Commandes
        CommandesDAO.ChargerListeCommandes();
        DefaultTableModel model = (DefaultTableModel) jTableCommandes.getModel();
        model.setRowCount(0);
        
        for (Commandes commande : CommandesDAO.getListeCommandes()) {
            if (!commande.isCommandeValider()) {
                Object[] row = { commande.getId(), commande.getClient(), commande.getFournisseur(), commande.getVaccin(),commande.getNombreVaccinCommander()};
                model.addRow(row);
            }
        }
        
        //Fournisseurs
        FournisseursDAO.ChargerListeFournisseurs();
        model = (DefaultTableModel) jTableFournisseurs.getModel();
        model.setRowCount(0);
        
        for (Fournisseurs fournisseur : FournisseursDAO.getListeFounisseurs()) {
            Object[] row = { fournisseur.getNomFournisseur(), fournisseur.getNombreCommander(), fournisseur.getNombreDisponible(), fournisseur.getTotalStocks() };
            model.addRow(row);
        }
        
        //Vaccins
        VaccinsDAO.ChargerListeVaccins();
        model = (DefaultTableModel) jTableVaccins.getModel();
        model.setRowCount(0);
        
        for (Vaccins vaccin : VaccinsDAO.getListeVaccins()) {
            Object[] row = { vaccin.getTypeVaccin(), vaccin.getNombreCommander(), vaccin.getNombreDisponible(), vaccin.getTotalStocks() };
            model.addRow(row);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonAnnuler;
    private javax.swing.JButton jButtonValidate;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTableCommandes;
    private javax.swing.JTable jTableFournisseurs;
    private javax.swing.JTable jTableVaccins;
    // End of variables declaration//GEN-END:variables
}
