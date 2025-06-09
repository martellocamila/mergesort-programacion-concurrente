package utilidades;

import java.util.Random;

public abstract class Array {
	   public static int[] generarArregloAleatorio(int size) {
	        Random rand = new Random();
	        int[] arreglo = new int[size];
	        for (int i = 0; i < size; i++) {
	            arreglo[i] = rand.nextInt(size * 10) - (size * 5);
	        }
	        return arreglo;
	    }

	    public static boolean estaOrdenado(int[] arr) {
	        for (int i = 1; i < arr.length; i++) {
	            if (arr[i - 1] > arr[i]) return false;
	        }
	        return true;
	    }
}
