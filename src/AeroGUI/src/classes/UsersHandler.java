
package classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersHandler {
  private UsersList _userslist =  new UsersList();
  private String _activeuseremail =  null;
  private User _activeuser =  null;
  private String _datafilename =  "data.bin";

  public UsersHandler() {
  }

  public UsersHandler(String datafile) {
        this._datafilename = datafile;
  }

  public void load() throws FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        this._userslist = UsersFileParser.loadData(this._datafilename);
  }

  public void save() throws FileNotFoundException, IOException {
        UsersFileParser.saveData(this._userslist, this._datafilename);
  }

  public User getActiveuser() {
        return _activeuser;
  }

  protected void setActiveuser(User _activeuser) {
        this._activeuser = _activeuser;
  }

  public String getActiveuseremail() {
        return _activeuseremail;
  }

  protected void setActiveuseremail(String _activeuseremail) {
        this._activeuseremail = _activeuseremail;
  }

  public void setActive(String email) {
        try {
            User activeuser = this._userslist.getUser(email);
            this.setActiveuser(activeuser);
            this.setActiveuseremail(email);
        } catch (NotFoundException ex) {
            Logger.getLogger(UsersHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
  }

  public String getDatafilename() {
        return _datafilename;
  }

  public void setDatafilename(String _datafile) {
        this._datafilename = _datafile;
  }

  public boolean login(String email, String password) {
        try {
            User logUser = this._userslist.getUser(email);
            return logUser.isPassword(password);
        } catch (NotFoundException ex) {
            return false;
        }

  }

  public boolean register(String fname, String lname, String address, String email, String password) {
        try {
            this._userslist.getUser(email);
            return false;
        } catch (NotFoundException ex) {
            User newUser = new User();
            newUser.setFirstname(fname);
            newUser.setLastname(lname);
            newUser.setAddress(address);
            newUser.setEmail(email);
            newUser.setPassword(password);

            this._userslist.getUsers().add(newUser);

            return true;
        }
  }

}
