/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
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
            Thread.sleep(Math.abs(500-(game.score)));
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
        placeFruit();
        
        addKeyListener(this);
        setFocusable(true);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        testGameOver();
        if(direction != -1){
            this.setBackground(Color.BLACK);
        
            g.setColor(Color.RED);
            for (Point point : snake) {
                g.fillRect(point.x, point.y, 40, 40);
            }

            g.setColor(Color.ORANGE);
            g.fillRect(fruit.x, fruit.y, 40, 40);
        }
        else gameOverScreen(g);
    }
    
    private void gameOverScreen(Graphics g){
        if(score > highScore)
                highScore = score;
        snake.clear();
        snake.add(new Point(0, 0));
        score = 0;
        placeFruit();
        
        
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));
        g.setColor(Color.RED);
        g.drawString("GAME OVER", 40, 40*2);
        g.setColor(Color.WHITE);
        g.drawString("Score : " + this.score, 40, 40*3+10);
        g.drawString("High Score : " + this.highScore, 40, 40*4+20);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        g.drawString("Press any key to continue...", 40, 40*5+30);
    }

    void drawSnake(){
        for (int i = snake.size()-1; i > 0; i--) {
            //if(i > 1)
            snake.get(i).x = snake.get(i-1).x;
            snake.get(i).y = snake.get(i-1).y;
        }
        
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
        
        if(fruit.x == snake.get(0).x && fruit.y == snake.get(0).y){
            placeFruit();
            score += 10;
            snake.add(new Point(-40, -40));
        }
    }
    
    private void testGameOver(){
        if(snake.get(0).x > 440 || snake.get(0).x < 0)
            direction = -1;
        else if(snake.get(0).y > 320 || snake.get(0).y < 0)
            direction = -1;
        for (int i = 1; i < snake.size(); i++){
            if(snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y)
                direction = -1;
        }
    }
    
    void placeFruit(){
        boolean onSnake = true;
        Random rnd = new Random();
        
        do{
            fruit.x = rnd.nextInt(12) * 40;
            fruit.y = rnd.nextInt(9) * 40;
            
            for (Point point : snake) {
                if((fruit.x == point.x) && (point.y == fruit.y))
                    onSnake = true;
                else
                    onSnake = false;
            }
        } while(onSnake);
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        if(direction != -1){
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
                case KeyEvent.VK_F:
                    placeFruit();
                    break;
            }
        }else {
            switch(ke.getKeyCode()){ 
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                default: 
                    direction = 3;
            }
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
