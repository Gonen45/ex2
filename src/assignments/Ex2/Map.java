package assignments.Ex2;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable {
    private int[][] _map;
    private int counter = 0;

    // edit this class below

    /**
     * Constructs a w*h 2D raster map with an init value v.
     *
     * @param w
     * @param h
     * @param v
     */


    public Map(int w, int h, int v) {
        init(w, h, v);
    }

    /**
     * Constructs a square map (size*size).
     *
     * @param size
     */
    public Map(int size) {
        this(size, size, 0);
    }

    /**
     * Constructs a map from a given 2D array.
     *
     * @param data
     */
    public Map(int[][] data) {
        init(data);
    }

    @Override
    public void init(int w, int h, int v) {
        _map = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                _map[i][j] = v;
            }
        }

    }

    @Override
    public void init(int[][] arr) {
        if ((arr == null) || (arr.length == 0)) {
            throw new RuntimeException("array cannot be null or with h=0 ");
        }
        int h = arr.length;
        int w;
        if (arr[0].length > 0) {
            w = arr[0].length;
        } else {
            throw new RuntimeException("w most be larger then 0");
        }

        _map = new int[h][w];
        for (int i = 0; i < h; i++) {
            if (arr[i].length != w) {
                throw new RuntimeException("Array must have same length");
            }
            for (int j = 0; j < w; j++) {
                _map[i][j] = arr[i][j];
            }
        }

    }

    @Override
    public int[][] getMap() {
        int h = _map.length;
        int w = _map[0].length;
        int[][] ans = new int[h][w];
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {//[2][1] arr[2] in place 1
                ans[r][c] = _map[r][c];
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
        int ans = _map.length;
        return ans;
    }

    @Override
    public int getPixel(int x, int y) {
        Index2D p = new Index2D(x, y);
        if (isInside(p)) {
            int ans = _map[y][x];
            return ans;
        } else {
            throw new RuntimeException("x or y out of dim");
        }
    }

    @Override
    public int getPixel(Pixel2D p) {
        if (isInside(p)) {
            int ans = _map[p.getY()][p.getX()];
            return ans;
        } else {
            throw new RuntimeException("x or y out of dim");
        }
    }

    @Override
    public void setPixel(int x, int y, int v) {//row-y  col-x
        Index2D p = new Index2D(x, y);
        if (isInside(p)) {
            _map[y][x] = v;
        } else {
            throw new RuntimeException("x or y out of dim");
        }

    }

    @Override
    public void setPixel(Pixel2D p, int v) {
        int x = p.getX(), y = p.getY();
        if (isInside(p)) {
            _map[y][x] = v;
        } else {
            throw new RuntimeException("x or y out of dim");
        }
    }

    @Override
    public boolean isInside(Pixel2D p) {
        boolean c1 = ((p.getY() < this.getHeight()) && (p.getX() < this.getWidth()));
        boolean c2=  (0<=p.getY()) && (0<=p.getX());
        return c1 && c2;
    }

    @Override
    public boolean sameDimensions(Map2D p) {
        boolean ans = ((p.getHeight() == this.getHeight()) && (p.getWidth() == this.getWidth()));
        return ans;
    }

    @Override
    public void addMap2D(Map2D p) {
        if (this.sameDimensions(p)) {
            for (int r = 0; r < p.getHeight(); r++) {
                for (int c = 0; c < p.getWidth(); c++) {
                    this.setPixel(c, r, (this.getPixel(c, r) + p.getPixel(c, r)));
                }
            }
        }

    }

    @Override
    public void mul(double scalar) {
        for (int r = 0; r < this.getHeight(); r++) {
            for (int c = 0; c < this.getWidth(); c++) {
                this.setPixel(c, r, (int) (this.getPixel(c, r) * scalar));
            }
        }
    }

    @Override
    public void rescale(double sx, double sy) {
        int new_h = (int) (this.getHeight() * sy), new_w = (int) (this.getWidth() * sx);
        int[][] re_map = new int[new_h][new_w];
        for (int r = 0; r < new_h; r++) {
            for (int c = 0; c < new_w; c++) {
                int old_w = (int) (c / sx), old_h = (int) (r / sy);
                re_map[r][c] = this.getPixel(old_w, old_h);
            }
        }
        this.init(re_map);

    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {
        if (rad <= 0) {
            throw new IllegalArgumentException("rad must be bigger than 0");
        }
        for (int r = (int) (center.getY() - rad); r < (int) (center.getY() + rad); r++) {
            for (int c = (int) (center.getX() - rad); c < (int) (center.getX() + rad); c++) {
                Index2D point = new Index2D(c, r);
                if (CircleContains(point, center, rad)) {
                    if (this.isInside(point)) {
                        this.setPixel(point, color);
                    }
                }
            }
        }
    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {
        if (!isInside(p1) || !isInside(p2)) return;
        if (p1.equals(p2)) {
            setPixel(p1.getX(), p1.getY(), color);
            return;
        }
        int x1 = p1.getX(), y1 = p1.getY(), x2 = p2.getX(), y2 = p2.getY();
        int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1);

        if (dx >= dy) {
            if (x1 > x2) {
                drawLine(p2, p1, color);
                return;
            }

            double slope = (double) (y2 - y1) / (x2 - x1), y = y1;

            for (int x = x1; x <= x2; x++) {
                int ry = (int) Math.round(y);
                if (this.isInside(new Index2D(x, ry))) {
                    setPixel(x, ry, color);
                }
                y += slope;
            }
        } else {
            if (y1 > y2) {
                drawLine(p2, p1, color);
                return;
            }

            double slope = (double) (x2 - x1) / (y2 - y1), x = x1;

            for (int y = y1; y <= y2; y++) {
                int rx = (int) Math.round(x);
                if (this.isInside(new Index2D(rx, y))) {
                    setPixel(rx, y, color);
                }
                x += slope;
            }
        }
    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {
        int x_start = Math.min(p1.getX(), p2.getX()), x_end = Math.max(p1.getX(), p2.getX());
        int y_start = Math.min(p1.getY(), p2.getY()), y_end = Math.max(p1.getY(), p2.getY());
        for (int r = y_start; r <= y_end; r++) {
            Index2D p_start = new Index2D(x_start, r), p_end = new Index2D(x_end, r);
            drawLine(p_start, p_end, color);
        }
    }

    @Override
    public boolean equals(Object ob) {
        boolean ans = false;
        if (ob == null || !(ob instanceof Map2D)) {
            return ans;
        }
        Map2D map2 = (Map2D) ob;
        if (!(this.sameDimensions(map2))) {
            return ans;
        }
        for (int r = 0; r < this.getHeight(); r++) {
            for (int c = 0; c < this.getWidth(); c++) {
                if (this.getPixel(c, r) != map2.getPixel(c, r)) {
                    return ans;
                }
            }
        }
        ans = true;
        return ans;
    }

    @Override
    /**
     * Fills this map with the new color (new_v) starting from p.
     * https://en.wikipedia.org/wiki/Flood_fill
     */
    public int fill(Pixel2D xy, int new_v, boolean cyclic) {
        this.counter = 0;
        if(!(this.isInside(xy))) {throw new RuntimeException("xy out of bounds");}
        int color = this.getPixel(xy);
        if(color==new_v){return 0;}
        int r=this.getHeight(), c=this.getWidth();

        int[][] dirct={{1,0},{-1,0},{0,1},{0,-1}};
        ArrayDeque<Index2D> q = new ArrayDeque<>();
        q.add((Index2D) xy);

        while(!q.isEmpty())
        {
            Index2D curr= q.removeFirst();
//            this.setPixel(curr,new_v);
//            counter++;
            for(int[] d: dirct){
                int nx=curr.getX()+d[0],ny=curr.getY()+d[1];
                Index2D n_curr= new Index2D(nx,ny);
                if(cyclic){
                    if(nx==c){nx=0;} else if (nx==-1) {nx=c-1;}
                    if(ny==r){ny=0;} else if (ny==-1) {ny=r-1;}
                    n_curr.change(nx,ny);
                }
                else {
                    if (!this.isInside(n_curr)) {
                        continue;
                    }
                }
                if (this.getPixel(n_curr) == color)
                {
                    this.setPixel(n_curr,new_v);
                    q.add(n_curr);
                    counter++;
                }
            }
        }




        return this.counter;
    }

    @Override
    /**
     * BFS like shortest the computation based on iterative raster implementation of BFS, see:
     * https://en.wikipedia.org/wiki/Breadth-first_search
     */
    public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
        Pixel2D[] ans = null;// the result.
        if(!(this.isInside(p1)) || !(this.isInside(p2))){
            throw new RuntimeException("p1 or p2 out of bounds");
        }
        if(p1.equals(p2)){return new Pixel2D[] {p1};}
        int r=this.getHeight(), c=this.getWidth();
        int  ex= p2.getX(),ey= p2.getY();
        Pixel2D[][] parents= new Pixel2D[r][c];
        boolean[][] visited= new boolean[r][c];
        int[][] dirct={{1,0},{-1,0},{0,1},{0,-1}};
        ArrayDeque<Index2D> q = new ArrayDeque<>();
        boolean found=false;
        q.add(new Index2D(p1));
        visited[p1.getY()][p1.getX()]=true;
        parents[p1.getY()][p1.getX()]=null;//start point don't have someone before it.

        while(!q.isEmpty())
        {
            Index2D curr= q.removeFirst();
            if(curr.equals(p2))
            {
             found=true;
             break;
            }

            for(int[] d: dirct){
                int nx=curr.getX()+d[0],ny=curr.getY()+d[1];
                Index2D n_curr= new Index2D(nx,ny);
                if(cyclic){
                    if(nx==c){nx=0;} else if (nx==-1) {nx=c-1;}
                    if(ny==r){ny=0;} else if (ny==-1) {ny=r-1;}
                    n_curr.change(nx,ny);
                }
                else
                {
                    if((nx<0) || (nx>=c) || (ny<0) || (ny>=r))
                    {
                        continue;
                    }
                }
                if(!visited[ny][nx] && this.getPixel(n_curr)!=obsColor){
                    visited[ny][nx]=true;
                    parents[ny][nx]=curr;
                    q.add(n_curr);
                }
            }
        }
        if(!found){return null;}//didn't find a path.

        ArrayList<Index2D> paths= new ArrayList<>();
        Index2D current= new Index2D(p2);
        while(!current.equals(p1)){
            paths.add(current);
            current= new Index2D(parents[current.getY()][current.getX()]);
        }
        paths.add((Index2D) p1);
        Collections.reverse(paths);
        ans= paths.toArray(new Pixel2D[0]);
        return ans;
    }

    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map ans =  new Map(this.getMap());
        ans.paddMap2D(obsColor);
        if(!(this.isInside(start))) {throw new RuntimeException("start point out of bounds");}
        int r=ans.getHeight(), c=ans.getWidth();
        int[][] dirct={{1,0},{-1,0},{0,1},{0,-1}};
        ArrayDeque<Index2D> q = new ArrayDeque<>();
        q.add((Index2D) start);
        ans.setPixel(start,0);

        while(!q.isEmpty())
        {
            Index2D curr= q.removeFirst();
            int curr_color=ans.getPixel(curr);

            for(int[] d: dirct){
                int nx=curr.getX()+d[0],ny=curr.getY()+d[1];
                Index2D n_curr= new Index2D(nx,ny);
                if(cyclic)
                {
                    if(nx==c){nx=0;} else if (nx==-1) {nx=c-1;}
                    if(ny==r){ny=0;} else if (ny==-1) {ny=r-1;}
                    n_curr.change(nx,ny);
                }
                else {
                    if (!ans.isInside(n_curr)) {
                        continue;
                    }
                }
                if (ans.getPixel(n_curr) == -1)
                {
                    q.add(n_curr);
                    ans.setPixel(n_curr,curr_color+1);
                }
            }

        }
        return ans;
    }

    /// /////////////////// Private Methods ///////////////////////
    ///
    private boolean CircleContains(Pixel2D p, Pixel2D center, double r) {
        return r >= p.distance2D(center);
    }


//    old version
    private int fill_in_rec(Pixel2D xy, int new_v, boolean cyclic) {
        this.counter = 0;
        int color = this.getPixel(xy);

        if (color != new_v && isInside(xy)) {
            filler(xy, color, new_v, cyclic);
        }
        return this.counter;
    }

    private void filler(Pixel2D p, int old_color, int new_v, boolean cyclic) {
        int x =p.getX(), y=p.getY();
        if (cyclic) {
            int w =getWidth();
            int h =getHeight();
            x = (x+w)%w;
            y = (y+h)%h;
            p = new Index2D(x,y);
        } else {
            if (!isInside(p)) {return;}
        }

        if (getPixel(p) != old_color) {return;}

        setPixel(p, new_v);
        this.counter++;

        filler(new Index2D(x, y + 1), old_color, new_v, cyclic);
        filler(new Index2D(x, y - 1), old_color, new_v, cyclic);
        filler(new Index2D(x + 1, y), old_color, new_v, cyclic);
        filler(new Index2D(x - 1, y), old_color, new_v, cyclic);
    }
    private int neighbors_min_color(Map2D map,Index2D curr, boolean cyclic){
        int v=0;
        int r=map.getHeight(), c=map.getWidth();
        ArrayList<Integer> list= new ArrayList<>();
        int[][] dirct={{1,0},{-1,0},{0,1},{0,-1}};
        for(int[] d: dirct)
        {
            int nx=curr.getX()+d[0],ny=curr.getY()+d[1];
            Index2D n_curr= new Index2D(nx,ny);
            if(cyclic){
                if(nx==c){nx=0;} else if (nx==-1) {nx=c-1;}
                if(ny==r){ny=0;} else if (ny==-1) {ny=r-1;}
                n_curr.change(nx,ny);
            }
            else {
                if ((nx < 0) || (nx >= c) || (ny < 0) || (ny >= r)) {
                    continue;
                }
            }if(map.getPixel(n_curr)>=0)
                {
                    list.add(map.getPixel(n_curr));
                }
            }

        if(!list.isEmpty())
        {
            v=Collections.min(list);
        }

        return v+1;
    }

    private void paddMap2D(int v) {
            for (int r = 0; r < this.getHeight(); r++) {
                for (int c = 0; c < this.getWidth(); c++) {
                    if(this.getPixel(c, r)!=v)
                    {
                        this.setPixel(c, r,-1);
                    }
                }
            }


    }



}






