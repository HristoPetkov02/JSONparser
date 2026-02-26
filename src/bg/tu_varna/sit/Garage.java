package bg.tu_varna.sit;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Garage {
    private static Garage instance;
    private Map <String,Car> carMap;
    private final List<GarageListener> listeners = new ArrayList<>();

    public Garage(){
        this.carMap=new LinkedHashMap<>();
        instance=this;
        this.addListener(new ConsoleGarageListener());
    }

    public void addCar(String key,Car value){
        carMap.put(key, value);
        notifyListeners(new GarageEvent(GarageEvent.Type.ADDED, key));
    }

    public void setCar(String key,Car value){
        carMap.replace(key, value);
        notifyListeners(new GarageEvent(GarageEvent.Type.UPDATED, key));
    }

    public void removeCar(String key){
        carMap.remove(key);
        notifyListeners(new GarageEvent(GarageEvent.Type.REMOVED, key));
    }

    public void clearGarage(){
        carMap.clear();
    }

    public static void removeGarage(){
        instance.clearGarage();
        instance=null;
    }

    public void setCarMap(Map<String, Car> carMap) {
        this.carMap = carMap;
    }

    public Map<String, Car> getCarMap() {
        return carMap;
    }

    public static Garage getInstance() {
        return instance;
    }


    @Override
    public String toString() {
        int i=1;
        if  (carMap.isEmpty())
            return "{}";
        StringBuilder sb=new StringBuilder();
        sb.append("Garage:");
        for (Map.Entry<String,Car> o: carMap.entrySet()) {
            sb.append("\n").append(o.getKey()).append(": {").append(o.getValue()).append("\n}");
            if (i!= carMap.size()) {
                sb.append("\n");
            }
            i++;
        }
        return sb.toString();
    }

    public String toJSON(){
        int i=1;
        if  (carMap.isEmpty())
            return "{}";

        StringBuilder sb=new StringBuilder();
        sb.append("{\n\"garage\":{");
        for (Map.Entry<String,Car> o: carMap.entrySet()) {
            sb.append("\n\t\"").append(o.getKey()).append("\": {").append(o.getValue().toJSON()).append("\n\t}");
            if (i!= carMap.size()) {
                sb.append(",");
            }
            i++;
        }
        sb.append("\n\t}\n}");
        return sb.toString();
    }


    // listener management
    public void addListener(GarageListener l) { listeners.add(l); }
    public void removeListener(GarageListener l) { listeners.remove(l); }

    private void notifyListeners(GarageEvent event) {
        for (GarageListener l : new java.util.ArrayList<>(listeners)) {
            l.onChange(event);
        }
    }
}

