public class SeleccionActividades {

    // Algoritmo greedy
    public static int[] seleccionDeActividades(int[] comienzo, int[] cierre, int n) {
        mergeSort(cierre, comienzo, 0, n - 1);

        int[] mapaActividades = new int[n];
        mapaActividades[0] = 0;

        int ultimoFin = cierre[0];
        int cant = 1;

        for (int i = 1; i < n; i++) {
            if (ultimoFin <= comienzo[i]) {
                mapaActividades[cant] = i;
                cant++;
                ultimoFin = cierre[i];
            }
        }

        // vector final solo con las actividades seleccionadas
        int[] resultado = new int[cant];
        for (int i = 0; i < cant; i++) {
            resultado[i] = mapaActividades[i];
        }

        return resultado;
    }

    // MergeSort
    public static void mergeSort(int[] cierre, int[] comienzo, int inicio, int fin) {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;

            mergeSort(cierre, comienzo, inicio, medio);
            mergeSort(cierre, comienzo, medio + 1, fin);

            merge(cierre, comienzo, inicio, fin);
        }
    }

    // Merge
    public static void merge(int[] cierre, int[] comienzo, int inicio, int fin) {
        int medio = (inicio + fin) / 2;

        int[] rCierre = new int[fin - inicio + 1];
        int[] rComienzo = new int[fin - inicio + 1];

        int i = inicio;
        int j = medio + 1;
        int k = 0;

        while (i <= medio && j <= fin) {
            if (cierre[i] <= cierre[j]) {
                rCierre[k] = cierre[i];
                rComienzo[k] = comienzo[i];
                i++;
            } else {
                rCierre[k] = cierre[j];
                rComienzo[k] = comienzo[j];
                j++;
            }
            k++;
        }

        // Copiamos lo que queda de la mitad izquierda
        while (i <= medio) {
            rCierre[k] = cierre[i];
            rComienzo[k] = comienzo[i];
            i++;
            k++;
        }

        // Copiamos lo que queda de la mitad derecha
        while (j <= fin) {
            rCierre[k] = cierre[j];
            rComienzo[k] = comienzo[j];
            j++;
            k++;
        }

        // Volvemos a copiar al vector original
        for (k = 0; k < rCierre.length; k++) {
            cierre[inicio + k] = rCierre[k];
            comienzo[inicio + k] = rComienzo[k];
        }
    }

    // Mostrar actividades
    public static void mostrarActividades(int[] comienzo, int[] cierre) {
        System.out.println("Actividades ordenadas por horario de cierre:");
        for (int i = 0; i < comienzo.length; i++) {
            System.out.println("Actividad " + i + ": [" + comienzo[i] + ", " + cierre[i] + "]");
        }
    }

    // Mostrar seleccionadas
    public static void mostrarSeleccionadas(int[] seleccionadas, int[] comienzo, int[] cierre) {
        System.out.println("\nActividades seleccionadas:");
        for (int i = 0; i < seleccionadas.length; i++) {
            int indice = seleccionadas[i];
            System.out.println("Actividad " + indice + ": [" + comienzo[indice] + ", " + cierre[indice] + "]");
        }
    }

    // Main con casos de prueba
    public static void main(String[] args) {
        // Caso de prueba 1
        int[] comienzo1 = {1, 3, 0, 5, 8, 5};
        int[] cierre1 =   {2, 4, 6, 7, 9, 9};
        int n1 = comienzo1.length;

        System.out.println("CASO 1");
        int[] seleccionadas1 = seleccionDeActividades(comienzo1, cierre1, n1);
        mostrarActividades(comienzo1, cierre1);
        mostrarSeleccionadas(seleccionadas1, comienzo1, cierre1);

        // Caso de prueba 2
        int[] comienzo2 = {10, 12, 20};
        int[] cierre2 =   {20, 25, 30};
        int n2 = comienzo2.length;

        System.out.println("\n-------------------------");
        System.out.println("CASO 2");
        int[] seleccionadas2 = seleccionDeActividades(comienzo2, cierre2, n2);
        mostrarActividades(comienzo2, cierre2);
        mostrarSeleccionadas(seleccionadas2, comienzo2, cierre2);

        // Caso de prueba 3
        int[] comienzo3 = {1, 2, 3, 4};
        int[] cierre3 =   {3, 5, 4, 6};
        int n3 = comienzo3.length;

        System.out.println("\n-------------------------");
        System.out.println("CASO 3");
        int[] seleccionadas3 = seleccionDeActividades(comienzo3, cierre3, n3);
        mostrarActividades(comienzo3, cierre3);
        mostrarSeleccionadas(seleccionadas3, comienzo3, cierre3);
    }
}
