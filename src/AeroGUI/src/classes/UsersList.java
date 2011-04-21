package classes;

import java.io.Serializable;
import java.util.ArrayList;
public class UsersList implements Serializable {
  private ArrayList<User> _users =  new ArrayList<User>();

  public UsersList() {
  }

  public UsersList(ArrayList<User> _users) {
        this._users = _users;
  }

  public ArrayList<User> getUsers() {
        return _users;
  }

  protected void setUsers(ArrayList<User> _users) {
        this._users = _users;
  }

  public User getUser(String email) throws NotFoundException {
        for (int i=0; i<this._users.size(); i++) {
          if (this._users.get(i).getEmail().equals(email))
              return this._users.get(i);
        }
        throw new NotFoundException();
  }

}
