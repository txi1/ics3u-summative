import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class GamePanel extends JPanel implements Runnable{
    
    //declare and initialize variables
    private static final int IFW = WHEN_IN_FOCUSED_WINDOW;
    public static final int WIDTH = 1280, HEIGHT = 980;
    
    private Thread thread;
    
    private int people, deadPlayers = 0;
    private boolean running, isArtMode;
    private Graphics g;
    
    ArrayList<ArrayList<Player>> play = new ArrayList<ArrayList<Player>>(3);
    ArrayList<Player> Player;
    private Player[] p;
    
    private Powerup powerup;
    private ArrayList<Powerup> pUps;
    
    private IO io;
    private String filePath = "Winners.txt";
    
    private Image img;
    
    private Random boi;
    
    
    private int ticks = 0;
    
    public GamePanel(boolean art, int people){
        
        //alllll initialising the game, self-explanatory
        this.setFocusable(true);
        this.requestFocus();
        this.setVisible(true);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
            
        img = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/fade.gif"));
        
        io = new IO();
        io.createOutputFile(filePath);
        
        isArtMode = art;
        this.people = people;
        
        for(int i = 0; i < people; i++){
        Player = new ArrayList<Player>();
        play.add(Player);
        }
        
        p = new Player[people];
        pUps = new ArrayList<Powerup>();
        boi = new Random();
        
        for(int i = 0; i < play.size(); i++){
        if(play.get(i).isEmpty()){
            p[i] = new Player(50, i*61+20, 4, 4, i+1,false, 20, 3, false, 0);
            play.get(i).add(p[i]);
        }
        }
        
        
        //This entire section is assigning keybinds to a function
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("ESCAPE"), "exit");
        
        this.getActionMap().put("exit", new Exit());
                
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), "up");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), "down");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), "left");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), "right");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("ENTER"), "boost");
        
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), "up2");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("S"), "down2");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("A"), "left2");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("D"), "right2");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("TAB"), "boost2");
        
        this.getActionMap().put("up", new MoveAction(1, 0));
        this.getActionMap().put("down", new MoveAction(2, 0));
        this.getActionMap().put("left", new MoveAction(3, 0));
        this.getActionMap().put("right", new MoveAction(4, 0));
        this.getActionMap().put("boost", new BoostAction(0));
        
        this.getActionMap().put("up2", new MoveAction(1, 1));
        this.getActionMap().put("down2", new MoveAction(2, 1));
        this.getActionMap().put("left2", new MoveAction(3, 1));
        this.getActionMap().put("right2", new MoveAction(4, 1));
        this.getActionMap().put("boost2", new BoostAction(1));
        
        if(people > 2){
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("NUMPAD5"), "up3");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("NUMPAD2"), "down3");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("NUMPAD1"), "left3");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("NUMPAD3"), "right3");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("NUMPAD7"), "boost3");
        
        this.getActionMap().put("up3", new MoveAction(1, 2));
        this.getActionMap().put("down3", new MoveAction(2, 2));
        this.getActionMap().put("left3", new MoveAction(3, 2));
        this.getActionMap().put("right3", new MoveAction(4, 2));
        this.getActionMap().put("boost3", new BoostAction(2));
        }
        
        if(people > 3){
            this.getInputMap(IFW).put(KeyStroke.getKeyStroke("U"), "up4");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("J"), "down4");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("H"), "left4");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("K"), "right4");
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("T"), "boost4");
        
        this.getActionMap().put("up4", new MoveAction(1, 3));
        this.getActionMap().put("down4", new MoveAction(2, 3));
        this.getActionMap().put("left4", new MoveAction(3, 3));
        this.getActionMap().put("right4", new MoveAction(4, 3));
        this.getActionMap().put("boost4", new BoostAction(3));
        }
        
        startGame();
    }
    
    public void restartGame(){
        deadPlayers = 0;
        //file io, printing the results of a match to a .txt file named Winners.txt
        for(int i = 0; i < people; i++){
            if(!p[i].isDead()){
                io.println("WINNER: PLAYER " +(i+1));
                System.out.println("WINNER: PLAYER " +(i+1));
            }
        }
        try {
            //pause the game for a bit
            thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Clear all lines from the board
         for(int i = 0; i < play.size(); i++){
                 play.get(i).clear();
                 }
         
         //reset every player back to their initial position
         for(int i = 0; i < play.size(); i++){
            p[i].setxCoor(50);
            p[i].setyCoor(i*61+20);
            p[i].setDirection(4);
            p[i].setColor(i+1);
            p[i].resetPlayer();
            play.get(i).add(p[i]);
            
        }
         
    }
    
    public void startGame(){
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public void stopGame(){
        running = false;
            try{
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
    }
    
    //Basically, every finished tick, the game goes through and updates
    public void tick(){
        ticks++;
        //can and should change as you see fit; on my computer, the game runs very fast
        //but on another, it may not. Lower the number to increase the speed at which
        //the game runs.
        if(ticks > 400000){
            ticks = 0;
            
            //You will see this for loop a lot: it's basically a loop that refers to every player
            for(int k = 0; k < play.size(); k++){
                
                //if the player of a person is not dead
                if(!p[k].isDead()){
                    //checking if they are using boost
                    if(p[k].isBoosting()){
                        if(p[k].getBoost() > 0)
                            //basically, doing a second update, effectively doubling distance travelled
                        p[k] = new Player(p[k].getxCoor(), p[k].getyCoor(), 4, p[k].getDirection(), p[k].getColor(), p[k].isBoosting(), p[k].getBoost(),p[k].getUses(), p[k].isPowered(), p[k].getPowerTick());
                        p[k].update();
                        p[k].useBoost();
                        play.get(k).add(p[k]); 
                    }
                    if(p[k].getBoost() == 0){
                        p[k].resetBoost();
                    }
                    //if the player has consumed a pellet
                    if(p[k].isPowered()){
                        //You can change how long the invincibility lasts in the Player.java file
                        //The larger the powerTick, the longer it lasts
                        p[k].COLORSHIFT();
                        p[k].tickDown();
                        p[k] = new Player(p[k].getxCoor(), p[k].getyCoor(), 4, p[k].getDirection(), p[k].getColor(), p[k].isBoosting(), p[k].getBoost(),p[k].getUses(), p[k].isPowered(), p[k].getPowerTick());
                        p[k].update();
                        play.get(k).add(p[k]);
                        if(p[k].getPowerTick() == 0){
                            p[k].setPower(false);
                        }
                    }
            //Default operation: create a new player body and add it to the arrayList
            p[k] = new Player(p[k].getxCoor(), p[k].getyCoor(), 4, p[k].getDirection(), p[k].getColor(), p[k].isBoosting(), p[k].getBoost(),p[k].getUses(),p[k].isPowered(), p[k].getPowerTick());
            p[k].update();
            play.get(k).add(p[k]);            
            }
                }
            
            //Powerups available will always be the amount of people playing - 1
            if(pUps.size() < people - 1){
                int x = boi.nextInt(127);
                int y = boi.nextInt(97);
                powerup = new Powerup(x,y,4);
                pUps.add(powerup);
            }
            
            //Checking if player is picking up a power pellet
            for(int i = 0; i < pUps.size(); i++){
                OUTER:
                for(int k = 0; k < play.size(); k++){
                    
                for(int j = 0; j < play.get(k).size(); j++){
                if(p[k].getxCoor() == pUps.get(i).getxCoor() && p[k].getyCoor() == pUps.get(i).getyCoor()){
                    pUps.remove(i);
                    if(!p[k].isPowered()){
                    p[k].powerUP();
                    p[k].setPower(true);
                }
                    break OUTER;
                    
                }
                }    
                }
            }
            
        //Collision detection: if in artmode, then this is ignored completely
        if(!isArtMode){
            
            //Checking every person's player head if they're colliding with their own player body
            for(int i = 0; i < play.size(); i++){
                for(int j = 0; j < play.get(i).size()-2; j++){
                        if(p[i].getxCoor() == play.get(i).get(j).getxCoor() && p[i].getyCoor() == play.get(i).get(j).getyCoor() && !p[i].isDead() && !p[i].isBoosting() && !p[i].isPowered()){
                     p[i].die();
                     deadPlayers++;
                     
                     //if there's only 1 person left
                        if(deadPlayers == people - 1){
                restartGame();
                        }
                        }
                   }
            
            //Same thing as above, except checking if the person's player head is colliding with another person's player body
                for(int j = 0; j < play.size(); j++){
                    for(int k = 0; k < play.get(j).size()-2; k++){
                        if(p[i].getxCoor() == play.get(j).get(k).getxCoor() && p[i].getyCoor() == play.get(j).get(k).getyCoor() && !p[i].isDead() && !p[i].isBoosting()&& !p[i].isPowered()){
                            p[i].die();
                            deadPlayers++;
                        
                        if(deadPlayers == people - 1){
                    restartGame();
                        }
                        }
                    }
                }
            }
        }
        //If any of the player's heads are at the edge, move em to the opposite end
        for(int i = 0; i < play.size(); i++){
            if(p[i].getxCoor() > 320){
                p[i].setxCoor(0);
            }
            if(p[i].getxCoor() < 0){
                p[i].setxCoor(320);
            }
            if(p[i].getyCoor() > 245){
                p[i].setyCoor(0);
            }
            if(p[i].getyCoor() < 0){
                p[i].setyCoor(245);
            }
        }
        }
    }
    
    @Override
    public void paint(Graphics g){
       //background
       g.drawImage(img, 0, 0, null);
        
       //draw the players
        for(int j = 0;j < play.size();j++){
            for(int i = 0; i < play.get(j).size(); i++){   
                play.get(j).get(i).draw(g);
            }
        }
        
        //draw the powerups
        for(int i = 0; i < pUps.size(); i++){
            pUps.get(i).draw(g);
        }
    }

    @Override
    public void run() {
        while(running){
            tick();
            repaint();
        }
    }

   
    //Basically, whenever one the defined "move" keys above are pressed, do something
    private class MoveAction extends AbstractAction {

        int direction;
        int player;
        
        MoveAction(int direction, int player) {
            this.direction = direction;
            this.player = player;
            
        }

        //the something is this: turn in the direction if it's not going backwards, individual to each player
        @Override
        public void actionPerformed(ActionEvent e) {
            if((direction == 1 && p[player].getDirection() != 2) || 
                    (direction == 3 && p[player].getDirection() != 4) || 
                    (direction == 2 && p[player].getDirection() != 1) || 
                    (direction == 4 && p[player].getDirection() != 3)){
                
                p[player].setDirection(direction);
                    try{
                        thread.sleep(25);
                    }catch (InterruptedException ex) {
                        Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }
    
    //same as above: boost key is pressed, do something
    private class BoostAction extends AbstractAction {

        int player;
        
        BoostAction(int player){
            this.player = player;
        }
        
        //the something is this: make this player boost
        @Override
        public void actionPerformed(ActionEvent e) {
            p[player].boost();
        }
        
    }
    private class Exit extends AbstractAction {
        
        Exit(){
            
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            io.closeOutputFile();
            System.exit(0);
        }
    }
}