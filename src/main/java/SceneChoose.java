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
    public static final int X_OF_WINDOW=0, Y_OF_WINDOW=0 , WIDTH_OF_WINDOW=700,HEIGHT_OF_WINDOW=600;



    public static void main(String[] args) {

    }

    public SceneChoose(String href, String nameOfLeague, SceneOfResult sceneOfResult) {
        whichLeague = nameOfLeague;
        this.setBounds(X_OF_WINDOW, Y_OF_WINDOW, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
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
        List<Element> listPointGroup;

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

            website = Jsoup.connect(href).get();
            listPointGroup = website.getElementsByClass("points");



            for (int i = 0; i < listPointGroup.size(); i++) {
                count++;
            }
            int finalCount = count;
            JButton[] button = createButton(finalCount);
             numberOfGroup = addListenerToButtons(button);



        } catch (IOException e) {
            e.printStackTrace();
        }



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

                ThreadTimer thread = new ThreadTimer(sceneOfResult);
                thread.start();


            });
        }
        return s.get();
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.background != null) {
            g.drawImage(this.background.getImage(), X_OF_WINDOW,Y_OF_WINDOW,WIDTH_OF_WINDOW,HEIGHT_OF_WINDOW, null);
        }
    }

}
