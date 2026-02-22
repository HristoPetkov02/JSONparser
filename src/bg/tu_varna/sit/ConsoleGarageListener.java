package bg.tu_varna.sit;

public class ConsoleGarageListener implements GarageListener {
    @Override
    public void onChange(GarageEvent event) {
        switch (event.getType()) {
            case ADDED:
                System.out.println("Car added: " + event.getKey());
                break;
            case UPDATED:
                System.out.println("Car updated: " + event.getKey());
                break;
            case REMOVED:
                System.out.println("Car removed: " + event.getKey());
                break;
        }
    }
}