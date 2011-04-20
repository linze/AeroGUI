package classes;

import java.util.ArrayList;

public class Operation {
    private OperationStatus _status;
    private ArrayList<Travel> _travels;

    public Operation() {
    }

    public Operation(OperationStatus _state, ArrayList<Travel> _travels) {
        this._status = _state;
        this._travels = _travels;
    }

    public OperationStatus getStatus() {
        return _status;
    }

    public void setStatus(OperationStatus _status) {
        this._status = _status;
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
