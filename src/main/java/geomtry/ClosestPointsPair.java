package geomtry;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Given a set of co-ordinates, find the closest pair based on euclidean distance distance(L2 pair)
 * Created by  on 10/13/14.
 */
public class ClosestPointsPair {

    public static class Point {
        final double x;
        final double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double euclideanDistance(Point p2) {
            return Math.sqrt(((p2.y - this.y)*(p2.y - this.y)) +
                    ((p2.x - this.x)*(p2.x - this.x)));
        }

        @Override
        public String toString() {
            return "{"+x+","+y+"}";
        }
    }

    public static class Pair {
        private final Point p1;
        private final Point p2;
        private final double delta;

        Pair(Point p1, Point p2, double delta) {
            this.p1 = p1;
            this.p2 = p2;
            this.delta = delta;
        }

        public double getDelta() {
            return delta;
        }

        public Point getP2() {
            return p2;
        }

        public Point getP1() {
            return p1;
        }

        @Override
        public String toString() {
            return "[" + p1 + ", " + p2 + "," + delta + "]" ;
        }
    }


    public static Pair getClosestPair(Point[] points) {
        Point[] result = new Point[2];

        //Make two copies of the array
        Point[] px = points;
        Point[] py = Arrays.copyOf(points,points.length);

        //Step 0: Sort both arrays , one by x and other y
        // Sort by x co-ordinate (lambda)
        Arrays.sort(px, (o1, o2) -> {
            if(o1.x < o2.x)
                return -1;
            if(o1.x > o2.x)
                return 1;

            return o1.y < o2.y ? -1 : 1;
        });
        //foreachPrint(px);

        //Sort by y co-ordinate
        Arrays.sort(py, (o1, o2) -> {
            if(o1.y < o2.y)
                return -1;
            if(o1.y > o2.y)
                return 1;

            return o1.x <= o2.x ? -1 : 1;
        });
        //foreachPrint(py);
        //Step1: Divide step. Find median:
        Point xmedian = px[px.length/2];

        System.out.println(checkDupe(px));
        return divide(px,py,Arrays.copyOf(py,py.length),0,px.length -1);
    }





    static Pair divide(Point[] px, Point[] py,Point[] auxy, int startx, int endx) {
        if((checkDupe(auxy)))
            System.out.println(checkDupe(py));
        if((endx - startx) < 1)
            return null; //Invalid input


        if(endx - startx <= 6) {
            return getBruteForceClosestPair(px,startx, endx);
        }

        int midx = (startx+ endx)/2;
        //Divide Py such that all sorted Y less than midx is on top half of the array and alll sorted Y greater than '
        // midx is on the bottom half of the array

        divideYByMidX(px,py,auxy,startx,endx,midx);

        //Recurse on sub problems. Find the min distance of subproblems
        Pair pair1 = divide(px,py,auxy, startx,midx);
        Pair pair2 = divide(px,py,auxy,midx + 1, endx );
        Pair optPair = pair1.delta <= pair2.delta ? pair1 : pair2; // min distance of two subproblems

        //Get all the points in the median xmedian + delta and xmedin - delta sorted by their y co-ordinates in two separate arrays
        Point[] leftBand = new Point[midx - startx + 1];
        int leftLength = 0;
        for(int j=startx; j <= midx; j++) {
            if(auxy[j].x >= (px[midx].x - optPair.delta)) {
                leftBand[leftLength] = auxy[j];
                leftLength++;
            }
        }



        Point[] rightBand = new Point[midx - startx + 1];
        int rightLength = 0;
        for(int j=midx + 1; j <= endx; j++) {
            try {
                if(auxy[j].x <= (px[midx].x + optPair.delta)) {
                    rightBand[rightLength] = auxy[j];
                    rightLength++;
                }    }catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
        if(leftLength ==0 || rightLength ==0)
            return optPair;

        //Find next optPair
        int startFrom =0;
        for (int i=0; i < leftLength; i++) {
            int counter = 0;
            for(int j=startFrom; j < rightLength; j++) {
                counter++;
                if((leftBand[i].y + optPair.delta) < rightBand[j].y) {
                    break;
                } else if((leftBand[i].y - optPair.delta) >= rightBand[j].y) {
                    startFrom = j;
                    continue;
                }
                //else
                double dist = leftBand[i].euclideanDistance(rightBand[j]);
                if(dist <= optPair.delta) {
                    optPair = new Pair(leftBand[i],rightBand[j],dist);
                }

            }

            if(counter > 7) {
                //System.out.println(counter);
            }
        }



        // combine(px,startx,endx,midx);
        return optPair;
    }

    static boolean checkY(Point[] py) {
        for (int i = 1; i < py.length; i++) {
            if(py[i -1].y > py[i].y)
                return true;
        }
        return false;
    }


    static boolean checkDupe(Point[] py) {
        for (int i = 1; i < py.length; i++) {
            if(py[i -1].y == py[i].y && py[i-1].x == py[i].x)
                return true;
        }
        return false;
    }





    public static Pair getBruteForceClosestPair(Point[] px,int start,int end) {
        Point p1 = null;
        Point p2 = null;
        double minDist = Double.MAX_VALUE;
        for(int i=start; i <= end; i++) {
            for(int j = i+1; j <= end; j++) {
                double dist = px[i].euclideanDistance(px[j]) ;
                if(dist <= minDist) {
                    minDist = dist;
                    p1 = px[i];
                    p2 = px[j];
                    //System.out.println(new Pair(p1,p2,minDist));

                }
            }
        }
        return new Pair(p1,p2,minDist);
    }


    static void divideYByMidX(Point[] px, Point[] py,Point[] auxy, int startx, int endx, int midx) {
        int counterL = startx;
        int counterR = midx + 1;

        int dupeCnter  = 1;

        for(int i= midx -1; i >= 0; i--)  {
            if(px[midx].x == px[i].x && px[midx].y == px[i].y)
                dupeCnter++;
            else
                break;
        }

        for(int i=startx; i <= endx ; i++ ) {
            if(py[i].x < px[midx].x) {
                auxy[counterL] = py[i];
                counterL++;
            }else if(py[i].x > px[midx].x){
                auxy[counterR] = py[i];
                counterR++;

            } else {
                //What if there are many points with same x but varying Y. Duplicate Points should never exist.
                // They should be caught in the intial check

                if(py[i].y < px[midx].y) {
                    auxy[counterL] = py[i];
                    counterL++;
                }else if(py[i].y > px[midx].y) {
                    auxy[counterR] = py[i];
                    counterR++;
                }else {
                    //Needs better handing of duplicates
                    if(dupeCnter  > 0) {
                        auxy[counterL] = py[i];
                        counterL++;
                        dupeCnter--;
                    }  else {
                        auxy[counterR] = py[i];
                        counterR++;
                    }



                }
            }

        }

        if(counterL != midx + 1) {
            System.out.println("Left counter fail "+ counterL + " " + (midx + 1) +" "+ startx);
        }

        if(counterR != endx + 1) {
            System.out.println("Right counter fail "+ counterR + " " + (endx + 1) + " ");
        }

        for(int i=startx; i <= endx; i++) {
            py[i] = auxy[i];
        }

    }


    static void foreachPrint(Point[] points) {

        for(Point p : points) {
            System.out.print(p);
        }
        System.out.println("]");
    }

    static Point[]  randPointGen(int noOfPoints, int xMax,int yMax) {
        Point[] pts = new Point[noOfPoints];
        SecureRandom randX = new SecureRandom();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SecureRandom randY = new SecureRandom();
        for(int i=0; i < noOfPoints; i++) {
            int x = randX.nextInt() % xMax;
            int y = randY.nextInt() % yMax;
            pts[i] = new Point(x,y);
        }
        return pts;
    }


    public static void main(String[] args) {


        /*
        Point[] points = {new Point(3,9),
                new Point(1,2),
                new Point(2,7),
                new Point(9,13),
                new Point(6,2),
                new Point(0,0),
                new Point(1,11),
               // new Point(1,8),
               // new Point(1,7),
               // new Point(1,6),
              //  new Point(1,5),
                new Point(2,5),
                new Point(3,5),


        };*/

        Point[] points = randPointGen(10000,1000000,1000000);
        //foreachPrint(points);
        //System.out.println(getClosestPair(points));
        System.out.println(getBruteForceClosestPair(points,0,points.length -1));
        System.out.println(getClosestPair(points));
    }




}
