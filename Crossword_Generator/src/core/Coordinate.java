package core;


public class Coordinate {
    private int r;
    private int c;

    public Coordinate(int r, int c) {
        this.r = r;
        this.c = c;
    }
    
    public int getR() {
    	return r;
    }
    
    public int getC() {
    	return c;
    }
    
    public boolean equals(Object other){
    	return (this.r == ((Coordinate)other).r) && 
    			(this.c == ((Coordinate)other).c);
    }
    
    public int hashCode(){
    	return (this.r + 1) * 31 + (this.c + 1);
    }
}