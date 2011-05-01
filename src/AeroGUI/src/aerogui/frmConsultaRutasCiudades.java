/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmConsultaRutasCiudades.java
 *
 * Created on 19-abr-2011, 15:07:53
 */

package aerogui;

import classes.JourneyInfo;
import classes.SearchResult;
import classes.TravelInfo;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author notrace
 */
public class frmConsultaRutasCiudades extends javax.swing.JFrame {
    
    private TravelInfo journeys;
    private String origin;
    private String destination;
    private SearchResult sr;
    private TravelInfo currentRoute;
    private JourneyInfo currentStage;
    private final long _32h = 115200000;
    private final long _48h = 172800000;
    private final long _5d = 432000000;
 
    /** Creates new form frmConsultaRutasCiudades */
    public frmConsultaRutasCiudades() {
        
        initComponents();
        //Gets all available journeys
        this.journeys = new TravelInfo(ComponentsBox.journeyshandler.getJourneysInfo());
        //Gets all posible cities to travel from and to.
        txtOriginSearch.removeAllItems();
        txtDestinationSearch.removeAllItems();
        ArrayList<String> origins = ComponentsBox.journeyshandler.getOrigins();
        for (int i=0; i<origins.size(); i++) {
            txtOriginSearch.addItem(origins.get(i));
        }    
        ArrayList<String> destinations = ComponentsBox.journeyshandler.getDestinations();
        for (int i=0; i<destinations.size(); i++) {
            txtDestinationSearch.addItem(destinations.get(i));
        }   
        
    }
    
    private void updateActualStageRecord(TravelInfo route, int i) {
        //Integer i = routeResults.getSelectedRow();
        this.currentStage = route.getJourneysinfo().get(i);
    }
    
    private double calcStagePrice() {
        Integer seats = (Integer)txtStageSeats.getValue();
        String selectedclass = (String)txtStageClass.getSelectedItem();
        if (selectedclass.equals("Turista")) {
            return this.currentStage.getTouristinfo().getPrice().getQuantity() * seats;
        } else {
            return this.currentStage.getBusinessinfo().getPrice().getQuantity() * seats;
        }

    }
    
    private void updateStageDetails(JourneyInfo stage){
        if (stage != null) {
            txtSDOrigin.setText(stage.getOrigin());
            txtSDDestination.setText(stage.getDestination());
            txtSDDeparture.setText(JourneyInfo.DATETIMEFORMAT.format(stage.getDeparture().getTime()));
            txtSDArrival.setText(JourneyInfo.DATETIMEFORMAT.format(stage.getArrival().getTime()));
            txtSDType.setText(stage.getType());
            txtSDPrice.setText(Double.toString(this.calcStagePrice()) + " Eur");
        } else {
            txtSDOrigin.setText("");
            txtSDDestination.setText("");
            txtSDDeparture.setText("");
            txtSDArrival.setText("");
            txtSDType.setText("");
            txtSDPrice.setText("");
        }
    }
    
    
    
    private void updateActualRoute(int r){
        this.currentRoute = this.sr.getTravelsinfo().get(r);
    }
    
    private double calcRoutePrice(TravelInfo route){
        int stages = route.getJourneysinfo().size();
        double price = 0.0;
        double added = 0.0;
        String currentClass = (String)txtRouteClass.getSelectedItem(); 
        for(int i=0;i<stages;i++){
            JourneyInfo current = route.getJourneysinfo().get(i);     
            if(currentClass.equals("Turista")){
                added = current.getTouristinfo().getPrice().getQuantity() * 
                        (Integer)txtRouteSeats.getValue();
            }
            else{
                added = current.getBusinessinfo().getPrice().getQuantity() * 
                        (Integer)txtRouteSeats.getValue();
            }
        price = price + added;
        }
        return price;
    }
    
    private void updateActualRouteDetails(TravelInfo route){
        if (route != null) {
            int lastIndex = route.getJourneysinfo().size()-1;
            JourneyInfo first = route.getJourneysinfo().get(0);
            JourneyInfo last = route.getJourneysinfo().get(lastIndex);
            txtRDOrigin.setText(first.getOrigin());
            txtRDDestination.setText(last.getDestination());
            txtRDDeparture.setText(JourneyInfo.DATETIMEFORMAT.format(first.getDeparture().getTime()));
            txtRDArrival.setText(JourneyInfo.DATETIMEFORMAT.format(last.getArrival().getTime()));
            txtRDStages.setText(Integer.toString(lastIndex));
            txtRDPrice.setText(Double.toString(this.calcRoutePrice(currentRoute)) + " Eur");
        } else {
            txtSDOrigin.setText("");
            txtSDDestination.setText("");
            txtSDDeparture.setText("");
            txtSDArrival.setText("");
            txtSDType.setText("");
            txtSDPrice.setText("");
        }
    }
    //Creates routes list
    private void createRoutesList(SearchResult sr){
            // Clean up previous search list
            routeSelection.removeAllItems();
            int nRoutes = sr.getTravelsinfo().size();
            String rName;
            // Add an item for each route 
            for(int i=0;i<nRoutes;i++){
                rName = "Ruta "+(i+1);
                routeSelection.addItem(rName);
            }    
        
    }
    
    private void showRoutes(SearchResult sr, javax.swing.JTable jTable1, int index){
            // Create results representation
            jTable1.removeAll();
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"Origen", "Destino", "Tipo", "Salida", "Llegada"});

            JourneyInfo currentResult;
            // Retrieves the search results by selected route
                    int srIdx = sr.getTravelsinfo().get(index).getJourneysinfo().size();
                    for (int j=0;j<srIdx;j++){
                    currentResult = sr.getTravelsinfo().get(index).getJourneysinfo().get(j);
                    //Adds selected fields to the model
                    model.addRow(new Object[]{currentResult.getOrigin(),
                        currentResult.getDestination(),currentResult.getType(),
                        JourneyInfo.DATETIMEFORMAT.format(currentResult.getDeparture().getTime()),
                        JourneyInfo.DATETIMEFORMAT.format(currentResult.getArrival().getTime())});
                }

            // Show the results
                jTable1.setModel(model);
        }
    
    /* This method gets all possible routes between two given towns
     * It doesn't take care of timing or restrictions*/
    
    private void routeSearch(TravelInfo journeys,ArrayList<String> vPlaces
                          , ArrayList<String> transportIds, String destParam
                          , SearchResult sr, JourneyInfo current, boolean canContinue){
        
        //Last town visited array index : ltv
        int ltv = vPlaces.size()-1;
        int journeyIndex = journeys.getJourneysinfo().size();
        String stageDestination = current.getDestination();
        String stageOrigin = current.getOrigin();
        String lastVisited;
        /* When current journey departures from last town arrived (route's 
         * origin in first iteration) is a possible stage of the route.
         * */
        if(stageOrigin.equals(vPlaces.get(ltv))){
            
            //Check if route has reached destination
            if(stageDestination.equals(destParam)){
                //The Id is added to the route. The route is then complete
                vPlaces.add(current.getDestination());
                ltv++;
                lastVisited = vPlaces.get(ltv);
                transportIds.add(current.getId());
                /* This block retrieves the JourneyInfo associated to each Id
                 * and adds it to the TravelInfo variable.
                 */
                TravelInfo route = new TravelInfo();
                for(int idIndex=0;idIndex<transportIds.size();idIndex++){
                    for(int jIndex=0;jIndex<journeyIndex;jIndex++){
                        JourneyInfo routeNode = journeys.getJourneysinfo().get(jIndex);
                        if(routeNode.getId().equals(transportIds.get(idIndex))){
                            route.addJourneyInfo(routeNode);
                            break;
                        }
                    }
                }
            //Finally the complete route info is added to the search result
            sr.addTravelInfo(route);
            
            int tIdx = transportIds.size()-1;
            //Backtracking to continue searching
            if(ltv>0){
                vPlaces.remove(ltv);
                ltv--;
                lastVisited = vPlaces.get(ltv);
                transportIds.remove(tIdx);
                canContinue = false;
                int nBr;
                //for(nBr=0;nBr<journeys.getJourneysinfo().size();nBr++){
                    current = journeys.getJourneysinfo().get(0);
                this.routeSearch(journeys, vPlaces, transportIds, destParam, sr, current, canContinue);
                //}
            }
            else{
                return;
            }
        }
        else{
            // Check possible loops in routes
            boolean loop = false;
            int visitedIndex = 0;
                
            while(!loop){
                try{
                    if(vPlaces.get(visitedIndex).equals(stageDestination)){
                        loop = true;
                    }
                    else{
                        visitedIndex++;
                    }
                }
                catch(IndexOutOfBoundsException iobe){
                    break;
                }
            }
                //if no loop is found search can continue
            if(!loop){
                vPlaces.add(stageDestination);
                ltv++;
                transportIds.add(current.getId());
                lastVisited = vPlaces.get(ltv);
                canContinue = false;
                int jidx;
                for(jidx=0;jidx<journeyIndex;jidx++){
                    JourneyInfo readJi = journeys.getJourneysinfo().get(jidx);
                    if(readJi.getOrigin().equals(lastVisited)){
                        canContinue=true;
                        current = journeys.getJourneysinfo().get(jidx);
                        this.routeSearch(journeys, vPlaces, transportIds, destParam, sr, current, canContinue);
                    }
                    
                }
                if(!canContinue){
                    vPlaces.remove(ltv);
                    ltv--;
                    lastVisited = vPlaces.get(ltv);
                    int tIdsIdx = transportIds.size()-1;
                    transportIds.remove(tIdsIdx);
                }
            }
            else{
                vPlaces.remove(ltv);
                ltv--;
                lastVisited = vPlaces.get(ltv);
                int tIdsIdx = transportIds.size()-1;
                transportIds.remove(tIdsIdx);
            }
        }
    }
}
    
    /*After getting the possible routes we'll check arrivals 
     * don't override next transport departure*/
    private void checkTiming(SearchResult sr){
        int srIdx = 0;
        while(true){
            try{
                TravelInfo current = sr.getTravelsinfo().get(srIdx);
                int tinfIdx = 0;
                while(true){
                    try{
                        JourneyInfo currentStageTiming = current.getJourneysinfo().get(tinfIdx);
                        JourneyInfo nextStage = current.getJourneysinfo().get(tinfIdx+1);
                        if(currentStageTiming.getArrival().compareTo(nextStage.getDeparture())>=0){
                            sr.getTravelsinfo().remove(srIdx);
                            break;
                        }
                        else{
                            tinfIdx++;
                        }
                    }
                    catch(IndexOutOfBoundsException iobex){
                        srIdx++;
                        break;
                    }
                }
            }
            catch(IndexOutOfBoundsException iob){
                break;
            }
        }
    }
    
    /*
     * This method checks restrictions to the routes:
     *      1: route elapsed time > 32 h && neither train nor ship
     *      2: route elapsed time > 48 h && train exclusively
     *      3: route elapsed time > 120 h && ship included
     */

    private void checkRestrictions(SearchResult sr){
        
        ArrayList<String> transports = new ArrayList<String>();
        long journeyTime = 0;
        TravelInfo currentRouteRestrictions;
        int i = 0;
        //Start exploring the routes
        while (true){
            transports.clear();
            try{
                currentRouteRestrictions = sr.getTravelsinfo().get(i);
                int lastStage = currentRouteRestrictions.getJourneysinfo().size();
                // Get first departure and last arrival
                Calendar starTime = currentRouteRestrictions.getJourneysinfo().get(0).getDeparture();
                Calendar endTime = currentRouteRestrictions.getJourneysinfo().get(lastStage).getArrival();
                // Set the time required to complete route
                journeyTime = starTime.getTimeInMillis()-endTime.getTimeInMillis();
                boolean train = false;
                boolean ship = false;
                boolean plane = false;
                boolean bus = false;
                // fills an array with each stage transport
                for(int j=0;j<lastStage;j++){
                    transports.add(currentRouteRestrictions.getJourneysinfo().get(lastStage).getType());
                }
                // Check transport used
                if(transports.contains("TR")){
                    train = true;
                }
                if(transports.contains("BA")){
                    ship = true;
                }
                if(transports.contains("AV")){
                    plane = true;
                }
                if(transports.contains("AU")){
                    bus = true;
                }
                /* And finally check restrictions. 
                 * If any then route will be deleted
                 * 
                 */
                if((journeyTime>_32h)&& !train && !ship){
                    sr.getTravelsinfo().remove(i);
                }
                else{
                    if((journeyTime>_48h) && train && !ship && !plane && !bus){
                        sr.getTravelsinfo().remove(i);
                    }
                    else{
                        if((journeyTime>_5d) && ship){
                            sr.getTravelsinfo().remove(i);
                        }
                        else{
                            i++;
                        }
                    }
                }
            }
            catch(IndexOutOfBoundsException iob){
                break;
            }
        }
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtOriginSearch = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtDestinationSearch = new javax.swing.JComboBox();
        btnBuscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        routeResults = new javax.swing.JTable();
        stageDetails = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtRDOrigin = new javax.swing.JTextField();
        txtRDDestination = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtRDDeparture = new javax.swing.JTextField();
        txtRDArrival = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtRDPrice = new javax.swing.JTextField();
        txtRouteClass = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtRouteSeats = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        txtRDStages = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSDOrigin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSDDestination = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSDDeparture = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSDPrice = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtStageSeats = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        txtStageClass = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtSDType = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtSDArrival = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        routeSelection = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Consulta de rutas entre ciudades"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Selección"));

        jLabel1.setText("Origen");

        txtOriginSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Destino");

        txtDestinationSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnBuscar.setText("Buscar transportes");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtOriginSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txtDestinationSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(btnBuscar)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtOriginSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtDestinationSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
        );

        jButton1.setText("Añadir al carrito");

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(btnCerrar)
                .addGap(564, 564, 564))
        );

        routeResults.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        routeResults.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Origen", "Destino", "Tipo", "Salida", "Llegada"
            }
        ));
        routeResults.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                routeResultsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(routeResults);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setText("Destino");

        jLabel12.setText("Origen");

        txtRDOrigin.setBackground(new java.awt.Color(150, 150, 150));
        txtRDOrigin.setEnabled(false);

        txtRDDestination.setBackground(new java.awt.Color(150, 150, 150));
        txtRDDestination.setEnabled(false);

        jLabel13.setText("Llegada");

        jLabel14.setText("Salida");

        txtRDDeparture.setBackground(new java.awt.Color(150, 150, 150));
        txtRDDeparture.setEnabled(false);

        txtRDArrival.setBackground(new java.awt.Color(150, 150, 150));
        txtRDArrival.setText("00/00/00 00:00");
        txtRDArrival.setEnabled(false);

        jLabel15.setText("Precio");

        txtRDPrice.setBackground(new java.awt.Color(150, 150, 150));
        txtRDPrice.setEnabled(false);

        txtRouteClass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Turista", "Ejecutivo" }));
        txtRouteClass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtRouteClassItemStateChanged(evt);
            }
        });

        jLabel16.setText("Clase");

        jLabel17.setText("Asientos");

        txtRouteSeats.setValue(1);
        txtRouteSeats.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtRouteSeatsStateChanged(evt);
            }
        });

        jLabel18.setText("Escalas");

        txtRDStages.setBackground(new java.awt.Color(150, 150, 150));
        txtRDStages.setEnabled(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRouteClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRDDestination)
                    .addComponent(txtRDOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRDArrival, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(txtRouteSeats, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(txtRDDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtRDPrice)
                    .addComponent(txtRDStages, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtRDOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txtRDStages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtRDDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtRDDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtRDPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtRDArrival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRouteClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtRouteSeats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stageDetails.addTab("Ruta completa", jPanel7);

        jLabel2.setText("Origen");

        txtSDOrigin.setBackground(new java.awt.Color(150, 150, 150));
        txtSDOrigin.setForeground(new java.awt.Color(243, 224, 224));
        txtSDOrigin.setEnabled(false);

        jLabel3.setText("Destino");

        txtSDDestination.setBackground(new java.awt.Color(150, 150, 150));
        txtSDDestination.setEnabled(false);

        jLabel4.setText("Salida");

        txtSDDeparture.setBackground(new java.awt.Color(150, 150, 150));
        txtSDDeparture.setEnabled(false);

        jLabel6.setText("Precio");

        txtSDPrice.setBackground(new java.awt.Color(150, 150, 150));
        txtSDPrice.setEnabled(false);

        jLabel7.setText("Asientos");

        txtStageSeats.setValue(1);
        txtStageSeats.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtStageSeatsStateChanged(evt);
            }
        });

        jLabel8.setText("Clase");

        txtStageClass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Turista", "Ejecutivo" }));
        txtStageClass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtStageClassItemStateChanged(evt);
            }
        });

        jLabel5.setText("Tipo");

        txtSDType.setBackground(new java.awt.Color(150, 150, 150));
        txtSDType.setEnabled(false);

        jLabel10.setText("Llegada");

        txtSDArrival.setBackground(new java.awt.Color(150, 150, 150));
        txtSDArrival.setText("00/00/00 00:00");
        txtSDArrival.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDDestination)
                    .addComponent(txtStageClass, 0, 136, Short.MAX_VALUE)
                    .addComponent(txtSDOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDArrival, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtStageSeats)
                    .addComponent(txtSDDeparture, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDPrice)
                    .addComponent(txtSDType, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtSDOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtSDType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSDDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(txtSDArrival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtStageSeats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStageClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        stageDetails.addTab("Etapa", jPanel4);

        routeSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
        routeSelection.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                routeSelectionItemStateChanged(evt);
            }
        });
        routeSelection.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                routeSelectionKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(routeSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(routeSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(stageDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(stageDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(182, 182, 182)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        routeSelection.removeAllItems();
        //this.sr.getTravelsinfo().clear();
        sr = new SearchResult();
        routeResults.removeAll();
        this.origin = txtOriginSearch.getSelectedItem().toString();
        this.destination = txtDestinationSearch.getSelectedItem().toString();
        ArrayList<String> vPlaces = new ArrayList<String>();
        vPlaces.add(this.origin);
        ArrayList<String> transportIds = new ArrayList<String>();
        String destParam = this.destination;
        int journeysIndex = journeys.getJourneysinfo().size();
        JourneyInfo current = new JourneyInfo();
        ArrayList<String> visitedIds = new ArrayList<String>();
        boolean canContinue = false;
        for(int or=0;or<journeysIndex;or++){
            current = journeys.getJourneysinfo().get(or);
            this.routeSearch(this.journeys,vPlaces,transportIds,destParam,sr,current,canContinue);
        }
        this.checkTiming(sr);
        this.checkRestrictions(sr);
        boolean emptyList = sr.getTravelsinfo().isEmpty();
        if (!emptyList){
            this.createRoutesList(sr);
            this.updateActualRoute(routeSelection.getSelectedIndex());
            this.updateActualStageRecord(this.currentRoute,0);
            this.updateActualRouteDetails(this.currentRoute);
            this.showRoutes(sr, routeResults, 0);
        
        }
        else{
            routeResults.removeAll();
            JOptionPane.showMessageDialog(this, "No se han encontrado rutas válidas"); 
        }
        
        //TODO: create method to check routes' timing and restrictions. 
 
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void routeSelectionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_routeSelectionItemStateChanged
            
            if (routeSelection.getItemCount()>0){
                int route = routeSelection.getSelectedIndex();
                this.updateActualRoute(route);
                this.updateActualStageRecord(this.currentRoute,0);
                this.updateActualRouteDetails(this.currentRoute);
                this.updateStageDetails(this.currentStage);
                this.showRoutes(sr, routeResults, route);
                
            }
        
    }//GEN-LAST:event_routeSelectionItemStateChanged

    private void routeSelectionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_routeSelectionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_routeSelectionKeyPressed

    private void routeResultsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_routeResultsMouseClicked
        this.updateActualRoute(routeSelection.getSelectedIndex());
        this.updateActualStageRecord(this.currentRoute,this.routeResults.getSelectedRow());
        this.updateActualRouteDetails(this.currentRoute);
        this.updateStageDetails(this.currentStage);
    }//GEN-LAST:event_routeResultsMouseClicked

    private void txtStageSeatsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtStageSeatsStateChanged
        this.updateStageDetails(currentStage);
    }//GEN-LAST:event_txtStageSeatsStateChanged

    private void txtStageClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtStageClassItemStateChanged
        this.updateStageDetails(currentStage);
    }//GEN-LAST:event_txtStageClassItemStateChanged

    private void txtRouteSeatsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtRouteSeatsStateChanged
        this.updateActualRouteDetails(currentRoute);
    }//GEN-LAST:event_txtRouteSeatsStateChanged

    private void txtRouteClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtRouteClassItemStateChanged
        this.updateActualRouteDetails(currentRoute);
    }//GEN-LAST:event_txtRouteClassItemStateChanged

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new frmConsultaRutasCiudades().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable routeResults;
    private javax.swing.JComboBox routeSelection;
    private javax.swing.JTabbedPane stageDetails;
    private javax.swing.JComboBox txtDestinationSearch;
    private javax.swing.JComboBox txtOriginSearch;
    private javax.swing.JTextField txtRDArrival;
    private javax.swing.JTextField txtRDDeparture;
    private javax.swing.JTextField txtRDDestination;
    private javax.swing.JTextField txtRDOrigin;
    private javax.swing.JTextField txtRDPrice;
    private javax.swing.JTextField txtRDStages;
    private javax.swing.JComboBox txtRouteClass;
    private javax.swing.JSpinner txtRouteSeats;
    private javax.swing.JTextField txtSDArrival;
    private javax.swing.JTextField txtSDDeparture;
    private javax.swing.JTextField txtSDDestination;
    private javax.swing.JTextField txtSDOrigin;
    private javax.swing.JTextField txtSDPrice;
    private javax.swing.JTextField txtSDType;
    private javax.swing.JComboBox txtStageClass;
    private javax.swing.JSpinner txtStageSeats;
    // End of variables declaration//GEN-END:variables

}
