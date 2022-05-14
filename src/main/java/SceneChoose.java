import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class SceneChoose extends JPanel {
    private ImageIcon background;
    private SceneOfResult sceneOfResult;
    String whichLeague;
    String numberOfGroup;


    public static void main(String[] args) {

    }

    public SceneChoose(String href, String nameOfLeague, SceneOfResult sceneOfResult) {
        whichLeague = nameOfLeague;
        this.setBounds(0, 0, 700, 600);
        this.sceneOfResult = sceneOfResult;
        this.setFocusable(true);
        this.requestFocus();
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setVisible(true);

        new Thread(() -> {
            this.background = new ImageIcon("background.jpg");
            repaint();

        }).start();
        int count = -1;

        Document website;
        List<Element> listNameGroup;
        List<Element> listPointGroup;

        System.out.println(nameOfLeague);
        try {
            if (Objects.equals(nameOfLeague, "ליגה הולנדית")) {
                href = "https://www.one.co.il/Soccer/League/680";
            }
            if (Objects.equals(nameOfLeague, "ליגה ספרדית")) {
                href = "https://www.one.co.il/Soccer/League/10";
            }
            if (Objects.equals(nameOfLeague, "ליגה איטלקית")) {
                href = "https://www.one.co.il/Soccer/League/8";
            }
            if (Objects.equals(nameOfLeague, "ליגה צרפתית")) {
                href = "https://www.one.co.il/Soccer/League/6";
            }
            if (Objects.equals(nameOfLeague, "ליגה אנגלית")) {
                href = "https://www.one.co.il/Soccer/League/5";
            }
            System.out.println(nameOfLeague);

            website = Jsoup.connect(href).get();
            listNameGroup = website.getElementsByClass("teamname");
            listPointGroup = website.getElementsByClass("points");
           // System.out.println(listPointGroup.get(2).text());
            //System.out.println(listNameGroup.get(2).text());


            for (int i = 0; i < listPointGroup.size(); i++) {
                Element currentElement = listPointGroup.get(i);
                count++;
                String text = currentElement.text();
            }
            int finalCount = count;
            JButton[] button = createButton(finalCount);
             numberOfGroup = addListenerToButtons(button);



        } catch (IOException e) {
            e.printStackTrace();
        }
        findGroup(numberOfGroup,nameOfLeague);



    }

    public JButton[] createButton(int num) {
        JButton button;
        JButton[] arr = new JButton[num];
        int x = 20, y = 10, width = 50, height = 50, number = 1;
        for (int i = 0; i < num / 2; i++) {
            button = new JButton(String.valueOf(i + 1));
            arr[i] = button;
            button.setBounds(x, y, width, height);
            add(button);
            y += 50;
            number++;
        }
        x += 100;
        y = 10;
        for (int i = num / 2; i < num; i++) {
            button = new JButton(String.valueOf(number));
            arr[i] = button;
            button.setBounds(x, y, width, height);
            add(button);
            y += 50;
            number++;
        }
        return arr;
    }
    public int findGroup(String numberOfClick,String nameOfLeague) {
        Document website;
        String href = null;
        int count=0;
        List<Element> listNameGroup;
        List<Element> listPointGroup;
        System.out.println(numberOfClick);
        System.out.println( nameOfLeague);


        return count;

    }


    public String addListenerToButtons(JButton[] arr) {
        int i;
        AtomicReference<String> s = new AtomicReference<>(" ");
        JButton button;
        for (i = 0; i < arr.length; i++) {
            button = arr[i];
            JButton finalButton = button;
            button.addActionListener((event) -> {

                s.set(finalButton.getText());
                for (int j = 0; j < arr.length; j++) {
                    remove(arr[j]);
                }

                SceneOfResult sceneOfResult = new SceneOfResult(String.valueOf(s),whichLeague);
                add(sceneOfResult);

            });
        }
        return s.get();
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.background != null) {
            g.drawImage(this.background.getImage(), 0, 0, 700, 600, null);
        }
    }

}
