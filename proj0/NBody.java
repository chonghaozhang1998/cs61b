public class NBody{
    public static double readRadius(String filePath) {
        In in = new In(filePath);
        int numbers = in.readInt();
        return in.readDouble(); 
    }

    public static Planet[] readPlanets(String filePath) {
        In in = new In(filePath);
        int numbers = in.readInt();
        Planet[] planets = new Planet[numbers];
        double radius = in.readDouble();
        for (int i = 0 ; i < numbers; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        int numbers = planets.length;

		StdDraw.enableDoubleBuffering(); // in order to soomth the figures

        StdDraw.setScale(0-radius, radius);
        StdDraw.clear();
    
        for (int i = 0; i < T; i += dt) {
            double[] xForces = new double[numbers];
            double[] yForces = new double[numbers];
            for (int j = 0; j < numbers; j++) {
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for (int j = 0; j < numbers; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet planet:planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", numbers);
        StdOut.printf("%.2e\n", radius);
        for (int j = 0; j < numbers; j++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
            planets[j].xxPos, planets[j].yyPos, 
            planets[j].xxVel, planets[j].yyVel, 
            planets[j].mass, planets[j].imgFileName);
        }

    }
}