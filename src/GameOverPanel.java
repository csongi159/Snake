import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameOverPanel extends JPanel {
    private JButton restart;
    private JButton homeButton;
    private JLabel gameOver;

    public GameOverPanel(SnakeGame snakeGame) {
        setLayout(new BorderLayout());
        setBackground(Color.ORANGE);

        homeButton = new JButton("Home");
        restart = new JButton("Restart");
        gameOver = new JLabel("GAME OVER", SwingConstants.CENTER);
        gameOver.setFont(new Font("arial", Font.BOLD, 40));

        JPanel buttons = new JPanel();
        buttons.add(homeButton);
        buttons.add(restart);

        add(gameOver);
        add(buttons, BorderLayout.SOUTH);

        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                snakeGame.restart();

            }
        });

        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                snakeGame.home();

            }
        });
    }

}
