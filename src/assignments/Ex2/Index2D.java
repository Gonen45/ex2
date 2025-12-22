package assignments.Ex2;

import classes.week6.Point2D;

public class Index2D implements Pixel2D {

    private int _h;
    private int _w;


    public Index2D(int w, int h) {
        _h=Math.abs(h); //abs is needed???
        _w=Math.abs(w);
    }
    public Index2D(Pixel2D other) {
//        copy constructor
        this._h=other.getY();
        this._w=other.getX();
    }
    @Override
    public int getX() {return _w;}

    @Override
    public int getY() {return _h;}

    @Override
    public double distance2D(Pixel2D p2) {
//        if p2 is null{}
        return Math.sqrt( (this._w * p2.getX()) + (this._h * p2.getY()) );
    }

    @Override
    public String toString() {
        String ans = "("+this._w+","+this._h+")";

        return ans;
    }

    @Override
    public boolean equals(Object p) {
        if(p==null || !(p instanceof Index2D)) {return false;}
        Index2D p2 = (Index2D)p;
        return (((this._h==p2.getY()) && (this._w==p2.getX())));
    }

    public void change(int w ,int h) {
        this._h = h;
        this._w = w;
    }
}
