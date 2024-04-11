public class ArrayEventList implements FutureEventList {
    private Event[] events;
    private int size;
    private int capacity;
    private int simulationTime;

    public ArrayEventList() {
        events = new Event[5];
        size = 0;
        capacity = 5;
        simulationTime = 0;
    }

    @Override
    public void insert(Event e) {
        if (size == capacity) {
            expandCapacity();
        }
        events[size++] = e;
    }

    private void expandCapacity() {
        Event[] newArray = new Event[capacity * 2];
        System.arraycopy(events, 0, newArray, 0, capacity);
        events = newArray;
        capacity *= 2;
    }

    @Override
    public Event removeFirst() {
        if (size == 0) {
            return null;
        }
        Event firstEvent = events[0];
        for (int i = 0; i < size - 1; i++) {
            events[i] = events[i + 1];
        }
        size--;
        simulationTime = firstEvent.getArrivalTime();
        return firstEvent;
    }

    @Override
    public boolean remove(Event e) {
        for (int i = 0; i < size; i++) {
            if (events[i] == e) {
                for (int j = i; j < size - 1; j++) {
                    events[j] = events[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int getSimulationTime() {
        return simulationTime;
    }
}
