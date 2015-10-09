package each.ppgsi.pca;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class PCA {

	private double[][] data;
	private double infoPerc = 0.9;
	
	public PCA(double[][] data){
		this.data = data;
	}
	
	public double[][] getPrincipalComponents(){
		int n = data.length;
		int p = data[0].length;
		
		double[][] newData = new double[n][p];
		newData = data;
		
		// Create mean array
		double mean[] = new double[p];
		for(int j = 0; j < p; j++){
			double sum = 0;
			for(int i = 0; i < n; i++){
				sum += data[i][j];
			}
			mean[j] = sum/n;
		}
		
		// Centralize data
		for(int i = 0; i < n; i++){
			for(int j = 0; j < p; j++){
				newData[i][j] -= mean[j];
			}
		}
		
		// Get covariance matrix
		newData = cov(newData);
		
		// Get eigenvalues
		EigenvalueDecomposition eigen = new Matrix(newData).eig();
		double[] eigval = reverse(eigen.getRealEigenvalues());
		double[][] eigvec = reverse(eigen.getV().getArray());
		
		// Calculate dimensions for information percentage wanted
		double sumeigval = sum(eigval);
		double gain = 0;
		int dimensions = 0;
		while(gain < infoPerc){
			gain += (eigval[dimensions]/sumeigval);
			dimensions++;
		}
		dimensions++;
		
		// Perform dot product with x and w
		Matrix x = new Matrix(data);
		Matrix w = new Matrix(getDimensions(eigvec,dimensions));
		
		return x.times(w).getArray();
	}
	
	public static double[] reverse(double[] a){
		int N = a.length;
		for(int i = 0; i < (N/2); i++){
			double aux = a[i];
			a[i] = a[N-1-i];
			a[N-1-i] = aux;
		}
		return a;
	}
	
	public static double[][] reverse(double[][] a){
		int N = a[0].length;
		for(int i = 0; i < N; i++){
			a[i] = reverse(a[i]);
		}
		return a;
	}
	
	private static double[][] getDimensions(double[][] a, int dimensions){
		int N = a.length;
		double[][] b = new double[N][dimensions];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < dimensions; j++){
				b[i][j] = a[i][j];
			}
		}
		return b;
	}
	
	public static double[] getColumn(double[][] a, int column){
		int N = a.length;
		double[] c = new double[N];
		for(int i = 0; i < N; i++){
			c[i] = a[i][column];
		}
		return c;
	}
	
	public static double[][] cov(double[][] a){
		int N = a[0].length;
		double[][] c = new double[N][N];
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				c[i][j] = cov_step(getColumn(a,i),getColumn(a,j));
			}
		}
		
		return c;
	}
	
	public static double cov_step(double[] a, double[] b){
		int N = a.length;
		
		double sum = 0;
		for(int i = 0; i < N; i++){
			sum += (a[i]-mean(a))*(b[i]-mean(b));
		}

		return  ((double)1/(N-1)) * sum;
	}
	
	public static double mean(double[] a){
		double mean = 0;
		for(int i = 0; i < a.length; i++){
			mean += a[i];
		}
		return mean/a.length;
	}
	
	public static double sum(double[] a){
		double sum = 0;
		for(int i = 0; i < a.length; i++){
			sum += a[i];
		}
		return sum;
	}
}
