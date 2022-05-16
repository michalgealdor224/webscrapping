import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OpenWindow extends JPanel {
    private ImageIcon background;
    private JButton spanish;
    private JButton italian;
    private JButton netherlands;
    private JButton french;
    private JButton english;
    private SceneOfResult sceneOfResult;
    public static final int X_OF_WINDOW=0, Y_OF_WINDOW=0 , WIDTH_OF_WINDOW=700,HEIGHT_OF_WINDOW=600;
    public static final int LOCATION_OF_SPANISH=50;
    public static final int LOCATION_OF_ITALIAN=150;
    public static final int LOCATION_OF_NETHERLANDS=250;
    public static final int LOCATION_OF_FRENCH=350;
    public static final int LOCATION_OF_ENGLISH=450;
    public static final int X_OF_BUTTON =100,WIDTH_OF_BUTTON= 150,HEIGHT_OF_BUTTON= 50;




    public static void main(String[] args) {



    }

    public OpenWindow(SceneOfResult sceneOfResult) {
        this.sceneOfResult = sceneOfResult;
        String league1 = findLeague("31");
        String league2 = findLeague("33");
        String league3 = findLeague("49");
        String league4 = findLeague("45");
        String league5 = findLeague("41");
        this.setBounds(X_OF_WINDOW, Y_OF_WINDOW, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
        this.setFocusable(true);
        this.requestFocus();
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setVisible(true);

        new Thread(() -> {
            this.background = new ImageIcon("background.jpg");
            spanish = createButton(league1, LOCATION_OF_SPANISH);
            italian = createButton(league2, LOCATION_OF_ITALIAN);
            netherlands = createButton(league3, LOCATION_OF_NETHERLANDS);
            french = createButton(league4, LOCATION_OF_FRENCH);
            english = createButton(league5, LOCATION_OF_ENGLISH);

            repaint();

        }).start();

    }

    public JButton createButton(String name, int y) {
        JButton button = new JButton(name);
        button.setBounds(X_OF_BUTTON, y, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        button.addActionListener((event) -> {
            remove(spanish);
            remove(italian);
            remove(netherlands);
            remove(french);
            remove(english);
            repaint();
            SceneChoose sceneChoose = new SceneChoose("A", button.getText(), sceneOfResult);
            add(sceneChoose);
        });
        this.add(button);
        return button;
    }



    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.background != null) {
            g.drawImage(this.background.getImage(), X_OF_WINDOW, Y_OF_WINDOW, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW, null);
        }
    }


    public static String findLeague(String numOfLeague) {
        String text = "";
        try {
            Document website = Jsoup.connect("https://www.one.co.il/").get();

            List<Element> elementList = website.getElementsByClass("one-navigation-right-container");
            for (int i = 0; i < elementList.size(); i++) {
                text = elementList.get(i).child(Integer.parseInt(numOfLeague)).text();
            }


        } catch (Exception e) {

        }
        return text;

    }
}
