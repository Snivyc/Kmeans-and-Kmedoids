import java.util.ArrayList;

public class Kmeans {
    final int K = 3;

    ArrayList<Point> centroids = new ArrayList<>();

    ArrayList<Point> newCentroids = new ArrayList<>();

    ArrayList<ArrayList<Point>> arr = new ArrayList<>();

    public Kmeans() {
        init();
        while (true) {
            arr = classify();
            newCentroids = cal_centroids();
            if (if_equal(newCentroids, centroids)) {
                break;
            } else {
                centroids = newCentroids;
            }
        }
        print_arr();
    }

    void init(){
        ArrayList<Point> a = new ArrayList<>();
        Point z = new Point(2,10,"A1");
        a.add(z);
        centroids.add(z);
        z = new Point(2,5,"A2");
        a.add(z);
        z = new Point(8,4,"A3");
        a.add(z);
        arr.add(a);
        a = new ArrayList<>();
        z = new Point(5,8,"B1");
        a.add(z);
        centroids.add(z);
        z = new Point(7,5,"B2");
        a.add(z);
        z = new Point(6,4,"B3");
        a.add(z);
        arr.add(a);
        a = new ArrayList<>();
        z = new Point(1,2,"C1");
        a.add(z);
        centroids.add(z);
        z = new Point(4,9,"C2");
        a.add(z);
        arr.add(a);
    }

    double cal_distance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public ArrayList<ArrayList<Point>> classify() {
        ArrayList<ArrayList<Point>> tarr = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            tarr.add(new ArrayList<Point>());
        }
        for (ArrayList<Point> i : arr) {
            for (Point j : i) {
                int minIndex = 0;
                double minDistance = cal_distance(j, centroids.get(0));
//                System.out.println(Arrays.toString(j));
                for (int k = 1; k < K; k++) {
                    if (minDistance > cal_distance(j, centroids.get(k))) {
                        minIndex = k;
                        minDistance = cal_distance(j, centroids.get(k));
                    }
                }
//                System.out.print(minIndex);
//                System.out.println(minDistance);
                tarr.get(minIndex).add(j);
            }
        }
        return tarr;
    }

    public ArrayList<Point> cal_centroids() {
        ArrayList<Point> t = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            double sumx = 0, sumy = 0;
            for (Point j : arr.get(i)) {
                sumx += j.x;
                sumy += j.y;
            }
            Point z = new Point(sumx/arr.get(i).size(), sumy/arr.get(i).size());

            t.add(z);
        }
        return t;
    }

    public void print_arr() {
        for (ArrayList<Point> i : arr) {
            System.out.print('[');
            for (Point j : i) {
                System.out.print(j.name + ", ");
            }
            System.out.println(']');
        }
    }

//    public void print_centroids() {
//        System.out.print("centroids");
//        for (Point i : newCentroids) {
//            System.out.print(i.name);
//        }
//        System.out.println();
//        System.out.println();
//    }

    public boolean if_equal(ArrayList<Point> a, ArrayList<Point> b) {
        for (int i = 0; i < K; i++) {

            if (Math.abs(a.get(i).x - b.get(i).x) > 1e-8 || Math.abs(a.get(i).y - b.get(i).y) > 1e-8) {
                return false;
            }

        }
        return true;
    }
}
