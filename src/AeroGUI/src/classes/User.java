
package classes;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import security.*;

public class User implements Serializable {
  private String _firstname;
  private String _lastname;
  private String _address;
  private String _email;
  private String _password;
  private ArrayList<Operation> _operations =  new ArrayList<Operation>();
  private Operation _cart = new Operation();

  public User() {
  }

  public String getAddress() {
        return _address;
  }

  public void setAddress(String _address) {
        this._address = _address;
  }

  public String getEmail() {
        return _email;
  }

  public void setEmail(String _email) {
        this._email = _email.toLowerCase();
  }

  public String getFirstname() {
        return _firstname;
  }

  public void setFirstname(String _firstname) {
        this._firstname = _firstname;
  }

  public String getLastname() {
        return _lastname;
  }

  public void setLastname(String _lastname) {
        this._lastname = _lastname;
  }

  public ArrayList<Operation> getOperations() {
        return _operations;
  }

  public void setOperations(ArrayList<Operation> _operations) {
        this._operations = _operations;
  }

  public Operation getCart() {
        return _cart;
  }

  public void setCart(Operation _cart) {
        this._cart = _cart;
  }


  public String getPasswordHash() {
        return _password;
  }

  protected void setPasswordHash(String _password) {
        this._password = _password;
  }

  public void setPassword(String _password) {
        try {
            this._password = md5.md5hash(_password);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
  }

  public boolean isPassword(String _password) {
        try {
            return this._password.equals(md5.md5hash(_password));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
  }

  public ArrayList<Operation> getReservedOperations() {
      ArrayList<Operation> result = new ArrayList<Operation>();
      Operation current;
      for (int i=0; i<this._operations.size(); i++) {
          current = this._operations.get(i);
          if (current.getStatus() == OperationStatus.RESERVED)
              result.add(current);
      }

      return result;
  }

  public ArrayList<Operation> getBoughtOperations() {
      ArrayList<Operation> result = new ArrayList<Operation>();
      Operation current;
      for (int i=0; i<this._operations.size(); i++) {
          current = this._operations.get(i);
          if (current.getStatus() == OperationStatus.BOUGHT)
              result.add(current);
      }

      return result;
  }

}
