package test;

import algoritmos.MergeSortSecuencial;
import algoritmos.MergeSortConcurrente;
import utilidades.Array;

import java.util.Arrays;

public class TestMergeSort {

    public static void main(String[] args) {
        int[] tamanios = {10, 1000, 100000, 1000000};

        for (int size : tamanios) {
            System.out.println("==============================================");
            System.out.println("Tamaño del arreglo: " + size);

            // Generar arreglo aleatorio
            int[] original = Array.generarArregloAleatorio(size);

            // Test MergeSort Secuencial
            int[] secuencial = Arrays.copyOf(original, original.length);
            long tiempoSecuencial = System.currentTimeMillis();
            MergeSortSecuencial.mergeSort(secuencial);
            tiempoSecuencial = System.currentTimeMillis() - tiempoSecuencial;
            System.out.println("Tiempo MergeSort Secuencial: " + tiempoSecuencial + " ms");

            // Test MergeSort Concurrente
            Integer[] concurrente = Arrays.stream(original).boxed().toArray(Integer[]::new);
            long tiempoConcurrente = System.currentTimeMillis();
            MergeSortConcurrente.threadedSort(concurrente);
            tiempoConcurrente = System.currentTimeMillis() - tiempoConcurrente;
            System.out.println("Tiempo MergeSort Concurrente: " + tiempoConcurrente + " ms");

            // Validación
            if (!(Array.estaOrdenado(secuencial))) {
                System.out.println("ERROR: Secuencial no ordenó correctamente.");
            }
            if (!(Array.estaOrdenado(Arrays.stream(concurrente).mapToInt(i -> i).toArray()))) {
                System.out.println("ERROR: Concurrente no ordenó correctamente.");
            }
        }
    }
    
}
