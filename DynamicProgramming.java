package dynamicprogramming;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Μαργαρίτα Σαράντη
 * ΑΕΜ: 2798
 * email: smargarit@csd.auth.gr
 * 
 */
public class DynamicProgramming {
    
    // Returns the minimum value of a matrix
    static int min(int[] c){
        int minimum= c[0];
        for (int i=1;i<c.length;i++){
            if (c[i]<=minimum) minimum = c[i];
        }
        return minimum;
    }
    
    // Returns a matrix of the minimum procedure cost
    static int[][] findCost(int N, int M, int w[][], int val[][])
    {
     int[][] C = new int[N][M]; // Costs' matrix
     for (int i=0;i<M;i++){
         C[0][i] = val[0][i];
     }
     /**
      * Computes M sums ( c matrix) which refer to the cost of each process 
      * in relation to which VM they were running on the previous step and
      * then finds and selects the minimum for the Costs' matrix
      */
     for (int i=1;i<N;i++){
         int[] c = new int[M];
         for (int j=0;j<M;j++){
             for (int k=0;k<M;k++){
                 c[k] = C[i-1][k]+w[k][j]+val[i][j];
             }
             C[i][j] = min(c);
         }
     }
     return C;
    }
    
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        String file = args[0];
        BufferedReader in = new BufferedReader(new FileReader(file));
        String l = in.readLine();
        int N = Integer.parseInt(l); // number of processes
        l = in.readLine();
        int M = Integer.parseInt(l); // number of VMs
        int values[][] = new int[N][M];
        int weights[][] = new int[M][M]; 
        l = in.readLine();
        // Reads values matrix
        for (int i=0;i<N;i++){
            l = in.readLine();
            String tokens[] = new String[M];
            tokens = l.split(" ");
            for (int j=0;j<M;j++){
                values[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        l = in.readLine();
        // Reads weights matrix
        for (int i=0;i<M;i++){
            l = in.readLine();
            String tokens[] = new String[M];
            tokens = l.split(" ");
            for (int j=0;j<M;j++){
                weights[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        int[][] Costs = findCost(N,M,weights,values);
        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                System.out.print(Costs[i][j] + " ");
            }
            System.out.println();
        }
    }
}
