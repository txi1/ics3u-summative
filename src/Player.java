import java.awt.Color;
import java.awt.Graphics;

public class Player {
    
    //declare variables
    private int xCoor, yCoor, width, height;
    private int color;
    private int direction;
    private boolean isDead, isBoosting, isPowered;
    private int boost, uses = 3, powerTick;

    public Player(int x, int y, int lineSize, int d, int c, boolean b, int boo, int u, boolean p, int pt){
        
        //initialize variables
        this.xCoor = x;
        this.yCoor = y;
        width = lineSize;
        height = lineSize;
        color = c;
        direction = d;
        isDead = false;
        isBoosting = b;
        boost = boo;
        uses = u;
        isPowered = p;
        powerTick = pt;
        }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public void die(){
        isDead = true;
    }
    
    public boolean isDead(){
        return isDead;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void boost(){
        if(uses > 0){
        isBoosting = true;
        uses --;
        }
    }
    
    public int getUses(){
        return uses;
    }
    
    public int getBoost(){
        return boost;
    }
    
    public void useBoost(){
        boost--;
    }
    public void resetBoost(){
        boost = 20;
        isBoosting = false;
    }
    
    public boolean isBoosting(){
        return isBoosting;
    }
    

    public int getDirection(){
        return direction;
    }
    
    public void setDirection(int d){
        direction = d;
    }
    
    public void tick(){
        
    }
    
    public void COLORSHIFT(){
        color++;
        if(color == 11){
            color = 1;
        }
    }
    
    public void update(){
        if(direction == 4) xCoor++;
            if(direction == 3) xCoor--;
            if(direction == 1) yCoor--;
            if(direction == 2) yCoor++;
    }
    
    public void draw(Graphics g){
        switch(color){
            case 1:
        g.setColor(Color.CYAN);
        break;
            case 2:
        g.setColor(Color.YELLOW);
        break;
            case 3:
        g.setColor(Color.PINK);
        break;
            case 4:
        g.setColor(Color.MAGENTA);
        break;
            case 5:
        g.setColor(Color.ORANGE);
        break;
            case 6:
        g.setColor(Color.GREEN);
        break;
            case 7:
        g.setColor(Color.WHITE);
        break;
            case 8:
        g.setColor(Color.DARK_GRAY);
        break;
            case 9:
        g.setColor(Color.GRAY);
        break;
            case 10:
        g.setColor(Color.BLUE);
        break;
        }
        if(isBoosting){
            g.setColor(Color.BLACK);
        }
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
    
    public boolean isPowered(){
        return isPowered;
    }
    
    public void setPower(boolean p){
        isPowered = p;
    }
    
    public int getPowerTick(){
        return powerTick;
    }
    
    public void tickDown(){
        powerTick--;
    }
    
    public void powerUP(){
        powerTick = 150;
    }
    
    public int getColor(){
        return color;
    }
    
    public void setColor(int c){
        color = c;
    }
    
    public void resetPlayer(){
        isPowered = false;
        isBoosting = false;
        boost = 20;
        powerTick = 0;
        uses = 3;
        isDead = false;
    }
}
