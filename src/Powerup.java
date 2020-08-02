import java.awt.Color;
import java.awt.Graphics;


public class Powerup {
    
    //declare variables
    private int xCoor, yCoor, width, height;
     
    public Powerup(int x, int y, int s){
        
        //initialize variables
        this.xCoor = x;
        this.yCoor = y;
        width = s;
        height = s;
}
    
    public void tick(){
        
    }
    
    //allocate and draw the powerup
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(xCoor * width, yCoor * height, width, height);
    }

    public int getxCoor() {
        return xCoor;
    }

    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }
}
