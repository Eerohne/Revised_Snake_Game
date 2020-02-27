/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author cstuser
 */
public class Main{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Snake Game");
        Snake game = new Snake();
        frame.add(game);
        frame.setSize(480, 360);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setVisible(true);
        
        while (true) {    
            Thread.sleep(100);
            game.drawSnake();
            game.repaint();
        }
    }
}

class Snake extends JPanel implements KeyListener{

    int score;
    int highScore;
    
    //1: UP; 2: DOWN; 3: RIGHT; 4: LEFT; 5: STOP
    int direction = 3;
    ArrayList<Point> snake = new ArrayList<>();
    Point fruit = new Point();

    public Snake() {
        snake.add(new Point(0,0));
        
        addKeyListener(this);
        setFocusable(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        this.setBackground(Color.BLACK);
        
        g.setColor(Color.RED);
        for (Point point : snake) {
            g.fillRect(point.x, point.y, 40, 40);
        }
        
        /*g.setColor(Color.ORANGE);
        g.fillRect(fruit.x, fruit.y, 40, 40);*/
    }

    void drawSnake(){
        /*for (int i = snake.size()-1; i >= 0; i--) {
            if(i > 1)
                snake.get(i).x = snake.get(i-1).x;
                snake.get(i).y = snake.get(i-1).y;
        }*/
        
        switch(direction){
            case 1:
                snake.get(0).y -= 40;
                break;
            case 2:
                snake.get(0).y += 40;
                break;
            case 3:
                snake.get(0).x += 40;
                break;
            case 4:
                snake.get(0).x -= 40;
                break;
        }
    }
    
    void placeFruit(){
        
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_UP:
                if(!(direction==2))
                    direction = 1;
                break;
            case KeyEvent.VK_DOWN:
                if(!(direction==1))
                    direction = 2;
                break;
            case KeyEvent.VK_LEFT:
                if(!(direction==3))
                    direction = 4;
                break;
            case KeyEvent.VK_RIGHT:
                if(!(direction==4))
                    direction = 3;
                break;
            case KeyEvent.VK_SPACE:
                direction = 5;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {}
}

class Point{
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }
}
