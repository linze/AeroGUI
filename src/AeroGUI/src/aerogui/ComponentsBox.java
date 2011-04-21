package aerogui;

import classes.*;
import java.io.FileNotFoundException;
import java.text.ParseException;

public class ComponentsBox {
    public static UsersHandler userhandler = new UsersHandler();
    public static JourneyHandler journeyhandler = new JourneyHandler();

    public static void initialize() throws FileNotFoundException, ParseException {
        // TODO: UsersHandler initialization

        // JourneyHandler initialization
        journeyhandler.setDatafilename("/tmp/viajes.txt");
        journeyhandler.load();
    }
}
