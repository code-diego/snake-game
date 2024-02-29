
import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Snake Game :D");
          
        SnakePanel snakeGame = new SnakePanel();
        snakeGame.startGame();
        frame.add(snakeGame);
        
        
        
        frame.setSize(snakeGame.WIDTH_SCREEN,snakeGame.HEIGHT_SCREEN);
        frame.setLocationRelativeTo(null);
        //frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
