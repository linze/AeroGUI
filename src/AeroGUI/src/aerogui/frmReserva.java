/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmReserva.java
 *
 * Created on 23-abr-2011, 15:13:41
 */

package aerogui;

import classes.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 *
 * @author notrace
 */
public class frmReserva extends javax.swing.JDialog {
    private double _totalprice;

    /** Creates new form frmReserva */
    public frmReserva(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        fillTree();
    }

    private void fillTree() {
        Operation cart = ComponentsBox.usershandler.getActiveuser().getCart();

        // Get the tree
        TreeModel model = new DefaultTreeModel(generateRootNode(cart));
        trReservation.setModel(model);

//        // Expand all
//        for (int i = 0; i < trReservation.getRowCount(); i++)
//            trReservation.expandRow(i);
    }

    private DefaultMutableTreeNode generateRootNode(Operation cart) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Contenido de la reserva");

        // Add the subnodes
        Travel tr;
        for(int i=0; i< cart.getTravels().size(); i++) {
            tr = cart.getTravels().get(i);
            root.add(generateTravelNode(tr, i+1));
        }
        
        // Add the price
        DefaultMutableTreeNode nprice = new DefaultMutableTreeNode("Precio total: " + Double.toString(this._totalprice) + " EUR");
        root.add(nprice);

        return root;
    }

    private DefaultMutableTreeNode generateTravelNode(Travel tr, Integer num) {
        DefaultMutableTreeNode result = new DefaultMutableTreeNode();

        DefaultMutableTreeNode tmp;
        tmp = new DefaultMutableTreeNode("Pasajeros: " + Integer.toString(tr.getNtravelers()));

        Journey cj;
        // Get the travel price
        JourneyInfo ji;
        double price = 0.0;
        for (int i=0; i<tr.getJourneys().size(); i++) {
            try {
                cj = tr.getJourneys().get(i);
                ji = TravelSearch.doJourneyInfoSearch(cj.getJourneyinfoid(), ComponentsBox.journeyshandler);
                if (cj.getJourneyclass().equals("Turista")) {
                    price += ji.getTouristinfo().getPrice().getQuantity();
                } else {
                    price += ji.getBusinessinfo().getPrice().getQuantity();
                }
            } catch (NotFoundException ex) {
                Logger.getLogger(frmReserva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tmp = new DefaultMutableTreeNode("Precio del viaje: " + Double.toString(price));
        result.add(tmp);
        this._totalprice += price;

        // Get the subnodes
        for (int i=0; i<tr.getJourneys().size(); i++) {
            cj = tr.getJourneys().get(i);
            result.add(generateJourneyNode(cj, i+1));
        }
        
        // Place origin and destination in title
        try {
            String origin = TravelSearch.doJourneyInfoSearch(tr.getOriginJourneyId(), ComponentsBox.journeyshandler).getOrigin();
            String destination = TravelSearch.doJourneyInfoSearch(tr.getDestinationJourneyId(), ComponentsBox.journeyshandler).getDestination();

            result.setUserObject("Viaje " + Integer.toString(num) + ": " + origin + " - " + destination);
        } catch (NotFoundException ex) {
            Logger.getLogger(frmReserva.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    private DefaultMutableTreeNode generateJourneyNode(Journey jr, Integer num) {
        DefaultMutableTreeNode result = new DefaultMutableTreeNode();
        try {
            
            DefaultMutableTreeNode tmp;
            JourneyInfo ji = TravelSearch.doJourneyInfoSearch(jr.getJourneyinfoid(), ComponentsBox.journeyshandler);
            tmp = new DefaultMutableTreeNode("Id trayecto: " + ji.getId());
            result.add(tmp);
            tmp = new DefaultMutableTreeNode("Origen: " + ji.getOrigin());
            result.add(tmp);
            tmp = new DefaultMutableTreeNode("Destino: " + ji.getDestination());
            result.add(tmp);
            tmp = new DefaultMutableTreeNode("Salida: " + JourneyInfo.DATETIMEFORMAT.format(ji.getDeparture().getTime()));
            result.add(tmp);
            tmp = new DefaultMutableTreeNode("Llegada: " + JourneyInfo.DATETIMEFORMAT.format(ji.getArrival().getTime()));
            result.add(tmp);
            if (jr.getJourneyclass().equals("Turista")) {
                tmp = new DefaultMutableTreeNode("Clase: Turista");
                result.add(tmp);
                tmp = new DefaultMutableTreeNode("Precio: " +
                        ji.getTouristinfo().getPrice().toString());
                result.add(tmp);
            } else {
                tmp = new DefaultMutableTreeNode("Clase: Turista");
                result.add(tmp);
                tmp = new DefaultMutableTreeNode("Precio: " +
                        ji.getBusinessinfo().getPrice().toString());
                result.add(tmp);
            }
            String origin;
            String destination;
            result.setUserObject("Etapa " + Integer.toString(num) + ": " + ji.getOrigin() + " - " + ji.getDestination());
            
        } catch (NotFoundException ex) {
            Logger.getLogger(frmReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        trReservation = new javax.swing.JTree();
        btnClose = new javax.swing.JButton();
        btnReserve = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Elementos seleccionados"));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        trReservation.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(trReservation);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
        );

        btnClose.setText("Cancelar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnReserve.setText("Aceptar y reservar");
        btnReserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReserveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(184, Short.MAX_VALUE)
                .addComponent(btnReserve)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(402, 402, 402)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClose)
                            .addComponent(btnReserve)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReserveActionPerformed
        // TODO: Check that they're seats left
        
        User user = ComponentsBox.usershandler.getActiveuser();
        user.getCart().setStatus(OperationStatus.RESERVED);
        user.getOperations().add(user.getCart());
        user.setCart(new Operation());
        try {
            ComponentsBox.saveAll();
            JOptionPane.showMessageDialog(this, "Los elementos se han reservado con éxito.");
            this.dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Se ha producido un error al guardar los datos.");
            Logger.getLogger(frmReserva.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnReserveActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmReserva dialog = new frmReserva(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnReserve;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree trReservation;
    // End of variables declaration//GEN-END:variables

}
