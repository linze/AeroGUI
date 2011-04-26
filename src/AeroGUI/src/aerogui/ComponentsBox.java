package aerogui;

import classes.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class ComponentsBox {
    public static UsersHandler usershandler = new UsersHandler();
    public static JourneyHandler journeyshandler = new JourneyHandler();

    public static void initialize() {
        journeyshandler.setDatafilename("/tmp/viajes.txt");
        usershandler.setDatafilename("/tmp/data.bin");
        loadAll();
    }

    public static void loadAll() {
        try {
            journeyshandler.load();
        } catch (FileNotFoundException ex) {
            System.out.println("[ERR] Travels information file not found.");
        } catch (ParseException ex) {
            System.out.println("[ERR] Travels information file have a wrong format.");
        }
        
        try {
            usershandler.load();
        } catch (FileNotFoundException ex) {
            System.out.println("[ERR] Users information file not found.");
        } catch (IOException ex) {
            System.out.println("[ERR] Users information file produced a error while reading.");
        } catch (ClassNotFoundException ex) {
            System.out.println("[ERR] Users information file have a wrong format.");
        } catch (ParseException ex) {
            System.out.println("[ERR] Users information file have a wrong format.");
        }
    }

    public static void saveAll() throws IOException {
        journeyshandler.save();
        usershandler.save();
    }
}
