package classes;

import java.util.ArrayList;

public class Operation {
    private OperationState _state;
    private ArrayList<Travel> _travels;

    public Operation() {
    }

    public Operation(OperationState _state, ArrayList<Travel> _travels) {
        this._state = _state;
        this._travels = _travels;
    }

    public OperationState getState() {
        return _state;
    }

    public void setState(OperationState _state) {
        this._state = _state;
    }

    public ArrayList<Travel> getTravels() {
        return _travels;
    }

    public void setTravels(ArrayList<Travel> _travels) {
        this._travels = _travels;
    }

    public void addTravel(TravelInfo travelinfo, Integer ntravelers) {
        Travel newTravel = new Travel(travelinfo);
        newTravel.setNtravelers(ntravelers);
        this._travels.add(newTravel);
    }

    

}
