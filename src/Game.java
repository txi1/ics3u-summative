import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class Game extends JFrame implements KeyListener{

    //declare variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel modeSelector;
    private javax.swing.JLabel title;
    private javax.swing.JLabel line1;
    private javax.swing.JLabel line2;
    private javax.swing.JLabel line3;
    private javax.swing.JLabel playerSelector;
    private javax.swing.JLabel line4;
    private javax.swing.JLabel page;
    
    private int players = 2, menuState;
    private boolean isArt = false;
    
    private JPanel menu;
    
    public Game(){
        
        //initializing the menu, self-explanatory
        this.setSize(540, 763);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        menu = new JPanel();
        menuState = 0;
        menu.setLayout(null);
        
        modeSelector = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        line1 = new javax.swing.JLabel();
        line2 = new javax.swing.JLabel();
        line3 = new javax.swing.JLabel();
        playerSelector = new javax.swing.JLabel();
        line4 = new javax.swing.JLabel();
        page = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        
        modeSelector.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        modeSelector.setForeground(new java.awt.Color(255, 204, 102));
        modeSelector.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        modeSelector.setText("Art mode: D I S A B L E D");
        menu.add(modeSelector);
        modeSelector.setBounds(10, 480, 520, 80);

        title.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        title.setForeground(new java.awt.Color(255, 204, 102));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("TRON");
        menu.add(title);
        title.setBounds(60, 20, 420, 80);
        
        line1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        line1.setForeground(new java.awt.Color(255, 204, 102));
        line1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        line1.setText("Arrow keys to change the # of players.");
        menu.add(line1);
        line1.setBounds(10, 220, 520, 80);

        line2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        line2.setForeground(new java.awt.Color(255, 204, 102));
        line2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        line2.setText("Spacebar to start. Check the winners log!");
        menu.add(line2);
        line2.setBounds(10, 260, 520, 80);

        line3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        line3.setForeground(new java.awt.Color(255, 204, 102));
        line3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        line3.setText("Shift to change modes. Esc to exit.");
        menu.add(line3);
        line3.setBounds(10, 300, 520, 80);

        playerSelector.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        playerSelector.setForeground(new java.awt.Color(255, 204, 102));
        playerSelector.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerSelector.setText("Number of Players: << 2 >>");
        menu.add(playerSelector);
        playerSelector.setBounds(10, 400, 520, 80);
        
        line4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        line4.setForeground(new java.awt.Color(255, 204, 102));
        line4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        line4.setText("Press I to access instructions.");
        menu.add(line4);
        line4.setBounds(10, 590, 520, 80);
        
        page.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        page.setForeground(new java.awt.Color(255, 204, 102));
        page.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        page.setText("<< Page 1 >>");
        menu.add(page);
        page.setBounds(10, 650, 520, 80);
        page.setVisible(false);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sparkles.gif"))); // NOI18N
        background.setText("jLabel1");
        menu.add(background);
        background.setBounds(0, 0, 540, 763);
        
        this.setFocusable(true);
        this.addKeyListener(this);
        this.add(menu);
        
        
    }
     
     public static void main(String[] args){
         //start up the menu
                new Game().setVisible(true);
            }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //self explanatory code, press a key, do something
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT){
            switch(menuState){
               case 0:
                   if(players < 4){
                   players++;
                   
           playerSelector.setText("Number of Players: << " +players +" >>");
                   }
           break;    
               case 1:
                   menuState++;
                   title.setText("PLAYER 2");
                   line1.setText("You are YELLOW. Use arrow keys to move.");
                   line3.setText("Press ENTER to boost.");
                   page.setText("<< Page " +menuState +" >>");
                   break;
               case 2:
                   menuState++;
                   title.setText("PLAYER 3");
                   line1.setText("You are PINK. Use NUMPAD 1235 to move.");
                   line3.setText("Press NUMPAD7 to boost.");
                   page.setText("<< Page " +menuState +" >>");
                   break;
               case 3:
                   menuState++;
                   title.setText("PLAYER 4");
                   line1.setText("You are MAGENTA. Use UHJK to move.");
                   line3.setText("Press T to boost.");
                   page.setText("<< Page " +menuState +" >>");
                   break;
               case 4:                   
           }
            
        }
        
        if(key == KeyEvent.VK_LEFT){
           switch(menuState){
               case 0:
                   if(players > 2){
                   players--;
                   
           playerSelector.setText("Number of Players: << " +players +" >>");
                   }
           break;    
               case 1:
                   break;
               case 2:
                   menuState--;
                   title.setText("PLAYER 1");
                   line1.setText("You are CYAN. Use WASD to move.");
                   line3.setText("Press TAB to boost.");
                   page.setText("<< Page " +menuState +" >>");
                   break;
               case 3:
                   menuState--;
                   title.setText("PLAYER 2");
                   line1.setText("You are YELLOW. Use arrow keys to move.");
                   line3.setText("Press ENTER to boost.");
                   page.setText("<< Page " +menuState +" >>");
                   break;
               case 4:
                   menuState--;
                   title.setText("PLAYER 3");
                   line1.setText("You are PINK. Use NUMPAD 1235 to move.");
                   line3.setText("Press NUMPAD7 to boost.");
                   page.setText("<< Page " +menuState +" >>");
                   break;
                   
           }
        }
        
        if(key == KeyEvent.VK_SPACE){
            setSize(1280,980);
            menu.setEnabled(false);
            GamePanel boi = new GamePanel(isArt, players);
            add(boi);
            setFocusable(false);
            playerSelector.setText("Number of Players: << " +0 +" >>");
            boi.setVisible(true);
            setContentPane(boi);
            boi.setEnabled(true);
            
        }
        
        if(key == KeyEvent.VK_SHIFT && menuState < 1){
            if(!isArt){
                isArt = true;
            modeSelector.setText("Art mode: E N A B L E D");
            }
            else{
                isArt = false;
            modeSelector.setText("Art mode: D I S A B L E D");
            }
            
        }
        
        if(key == KeyEvent.VK_I && menuState < 1){
            background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moon.gif"))); // NOI18N
            title.setText("PLAYER 1");
            line1.setText("You are CYAN. Use WASD to move.");
            line2.setVisible(false);
            line3.setText("Press TAB to boost.");
            playerSelector.setText("Collect a red pellet to become invincible!");
            modeSelector.setVisible(false);
            line4.setText("Press BACKSPACE to go back to the menu.");
            menuState = 1;
            page.setText("<< Page 1 >>");
            page.setVisible(true);
        }
        
        if(key == KeyEvent.VK_BACK_SPACE && menuState > 0){
            title.setText("TRON");
        line1.setText("Arrow keys to change the # of players.");
        line2.setText("Spacebar to start. Check the winners log!");
        line2.setVisible(true);
        line3.setText("Shift to change modes. Esc to exit.");
        line4.setText("Press I to access instructions.");
        playerSelector.setText("Number of Players: << " +players +" >>");
        modeSelector.setVisible(true);
        page.setVisible(false);
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sparkles.gif")));
        menuState = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
     }
    




