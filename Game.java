import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.border.Border;

import static java.lang.Thread.sleep;

public class Game extends JFrame implements KeyListener {

    static  boolean startObstacle = false;
    static  JLabel Police;
    static  JLabel Thief;
    static  JLabel escapeDoor;
    static  JLabel boundary,bg,obstacle,thiefLabel;//bgTemp;
    static  int hits=0;
    static  JProgressBar pb = new JProgressBar();
    static int code, width, height, code2;
    static int min, sec;
    static  boolean exit = false;
    static JLabel lblMin, lblSec, colen;
    static JLabel thiefWins;
    static JLabel policeWins; //For timer
    static JButton b;//For timer
    static boolean flag = true;
    static Timer timer = new Timer();
    static boolean timerStarted = false;
    static boolean GameOver =  false;
    static boolean ThiefWins=false;
    static boolean PoliceWins=false;
    static int health;
    static public JFrame f = new JFrame();

    @Override
    public void keyPressed(KeyEvent e) {

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        // width will store the width of the screen
        width = (int) size.getWidth();
        // height will store the height of the screen
        height = (int) size.getHeight();


        if (e.getKeyCode()==KeyEvent.VK_SPACE){
            timerStarted = true;

            startTimer();


        }
        if (e.getKeyCode() >= 37 && e.getKeyCode() <= 40 && timerStarted) {
            code = e.getKeyCode();
            checkEscapeCollision(Thief, escapeDoor);
            //checkCollision(Police, Thief);

        }
        if ((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S) && timerStarted) {
            code2 = e.getKeyCode();
            checkEscapeCollision(Thief, escapeDoor);
            //checkCollision(Police, Thief);
        }
    }

    private void startTimer()  {
        startObstacle = true;

        timer.schedule(task, 100000);



    }

    Game() {

        f = new JFrame("2D GAME");
        Border yellowness = BorderFactory.createLineBorder(Color.yellow); // for border
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        // width will store the width of the screen
        int width = (int) size.getWidth();
        // height will store the height of the screen
        int height = (int) size.getHeight();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(width, height);
        f.setLayout(null);
        f.addKeyListener(this);
        Police = new JLabel(); // char 1
        Thief = new JLabel(); // char 2
        escapeDoor = new JLabel(); // escape door
        obstacle = new JLabel(); //obstacles
        thiefLabel = new JLabel();

        //bgTemp = new JLabel();
        bg= new JLabel();

        Thief.setBounds(10, 10,105,105); // places in top left corner
        //For timer-----------------------------------------------
        lblMin =new JLabel("00");
        lblMin.setForeground(Color.yellow);
        lblSec=new JLabel("Level 1");
        lblSec.setForeground(Color.red);
        colen=new JLabel(":");
        colen.setForeground(Color.yellow);
        thiefWins=new JLabel();
        policeWins=new JLabel();
        b=new JButton();
        pb = new JProgressBar();

        Police.setBounds(width - 120, height - 250, 144,144); // places in bottom right corner
        boundary = new JLabel();
        boundary.setBounds(5, 1, width - 5, height - 64); // creates a border
        Police.setIcon(new ImageIcon("static/PoliceLeft.png"));
        boundary.setBorder(yellowness); // yellow color to the border
        //setLayout(new BorderLayout());

        //escapeDoor.setBackground(Color.blue);
        Thief.setIcon(new ImageIcon("static/Thief.png"));

        escapeDoor.setIcon(new ImageIcon("static/EscapeDoor.png"));
        //escapeDoor.setOpaque(true);
        ImageIcon imageIcon = new ImageIcon("static/bg1.jpeg");
        Image img = imageIcon.getImage(); // transform it
        Image bgTemp = img.getScaledInstance(width,height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way




        obstacle.setIcon(new ImageIcon("static/Obstacle5gif.gif"));
        bg.setIcon(new ImageIcon(bgTemp));
        bg.setLocation(0,0);
        bg.setSize(width,height);
        bg.setLayout(null);
        //bg.getContentPane().setBackground(Color.black);
        //f.add(bg);
        bg.add(boundary);
        bg.add(Thief);
        bg.add(obstacle);
        bg.add(Police);
        bg.add(escapeDoor);


        // for timer
//       lblMin.setBounds(width-150,20,100,100);
//       colen.setBounds(width-130,20,100,100);
        lblSec.setBounds(width-110,20,100,100);
        //b.setBounds(300,150,100,100);
        bg.add(lblMin);
        bg.add(lblSec);
        bg.add(colen);
        pb.setValue(100);

        pb.setBounds(width-150,2,145,20);
        thiefLabel.setText("Thief HP");
        thiefLabel.setForeground(Color.yellow);
        thiefLabel.setBounds(width-200,2,145,20);
        pb.setBackground(Color.BLACK);
        pb.setForeground(Color.red);
        bg.add(pb);
        bg.add(thiefLabel);
        f.add(bg);
        f.setVisible(true);

        // add(bg);




    }
    public void GameOver(){
        f.dispose();
        f.setVisible(false);
        //System.exit(0);
        Gamelevel2 g = new Gamelevel2();

    }

    // Will check the collision between both the characters
    void checkCollision(JLabel a, JLabel b) {
        Rectangle rectA = a.getBounds();
        Rectangle rectB = b.getBounds();
        if(!GameOver){

            if (rectA.intersects(rectB)) {
                GameOver =true;
                PoliceWins = true;
                GameOver();
                System.out.println("GAME OVER.Police WINS!");
            } else {
                moveChar();
                moveChar2();
            }
        }
    }
    void checkEscapeCollision(JLabel a, JLabel b) {
        checkObstacleCollision();
        Rectangle rectA = a.getBounds();
        Rectangle rectB = b.getBounds();
        if(!GameOver){

            if (rectA.intersects(rectB)) {
                GameOver =true;
                ThiefWins=true;
                GameOver();
                System.out.println("GAME OVER.Thief WINS!");
            } else {
                checkCollision(Police, Thief);
            }
        }
    }
    void checkObstacleCollision(){
        if(!GameOver){
            Rectangle rectA = obstacle.getBounds();
            Rectangle rectB = Thief.getBounds();
            if (rectA.intersects(rectB)) {
                health = pb.getValue();
                health = health-10;
                pb.setValue(health);
                obstacle.setLocation(width+100,height+100);
                if(health==0){
                    PoliceWins=true;
                    GameOver =true;
                    GameOver();
                    System.out.println("GAME OVER.Police WINS!");
                }
            }

        }
    }
    //function for up action

    void UpAction(JLabel a) {
        if(a == Thief ){

            a.setIcon(new ImageIcon("static/ThiefBack.png"));
            a.setLocation(a.getX(), a.getY() - 20); // up
        }
        else if(a== Police ){
            a.setIcon(new ImageIcon("static/PoliceBack.png"));
            a.setLocation(a.getX(), a.getY() - 20); // up
        }
    }
    //function for down action

    void DownAction(JLabel a) {
        if(a == Thief ){

            a.setIcon(new ImageIcon("static/ThiefFront.png"));
            a.setLocation(a.getX(), a.getY() + 20); // down
        }
        else if(a== Police ){
            a.setIcon(new ImageIcon("static/Police.png"));
            a.setLocation(a.getX(), a.getY() + 20); // down
        }
    }
    //function for right action

    void RightAction(JLabel a) {
        if(a == Thief ){

            a.setIcon(new ImageIcon("static/ThiefRight.png"));
            a.setLocation(a.getX() + 20, a.getY()); // right
        }
        else if(a== Police ){
            a.setIcon(new ImageIcon("static/PoliceRight.png"));
            a.setLocation(a.getX() + 20, a.getY()); // right
        }
    }
    //function for left action

    void LeftAction(JLabel a) {
        if(a == Thief){

            a.setIcon(new ImageIcon("static/ThiefLeft.png"));
            a.setLocation(a.getX() - 20, a.getY()); // left
        }
        else if(a== Police){
            a.setIcon(new ImageIcon("static/PoliceLeft.png"));
            a.setLocation(a.getX() - 20, a.getY()); // left
        }
    }
    // function will first check the corners and then  move the first element

    void moveChar() {
        if ((Thief.getX() <= 10)) {
            if (Thief.getY() <= 10)// the player will be at top left corner
            {
                System.out.println("Police 2 reached top left corner" + code2);
                if (code2 == KeyEvent.VK_S) {
                    DownAction(Thief); // will call down action and move the element down
                }
                if (code2 == KeyEvent.VK_D) {
                    RightAction(Thief); // will call right action and move the element to right

                }


            } else if (Thief.getY() >= height - 120)// the player will be at bottom left corner
            {
                System.out.println("Police 2 reached bottom left corner");
                if (code2 == KeyEvent.VK_D) {
                    RightAction(Thief); // will call right action and move the element to right

                }
                if (code2 == KeyEvent.VK_W) {
                    UpAction(Thief); // will call up action and move the element above

                }
            } else {
                if (code2 == KeyEvent.VK_D) {
                    RightAction(Thief); // will call right action and move the element to right

                }
                if (code2 == KeyEvent.VK_W) {
                    UpAction(Thief); // will call up action and move the element above

                }
                if (code2 == KeyEvent.VK_S) {
                    DownAction(Thief); // will call down action and move the element down
                }
            }
        } else if (Thief.getY() <= 10) {

            if (Thief.getX() >= width - 55) {
                if (code2 == KeyEvent.VK_S) {
                    DownAction(Thief); // will call down action and move the element down
                }
                if (code2 == KeyEvent.VK_A) {
                    LeftAction(Thief); // will call left action and move the element towards left
                }

            } else {
                if (code2 == KeyEvent.VK_S) {
                    DownAction(Thief); // will call down action and move the element down
                }
                if (code2 == KeyEvent.VK_D) {
                    RightAction(Thief); // will call right action and move the element to right

                }
                if (code2 == KeyEvent.VK_A) {
                    LeftAction(Thief); // will call left action and move the element towards left
                }
            }
        } else if (Thief.getX() >= width - 55) {

            if (Thief.getY() >= height - 120) // the element will be in bottom right corner
            {
                if (code2 == KeyEvent.VK_A) {
                    LeftAction(Thief); // will call left action and move the element towards left
                }
                if (code2 == KeyEvent.VK_W) {
                    UpAction(Thief); // will call up action and move the element above

                }
            } else {
                if (code2 == KeyEvent.VK_A) {
                    LeftAction(Thief); // will call left action and move the element towards left
                }
                if (code2 == KeyEvent.VK_W) {
                    UpAction(Thief); // will call up action and move the element above

                }
                if (code2 == KeyEvent.VK_S) {
                    DownAction(Thief); // will call down action and move the element down
                }

            }
        } else if (Thief.getY() >= height - 120) {
            //System.out.println("cWALLED");
            if (code2 == KeyEvent.VK_W) {
                UpAction(Thief); // will call up action and move the element above

            }
            if (code2 == KeyEvent.VK_D) {
                RightAction(Thief); // will call right action and move the element to right

            }
            if (code2 == KeyEvent.VK_A) {
                LeftAction(Thief); // will call left action and move the element towards left
            }
        } else {

            if (code2 == KeyEvent.VK_W) {
                UpAction(Thief); // will call up action and move the element above

            }
            if (code2 == KeyEvent.VK_D) {
                RightAction(Thief); // will call right action and move the element to right

            }
            if (code2 == KeyEvent.VK_A) {
                LeftAction(Thief); // will call left action and move the element towards left
            }
            if (code2 == KeyEvent.VK_S) {
                DownAction(Thief); // will call down action and move the element down
            }
        }


    }
    // function will first check the corners and then  move the second element

    void moveChar2() {
        if ((Police.getX() <= 10)) {
            if (Police.getY() <= 10)// the player will be at top left corner
            {
                if (code == 40) {
                    DownAction(Police); // will call down action and move the element down
                }
                if (code == 39) {
                    RightAction(Police); // will call right action and move the element to right

                }


            } else if (Police.getY() >= height - 120)// the player will be at bottom left corner
            {
                if (code == 39) {
                    RightAction(Police); // will call right action and move the element to right

                }
                if (code == 38) {
                    UpAction(Police); // will call up action and move the element above

                }
            } else {
                if (code == 39) {
                    RightAction(Police); // will call right action and move the element to right

                }
                if (code == 38) {
                    UpAction(Police); // will call up action and move the element above

                }
                if (code == 40) {
                    DownAction(Police); // will call down action and move the element down
                }
            }
        } else if (Police.getY() <= 10) {

            if (Police.getX() >= width - 55) {
                if (code == 40) {
                    DownAction(Police); // will call down action and move the element down
                }
                if (code == 37) {
                    LeftAction(Police); // will call left action and move the element towards left
                }

            } else {
                if (code == 40) {
                    DownAction(Police); // will call down action and move the element down
                }
                if (code == 39) {
                    RightAction(Police); // will call right action and move the element to right

                }
                if (code == 37) {
                    LeftAction(Police); // will call left action and move the element towards left
                }
            }
        } else if (Police.getX() >= width - 55) {

            if (Police.getY() >= height - 120) // the element will be in bottom right corner
            {
                if (code == 37) {
                    LeftAction(Police); // will call left action and move the element towards left
                }
                if (code == 38) {
                    UpAction(Police); // will call up action and move the element above

                }
            } else {
                if (code == 37) {
                    LeftAction(Police); // will call left action and move the element towards left
                }
                if (code == 38) {
                    UpAction(Police); // will call up action and move the element above

                }
                if (code == 40) {
                    DownAction(Police); // will call down action and move the element down
                }

            }
        } else if (Police.getY() >= height - 120) {
            //System.out.println("cWALLED");
            if (code == 38) {
                UpAction(Police); // will call up action and move the element above

            }
            if (code == 39) {
                RightAction(Police); // will call right action and move the element to right

            }
            if (code == 37) {
                LeftAction(Police); // will call left action and move the element towards left
            }
        } else {


            if (code == 37) {
                LeftAction(Police); // will call left action and move the element towards left
            }

            if (code == 38) {
                UpAction(Police); // will call up action and move the element above
            }
            if (code == 39) {
                RightAction(Police); // will call right action and move the element to right
            }
            if (code == 40) {
                DownAction(Police); // will call down action and move the element down
            }


        }

    }


    @Override
    public void keyReleased(KeyEvent e) {
        if ((e.getKeyCode() >= 37 && e.getKeyCode() <= 40)) {
            code = 0;

        } else if ((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S)) {
            code2 = 0;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    TimerTask task = new TimerTask() {

        @Override
        public void run() {

            while (!exit)
            {
                if (sec == 0) {
                    sec = 60;
                    min--;
                }

                if (sec <= 10) {
                    lblMin.setForeground(Color.red);
                    lblSec.setForeground(Color.red);
                }

                if (min < 0) {

                    //JOptionPane.showMessageDialog(rootPane, "Times Up", "Stopped", JOptionPane.ERROR_MESSAGE);// Displays timed out message at the end
                    min = 0;
                    sec = 0;
                    exit =true;
                }
                else {
                    System.out.println("He's here");
                    sec--;
                    if (sec < 10) {
                        lblSec.setText("0" + sec);
                        flag = false;
                    }
                    if (min < 10) {
                        lblMin.setText("0" + min);
                        System.out.println(lblMin.getText());
                        if (sec < 10) {
                            lblSec.setText("0" + sec);
                            System.out.println(lblSec.getText());
                        }
                        else
                            lblSec.setText("" + sec);
                        flag = false;
                    }
                    if (flag) {
                        lblMin.setText("" + min);
                        lblSec.setText("" + sec);
                    }

                }


            }
            if(!GameOver) {
                escapeDoor();
            }
        }



    };
    public void escapeDoor() {
        System.out.println("I am called" +width + " " + height +" "+Thief.getX()+" "+Thief.getY());
        int x;
        int y;
        int min1 =5;
        int max1= height - 120;
        int min2 = 5;
        int max2 =width-150;

        while (true) {
            Random rd = new Random(); // creating Random object
            int rand = rd.nextInt(max1 + 1 - min1) + min1;
            if (  !(rand == Thief.getY()) && rand > Thief.getY() + 70 && rand < Thief.getY() - 70) {
                y = rand;
                System.out.println("I am selected" + 3 + " " + y);
                break;
            }
            else if (  !(rand == Thief.getY()) &&  rand < Thief.getY() - 70) {
                y = rand;
                System.out.println("I am selected" + 4 + " " + y);
                break;
            }


        }
        while (true) {
            Random rd = new Random(); // creating Random object
            int rand = rd.nextInt(max2 + 1 - min2) + min2;
            if (  !(rand == Thief.getX()) && rand > Thief.getX() + 70 ) {
                x =rand;
                System.out.println("I am selected" + 1 + " " + x);
                break;

            }
            else if(!(rand == Thief.getX()) && rand < Thief.getX() - 70){
                x =rand;
                System.out.println("I am selected" + 2 + " " + x);
                break;
            }

            System.out.println("I am selected " + y);

        }
        escapeDoor.setBounds(x, y, 192,191);
        //System.out.println(rd.nextInt());
    }

    public static void main(String[] args) {

    }
}



