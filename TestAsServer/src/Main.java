import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Сервер запущен и ожидает подключения...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    double[][] matrix = (double[][]) in.readObject();
                    double average = calculateAverage(matrix); //среднее
                    double[][] result = divideMatrixByAverage(matrix, average);
                    out.writeObject(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double calculateAverage(double[][] matrix) {
        double sum = 0;
        int count = 0;
        for (double[] row : matrix) {
            for (double value : row) {
                sum += value;
                count++;
            }
        }
        return sum / count;
    }

    private static double[][] divideMatrixByAverage(double[][] matrix, double average) {
        double[][] result = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i][j] = matrix[i][j] / average;
            }
        }
        return result;
    }
}