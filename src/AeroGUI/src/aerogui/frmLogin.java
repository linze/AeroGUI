/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmLogin.java
 *
 * Created on 21-abr-2011, 21:36:21
 */

package aerogui;

import java.util.Timer;
import javax.swing.JOptionPane;

/**
 *
 * @author notrace
 */
public class frmLogin extends javax.swing.JDialog {
    private java.awt.Frame _parent;
    private Timer _splashUpdater;
    private long _msChangeTime = 6*1000;

    /** Creates new form frmLogin */
    public frmLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this._parent = parent;
        initComponents();
        initSplash();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    private void initSplash() {
        this._splashUpdater = new Timer();
        this._splashUpdater.schedule(new SplashTask(lbSplash), 0, _msChangeTime);
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
        txtEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtLogin = new javax.swing.JButton();
        txtRegister = new javax.swing.JButton();
        lbSplash = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Acceso al sistema"));

        jLabel1.setText("Email");

        jLabel2.setText("Contraseña");

        txtLogin.setText("Acceder");
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });

        txtRegister.setText("Registrarse");
        txtRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRegister)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLogin)
                    .addComponent(txtRegister))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbSplash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSplash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/splash1.png"))); // NOI18N
        lbSplash.setBorder(null);
        lbSplash.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbSplash.setIconTextGap(0);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbSplash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lbSplash)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRegisterActionPerformed
        frmRegistro fr = new frmRegistro(this._parent, true);
        fr.setVisible(true);
    }//GEN-LAST:event_txtRegisterActionPerformed

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        boolean result;
        result = ComponentsBox.usershandler.login(txtEmail.getText().toLowerCase(),
                String.valueOf(txtPassword.getPassword()));
        if (result == true) {
            ComponentsBox.usershandler.setActive(txtEmail.getText().toLowerCase());
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "La combinación de usuario y contraseña no es válida.");
        }
    }//GEN-LAST:event_txtLoginActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmLogin dialog = new frmLogin(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbSplash;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JButton txtLogin;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JButton txtRegister;
    // End of variables declaration//GEN-END:variables

}
