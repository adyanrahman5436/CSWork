import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayEventList eventList = new ArrayEventList();
        Timer[] timers = new Timer[100]; // Array to store timers
        int currentTime = 0;

        try {
            File file = new File("Events.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                processCommand(line, eventList, timers, currentTime);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Future event list size: " + eventList.size());
        System.out.println("Future event list capacity: " + eventList.capacity());
    }

    public static void processCommand(String command, ArrayEventList eventList, Timer[] timers, int currentTime) {
        String[] parts = command.split(" ");
        char action = parts[0].charAt(0);
        switch (action) {
            case 'I':
                int duration = Integer.parseInt(parts[1]);
                Timer timer = new Timer(duration);
                timer.setInsertionTime(currentTime);
                eventList.insert(timer);
                System.out.println(timer.toString());
                break;
            case 'R':
                Event event = eventList.removeFirst();
                if (event != null) {
                    event.handle();
                }
                currentTime = eventList.getSimulationTime();
                break;
            case 'C':
                int timerId = Integer.parseInt(parts[1]);
                if (timers[timerId] != null) {
                    timers[timerId].cancel(currentTime);
                    eventList.remove(timers[timerId]);
                }
                break;
            default:
                System.out.println("Invalid command: " + command);
        }
    }
}
