package algoritmos;

import java.util.ArrayList;

public class MergeSortConcurrente {
	
    // numero maximo de hilos (se puede ajustar segun la cantidad de nucleos logicos)
    private static final int MAX_THREADS = 4;
    
    /**
     * clase interna que extiende Thread.
     * cada instancia de SortThreads ejecutara un mergeSort sobre una porcion del arreglo.
     */
    private static class SortThreads extends Thread {
        SortThreads(Integer[] array, int begin, int end) {
            // creamos un hilo que ejecuta mergeSort sobre el rango [begin, end]
            super(() -> mergeSort(array, begin, end));
            this.start(); // inicia el hilo inmediatamente
        }
    }
    
    
    // metodo principal que divide el arreglo y lanza varios hilos para ordenarlo en paralelo.
    public static void threadedSort(Integer[] array) {
        final int length = array.length;
        // calculamos la cantidad de elementos que manejara cada hilo
        boolean exact = length % MAX_THREADS == 0;
        int maxlim = exact ? length / MAX_THREADS : length / (MAX_THREADS - 1);
        // aseguramos que al menos haya tantos elementos como hilos
        maxlim = Math.max(maxlim, MAX_THREADS);

        ArrayList<SortThreads> threads = new ArrayList<>();
        
        // dividimos el arreglo en secciones y creamos un hilo para cada una
        for (int i = 0; i < length; i += maxlim) {
            int beg = i;
            int remain = length - i;
            int end = remain < maxlim ? i + (remain - 1) : i + (maxlim - 1);
            threads.add(new SortThreads(array, beg, end));
        }
        
        // esperamos a que todos los hilos terminen
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ignored) {}
        }
        
        // una vez que todas las partes estan ordenadas, las fusionamos progresivamente
        for (int i = 0; i < length; i += maxlim) {
            int mid = i == 0 ? 0 : i - 1;
            int remain = length - i;
            int end = remain < maxlim ? i + (remain - 1) : i + (maxlim - 1);
            merge(array, 0, mid, end);	// se hace merge acumulativo desde el inicio
        }
    }

    public static void mergeSort(Integer[] array, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) / 2;
            mergeSort(array, begin, mid);
            mergeSort(array, mid + 1, end);
            merge(array, begin, mid, end);
        }
    }

    public static void merge(Integer[] array, int begin, int mid, int end) {
        Integer[] temp = new Integer[end - begin + 1];
        int i = begin, j = mid + 1, k = 0;

        while (i <= mid && j <= end) {
            temp[k++] = (array[i] <= array[j]) ? array[i++] : array[j++];
        }

        while (i <= mid) temp[k++] = array[i++];
        while (j <= end) temp[k++] = array[j++];

        for (i = begin, k = 0; i <= end; i++, k++) {
            array[i] = temp[k];
        }
    }
}
