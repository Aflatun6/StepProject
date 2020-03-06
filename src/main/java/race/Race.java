package race;

public class Race {
    private String destination;
    private String date;
    private int capacity;
    private int time;
    private static int ids = 0;
    public final int id;

    public Race(String destination, String date, int capacity, int time) {
        this.destination = destination;
        this.date = date;
        this.capacity = capacity;
        this.time = time;
        this.id = ++ids;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public static Race parse(String r){
        String[] splited = r.split(":");
        return new Race(
                splited[0],
                splited[1],
                Integer.parseInt(splited[2]),
                Integer.parseInt(splited[3])
        );
    }

    public String represent(){
        return String.format("%s:%s:%d:%d%d",destination,date,capacity,time,id);
    }

    @Override
    public String toString() {
        return String.format("Race{destination='%s', date='%s', capacity=%d, time=%d, id=%d}", destination, date, capacity, time, id);
    }
}
