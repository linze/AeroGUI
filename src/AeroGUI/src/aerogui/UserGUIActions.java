package aerogui;

import classes.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserGUIActions {
    public static boolean verifySeats(Journey j, Integer seats) {
        try {
            JourneyInfo ji = TravelSearch.doJourneyInfoSearch(j.getJourneyinfoid(), ComponentsBox.journeyshandler);
            if (j.getJourneyclass().equals("Turista")) {
                if (ji.getTouristinfo().getSeatsLeft() >= seats) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (ji.getBusinessinfo().getSeatsLeft() >= seats) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (NotFoundException ex) {
            return false;
        }
    }

    // FIXME: It doesn't check for repeated Journeys
    public static boolean verifySeats(Travel t) {
        boolean result = true;
        for (int i=0; i<t.getJourneys().size(); i++) {
            result = verifySeats(t.getJourneys().get(i), t.getNtravelers());
            if (result == false)
                break;
        }

        return result;
    }

    // FIXME: It doesn't check for repeated Journeys
    public static boolean verifySeats(Operation o) {
        boolean result = true;
        for (int i=0; i<o.getTravels().size(); i++) {
            result = verifySeats(o.getTravels().get(i));
            if (result == false)
                break;
        }

        return result;
    }

    public static void updateSeats(Journey j, Integer seats) {
        try {
            JourneyInfo ji = TravelSearch.doJourneyInfoSearch(j.getJourneyinfoid(), ComponentsBox.journeyshandler);
            if (j.getJourneyclass().equals("Turista")) {
                ji.getTouristinfo().setSeatsLeft(ji.getTouristinfo().getSeatsLeft() - seats);
            } else {
                ji.getBusinessinfo().setSeatsLeft(ji.getBusinessinfo().getSeatsLeft() - seats);
            }
        } catch (NotFoundException ex) {
            Logger.getLogger(UserGUIActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateSeats(Travel t) {
        for (int i=0; i<t.getJourneys().size(); i++) {
            updateSeats(t.getJourneys().get(i), t.getNtravelers());
        }
    }

    public static void updateSeats(Operation o) {
        for (int i=0; i<o.getTravels().size(); i++) {
            updateSeats(o.getTravels().get(i));
        }
    }
}
