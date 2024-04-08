import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private int direction;
    private List<Point> endPoints;
    private int score;
    public Snake(){
        score = 0;
        direction = 3;
        endPoints = new ArrayList<Point>();
        endPoints.add(new Point(100,30));
        endPoints.add(new Point(105,30));
        endPoints.add(new Point(110,30));
        endPoints.add(new Point(115,30));
        endPoints.add(new Point(120,30));
        endPoints.add(new Point(125,30));
        endPoints.add(new Point(130,30));
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public List<Point> getEndPoints() {
        return endPoints;
    }

    public void setEndPoints(List<Point> endPoints) {
        this.endPoints = endPoints;
    }

    public void setHead(Point head) {
        endPoints.add(head);
    }

    public Point getHead() {
        return endPoints.get(endPoints.size()-1);
    }

    public void removeTail() {
        endPoints.remove(0);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
