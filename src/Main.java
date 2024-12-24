import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество уравнений: ");
        int n = scanner.nextInt();

        double[][] matrix = new double[n][n + 1];

        System.out.println("Введите коэффициенты матрицы (включая свободные члены):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        double[] solution = solve(matrix);
        System.out.println("Решение: " + Arrays.toString(solution));
    }

    public static double[] solve(double[][] matrix) {
        int n = matrix.length;

        // Прямой ход метода Гаусса
        for (int i = 0; i < n; i++) {
            // Находим максимальный элемент в текущем столбце
            double maxEl = Math.abs(matrix[i][i]);
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > maxEl) {
                    maxEl = Math.abs(matrix[k][i]);
                    maxRow = k;
                }
            }

            // Меняем текущую строку с строкой с максимальным элементом
            double[] temp = matrix[maxRow];
            matrix[maxRow] = matrix[i];
            matrix[i] = temp;

            // Обнуляем элементы под текущей строкой
            for (int k = i + 1; k < n; k++) {
                double c = -matrix[k][i] / matrix[i][i];
                for (int j = i; j < n + 1; j++) {
                    if (i == j) {
                        matrix[k][j] = 0;
                    } else {
                        matrix[k][j] += c * matrix[i][j];
                    }
                }
            }
        }

        // Обратный ход
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = matrix[i][n] / matrix[i][i];
            for (int k = i - 1; k >= 0; k--) {
                matrix[k][n] -= matrix[k][i] * x[i];
            }
        }

        return x;
    }
}
