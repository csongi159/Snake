import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;


public class SnakeController implements Runnable{
    private Clip clip;
    private SnakeGame snakeGame;
    private SnakePanel snakePanel;
    private Snake snake;
    private SnakeView snakeView;
    private Point apple;
    private Random rand;
    private double speed;
    private AudioInputStream audioInputStream;
    public SnakeController(Snake snake, SnakeView snakeView, SnakeGame snakegame, SnakePanel snakePanel, double speed, Point fApple) {
        this.speed=speed;
        this.snakePanel=snakePanel;
        this.snake=snake;
        this.snakeView=snakeView;
        this.snakeGame=snakegame;
        rand= new Random();
        if(fApple==null) {
            apple = new Point(rand.nextInt(100), rand.nextInt(100));
            snakeView.apple=apple;
        }
        else{
            apple = fApple;
            snakeView.apple=apple;
        }
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("music/point.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            //clip.
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
        }


    }

    public void move(){
        Point point = snake.getHead();
        if(snake.getDirection()==1){
            snake.setHead(new Point(point.x - 5, point.y));
        } else if (snake.getDirection()==2) {
            snake.setHead(new Point(point.x, point.y-5));
        } else if (snake.getDirection()==3) {
            snake.setHead(new Point(point.x + 5, point.y));
        } else if (snake.getDirection()==4) {
            snake.setHead(new Point(point.x, point.y+5));
        }
        if(apple.distance(snake.getHead())<6) {

            apple.move(rand.nextInt(snakeView.getWidth()), rand.nextInt(snakeView.getHeight()));
            snakeView.apple=apple;
            snakeGame.setScore(snakeGame.getScore()+1);
            snakePanel.update_score();
            clip.setFramePosition(0);
            clip.start();
        }
        else{
            snake.removeTail();
        }
    }


    @Override
    public void run() {
        while(true){
            move();
            List<Point> points = snake.getEndPoints();
            if(snake.getHead().x<0 || snake.getHead().y<0 || snake.getHead().x> snakeGame.getWidth() - 25 || snake.getHead().y>snakeGame.getHeight()-100){
                snakeGame.gameover();
                return;
            }
            for(int i=0;i<points.size()-1;i++){
                if(points.get(i).equals(snake.getHead())){
                    System.out.println("Meghalt");
                    snakeGame.gameover();
                    return;
                }
            }
            try {
                Thread.sleep((long) (100/speed));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            snakeView.repaint();
        }
    }
}

