import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OpenWindow extends JPanel {
    private ImageIcon background;
    private JButton spanish;
    private JButton italian;
    private JButton netherlands;
    private JButton french;
    private JButton english;
    private SceneOfResult sceneOfResult;


    public static void main(String[] args) {


    }

    public OpenWindow(SceneOfResult sceneOfResult) {
        this.sceneOfResult = sceneOfResult;
        String league1 = findLeague("31");
        String league2 = findLeague("33");
        String league3 = findLeague("49");
        String league4 = findLeague("45");
        String league5 = findLeague("41");
        this.setBounds(0, 0, 700, 600);
        this.setFocusable(true);
        this.requestFocus();
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setVisible(true);

        new Thread(() -> {
            this.background = new ImageIcon("background.jpg");
            spanish = createButton(league1, 50);
            italian = createButton(league2, 150);
            netherlands = createButton(league3, 250);
            french = createButton(league4, 350);
            english = createButton(league5, 450);

            repaint();

        }).start();

    }

    public JButton createButton(String name, int y) {
        JButton button = new JButton(name);
        button.setBounds(100, y, 150, 50);
        button.addActionListener((event) -> {
            remove(spanish);
            remove(italian);
            remove(netherlands);
            remove(french);
            remove(english);
            repaint();
            System.out.println(button.getText());
            SceneChoose sceneChoose = new SceneChoose("A", button.getText(), sceneOfResult);
            //   SecondScene secondScene = new SecondScene(button.getText());
            //  add(secondScene);
            add(sceneChoose);
        });
        this.add(button);
        return button;
    }



    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.background != null) {
            g.drawImage(this.background.getImage(), 0, 0, 700, 600, null);
        }
    }


    public static String findLeague(String numOfLeague) {
        String text = "";
        try {
            Document website = Jsoup.connect("https://www.one.co.il/").get();

            List<Element> elementList1 = website.getAllElements();
            List<Element> elementList = website.getElementsByClass("one-navigation-right-container");
            for (int i = 0; i < elementList.size(); i++) {
                Element currentElement = elementList.get(i);
                System.out.println(elementList.get(0).child(Integer.parseInt(numOfLeague)).text());
                // System.out.println(elementList.get(0).child(Integer.parseInt(numOfLeague)).text());

                text = elementList.get(0).child(Integer.parseInt(numOfLeague)).text();
            }
            for (int i = 0; i < elementList1.size(); i++) {
                //   System.out.println(elementList1.get(i).text());
            }

        } catch (Exception e) {

        }
        return text;

    }
}
