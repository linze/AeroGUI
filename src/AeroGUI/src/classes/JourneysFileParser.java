package classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JourneysFileParser {
    public static ArrayList<JourneyInfo> loadData(String file) throws FileNotFoundException {
        ArrayList<JourneyInfo> result = new ArrayList<JourneyInfo>();

        BufferedReader bi = new BufferedReader(new FileReader(file));
        String line;
        String[] lineinfo;
        try {
            while ((line = bi.readLine()) != null) {
                JourneyInfo journeyinfo = new JourneyInfo();
                lineinfo = line.split("#");

                // Transport information
                journeyinfo.setId(lineinfo[0]);
                journeyinfo.setType(lineinfo[1]);

                // Journey information
                journeyinfo.setOrigin(lineinfo[2]);
                journeyinfo.setDestination(lineinfo[3]);
                
                // TODO: Check email to know teachers answer and date-time format
                journeyinfo.setDeparture(null);
                journeyinfo.setArrival(null);

                // Classes information
                journeyinfo.getTouristinfo().getPrice().setQuantity(Integer.parseInt(lineinfo[6]));
                journeyinfo.getTouristinfo().setSeatsLeft(Integer.parseInt(lineinfo[7]));
                journeyinfo.getBusinessinfo().getPrice().setQuantity(Integer.parseInt(lineinfo[8]));
                journeyinfo.getBusinessinfo().setSeatsLeft(Integer.parseInt(lineinfo[9]));

                // State information
                journeyinfo.setStatus(lineinfo[10]);

                // Append the new JourneyInfo
                result.add(journeyinfo);

            }
        } catch (IOException ex) {
            Logger.getLogger(JourneysFileParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Return the result ArrayList
        return result;
    }

    public static void saveData(ArrayList<JourneyInfo> journeysinfo, String file) {
        // TODO: Implement this
    }

}
