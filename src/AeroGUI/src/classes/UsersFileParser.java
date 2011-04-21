package classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;

public class UsersFileParser {

  public static UsersList loadData(String file) throws FileNotFoundException, ParseException, IOException, ClassNotFoundException
  {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        UsersList alu = (UsersList)ois.readObject();
        ois.close();
        return alu;
  }

  public static void saveData(UsersList userslist, String file) throws IOException
  {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(userslist);
        oos.close();
  }
}
