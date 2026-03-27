package main;

public class main {

	public static void mergeSort(int[] x, int [] y, int inicio, int fin) {
		if(inicio < fin){
			int medio = (inicio + fin) / 2;
			mergeSort(x, y, 0, medio);
			mergeSort(x, y, medio+1, fin);
			merge(x, y, inicio, fin);
		}
	}
	
	public static void merge(int[] x, int [] y, int inicio, int fin) {
		int [] rx = new int[fin - inicio + 1];
		int [] ry = new int[fin - inicio + 1];
		int medio = (inicio + fin) / 2;
		int i = inicio;
		int j = medio + 1;
		for(int k=0; k <= (fin - inicio); k++) {
			if (i > fin || x[i]<= x[j] && i <= medio) {
				rx[k] = x[i];
				ry[k] = x[i];
				i ++;
			}
			else{
				rx[k] = x[j];
				ry[k] = x[j];
				j ++;
			}
		}
		for(int k = 0; k < (fin-inicio);k++) {
			x[inicio + k] = rx[k];
			y[inicio + k] = ry[k];
		}
	}
	public static double[] cercania(int[] x, int[] y, int inicio, int fin) {
	    // CASO BASE
	    if ((fin - inicio) <= 2) {
	        double distMin = Double.MAX_VALUE;
	        int iMin = -1;
	        int jMin = -1;
	        for (int i = inicio; i < fin; i++) { 
	            for (int j = i + 1; j <= fin; j++) {
	                double dist = Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2);
	                if (dist < distMin) {
	                    distMin = dist;
	                    iMin = i; jMin = j;
	                }
	            }
	        }
	        return new double[]{(double) iMin, (double) jMin, Math.sqrt(distMin)};
	    }

	    // DIVIDE
	    int medio = (inicio + fin) / 2;
	    double[] d1 = cercania(x, y, inicio, medio);
	    double[] d2 = cercania(x, y, medio + 1, fin);

	    // VENCERÁ (Elegir el mejor de los dos lados)
	    double[] mejor;
	    if (d1[2] < d2[2]) {
	        mejor = d1;
	    } else {
	        mejor = d2;
	    }

	    double distMin = mejor[2];
	    int iMin = (int) mejor[0];
	    int jMin = (int) mejor[1];
	    int centroX = x[medio];
	    for (int i = inicio; i <= fin; i++) {
	        if (Math.abs(x[i] - centroX) < distMin) {
	            for (int j = i + 1; j <= fin; j++) {
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
	
	
	/*public static double[] cercania(int[] x, int [] y, int inicio, int fin) {
		if ((fin - inicio) <= 2){
			int distMin = 10000000;
			int iMin = -1;
			int jMin = -1;
			
			for(int i = inicio; i <= (fin - 1); i++) {
				for(int j = i + 1; i <= fin; j++) {
					int dist = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
					
					if(dist < distMin) {
						distMin = dist;
						iMin = i;
						jMin = j;
					}
				}
			}
			return new double[] { (double)iMin, (double)jMin, Math.sqrt(distMin) };
		}
		
		int medio = (inicio + fin) / 2;
		int i1; int j1; double[] d1 = cercania(x, y, inicio, medio);
		int i2; int j2; double[] d2 = cercania(x, y, medio + 1, fin);
		
		if(d1 < d2) {
			int distMin = d1;
			int iMin = i1;
			int jMin = j1;
		}
		else {
			int distMin = d2;
			int iMin = i2;
			int jMin = j2;
		}
		
		int centroX = x[medio];
				for(int i = inicio; i <= fin; i++) {
					if(|x[i] - centroX| < distMin) {
						for(int j = i + 1; j<= fin; j++) {
							if(|y[i] - y[j]| < distMin) {
								int dist = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
								if(dist < distMin) {
									distMin = dist;
									iMin = i;
									jMin = j;
								}
							}
						}
					}
				}
		return iMin, jMin, sqrt(distMin);
	}
	*/
	public static void main(String[] args) {
		int [] x = {5, 2, 9, 1, 5, 6};
		int [] y = {3, 4, 1, 2, 8, 7};
		int n = x.length;
		mergeSort(x, y, 0, n-1);
		double resultado[] = cercania(x, y, 0, n-1);
		
		int i = (int)resultado[0], j = (int)resultado[1];
		double distancia = resultado[2];
		

	}

}
