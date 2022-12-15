import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
 
public class Sound 
{
     Clip clip;
     URL soundUrl[] = new URL[30]; // to store the path of sound file

     public Sound()
     {
        soundUrl[0] = getClass().getResource("Sounds/ThemeSong.wav");
        soundUrl[1] = getClass().getResource("Sounds/victory.wav");
     }

    //  public void s1()
    //  {
    //     soundUrl[1] = getClass().getResource("Sounds/victory.wav"); 
    //  }

     // format 
     public void setFile(int i) 
     {
         try 
         {
           AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);   
           clip = AudioSystem.getClip();
           clip.open(ais);
         } 
         catch (Exception e) 
         {
            e.printStackTrace();
         }
     }

     public void play(int i) 
     {
         clip.start();
     }

     public void loop(int i) 
     {
         clip.loop(Clip.LOOP_CONTINUOUSLY);
     }

     public void stop(int i) 
     {
         clip.stop();
     }

}
