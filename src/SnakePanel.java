
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class SnakePanel extends JPanel{
    
    //CONSTANTES
    final int WIDTH_SNAKE_PART = 10;
    final int HEIGHT_SNAKE_PART = 10;
    //final int SPEED = 10;
    final int WIDTH_SCREEN = 600;
    final int HEIGHT_SCREEN = 400;
    
    //variables
    int snakeHeadX = 70;
    int snakeHeadY = 70;
    char letterPress = ' '; // w, a, s, d
    ArrayList <Point> snakePoints = new ArrayList<>();
    Point pointTail;
    int foodX = 0;
    int foodY = 0;
    int score = 0;
    
    int headPaint = 0;
    
    //Contructor
    public SnakePanel(){
        this.setSize(WIDTH_SCREEN,HEIGHT_SCREEN);
        this.setBackground(Color.gray);
    }
    
    public void startGame(){
        //Agregamos la cabeza 
        snakePoints.add(new Point(snakeHeadX, snakeHeadY));
        generateFood();
        snakeKeylistener();
        
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        //Dubujando una la cabeza de la serpiente :D 
        for ( Point  point : snakePoints ){
            paintSnake(g,point.x,point.y); 
        }
        paintFood(g,foodX,foodY);
    }
    
    public void paintSnake(Graphics g, int x, int y){
        if (x == snakeHeadX && y == snakeHeadY){ g.setColor(Color.cyan);}
        else {g.setColor(Color.magenta);} //cuerpo de snake
        g.fillRect(x, y, WIDTH_SNAKE_PART, HEIGHT_SNAKE_PART);
        headPaint ++;
    }
    
    public void paintFood(Graphics g, int x, int y){
        g.setColor(Color.red);
        g.fillRect(x, y, WIDTH_SNAKE_PART, HEIGHT_SNAKE_PART);
    }

    public void snakeKeylistener(){
        KeyListener keyListenerSnake = new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                letterPress = e.getKeyChar();
                switch (letterPress) {
                    case 'w':
                    case 'W':
                        snakeHeadY -= HEIGHT_SNAKE_PART;
                        break;
                    case 's':
                    case 'S':
                        snakeHeadY += HEIGHT_SNAKE_PART;
                        break;
                    case 'a':
                    case 'A':
                        snakeHeadX -= WIDTH_SNAKE_PART;
                        break;
                    case 'd':
                    case 'D':
                        snakeHeadX += WIDTH_SNAKE_PART;
                        break;
                    default:
                        break;
                }

                moveSnake(snakeHeadX,snakeHeadY);
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {} 
        };
        addKeyListener(keyListenerSnake);
        setFocusable(true);
    }
    
    
    public void moveSnake(int x, int y){
        pointTail = new Point(snakePoints.get(snakePoints.size()-1));  
      
        if ( snakePoints.size() >= 2 ){
            for ( int i=1; i<snakePoints.size(); i++ ){
                snakePoints.set(snakePoints.size()-i,snakePoints.get(snakePoints.size()-i-1));
            } 
        }
        
        snakePoints.set(0,new Point(x,y));
        
        checkCollisions();
        repaint();
    }
    
    public void generateFood(){
        Random randy = new Random();
        int posX = randy.nextInt(WIDTH_SCREEN/WIDTH_SNAKE_PART)*10;
        int posY = randy.nextInt(HEIGHT_SCREEN/HEIGHT_SNAKE_PART)*10;
        foodX = posX;
        foodY = posY;
        if(foodX >= 570){foodX-=20;}
        if(foodY >= 350){foodY-=50;} // mientras arreglo el tamaño del titulo x
        
        repaint();
    }
    
    public void generateTail(){
        snakePoints.add(pointTail);
    }
    
    public void checkCollisions(){
        //Interacion con la comida
        if (snakeHeadX == foodX && snakeHeadY == foodY ){
            generateFood();
            score++;
            generateTail();
        }
        //Interacion con su propio cuerpo
        
        for ( int i=1; i<snakePoints.size(); i++ ){
            if (snakePoints.get(0) == snakePoints.get(i) ){
                gameOver();
            }
        }
    }
    
    public void gameOver(){
        System.out.println("LOSE!");
    }
    
}
