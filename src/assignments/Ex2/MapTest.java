package assignments.Ex2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Intro2CS, 2026A, this is a very
 */
class MapTest {
    /**
     */
    private int[][] _map_3_3 = {{0,1,0}, {1,0,1}, {0,1,0}};
    private Map2D _m0, _m1, _m3_3;
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
            int h=_m0.getHeight();
            assertEquals(9, h);
        }

        @Test
        void getPixel() {
            _m0 =new Map(9,4,1);
            int p= _m0.getPixel(0,0);
            assertEquals(1, p);

        }

        @Test
        void testGetPixel() {//TODO
        }

        @Test
        void setPixel() {
            _m0 =new Map(9,4,1);
            _m0.setPixel(0,0,8);
            int p= _m0.getPixel(0,0);
            assertEquals(8, p);

        }

        @Test
        void testSetPixel() {
        }

        @Test
        void isInside() {
        }

        @Test
        void sameDimensions() {
        }

        @Test
        void addMap2D() {
        }

        @Test
        void mul() {
        }

        @Test
        void rescale() {
        }

        @Test
        void drawCircle() {
        }

        @Test
        void drawLine() {
        }

        @Test
        void drawRect() {
        }


        @Test
        void fill() {
        }

        @Test
        void shortestPath() {
        }

        @Test
        void allDistance() {
        }
    }
