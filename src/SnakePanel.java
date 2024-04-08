import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakePanel extends JPanel {
    private SnakeGame snakeGame;
    private JLabel score;
    private JLabel best_score;
    public SnakePanel(SnakeView snakeView, SnakeGame snakeGame, Snake snake){

        this.snakeGame=snakeGame;
        setLayout(new BorderLayout());

        score = new JLabel("Score: " + snakeGame.getScore(), SwingConstants.LEFT);
        best_score = new JLabel("Best score: " + snakeGame.getBest_score());
        JPanel scores = new JPanel();
        scores.add(score);
        scores.add(best_score);

        add(snakeView);
        add(scores, BorderLayout.SOUTH);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.getDirection() != 3) {
                    snake.setDirection(1);
                } else if (e.getKeyCode() == KeyEvent.VK_UP && snake.getDirection() != 4) {
                    snake.setDirection(2);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getDirection() != 1) {
                    snake.setDirection(3);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.getDirection() != 2) {
                    snake.setDirection(4);
                }
            }

        });
    }
    public void update_score(){
        score.setText("Score: " + snakeGame.getScore());
    }
}
