package Modelo;

public class main {

    public static void mergeSort(int[] x, int[] y, int inicio, int fin) {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;
            // CORRECCIÓN: Usar 'inicio' en lugar de '0'
            mergeSort(x, y, inicio, medio);
            mergeSort(x, y, medio + 1, fin);
            merge(x, y, inicio, fin);
        }
    }

    public static void merge(int[] x, int[] y, int inicio, int fin) {
        int n = fin - inicio + 1;
        int[] rx = new int[n];
        int[] ry = new int[n];
        int medio = (inicio + fin) / 2;
        int i = inicio;
        int j = medio + 1;

        for (int k = 0; k < n; k++) {
            // CORRECCIÓN: Lógica de índices y sincronización de X e Y
            if (i <= medio && (j > fin || x[i] <= x[j])) {
                rx[k] = x[i];
                ry[k] = y[i];
                i++;
            } else {
                rx[k] = x[j];
                ry[k] = y[j];
                j++;
            }
        }

        for (int k = 0; k < n; k++) {
            x[inicio + k] = rx[k];
            y[inicio + k] = ry[k];
        }
    }

    public static double[] cercania(int[] x, int[] y, int inicio, int fin) {
        // CASO BASE: Si hay 3 puntos o menos, resolvemos por fuerza bruta
        if ((fin - inicio) <= 2) {
            double distMin = Double.MAX_VALUE;
            int iMin = -1;
            int jMin = -1;
            for (int i = inicio; i < fin; i++) {
                for (int j = i + 1; j <= fin; j++) {
                    double dist = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
                    if (dist < distMin) {
                        distMin = dist;
                        iMin = i;
                        jMin = j;
                    }
                }
            }
            return new double[]{(double) iMin, (double) jMin, distMin};
        }

        // DIVIDE
        int medio = (inicio + fin) / 2;
        double[] d1 = cercania(x, y, inicio, medio);
        double[] d2 = cercania(x, y, medio + 1, fin);

        // CONQUISTA: Elegir el mejor de los dos lados
        double[] mejor = (d1[2] < d2[2]) ? d1 : d2;

        double distMin = mejor[2];
        int iMin = (int) mejor[0];
        int jMin = (int) mejor[1];

        // COMBINA: Revisar la franja central (Strip)
        int centroX = x[medio];
        for (int i = inicio; i <= fin; i++) {
            // Solo revisamos puntos que estén a menos de distMin de la línea central
            if (Math.abs(x[i] - centroX) < distMin) {
                for (int j = i + 1; j <= fin; j++) {
                    // Solo revisamos si la distancia en Y también es menor a la actual
                    if (Math.abs(y[i] - y[j]) < distMin) {
                        double dist = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
                        if (dist < distMin) {
                            distMin = dist;
                            iMin = i;
                            jMin = j;
                        }
                    }
                }
            }
        }
        return new double[]{(double) iMin, (double) jMin, distMin};
    }

    public static void main(String[] args) {
        // Ejemplo con puntos desordenados
        int[] x = {7, 2, 9, 0, 4, 1};
        int[] y = {2, 5, 3, 2, 7, 7};



        int n = x.length;

        // 1. Ordenar por coordenada X es requisito fundamental
        mergeSort(x, y, 0, n - 1);

        // 2. Ejecutar algoritmo de cercanía
        double[] resultado = cercania(x, y, 0, n - 1);

        int i = (int) resultado[0];
        int j = (int) resultado[1];
        double distancia = resultado[2];

        System.out.println("Puntos más cercanos encontrados:");
        System.out.println("Punto 1: (" + x[i] + ", " + y[i] + ")");
        System.out.println("Punto 2: (" + x[j] + ", " + y[j] + ")");
        System.out.printf("Distancia mínima: %.4f\n", distancia);
    }
}