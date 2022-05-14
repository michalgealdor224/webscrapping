import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame  {




    public static void main(String[] args) {

        new Main();

    }
    public Main () {
        this.setSize(700, 600);
        this.setResizable(false);
        this.setBackground(Color.cyan);

        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        SceneOfResult sceneOfResult = new SceneOfResult(null,null);
        OpenWindow openWindow = new OpenWindow(sceneOfResult);
      //  SecondScene secondScene = new SecondScene();
        //  this.add(secondScene);
        //   SceneChoose sceneChoose = new SceneChoose("2","ליגה ספרדית");
       // add(sceneChoose);
      //  add(sceneOfResult);

       this.add(openWindow);

    }


}
