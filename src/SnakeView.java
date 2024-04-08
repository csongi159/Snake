import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SnakeView extends JPanel {
    private Snake snake;
    public Point apple;


    public SnakeView(Snake snake, Point apple) {
        this.snake = snake;
        this.apple = apple;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Point> points = snake.getEndPoints();

        for (Point point : points) {
            g.setColor(Color.GREEN);
            g.fillOval(point.x, point.y, 10, 10);
        }
        g.setColor(Color.RED);
        g.fillOval(apple.x, apple.y, 10, 10);
        g.setColor(Color.YELLOW);
        g.fillOval(snake.getHead().x, snake.getHead().y, 10, 10);
        setBackground(Color.darkGray);
    }
}
