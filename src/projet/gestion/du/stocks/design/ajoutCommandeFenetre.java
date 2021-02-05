/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks.design;

import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import projet.gestion.du.stocks.dao.CommandesDAO;
import projet.gestion.du.stocks.dao.FournisseursDAO;
import projet.gestion.du.stocks.dao.VaccinsDAO;

/**
 *
 * @author Kalictong
 */
public class ajoutCommandeFenetre extends javax.swing.JDialog {

    /**
     * Creates new form ajoutCommandeFenêtre
     * @param parent
     * @param modal
     */
    public ajoutCommandeFenetre(java.awt.Frame parent, boolean modal) {
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

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        NomClient = new javax.swing.JTextField();
        Quantité = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        NomFournisseur = new javax.swing.JComboBox<>();
        TypeVaccins = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setText("Nom du client :");

        jLabel4.setText("Quantité :");

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Type de vaccin :");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ajouter une commande");

        jLabel2.setText("Founisseur :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jButton1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(TypeVaccins, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(NomClient, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                .addComponent(Quantité, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                .addComponent(NomFournisseur, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel1)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TypeVaccins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NomFournisseur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NomClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Quantité, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Vérification label sont bien remplie et avec les bonnes données
        String oublie = ""; int label = 0;
        
        //Vérification label sont bien remplie et avec les bonnes données
        if(TypeVaccins.getSelectedItem() == null || TypeVaccins.getSelectedItem().toString().isBlank()){
            oublie += "Type de vaccin, ";
            label++;
        }
        if(NomFournisseur.getSelectedItem() == null || NomFournisseur.getSelectedItem().toString().isBlank()) {
            oublie += "Nom du fournisseur, ";
            label++;
        }
        if (NomClient.getText().isBlank()){
            oublie += "Nom du client, ";
            label++;
        }
        if (Quantité.getText().isBlank() || !Pattern.matches("[0-9]+", Quantité.getText())) {
            oublie += "quantité ";
            label++;
        }
        
        if (!oublie.isEmpty() && label == 1) {
            JOptionPane.showMessageDialog(null, oublie+" est incorrect");
        } else if (!oublie.isEmpty() && label > 1){
            JOptionPane.showMessageDialog(null, oublie+" sont incorrect");
        } else {
            try {
                //Ajoute la quantité
                String typeVaccin = TypeVaccins.getSelectedItem().toString();
                String nomFournisseur = NomFournisseur.getSelectedItem().toString();
                String nomClient = NomClient.getText();
                int quantité = Integer.valueOf(Quantité.getText());

                CommandesDAO.AjouterUneCommande(typeVaccin, nomFournisseur, nomClient, quantité);

                JOptionPane.showMessageDialog(null, "Ajout de la commande réussi.");

                this.dispose();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ajout de la commande échouer, veuillez réessayer.");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ajoutCommandeFenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            ajoutCommandeFenetre dialog = new ajoutCommandeFenetre(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NomClient;
    private javax.swing.JComboBox<String> NomFournisseur;
    private javax.swing.JTextField Quantité;
    private javax.swing.JComboBox<String> TypeVaccins;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
