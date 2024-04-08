import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameSettings extends JPanel {
    private JButton backButton;
    private JLabel speedText;
    private JCheckBox speed1,speed15, speed2;
    public GameSettings(SnakeGame snakeGame){
        setLayout(new GridLayout(3,1));
        setBackground(Color.ORANGE);

        backButton = new JButton("<- Back");
        backButton.setFont(new Font("arial", Font.BOLD, 10));

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                snakeGame.home();
            }
        });

        JLabel settingsText = new JLabel("Settings", SwingConstants.CENTER);
        settingsText.setFont(new Font("arial", Font.BOLD, 40));
        add(settingsText);

        JPanel speed = new JPanel();
        speed.setBackground(Color.ORANGE);
        speedText = new JLabel("Speed:   ");
        speed1 = new JCheckBox("1x");
        speed15 = new JCheckBox("1.5x");
        speed2 = new JCheckBox("2x");
        speed1.setBackground(Color.ORANGE);
        speed15.setBackground(Color.ORANGE);
        speed2.setBackground(Color.ORANGE);
        speed.add(speedText);
        speed.add(speed1);
        speed.add(speed15);
        speed.add(speed2);

        add(speed);
        add(backButton);

        if(snakeGame.getSpeed()==1){
            speed1.setSelected(true);
        }
        else if(snakeGame.getSpeed()==1.5){
            speed15.setSelected(true);
        }
        else if(snakeGame.getSpeed()==2){
            speed2.setSelected(true);
        }

        speed1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(speed1.isSelected()){
                    speed15.setSelected(false);
                    speed2.setSelected(false);
                    snakeGame.setSpeed(1);
                }
                else{
                    speed1.setSelected(true);
                }
            }
        });

        speed2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(speed2.isSelected()){
                    speed15.setSelected(false);
                    speed1.setSelected(false);
                    snakeGame.setSpeed(2);
                }
                else{
                    speed2.setSelected(true);
                }
            }
        });

        speed15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(speed15.isSelected()){
                    speed1.setSelected(false);
                    speed2.setSelected(false);
                    snakeGame.setSpeed(1.5);
                }
                else{
                    speed15.setSelected(true);
                }
            }
        });
    }
}
