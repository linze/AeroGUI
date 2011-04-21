package aerogui;

import classes.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComponentsBox {
    public static UsersHandler usershandler = new UsersHandler();
    public static JourneyHandler journeyshandler = new JourneyHandler();

    public static void initialize() {
        journeyshandler.setDatafilename("/tmp/viajes.txt");
        usershandler.setDatafilename("/tmp/data.bin");
        try {
            loadAll();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ComponentsBox.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ComponentsBox.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ComponentsBox.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComponentsBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loadAll() throws FileNotFoundException, ParseException, IOException, ClassNotFoundException {
        journeyshandler.load();
        usershandler.load();
    }

    public static void saveAll() throws IOException {
        journeyshandler.save();
        usershandler.save();
    }
}
