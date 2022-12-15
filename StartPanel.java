
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;



public class StartPanel implements ActionListener{
    Sound sound = new Sound(); 
    JFrame f2 = new JFrame("START GAME");
    @Override
    public void actionPerformed(ActionEvent e) {
        f2.dispose();
        Game game = new Game();
        playMusic(0);

    }
    StartPanel() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        // width will store the width of the screen
        int width = (int) size.getWidth();
        // height will store the height of the screen
        int height = (int) size.getHeight();
        JLabel bg1 = new JLabel();
        //JFrame f2 = new JFrame("START GAME");
        ImageIcon imageIcon2 = new ImageIcon("static/UI_Start.png");
        Image img2 = imageIcon2.getImage(); // transform it
        Image bgTemp2 = img2.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        bg1.setIcon(new ImageIcon(bgTemp2));
        ImageIcon icon = new ImageIcon("static/StartGame.jpeg");
        f2.setSize(width, height);
        f2.setVisible(true);
        f2.add(bg1);
        JButton b1 = new JButton(icon);
        b1.addActionListener(this);
        b1.setBounds((width/2)-230,100,500,98);
        b1.setOpaque(false);
        b1.setContentAreaFilled(false);
        b1.setBorderPainted(false);
        b1.setFocusPainted(false);
        bg1.add(b1);
        f2.add(bg1);
        //b1.setBounds;
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setLayout(null);

    }

    public void playMusic(int i)
    {
        sound.setFile(i);
        sound.play(i);
        sound.loop(i);
    }

    public void stopMusic(int i) 
    {
        sound.stop(i);
    }

    public void PlaySE(int i) 
    {
        sound.setFile(i);
        sound.play(i);
    }
}

