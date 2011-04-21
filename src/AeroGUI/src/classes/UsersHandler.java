
package classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

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

  public void setActiveuser(User _activeuser) {
        this._activeuser = _activeuser;
  }

  public String getActiveuseremail() {
        return _activeuseremail;
  }

  public void setActiveuseremail(String _activeuseremail) {
        this._activeuseremail = _activeuseremail;
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
