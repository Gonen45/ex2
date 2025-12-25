package assignments.Ex2;


import classes.week6.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Intro2CS_2026A
 * This class represents a Graphical User Interface (GUI) for Map2D.
 * The class has save and load functions, and a GUI draw function.
 * You should implement this class, it is recommender to use the StdDraw class, as in:
 * https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
 *
 *
 */
public class Ex2_GUI {
    public static void drawMap(Map2D map) {
        //
        int w= map.getWidth(), h= map.getHeight(),v;
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(h, 0);

        StdDraw.clear(StdDraw.WHITE);
        for (int r = 0; r < map.getHeight(); r++) {
            for (int c = 0; c < map.getWidth(); c++) {
                v=map.getPixel(c,r);

                if(v==0){StdDraw.setPenColor(StdDraw.WHITE);}
                else if (v==1) {StdDraw.setPenColor(StdDraw.BLACK);}
                else if (v==2) {StdDraw.setPenColor(StdDraw.RED);}
                else if (v==3) {StdDraw.setPenColor(StdDraw.BLUE);}
                else if (v==4) {StdDraw.setPenColor(StdDraw.DARK_GRAY);}
                else if (v==5) {StdDraw.setPenColor(StdDraw.GREEN);}
                else if (v==6) {StdDraw.setPenColor(StdDraw.YELLOW);}
                else if (v==7) {StdDraw.setPenColor(StdDraw.ORANGE);}
                else if (v==8) {StdDraw.setPenColor(StdDraw.BOOK_BLUE);}
                else if (v==9) {StdDraw.setPenColor(StdDraw.LIGHT_GRAY);}
                else{StdDraw.setPenColor(StdDraw.BLACK);}

//                StdDraw.filledSquare(c,r,0.5);
                StdDraw.filledSquare(c + 0.5, r + 0.5, 0.5);
            }
        }
        StdDraw.show();
    }

    /**
     * @param mapFileName
     * @return
     */
    public static Map2D loadMap(String mapFileName) throws FileNotFoundException {
        Map2D ans = null;
        File myObj = new File(mapFileName);

        try (Scanner myReader = new Scanner(myObj)) {
            int h = myReader.nextInt(),w = myReader.nextInt(),v;
            ans= new Map(w,h,0);
            for (int r = 0; r < h; r++) {
                for (int c = 0; c < w; c++)
                {
                    if(myReader.hasNextInt())
                    {
                        v = myReader.nextInt();
                        ans.setPixel(c,r,v);
                    }
                }
            }
        }
        return ans;
    }

    /**
     *
     * @param map
     * @param mapFileName
     */
    public static void saveMap(Map2D map, String mapFileName) {

        try {
            FileWriter myWriter = new FileWriter(mapFileName);
            int h=map.getHeight(),w=map.getWidth();
            myWriter.write(map.getWidth() + " " + map.getHeight() + "\n");
            for (int r = 0; r < h; r++) {
                for (int c = 0; c < w; c++) {
                    myWriter.write(map.getPixel(c, r) + " ");
                }
                myWriter.write("\n");
            }
            myWriter.close();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
        public static void main(String[] a) throws FileNotFoundException {
        String mapFile = "map.txt";
        Map2D map = new Map(50,50,3);
//        map= maze((Map)map);

//        senrio 2:
        map.drawCircle(new Index2D(25,25),20,4);
        map= maze2((Map)map);
        map.drawLine(new Index2D(25,1),new Index2D(25,49),3);
        map.setPixel(25,24,7);
        Index2D p1= new Index2D(map.getWidth()-1,0),p2= new Index2D(0,map.getHeight()-1);
        map= s_p((Map)map,p1,p2,9);
        saveMap(map,mapFile);
        map = loadMap(mapFile);
        drawMap(map);
    }
    /// ///////////// Private functions ///////////////
//  old version not like i desired but still cool
    private static Map maze(Map2D map){
        int h= map.getHeight(),w=map.getWidth();
        Index2D p1=new Index2D(0,0), p2=new Index2D(w-2,0);
        for (int r = 0; r < h; r=r+2) {
            {
                p1.change(w-1,r);
                p2.change(1,r);
                if(r%3==0)
                {
                 p1.change(0,r);
                 p2.change(w-2,r);
                }
                map.drawLine(p1,p2,7);

            }
        }
        return (Map) map;
    }
//used AI it's 2:00 im super tired
    public static Map2D maze2(Map map) {
        int h = map.getHeight();
        int w = map.getWidth();
        int wallColor = 7;

        // Reuse these point objects to save memory
        Index2D p1 = new Index2D(0, 0);
        Index2D p2 = new Index2D(0, 0);

        boolean rightGap = true; // Toggle to switch sides

        // Step by 2 to leave space between walls
        for (int r = 0; r < h; r += 2) {
            if (rightGap) {
                // Gap is on the RIGHT (at index w-1)
                // Draw line from 0 to w-2
                p1.change(w-2, r);
                p2.change(0, r);
            } else {
                // Gap is on the LEFT (at index 0)
                // Draw line from 1 to w-1
                p1.change(1, r);
                p2.change(w - 1, r);
            }

            map.drawLine(p1, p2, wallColor);

            // Flip the side for the next wall
            rightGap = !rightGap;
        }

        return map;
    }

    public static Map2D s_p(Map map,Index2D p1,Index2D p2,int new_v) {
        Pixel2D[] path= map.shortestPath(p1,p2,7,false);
        for (Pixel2D p : path)
        {
                Index2D po= new Index2D(p);
                map.setPixel(po.getX(), po.getY(), new_v);

         }
        return map;
    }
}
