import javax.swing.*;
import java.awt.*;

public class GameOver extends Gamelevel2{
    Sound sound = new Sound();
    GameOver(){
        f.dispose();
        playMusic(1);
        f=null;
        if(ThiefWins){
            JFrame f1 = new JFrame();
            JLabel thiefWins = new JLabel();
            JLabel policeWins = new JLabel(); //For timer
            f1.setVisible(true);
            ImageIcon imageIcon2 = new ImageIcon("static/ThiefWins.jpeg");
            Image img2 = imageIcon2.getImage(); // transform it
            Image bgTemp2 = img2.getScaledInstance(width,height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            thiefWins.setIcon(new ImageIcon(bgTemp2));
            f1.setSize(width,height);

            f1.add(thiefWins);
            thiefWins.setLocation(0,0);
//            f.add(thiefWins);
//            f.setVisible(true);
        }
        else if (PoliceWins){
            JFrame f1 = new JFrame();
            f1.setSize(width,height);
            JLabel thiefWins = new JLabel();
            JLabel policeWins = new JLabel(); //For timer
            f1.setVisible(true);
            ImageIcon imageIcon3 = new ImageIcon("static/PoliceWins.jpeg");
            Image img3 = imageIcon3.getImage(); // transform it
            Image bgTemp3= img3.getScaledInstance(width,height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            policeWins.setIcon(new ImageIcon(bgTemp3));
            policeWins.setLocation(0,0);
            f1.add(policeWins);

        }
    }
    public void playMusic(int i)
    {
        sound.setFile(i);
        sound.play(i);
        //sound.loop(i);
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
