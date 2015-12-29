import java.util.LinkedList;

/**
 * Created by yuriyarabskyy on 07/12/15.
 */


public class BinaereSuche<K extends Comparable> {

     boolean binarySearch(LinkedList<K> list, K value) {

        int left = 0, right = list.size() - 1;

        while(right >= left) {

            int middle = (left + right) / 2;

            if(list.get(middle).compareTo(value) < 0) {
                left = middle + 1;
            } else if(list.get(middle).compareTo(value) > 0) {
                right = middle - 1;
            } else {
                return true;
            }

        }

        return false;

    }

}
