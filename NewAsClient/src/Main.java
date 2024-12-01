import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("rows & columns: ");
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            double[][] matrix = new double[rows][columns];
            System.out.println("elements of matrix: ");
            for(int i=0; i<rows; i++){
                for(int j=0; j<columns; j++){
                    matrix[i][j] = scanner.nextDouble();
                }
            }

            out.writeObject(matrix);
            double[][] result = (double[][]) in.readObject();
            System.out.println("Результат:");

            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    System.out.println(result[i][j] + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}