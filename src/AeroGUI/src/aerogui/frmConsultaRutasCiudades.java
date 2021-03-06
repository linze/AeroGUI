/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmConsultaRutasCiudades.java
 *
 * Created on 02-may-2011, 15:58:38
 */

package aerogui;

import classes.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author notrace
 */
public class frmConsultaRutasCiudades extends javax.swing.JFrame {
    private SearchResult _sr = null;
    private TravelInfo _travel = null;
    private JourneyInfo _journey = null;


    /** Creates new form frmConsultaRutasCiudades */
    public frmConsultaRutasCiudades() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtOriginSearch.removeAllItems();
        txtDestinationSearch.removeAllItems();
        ArrayList<String> origins = ComponentsBox.journeyshandler.getOrigins();
        for (int i=0; i<origins.size(); i++) {
            txtOriginSearch.addItem(origins.get(i));
        }   
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
        jLabel1 = new javax.swing.JLabel();
        txtOriginSearch = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtDestinationSearch = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        selClass = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        txtSeats = new javax.swing.JSpinner();
        btnSearch = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblJourneys = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTravels = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnCart = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtOrigin1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDestination1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtArrival1 = new javax.swing.JTextField();
        txtDeparture1 = new javax.swing.JTextField();
        txtPrice1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSeats1 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDestination = new javax.swing.JTextField();
        txtOrigin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtArrival = new javax.swing.JTextField();
        txtDeparture = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        txtType = new javax.swing.JTextField();
        txtClass = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Selección"));

        jLabel1.setText("Origen");

        txtOriginSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtOriginSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtOriginSearchItemStateChanged(evt);
            }
        });

        jLabel9.setText("Destino");

        txtDestinationSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtDestinationSearch.setEnabled(false);

        jLabel7.setText("Clase");

        selClass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Turista", "Ejecutivo" }));
        selClass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selClassItemStateChanged(evt);
            }
        });

        jLabel17.setText("Asientos");

        txtSeats.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        txtSeats.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtSeatsStateChanged(evt);
            }
        });

        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(selClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtOriginSearch, 0, 203, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDestinationSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSeats, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(242, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtDestinationSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtOriginSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(selClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtSeats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tblJourneys.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblJourneys.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblJourneysMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblJourneys);

        txtTravels.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTravelsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(txtTravels);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );

        btnClose.setText("Cerrar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnCart.setText("Añadir al carrito");
        btnCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(btnCart, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(btnCart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose))
        );

        jLabel10.setText("Origen");

        txtOrigin1.setEnabled(false);

        jLabel11.setText("Destino");

        txtDestination1.setEnabled(false);

        jLabel12.setText("Salida");

        jLabel13.setText("Llegada");

        txtArrival1.setEnabled(false);

        txtDeparture1.setEnabled(false);

        txtPrice1.setEnabled(false);

        jLabel14.setText("Precio");

        jLabel15.setText("Asientos");

        txtSeats1.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(424, 424, 424)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSeats1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(txtPrice1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtOrigin1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addComponent(txtDestination1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                    .addGap(17, 17, 17)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel13)
                        .addComponent(jLabel12))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtArrival1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtDeparture1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                    .addGap(204, 204, 204)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSeats1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtDeparture1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(txtOrigin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtDestination1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(txtArrival1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(55, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Descripción del viaje", jPanel6);

        jLabel2.setText("Origen");

        jLabel3.setText("Destino");

        jLabel8.setText("Clase");

        txtDestination.setEnabled(false);

        txtOrigin.setEnabled(false);

        jLabel4.setText("Salida");

        jLabel16.setText("Llegada");

        txtArrival.setEnabled(false);

        txtDeparture.setForeground(new java.awt.Color(17, 5, 5));
        txtDeparture.setEnabled(false);

        jLabel5.setText("Tipo");

        jLabel6.setText("Precio");

        txtPrice.setEnabled(false);

        txtType.setEnabled(false);

        txtClass.setEnabled(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOrigin, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(txtDestination, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(txtClass, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtArrival, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDeparture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel16)
                    .addComponent(txtArrival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Descripción de la etapa", jPanel7);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(73, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtOriginSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtOriginSearchItemStateChanged
        String origin = (String)txtOriginSearch.getSelectedItem();
        txtDestinationSearch.removeAllItems();
        ArrayList<String> destinations = ComponentsBox.journeyshandler.getDestinations();
        for (int i=0; i<destinations.size(); i++) {
            if (!destinations.get(i).equals(origin))
                txtDestinationSearch.addItem(destinations.get(i));
        }
        txtDestinationSearch.setEnabled(true);
}//GEN-LAST:event_txtOriginSearchItemStateChanged

    private void tblJourneysMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblJourneysMouseClicked
        updateActualRecord();
        onJourneyChange();
}//GEN-LAST:event_tblJourneysMouseClicked

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
}//GEN-LAST:event_btnCloseActionPerformed

    private void btnCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartActionPerformed
        if ((this._journey != null) && (this._travel != null)) {
            Travel newt = new Travel();
            newt.setNtravelers((Integer)txtSeats.getValue());

            String selectedclass = (String)selClass.getSelectedItem();
            Journey newj;
            for (JourneyInfo journeyinfo : _travel.getJourneysinfo()) {
                newj = new Journey(journeyinfo, selectedclass);
                newt.getJourneys().add(newj);
            }
            
            if (GUIActions.verifySeats(newt)) {
                ComponentsBox.usershandler.getActiveuser().getCart().getTravels().add(newt);
                try {
                    ComponentsBox.saveAll();
                    JOptionPane.showMessageDialog(this, "Los elementos se han añadido al carrito exitosamente.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Se ha producido un error al guardar los datos.");
                    Logger.getLogger(frmConsultaDirecto.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Lo lamentamos, el transporte no dispone de tantas plazas.");
            }
        }
}//GEN-LAST:event_btnCartActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // Clear details view
        this._journey = null;
        // TODO: Clear fields
        //updateDetails();

        // Do the search
        String origin = (String)txtOriginSearch.getSelectedItem();
        String destination = (String)txtDestinationSearch.getSelectedItem();
        this._sr = TravelSearch.doRouteSearch(origin, destination, ComponentsBox.journeyshandler);
        tblJourneys.removeAll();
        if(!this._sr.getTravelsinfo().isEmpty()){
            System.out.println("Busqueda completa");
            for (TravelInfo t : _sr.getTravelsinfo()) {
                System.out.println("+ Viaje ");
                for (JourneyInfo j : t.getJourneysinfo())
                    System.out.println("|---- " + j.getOrigin() + "->" +j.getDestination());
            }
        txtTravels.setModel(GUIActions.searchResultList(_sr));
        }
        else{
            JOptionPane.showMessageDialog(this, "No se han encontrado rutas válidas");
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtTravelsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTravelsMouseClicked
        onTravelChange();
    }//GEN-LAST:event_txtTravelsMouseClicked

    private void txtSeatsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtSeatsStateChanged
        updateTravelDetails();
        updateJourneyDetails();
    }//GEN-LAST:event_txtSeatsStateChanged

    private void selClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selClassItemStateChanged
        updateTravelDetails();
        updateJourneyDetails();
    }//GEN-LAST:event_selClassItemStateChanged

    private void updateActualRecord() {
        this._journey = _travel.getJourneysinfo().get(tblJourneys.getSelectedRow());
    }

    private void onTravelChange() {
        this._travel = this._sr.getTravelsinfo().get(txtTravels.getSelectedIndex());

        tblJourneys.setModel(GUIActions.travelInfoTable(_travel));

        updateTravelDetails();
    }

    private void onJourneyChange() {
        this._journey = _travel.getJourneysinfo().get(tblJourneys.getSelectedRow());
        updateJourneyDetails();
    }

    private void updateTravelDetails() {
        if (this._travel != null) {
            JourneyInfo firstji = _travel.getJourneysinfo().get(0);
            JourneyInfo lastji = _travel.getJourneysinfo().get(_travel.getJourneysinfo().size() - 1);

            txtOrigin1.setText(firstji.getOrigin());
            txtDestination1.setText(lastji.getDestination());
            txtDeparture1.setText(JourneyInfo.DATETIMEFORMAT.format(firstji.getDeparture().getTime()));
            txtArrival1.setText(JourneyInfo.DATETIMEFORMAT.format(lastji.getArrival().getTime()));
            txtPrice1.setText(this.updateTravelPrice());
            txtSeats1.setText(txtSeats.getValue().toString());

        } else {
            txtOrigin1.setText("");
            txtDestination1.setText("");
            txtDeparture1.setText("");
            txtArrival1.setText("");
            txtPrice1.setText("");
        }
    }
    private void updateJourneyDetails() {
        if (this._journey != null) {
            txtOrigin.setText(this._journey.getOrigin());
            txtDestination.setText(this._journey.getDestination());
            txtDeparture.setText(JourneyInfo.DATETIMEFORMAT.format(this._journey.getDeparture().getTime()));
            txtArrival.setText(JourneyInfo.DATETIMEFORMAT.format(this._journey.getArrival().getTime()));
            txtType.setText(this._journey.getType());
            txtPrice.setText(String.valueOf(calcStagePrice(this._journey)));
            txtClass.setText((String)selClass.getSelectedItem());
        } else {
            txtOrigin.setText("");
            txtDestination.setText("");
            txtDeparture.setText("");
            txtArrival.setText("");
            txtType.setText("");
            txtPrice.setText("");
            txtClass.setText("");
        }
    }
    
    private double calcStagePrice(JourneyInfo journey) {
        Integer seats = (Integer)txtSeats.getValue();
        String selectedclass = (String)selClass.getSelectedItem();
        if (selectedclass.equals("Turista")) {
            return journey.getTouristinfo().getPrice().getQuantity() * seats;
        } else {
            return journey.getBusinessinfo().getPrice().getQuantity() * seats;
        }

    }
    
    private String updateTravelPrice(){
        int travelSize = this._travel.getJourneysinfo().size();
        String result;
        double price = 0.0;
        double added = 0.0;
        for(int i=0;i<travelSize;i++){
            JourneyInfo current = this._travel.getJourneysinfo().get(i);
            added = calcStagePrice(current);
            price += added;
        }
        result = String.valueOf(price);
        return result;
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmConsultaRutasCiudades().setVisible(true);
                       
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCart;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox selClass;
    private javax.swing.JTable tblJourneys;
    private javax.swing.JTextField txtArrival;
    private javax.swing.JTextField txtArrival1;
    private javax.swing.JTextField txtClass;
    private javax.swing.JTextField txtDeparture;
    private javax.swing.JTextField txtDeparture1;
    private javax.swing.JTextField txtDestination;
    private javax.swing.JTextField txtDestination1;
    private javax.swing.JComboBox txtDestinationSearch;
    private javax.swing.JTextField txtOrigin;
    private javax.swing.JTextField txtOrigin1;
    private javax.swing.JComboBox txtOriginSearch;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtPrice1;
    private javax.swing.JSpinner txtSeats;
    private javax.swing.JTextField txtSeats1;
    private javax.swing.JList txtTravels;
    private javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables

}
