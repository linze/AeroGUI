package classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
                journeyinfo.getTouristinfo().getPrice().setQuantity(Double.parseDouble(lineinfo[6]));
                journeyinfo.getTouristinfo().setSeatsLeft(Integer.parseInt(lineinfo[7]));
                journeyinfo.getBusinessinfo().getPrice().setQuantity(Double.parseDouble(lineinfo[8]));
                journeyinfo.getBusinessinfo().setSeatsLeft(Integer.parseInt(lineinfo[9]));

                // State information
                journeyinfo.setStatus(lineinfo[10]);

                // Append the new JourneyInfo
                result.add(journeyinfo);

            }

            bi.close();
        } catch (IOException ex) {
            Logger.getLogger(JourneysFileParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Return the result ArrayList
        return result;
    }

    public static void saveData(ArrayList<JourneyInfo> journeysinfo, String file) throws IOException {
       PrintWriter pw = new PrintWriter(new FileWriter(file));

       JourneyInfo ji;
       for (int i=0; i<journeysinfo.size(); i++) {
           ji = journeysinfo.get(i);
           pw.print(ji.getId() + "#");
           pw.print(ji.getType() + "#");
           pw.print(ji.getOrigin() + "#");
           pw.print(ji.getDestination() + "#");
           // FIXME: Add Date print when teachers reply
           pw.print("FIXME" + "#");
           pw.print("FIXME" + "#");
           pw.print(Double.toString(ji.getTouristinfo().getPrice().getQuantity()) + "#");
           pw.print(Integer.toString(ji.getTouristinfo().getSeatsLeft()) + "#");
           pw.print(Double.toString(ji.getBusinessinfo().getPrice().getQuantity()) + "#");
           pw.print(Integer.toString(ji.getBusinessinfo().getSeatsLeft()) + "#");
           pw.println(ji.getStatus());
       }

       pw.close();
    }

}
