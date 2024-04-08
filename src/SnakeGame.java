import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SnakeGame extends JFrame {

    private Clip clip;
    private double speed;
    private int score;
    private int best_score;
    //private Point apple;
    private SnakePanel sp;
    private SnakeView sv;
    private Snake snake;
    private SnakeController sc;
    private HomePanel hp;
    private Thread th;
    private JMenu saveMenu;
    private JMenuItem saveItem;

    public SnakeGame() {
        setTitle("Snake");
        setBounds(100, 100, 600, 300);
        score = 0;
        best_score = 0;
        speed = 1;

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        saveMenu = new JMenu("Save");
        JMenu settingsMenu = new JMenu("Settings");
        menuBar.add(settingsMenu);
        menuBar.add(saveMenu);

        saveMenu.setVisible(false);

        saveMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    FileWriter fw = new FileWriter("save.txt");
                    fw.write(snake.getDirection() + "\n");
                    fw.write(snake.getScore() + "\n");
                    fw.write(sv.apple.x + " " + sv.apple.y + "\n");

                    for(Point point:snake.getEndPoints()){
                        fw.write(point.x + " " + point.y + "\n");
                    }
                    fw.flush();
                    fw.close();
                    th.interrupt();
                    home();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        settingsMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(th!=null) {
                    th.interrupt();
                }
                settings();
            }
        });


        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("music/music.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
        }
        hp = new HomePanel(this);

        add(hp);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void setPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        getContentPane().revalidate();
    }

    public void gameover() {
        File f = new File("save.txt");
        f.delete();
        saveMenu.setVisible(false);
        if (score > best_score) best_score = score;
        score = 0;
        GameOverPanel gm;
        gm = new GameOverPanel(this);
        setPanel(gm);
    }

    public void home() {
        saveMenu.setVisible(false);
        if (score > best_score) best_score = score;
        score = 0;
        HomePanel hm;
        hm = new HomePanel(this);
        setPanel(hm);
    }

    public void restart() {
        File f = new File("save.txt");
        f.delete();
        saveMenu.setVisible(true);
        snake = new Snake();
        sv = new SnakeView(snake, null);
        sp = new SnakePanel(sv, this, snake);
        sc = new SnakeController(snake, sv, this, sp, speed, null);
        th = new Thread(sc);
        th.start();
        setPanel(sp);
        sp.requestFocus();
    }

    public void settings() {
        saveMenu.setVisible(false);
        GameSettings gs = new GameSettings(this);
        setPanel(gs);
    }

    public void load(Snake loadedSnake, Point apple){
        saveMenu.setVisible(true);
        snake = loadedSnake;
        score = loadedSnake.getScore();
        File f = new File("save.txt");
        f.delete();
        sv = new SnakeView(snake, apple);
        sp = new SnakePanel(sv, this, snake);
        sc = new SnakeController(snake, sv, this, sp, speed, apple);
        th = new Thread(sc);
        th.start();
        setPanel(sp);
        sp.requestFocus();
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBest_score() {
        return best_score;
    }

    public static void main(String[] args) {
        SnakeGame snakeGame = new SnakeGame();
    }
}
