package aerogui;

import classes.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class GUIActions {   
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

    public static boolean verifySeats(Travel t) {
        boolean result = true;
        for (int i=0; i<t.getJourneys().size(); i++) {
            result = verifySeats(t.getJourneys().get(i), t.getNtravelers());
            if (result == false)
                break;
        }

        return result;
    }

    public static boolean verifySeats(Operation o) {
        boolean result = true;
        for (int i=0; i<o.getTravels().size(); i++) {
            result = verifySeats(o.getTravels().get(i));
            if (result == false)
                break;
        }

        return result;
    }

    public static boolean complexVerifySeats(Operation o) {
        ArrayList<String> touristid = new ArrayList<String>();
        ArrayList<Integer> touristseats = new ArrayList<Integer>();
        ArrayList<String> businessid = new ArrayList<String>();
        ArrayList<Integer> businessseats = new ArrayList<Integer>();

        // Current journey
        Journey cj = null;
        // Current travel
        Travel ct = null;
        // Temporal journey related journeyinfo
        JourneyInfo ji = null;
        // Temporal ArrayList index
        Integer idx = null;
        for (int i=0; i < o.getTravels().size() ; i++) {
            // We get all the travels
            ct = o.getTravels().get(i);
            for (int j=0; j< ct.getJourneys().size(); j++) {
                // We get all the journeys
                cj = ct.getJourneys().get(j);
                // Se search if it's already contained
                if (cj.getJourneyclass().equals("Turista")) {
                    idx = touristid.indexOf(cj.getJourneyinfoid());
                    // If it's not contained, add it
                    if (idx == -1) {
                        touristid.add(cj.getJourneyinfoid());
                        touristseats.add(ct.getNtravelers());
                    // If it's contained, sum the seats
                    } else {
                        touristseats.set(idx, touristseats.get(idx) + ct.getNtravelers());
                    }
                } else {
                    idx = businessid.indexOf(cj.getJourneyinfoid());
                    // If it's not contained, add it
                    if (idx == -1) {
                        businessid.add(cj.getJourneyinfoid());
                        businessseats.add(ct.getNtravelers());
                    // If it's contained, sum the seats
                    } else {
                        businessseats.set(idx, businessseats.get(idx) + ct.getNtravelers());
                    }
                }
            }
        }

        // Generate the Journey and Travel
        Operation metaop = new Operation();
        Travel metatr;
        Journey metajr;
        // First the tourist
        for (int i=0; i<touristid.size(); i++) {
            metatr = new Travel();
            metatr.setNtravelers(touristseats.get(i));

            metajr = new Journey();
            metajr.setJourneyclass("Turista");
            metajr.setJourneyinfoid(touristid.get(i));
            
            metatr.getJourneys().add(metajr);

            metaop.getTravels().add(metatr);
        }
        
        // Last the executives
        for (int i=0; i<businessid.size(); i++) {
            metatr = new Travel();
            metatr.setNtravelers(businessseats.get(i));

            metajr = new Journey();
            metajr.setJourneyclass("Ejecutivo");
            metajr.setJourneyinfoid(businessid.get(i));

            metatr.getJourneys().add(metajr);

            metaop.getTravels().add(metatr);
        }

        return verifySeats(metaop);
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
            Logger.getLogger(GUIActions.class.getName()).log(Level.SEVERE, null, ex);
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

    /*
     * Fill a table with all the JourneyInfo's found in a TravelInfo
     */
    public static DefaultTableModel travelInfoTable(TravelInfo tr) {
        ArrayList<JourneyInfo> jis = tr.getJourneysinfo();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Origen", "Destino", "Tipo", "Precio"});

        JourneyInfo current;
        for (int i=0; i < jis.size(); i++) {
            // We access to position 0 because origin searchs have only one step
            current = jis.get(i);
            model.addRow(new Object[]{current.getOrigin(),
                current.getDestination(),
                current.getType(),
                "Tur: " + current.getTouristinfo().getPrice().toString() +
                " Ejec: " + current.getBusinessinfo().getPrice().toString()});
        }
        
        return model;
    }

    /*
     * Fill a thable with all one-journeyinfo TravelInfo found in a SearchResult
     */
    public static DefaultTableModel oneItemSRTable(SearchResult sr) {
        ArrayList<TravelInfo> tis = sr.getTravelsinfo();
        
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Origen", "Destino", "Tipo", "Precio"});

        JourneyInfo current;
        for (int i=0; i < tis.size(); i++) {
            // We access to position 0 because origin searchs have only one step
            current = tis.get(i).getJourneysinfo().get(0);
            model.addRow(new Object[]{current.getOrigin(),
                current.getDestination(),
                current.getType(),
                "Tur: " + current.getTouristinfo().getPrice().toString() +
                " Ejec: " + current.getBusinessinfo().getPrice().toString()});
        }
        
        return model;
    }

    public static DefaultTableModel travelsTable(Travel t) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Origen", "Destino", "Tipo", "Precio"});

        JourneyInfo ji;
        Journey j;
        for (int i=0; i<t.getJourneys().size(); i++) {
            try {
                j = t.getJourneys().get(i);
                ji = TravelSearch.doJourneyInfoSearch(j.getJourneyinfoid(), ComponentsBox.journeyshandler);
                if (j.getJourneyclass().equals("Turista")) {
                    model.addRow(new Object[]{ji.getOrigin(),
                        ji.getDestination(),
                        ji.getType(),
                        ji.getTouristinfo().getPrice().toString()});
                } else {
                    model.addRow(new Object[]{ji.getOrigin(),
                        ji.getDestination(),
                        ji.getType(),
                        ji.getBusinessinfo().getPrice().toString()});
                }
            } catch (NotFoundException ex) {
                Logger.getLogger(frmCart.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return model;
    }

    public static DefaultListModel stringList(ArrayList<String> strings) {
        DefaultListModel dlm = new DefaultListModel();

        for (int i=0; i< strings.size(); i++) {
            dlm.addElement(strings.get(i));
        }

        return dlm;
    }

    public static DefaultListModel operationList(Operation o) {
        ArrayList<String> als = new ArrayList<String>();
        
        String origin;
        String destination;
        for (int i=0; i< o.getTravels().size(); i++) {
            origin = o.getTravels().get(i).getOriginJourneyId();
            destination = o.getTravels().get(i).getDestinationJourneyId();
            try {
                origin = TravelSearch.doJourneyInfoSearch(origin, ComponentsBox.journeyshandler).getOrigin();
                destination = TravelSearch.doJourneyInfoSearch(destination, ComponentsBox.journeyshandler).getDestination();
            } catch (NotFoundException ex) {
                Logger.getLogger(frmCart.class.getName()).log(Level.SEVERE, null, ex);
            }
            als.add(origin + "-" + destination);
        }
        
        return stringList(als);
    }

    public static DefaultListModel searchResultList(SearchResult sr) {
        ArrayList<String> als = new ArrayList<String>();

        String origin;
        String destination;
        int i = 0;
        for (TravelInfo travel : sr.getTravelsinfo()) {
            origin = travel.getJourneysinfo().get(0).getOrigin();
            destination = travel.getJourneysinfo().get(travel.getJourneysinfo().size()-1).getDestination();
            als.add(origin + "-" + destination + " #" + Integer.toString(i));
            i++;
        }

        return stringList(als);
    }

    public static Price calcJourneyPrice(Journey j) {
        Price result = new Price();
        
        try {
            JourneyInfo ji = TravelSearch.doJourneyInfoSearch(j.getJourneyinfoid(), ComponentsBox.journeyshandler);
            if (j.getJourneyclass().equals("Turista")) {
                result.setQuantity(ji.getTouristinfo().getPrice().getQuantity());
                result.setCurrency(ji.getTouristinfo().getPrice().getCurrency());
            } else {
                result.setQuantity(ji.getBusinessinfo().getPrice().getQuantity());
                result.setCurrency(ji.getBusinessinfo().getPrice().getCurrency());
            }
        } catch (NotFoundException ex) {
            Logger.getLogger(GUIActions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static Price calcTravelPrice(Travel t) {
        Price result = new Price();

        Journey j;
        Price jp;
        for (int i=0; i<t.getJourneys().size(); i++) {
            // Get one journey price
            j = t.getJourneys().get(i);
            jp = calcJourneyPrice(j);
            // Calc it for N travelers
            jp.setQuantity(jp.getQuantity() * t.getNtravelers());
            // Add the result
            result.setQuantity(result.getQuantity() + jp.getQuantity());
        }

        return result;
    }

    public static Price calcOperationPrice(Operation o) {
        Price result = new Price();

        Travel t;
        Price tp;
        for (int i=0; i<o.getTravels().size(); i++) {
            // Get one journey price
            t = o.getTravels().get(i);
            tp = calcTravelPrice(t);
            // Add the result
            result.setQuantity(result.getQuantity() + tp.getQuantity());
        }

        return result;
    }

    /*
     * Operation tree representation
     */


    public static TreeModel operationTree(Operation o) {
         return new DefaultTreeModel(operationNodeTree(o));
    }

    private static DefaultMutableTreeNode operationNodeTree(Operation o) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Contenido de la reserva");

        // Add the price
        Price oprice = calcOperationPrice(o);
        DefaultMutableTreeNode nprice = new DefaultMutableTreeNode("Precio total: " + oprice.toString());
        root.add(nprice);

        // Add the subnodes
        Travel tr;
        for(int i=0; i< o.getTravels().size(); i++) {
            tr = o.getTravels().get(i);
            root.add(travelNodeTree(tr, i+1));
        }

        return root;
    }

    private static DefaultMutableTreeNode travelNodeTree(Travel tr, Integer num) {
        DefaultMutableTreeNode result = new DefaultMutableTreeNode();

        DefaultMutableTreeNode tmp;
        tmp = new DefaultMutableTreeNode("Pasajeros: " + Integer.toString(tr.getNtravelers()));

        Price trprice = calcTravelPrice(tr);
        tmp = new DefaultMutableTreeNode("Precio del viaje: " + trprice.toString());
        result.add(tmp);

        Journey cj;
        // Get the subnodes
        for (int i=0; i<tr.getJourneys().size(); i++) {
            cj = tr.getJourneys().get(i);
            result.add(journeyNodeTree(cj, i+1));
        }

        // Place origin and destination in title
        try {
            String origin = TravelSearch.doJourneyInfoSearch(tr.getOriginJourneyId(), ComponentsBox.journeyshandler).getOrigin();
            String destination = TravelSearch.doJourneyInfoSearch(tr.getDestinationJourneyId(), ComponentsBox.journeyshandler).getDestination();

            result.setUserObject("Viaje " + Integer.toString(num) + ": " + origin + " - " + destination);
        } catch (NotFoundException ex) {
            Logger.getLogger(frmReserva.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    private static DefaultMutableTreeNode journeyNodeTree(Journey jr, Integer num) {
        DefaultMutableTreeNode result = new DefaultMutableTreeNode();
        try {
            DefaultMutableTreeNode tmp;
            JourneyInfo ji = TravelSearch.doJourneyInfoSearch(jr.getJourneyinfoid(), ComponentsBox.journeyshandler);
            tmp = new DefaultMutableTreeNode("Id trayecto: " + ji.getId());
            result.add(tmp);
            tmp = new DefaultMutableTreeNode("Origen: " + ji.getOrigin());
            result.add(tmp);
            tmp = new DefaultMutableTreeNode("Destino: " + ji.getDestination());
            result.add(tmp);
            tmp = new DefaultMutableTreeNode("Salida: " + JourneyInfo.DATETIMEFORMAT.format(ji.getDeparture().getTime()));
            result.add(tmp);
            tmp = new DefaultMutableTreeNode("Llegada: " + JourneyInfo.DATETIMEFORMAT.format(ji.getArrival().getTime()));
            result.add(tmp);
            if (jr.getJourneyclass().equals("Turista")) {
                tmp = new DefaultMutableTreeNode("Clase: Turista");
                result.add(tmp);
                tmp = new DefaultMutableTreeNode("Precio: " +
                        ji.getTouristinfo().getPrice().toString());
                result.add(tmp);
            } else {
                tmp = new DefaultMutableTreeNode("Clase: Ejecutivo");
                result.add(tmp);
                tmp = new DefaultMutableTreeNode("Precio: " +
                        ji.getBusinessinfo().getPrice().toString());
                result.add(tmp);
            }
            result.setUserObject("Etapa " + Integer.toString(num) + ": " + ji.getOrigin() + " - " + ji.getDestination());

        } catch (NotFoundException ex) {
            Logger.getLogger(frmReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /*
     * SearchResult tree representation
     */

    public static TreeModel searchTree(SearchResult sr) {
         return new DefaultTreeModel(searchResultNodeTree(sr));
    }

    private static DefaultMutableTreeNode searchResultNodeTree(SearchResult sr) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Contenido de la búsqueda");

        // Add the price
        //Price oprice = calcOperationPrice(o);
        //DefaultMutableTreeNode nprice = new DefaultMutableTreeNode("Precio total: " + oprice.toString());
        //root.add(nprice);

        // Add the subnodes
        TravelInfo ti;
        for(int i=0; i< sr.getTravelsinfo().size(); i++) {
            ti = sr.getTravelsinfo().get(i);
            root.add(travelInfoNodeTree(ti, i+1));
        }

        return root;
    }

    private static DefaultMutableTreeNode travelInfoNodeTree(TravelInfo ti, Integer num) {
        DefaultMutableTreeNode result = new DefaultMutableTreeNode();

        //Price trprice = calcTravelPrice(tr);
        //tmp = new DefaultMutableTreeNode("Precio del viaje: " + trprice.toString());
        //result.add(tmp);

        JourneyInfo ji;
        // Get the subnodes
        for (int i=0; i<ti.getJourneysinfo().size(); i++) {
            ji = ti.getJourneysinfo().get(i);
            result.add(journeyInfoNodeTree(ji, i+1));
        }

        // Place origin and destination in title
        String origin = ti.getJourneysinfo().get(0).getOrigin();
        String destination = ti.getJourneysinfo().get(ti.getJourneysinfo().size()-1).getDestination();

        result.setUserObject("Viaje " + Integer.toString(num) + ": " + origin + " - " + destination);

        return result;
    }

    private static DefaultMutableTreeNode journeyInfoNodeTree(JourneyInfo ji, Integer num) {
        DefaultMutableTreeNode result = new DefaultMutableTreeNode();

        DefaultMutableTreeNode tmp;
        tmp = new DefaultMutableTreeNode("Id trayecto: " + ji.getId());
        result.add(tmp);
        tmp = new DefaultMutableTreeNode("Origen: " + ji.getOrigin());
        result.add(tmp);
        tmp = new DefaultMutableTreeNode("Destino: " + ji.getDestination());
        result.add(tmp);
        tmp = new DefaultMutableTreeNode("Salida: " + JourneyInfo.DATETIMEFORMAT.format(ji.getDeparture().getTime()));
        result.add(tmp);
        tmp = new DefaultMutableTreeNode("Llegada: " + JourneyInfo.DATETIMEFORMAT.format(ji.getArrival().getTime()));
        result.add(tmp);

        // FIXME: Do it fancier
        tmp = new DefaultMutableTreeNode("Clase Turista");
        result.add(tmp);
        tmp = new DefaultMutableTreeNode("Precio: " + ji.getTouristinfo().getPrice().toString());
        result.add(tmp);

        // FIXME: Do it fancier
        tmp = new DefaultMutableTreeNode("Clase: Ejecutivo");
        result.add(tmp);
        tmp = new DefaultMutableTreeNode("Precio: " + ji.getBusinessinfo().getPrice().toString());
        result.add(tmp);

        result.setUserObject("Etapa " + Integer.toString(num) + ": " + ji.getOrigin() + " - " + ji.getDestination());

        return result;
    }
}
