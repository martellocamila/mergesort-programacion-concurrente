package algoritmos;

import java.util.Arrays;

public class MergeSortSecuencial {

    // metodo público que inicia el ordenamiento
    public static void mergeSort(int[] arr) {
        // caso base: si el arreglo tiene 1 o 0 elementos, ya esta ordenado
        if (arr.length <= 1) return;

        // dividir el arreglo en dos mitades
        int mid = arr.length / 2;

        // copiar los elementos en los arreglos izquierdo y derecho
        int[] left = Arrays.copyOfRange(arr, 0, mid);          // desde 0 hasta mid-1
        int[] right = Arrays.copyOfRange(arr, mid, arr.length); // desde mid hasta el final

        // ordenar recursivamente ambas mitades
        mergeSort(left);
        mergeSort(right);

        // combinar las dos mitades ya ordenadas
        mezcla(arr, left, right);
    }

    // metodo que fusiona dos subarreglos ordenados en uno solo
    private static void mezcla(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // comparar elementos de ambos arreglos y copiar el menor al arreglo original
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        // copiar cualquier elemento restante del arreglo izquierdo
        while (i < left.length) {
            arr[k++] = left[i++];
        }

        // copiar cualquier elemento restante del arreglo derecho
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}
