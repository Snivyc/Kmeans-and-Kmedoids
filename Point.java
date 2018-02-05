public class Point {
    double x, y;
    String name;

    public Point(double _x, double _y) {
        x = _x;
        y = _y;
        name = null;
    }

    public Point(double _x, double _y, String _name) {
        x = _x;
        y = _y;
        name = _name;
    }
}