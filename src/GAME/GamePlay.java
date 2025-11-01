package GAME;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements ActionListener , KeyListener {
    private boolean play = false;
    private int score=0;
    private int totalbricks=21;

    private Timer timer;
    private int delay=9;

    private int playerX=350;
    private int ballposX=120;
    private int ballposY=350;

    private int ballXdir=-1;
    private int ballYdir=-2;
    private  Bricks map;

    public  GamePlay() {
        addKeyListener(this);
        map=new Bricks(3,7);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
    }
    public void paint(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,693,600);
        //borders
        g.setColor(Color.MAGENTA);
        g.fillRect(0,0,680,3);
        g.fillRect(685,0,3,600);
        g.fillRect(0,0,3,600);
        //drawBricks
        map.drawBricks((Graphics2D) g);

        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Times New Roman",Font.BOLD,30));
        g.drawString("Score:"+score,550,30);

        //peddle
        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);

        if (totalbricks==0){
            play=false;
            ballXdir=0;
            ballYdir=0;

            g.setColor(Color.MAGENTA);
            g.setFont(new Font("SAN SERIF",Font.BOLD,50));
            g.drawString("YOU HAVE WON",110,250);

            g.setColor(Color.MAGENTA);
            g.drawString("your score "+ score,150,300);

            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial",Font.BOLD,35));
            g.drawString("Press Enter to Restart the Game",50,350);

        }

        if(ballposY>600){
            play=false;
            ballXdir=0;
            ballYdir=0;

            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD,50));
            g.drawString("You lost Game over",150,150);

            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial",Font.BOLD,35));
            g.drawString("Press Enter to Restart the Game",50,300);


        }




        //ball
        g.setColor(Color.red);
        g.fillOval(ballposX,ballposY,20,20);
        g.dispose();



    }



    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            if(new Rectangle(ballposX,ballposY,20,20).intersects
                    (new Rectangle(playerX,550,100,8))){
                ballYdir=-ballYdir;
            }

            A:for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brickX=j* map.brickWidth+80;
                        int brickY=i*map.brickHeight+50;
                        int brickWidth=map.brickWidth;
                        int brickHeight=map.brickHeight;

                        Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
                        Rectangle brickRect=rect;
                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0,i,j);
                            totalbricks--;
                            score+=5;

                            if(ballposX +19 <= brickRect.x||ballposX+1 >=brickRect.x+brickRect.width){
                                ballXdir=-ballXdir;
                            }else {
                                ballYdir=-ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }


            ballposX+=ballXdir;
            ballposY+=ballYdir;
            if(ballposX<0){
                ballXdir=-ballXdir;
            }
            if (ballposY<0){
                ballYdir=-ballYdir;
            }
            if(ballposX>660){
                ballXdir=-ballXdir;
            }

        }

        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if (playerX>=580){
                playerX=580;
            }
            else {
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX=10;
            }
            else {
                moveLeft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                play=true;
                ballposY=500;
                ballposX=250;
                ballXdir=-1;
                ballYdir=-2;
                playerX=250;
                score=0;
                totalbricks=21;

                map=new Bricks(3,7);
                repaint();

            }
        }
    }

    public  void moveRight(){
        play=true;
        playerX+=20;
    }
    public void moveLeft(){
        play=true;
        playerX -=20;
    }
}
