import java.util.Random;

import static java.lang.Thread.sleep;

public class Main extends Game{
    static class ObstacleThread implements Runnable {
        public void run() {

            while (true) {

                if (!exit && timerStarted && !GameOver) {
                    System.out.println("USED");
                    int x;
                    int y;
                    int min1 = 5;
                    int max1 = height - 120;
                    int min2 = 5;
                    int max2 = width - 150;

                    while (true) {
                        Random rd = new Random(); // creating Random object
                        int rand = rd.nextInt(max1 + 1 - min1) + min1;
                        if (!(rand == Thief.getY()) && rand < Thief.getY() + 70 && rand > Thief.getY() - 70) {
                            y = rand;
                            System.out.println("I am selected" + 3 + " " + y);
                            break;
                        } else if (!(rand == Thief.getY()) && rand < Thief.getY() - 70) {
                            y = rand;
                            System.out.println("I am Selected" + 4 + " " + y);
                            break;
                        }


                    }
                    while (true) {
                        Random rd = new Random(); // creating Random object
                        int rand = rd.nextInt(max2 + 1 - min2) + min2;
                        if (!(rand == Thief.getX()) && rand > Thief.getX() + 70) {
                            x = rand;
                            System.out.println("I am selected" + 1 + " " + x);
                            break;

                        } else if (!(rand == Thief.getX()) && rand < Thief.getX() - 70) {
                            x = rand;
                            System.out.println("I am selected" + 2 + " " + x);
                            break;
                        }

                        System.out.println("I am selected " + y);

                    }
                    obstacle.setBounds(x, y, 192, 192);
                }
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public static void main(String[] args)  {
        Sound sound = new Sound();
        StartPanel sp = new StartPanel();
        ObstacleThread t1 = new ObstacleThread();
        t1.run();

    }
}