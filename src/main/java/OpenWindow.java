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
    private JButton holand;
    private JButton french;
    private JButton english;
    private SceneOfResult sceneOfResult;


    public static void main(String[] args) {


    }

    public OpenWindow(SceneOfResult sceneOfResult) {
        this.sceneOfResult = sceneOfResult;
        String league1 = findLeague("512");
        String league2 = findLeague("4909");
        String league3 = findLeague("29243");
        String league4 = findLeague("29242");
        String league5 = findLeague("1383");
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
            holand = createButton(league3, 250);
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
            remove(holand);
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
            Elements element = website.select("a[href]");
            ArrayList<String> hyperLinks = new ArrayList<String>();
            for (Element e : element) {
                hyperLinks.add("text" + e.text());
                // hyperLinks.add("link"+e.attr("href"));
            }
            for (String s : hyperLinks) {
             //   System.out.println(s);
            }
            List<Element> elementList1 = website.getAllElements();
            List<Element> elementList = website.getElementsByClass("one-navigation-right-item one-navigation-right-item-" + numOfLeague);
            for (int i = 0; i < elementList.size(); i++) {
                Element currentElement = elementList.get(i);
                text = currentElement.text();
            }
            for (int i = 0; i < elementList1.size(); i++) {
                //   System.out.println(elementList1.get(i).text());
            }

        } catch (Exception e) {

        }
        return text;

    }
}
