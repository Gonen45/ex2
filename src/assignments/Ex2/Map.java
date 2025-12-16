package assignments.Ex2;
import java.io.Serializable;

import static jdk.internal.org.jline.utils.Colors.h;

/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable{
    private int[][] _map;

    // edit this class below
	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w
	 * @param h
	 * @param v
	 */


	public Map(int w, int h, int v) {init(w, h, v);}
	/**
	 * Constructs a square map (size*size).
	 * @param size
	 */
	public Map(int size) {this(size,size, 0);}
	
	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */
	public Map(int[][] data) {
		init(data);
	}
	@Override
	public void init(int w, int h, int v) {
        _map = new int[h][w];
        for (int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                _map[i][j]=v;
            }
        }

	}
	@Override
	public void init(int[][] arr) {
        if ((arr== null)||(arr.length == 0)) {throw new RuntimeException("array cannot be null or with h=0 ");}
        int h= arr.length;
        int w; //is that needed?
        if (arr[0].length>0 ){
         w=arr[0].length;}
        else {throw new RuntimeException("w most be larger then 0");}

        _map = new int[h][w];
        for (int i=0; i<h; i++){
            if (arr[i].length != w) {
                throw new RuntimeException("Array must have same length");
            }
            for(int j=0; j<w; j++){
                _map[i][j] = arr[i][j];
            }
        }

	}
	@Override
	public int[][] getMap() {
        int h= _map.length;
        int w=_map[0].length;
		int[][] ans = new int[h][w];
        for(int r=0; r<h; r++){
            for(int c=0; c<w; c++){//[2][1] arr[2] in place 1
                ans[r][c]=_map[r][c];
            }
        }

		return ans;
	}
	@Override
	public int getWidth() {
        int ans = _map[0].length;
        return ans;
    }
	@Override
	public int getHeight() {
        int ans = _map.length ;
        return ans;
    }
	@Override
	public int getPixel(int x, int y) {
        Index2D p = new Index2D(x, y);
        if(isInside(p)){
        int ans =_map[y][x];
        return ans;}
        else{throw new RuntimeException("x or y out of dim"); }
    }
	@Override
	public int getPixel(Pixel2D p) {
        if(isInside(p)){
        int ans =_map[p.getY()][p.getX()];
        return ans;}
        else{throw new RuntimeException("x or y out of dim"); }
	}
	@Override
	public void setPixel(int x, int y, int v) {
        Index2D p = new Index2D(x, y);
        if(isInside(p)){
            _map[y][x]=v;
        }
        else{throw new RuntimeException("x or y out of dim"); }

    }
	@Override
	public void setPixel(Pixel2D p, int v) {
        int x=p.getX(),y=p.getY();
        if(isInside(p)){
            _map[y][x]=v;}
        else{throw new RuntimeException("x or y out of dim"); }
    }

    @Override
    public boolean isInside(Pixel2D p) {
        boolean ans =  (p.getY() < this.getHeight()) && (p.getX() < this.getWidth());
        return ans;
    }

    @Override
    public boolean sameDimensions(Map2D p) {
        boolean ans = false;

        return ans;
    }

    @Override
    public void addMap2D(Map2D p) {

    }

    @Override
    public void mul(double scalar) {

    }

    @Override
    public void rescale(double sx, double sy) {

    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {

    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public boolean equals(Object ob) {
        boolean ans = false;

        return ans;
    }
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) {
		int ans = -1;

		return ans;
	}

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
		Pixel2D[] ans = null;  // the result.

		return ans;
	}
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = null;  // the result.

        return ans;
    }
	////////////////////// Private Methods ///////////////////////
    private boolean dimFit(int row, int col) {
        boolean ans = (row <= _map.length) && (col <= _map[0].length);
        return ans;
    }

    }


