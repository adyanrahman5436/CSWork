public class Timer implements Event {
    private static int nextId = 0;
    private int id;
    private int insertionTime;
    private int duration;

    public Timer(int duration) {
        this.id = nextId++;
        this.duration = duration;
    }

    @Override
    public void setInsertionTime(int currentTime) {
        this.insertionTime = currentTime;
    }

    @Override
    public int getInsertionTime() {
        return insertionTime;
    }

    @Override
    public int getArrivalTime() {
        return insertionTime + duration;
    }

    @Override
    public void cancel(int currentTime) {
        System.out.println("Timer " + id + " canceled at time: " + currentTime);
    }

    @Override
    public void handle() {
        System.out.println("Timer " + id + " handled (arrival time: " + getArrivalTime() + ")");
    }

    @Override
    public String toString() {
        return "Timer " + id + " (insertion time: " + insertionTime + ", arrival time: " + getArrivalTime() + ")";
    }
}
