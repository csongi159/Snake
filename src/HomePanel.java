import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePanel extends JPanel {
    private JButton playButton;
    private JLabel snakeText;
    private JButton continueButton;

    public HomePanel(SnakeGame snakeGame) {
        setLayout(new BorderLayout());
        setBackground(Color.ORANGE);

        playButton = new JButton("Play");
        continueButton = new JButton("Continue");
        snakeText = new JLabel("Snake", SwingConstants.CENTER);
        snakeText.setFont(new Font("Snap ITC", Font.BOLD, 100));

        JPanel buttons = new JPanel();
        buttons.add(playButton);

        File f = new File("save.txt");
        if(f.exists() && !f.isDirectory()) {
            buttons.add(continueButton);
        }

        add(snakeText);
        add(buttons, BorderLayout.SOUTH);

        playButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                snakeGame.restart();

            }
        });

        continueButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Snake snake = new Snake();
                try (BufferedReader read = new BufferedReader(new FileReader(f))) {
                    String lines;

                    lines = read.readLine();
                    snake.setDirection(Integer.parseInt(lines));
                    lines = read.readLine();
                    snake.setScore(Integer.parseInt(lines));
                    lines = read.readLine();

                    String[] tokens;
                    tokens = lines.split(" ");
                    Point apple = new Point(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));

                    List<Point> endPoints = new ArrayList<Point>();
                    while ((lines=read.readLine()) != null) {
                        tokens = lines.split(" ");
                        endPoints.add(new Point(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
                    }
                    snake.setEndPoints(endPoints);
                    snakeGame.load(snake, apple);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}
