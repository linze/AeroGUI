package classes;

import java.util.ArrayList;

public class UsersHandler {
    private ArrayList<User> _users = new ArrayList<User>();

    public UsersHandler() {
    }

    public UsersHandler(ArrayList<User> _users) {
        this._users = _users;
    }

    public ArrayList<User> getUsers() {
        return _users;
    }

    protected void setUsers(ArrayList<User> _users) {
        this._users = _users;
    }

    // TODO: Check if this works as supposed
    public User getUser(String email) {
        for (int i=0; i<this._users.size(); i++) {
          if (this._users.get(i).getEmail().equals(email))
              return this._users.get(i);
        }
        // TODO: Raise a exception here instead of returning null
        return null;
    }
    

}
