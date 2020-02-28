/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Tetris {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setSize(200, 400);
        frame.setUndecorated(true);
        
        Game game = new Game();
        frame.add(game);
        
        frame.setVisible(true);
    }
    
}

class Game extends JPanel{
    ArrayList<Tetromino> tetrominos = new ArrayList<>();
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);
        
        
    }
    
}

class Tetromino{
    
}
