public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int n = in.readInt();
        in.readDouble();
        Planet[] ps = new Planet[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return ps;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] ps = readPlanets(filename);
        double radius = readRadius(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        double t = 0;
        int n = ps.length;
        while(t <= T) {
            double[] xForces = new double[n];
            double[] yForces = new double[n]; 
            for(int i = 0; i < n; i++) {
                xForces[i] = ps[i].calcNetForceExertedByX(ps);
                yForces[i] = ps[i].calcNetForceExertedByY(ps);
            }
            for(int i = 0; i < n; i++) {
                ps[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p: ps) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < n; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                            ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                            ps[i].yyVel, ps[i].mass, ps[i].imgFileName);   
        }
    }
}