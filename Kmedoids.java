import java.util.ArrayList;

public class Kmedoids extends Kmeans {

    @Override
    public ArrayList<Point> cal_centroids() {
        ArrayList<Point> t = new ArrayList<>();
        for (int i = 0; i < K; i++) {

            double[][] table = new double[arr.get(i).size()][arr.get(i).size()];
            for (int j = 0; j < arr.get(i).size(); j++) {
                table[j][j] = 0;
                for (int k = j + 1; k < arr.get(i).size(); k++) {
                    table[j][k] = cal_distance(arr.get(i).get(j), arr.get(i).get(k));
                    table[k][j] = table[j][k];
                }
            }

            double minSumDistance = 0;
            int minIndex = 0;
            for (double x : table[0]) {
                minSumDistance += x;
            }
            for (int j = 1; j < arr.get(i).size(); j++) {
                double sumDistance = 0;
                for (double x : table[j]) {
                    sumDistance += x;
                }
                if (minSumDistance > sumDistance) {
                    minIndex = j;
                    minSumDistance = sumDistance;
                }
            }

            t.add(arr.get(i).get(minIndex));

        }
        return t;
    }
}
