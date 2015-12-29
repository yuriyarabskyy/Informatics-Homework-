import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by yuriyarabskyy on 24/11/15.
 */
public class magic {
    public static void main(String[] args) {
        int n = 4;
        int[][] arr = new int[n][n];
        int k =0;

        for(int i = 1; i<n;i++) {
            for (int j = 1; j < n; j++) {
                k++;
                arr[i][j] = k;
            }
        }

        boolean flag = true;

        while(flag){
            int[] sum_row = new int[n];
            int[] sum_column = new int[n];
            int[] sum_diagonal = new int[2];
            for(int i = 1; i<n;i++){
                for(int j = 1; j<n;j++){
                    sum_row[i] += arr[i][j];
                    sum_column[j] += arr[i][j];
                    if(i==j) sum_diagonal[0] += arr[i][j];
                    if(n-i==j) sum_diagonal[1] += arr[i][j];
                }
            }
            boolean found = true;
            int number = sum_column[1];
            for(int i = 1; i < n; i++){
                if(sum_column[i]!=number||sum_row[i]!=number) found = false;
            }
            if(sum_diagonal[0]!=number||sum_diagonal[1]!=number) found = false;

            if(found){
                for(int i = 1; i<n;i++){
                    for(int j = 1; j<n;j++) {
                        System.out.print(arr[i][j]+"\t ");
                    }
                    System.out.println();
                }
                break;
            }

            int[] list = new int[(n-1)*(n-1)+1];

            k = (n-1)*(n-1)+1;
            int s = 1;
            found = false;
            for(int i = 1; i<n;i++) {
                for (int j = 1; j < n; j++) {
                    if(arr[i][j]!=k--) found = true;
                    list[s++] = arr[i][j];
                }
            }
            if(!found) break;


            k = (n-1)*(n-1);
            while(list[k-1]>list[k]) {
                k--;
            }
            if(k-1<=0) break;

            int min = k;
            for(int i = k; i < (n-1)*(n-1)+1; i++){
                if(list[i]<list[min]&&list[k-1]<list[i]) min = i;
            }

            int temp = list[k-1];
            list[k-1] = list[min];
            list[min] = temp;
            Arrays.sort(list,k,(n-1)*(n-1)+1);

            k = 1;
            for(int i = 1; i<n;i++) {
                for (int j = 1; j < n; j++) {
                    arr[i][j] = list[k++];
                }
            }

        }

    }
}
