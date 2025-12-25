package assignments.Ex2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Intro2CS, 2026A, this is a very
 */
class MapTest {
    /**
     */
    private int[][] _map_3_3 = {{0,1,0}, {1,0,1}, {0,1,0}};
    private Map2D _m0, _m1, _m3_3;
    private Index2D p = new Index2D(0,0);
    @BeforeEach
    public void setuo() {
        _m1=new Map(100);
        _m0=new Map(100);
        _m3_3 = new Map(_map_3_3);
    }
    @Test
    @Timeout(value = 1, unit = SECONDS)
    void init() {
        int[][] bigarr = new int [500][500];
        _m1.init(bigarr);
        assertEquals(bigarr.length, _m1.getWidth());
        assertEquals(bigarr[0].length, _m1.getHeight());
        Pixel2D p1 = new Index2D(3,2);
        _m1.fill(p1,1, true);
    }

    @Test
    void testInit() {
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0, _m1);
    }
    @Test
    void testEquals() {
        assertEquals(_m0, _m1);
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0, _m1);
    }


        @Test
        void getMap() {
        _m0=new Map(10);
        _m0= new Map(_m1.getMap());
        assertEquals(_m0, _m1);
        }

        @Test
        void getWidth() {
        _m0=new Map(9,4,1);
        int w=_m0.getWidth();
        assertEquals(9, w);
        }

        @Test
        void getHeight() {
            _m0=new Map(9);
            int h=_m0.getHeight();
            assertEquals(9, h);
        }

        @Test
        void getPixel() {
            _m0 =new Map(9,4,1);
            int i= _m0.getPixel(0,0);
            assertEquals(1, i);

        }

        @Test
        void testGetPixel() {
            _m0 =new Map(9,4,1);
            _m0.setPixel(p,8);
            int i= _m0.getPixel(p);
            assertEquals(8, i);


        }

        @Test
        void setPixel() {
            _m0 =new Map(9,4,1);
            _m0.setPixel(0,0,8);
            int i= _m0.getPixel(0,0);
            assertEquals(8, i);

        }

        @Test
        void testSetPixel() {
            _m0 =new Map(5);
            _m0.setPixel(p,8);
            int i= _m0.getPixel(p);
            assertEquals(8, i);

        }

        @Test
        void isInside() {
        assertTrue(_m0.isInside(p));

        _m0=new Map(100);
        Index2D p2= new Index2D(99,99);
        assertTrue(_m0.isInside(p2));

        p.change(-1,4);
        assertFalse(_m0.isInside(p));
        }

        @Test
        void sameDimensions() {
            _m1=new Map(80,80,1);
            _m0=new Map(80,80,8);
            assertTrue(_m0.sameDimensions(_m1));

            _m1=new Map(80);
            _m0=new Map(8);
            assertFalse(_m0.sameDimensions(_m1));

        }

        @Test
        void addMap2D() {
            _m1=new Map(80,80,1);
            _m0=new Map(80,80,-1);
            _m0.addMap2D(_m1);

            _m1=new Map(80);//recreated to map of zeros
            assertEquals(_m0,_m1);

        }

        @Test
        void mul() {
            _m1=new Map(80,80,2);
            _m0=new Map(80,80,4);
            _m1.mul(2);
            assertEquals(_m0,_m1);

            _m1=new Map(80,80,2);
            _m0=new Map(80,80,2);
            _m1.mul(1.4);
            assertEquals(_m0,_m1);

        }

        @Test
        void rescale() {
            int[][] data = {
                    {10, 10, 20, 20},
                    {10, 10, 20, 20},
                    {30, 20, 40, 40},
                    {30, 30, 40, 40}};
            _m0 = new Map(data);
            _m0.rescale(0.5, 0.5);
            int i=_m0.getPixel(1,1);
            assertEquals(40,i);

        }

        @Test
        void drawCircle() {
            _m0=new Map(5);
            p.change(2,2);
            _m0.drawCircle(p,8,7);

            _m1= new Map(5,5,7);
            assertEquals(_m1,_m0);
        }

        @Test
        void drawLine() {
            _m0=new Map(100);
            int r=_m0.getHeight()/2;
            p.change(0,r);
            Index2D p2= new Index2D(99,r);
            _m0.drawLine(p2,p,4);
            int n = (int) (Math.random() * 100);

            int i=_m0.getPixel(n,r);
            assertEquals(4,i);

            n = (int) (Math.random() * 100);
            i=_m0.getPixel(n,r-1);
            assertNotEquals(4,i);
            i=_m0.getPixel(n,r+1);
            assertNotEquals(4,i);

        }

        @Test
        void drawRect() {
        _m1=new Map(50);
        Index2D p2= new Index2D(10,10);
        _m1.drawRect(p,p2,7);
//        _m1.print

        }


        @Test
        void fill() {
            _m0=new Map(100);
            int r=_m0.getHeight()/2;
            Index2D p1= new Index2D(0,r);
            Index2D p2= new Index2D(99,r);
            _m0.drawLine(p2,p1,4);

            _m0.fill(p,5,true);
            Index2D p3= new Index2D(90,90);
            assertEquals(_m0.getPixel(p3),_m0.getPixel(p));

            int n = (int) (Math.random() * 100);
            int i=_m0.getPixel(n,r);
            assertEquals(4,i);//line stay the same


        }

        @Test
        void shortestPath() {
            _m0=new Map(10);
            Index2D p1= new Index2D(_m0.getWidth()-1,0),p2= new Index2D(0,0);
            Pixel2D[] path= _m0.shortestPath(p1,p2,7,false);
            for (Pixel2D p : path)
            {
                Index2D po= new Index2D(p);
                _m0.setPixel(po.getX(), po.getY(),3);

            }

            _m1=new Map(10);
            _m1.drawLine(p2,p1,3);

            assertEquals(_m1,_m0);

        }

        @Test
        void allDistance() {
            int[][] data = {{4,3,2,3,4},{3,2,1,2,3},{2,1,0,1,2},{3,2,1,2,3},{4,3,2,3,4}};
            _m0= new Map(data);
            p.change(2,2);
            _m1=new Map(5);
            Map2D map= new Map(5);
            map=_m1.allDistance(p,4,false);
            assertEquals(_m0,map);

        }
    }
