import java.io.*;
import java.util.RandomAccess;

public class Main {
    public static void main(String[] args) {

    for (int i = 0; i < 5; i++)
        addCar("Toyota", "Automatic", "Corolla", "2019","195","Black");
    addCar("Toyota", "Manual", "Corolla", "2019","300","Black");

    topSpeed();
    }

    public static void addCar(String name, String gearType, String model, String productionYear, String topSpeed, String colour) {
        File carFile = new File("cars.txt");

        try {
            FileWriter Filewriter = new FileWriter(carFile, true);
            PrintWriter Writer = new PrintWriter(Filewriter);

            Writer.printf("%-15s %-10s %-7s %-4s %-3s %-9s \n",
                    name,gearType,model,productionYear,topSpeed,colour);

            Filewriter.close();
            Writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void topSpeed() {
        File carFile = new File("cars.txt");
        int topSpeedPOS = 40;
        try {
            RandomAccessFile access = new RandomAccessFile(carFile, "r");
            byte [] buf = new byte[3];
            String car;
            String speed, max = "300";
            while (access.getFilePointer() < access.length() - 3){
                access.seek(topSpeedPOS);
                access.read(buf,0,3);
                speed = new String(buf);

                if (speed.equals(max)){
                    access.seek(0);
                    car = access.readLine();
                    System.out.println(car);
                    access.close();
                    break;
                }
                access.seek(14);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

