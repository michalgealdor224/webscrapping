import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class SceneOfResult extends JPanel {




    private ImageIcon background;
    private JLabel label;
    private JLabel label2;
    String nameOfLeague;
    public static final int X_OF_WINDOW=0, Y_OF_WINDOW=0 , WIDTH_OF_WINDOW=700,HEIGHT_OF_WINDOW=600;
    public static final int X_OF_LABEL1=100, Y_OF_LABEL1=150 , WIDTH_OF_LABEL1=400,HEIGHT_OF_LABEL1=50;
    public static final int X_OF_LABEL2=100, Y_OF_LABEL2=200 , WIDTH_OF_LABEL2=400,HEIGHT_OF_LABEL2=50,SIZE_OF_LABEL =20;






    public static void main(String[] args) {


    }

    public void transitionToWindow(){
        OpenWindow open = new OpenWindow(this);
        this.add(open);
    }


    public SceneOfResult(String numberOfClick,String whichLeague) {

        nameOfLeague = whichLeague;
        String theGroup = null;
        String thePoint = null;
        this.setBounds(X_OF_WINDOW, Y_OF_WINDOW, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
        this.setFocusable(true);
        this.requestFocus();
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setVisible(true);

        Document website;
        String href = null;
        int count = 0;
        List<Element> listNameGroup;
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


            assert href != null;
            website = Jsoup.connect(href).get();
            listNameGroup = website.getElementsByClass("teamname");
            listPointGroup = website.getElementsByClass("points");
            for (int i = 0; i < listNameGroup.size(); i++) {
                System.out.println(listNameGroup.get(i).text());
            }
            thePoint = listPointGroup.get(Integer.parseInt(numberOfClick)).text();
            theGroup = listNameGroup.get(Integer.parseInt(numberOfClick)).text();


            website = Jsoup.connect(href).get();
            List<Element> elementList = website.getElementsByClass(numberOfClick);
            System.out.println(numberOfClick);
            System.out.println(elementList.get(Integer.parseInt(numberOfClick)).text());
            for (int i = 0; i < elementList.size(); i++) {
                count++;
            }



        } catch (Exception e) {

        }


        String finalTheGroup = theGroup;
        String finalThePoint = thePoint;

        this.background = new ImageIcon("background.jpg");
        this.label = new JLabel("THE GROUP IS:" + " " + finalTheGroup);
        this.label2 = new JLabel("SCORE:" + " " + finalThePoint);
        this.label.setBounds(X_OF_LABEL1, Y_OF_LABEL1, WIDTH_OF_LABEL1, HEIGHT_OF_LABEL1);
        this.label.setForeground(Color.green);
        this.label.setFont(new Font("TimesRoman", Font.PLAIN, SIZE_OF_LABEL));
        add(label);
        this.label2.setBounds(X_OF_LABEL2, Y_OF_LABEL2, WIDTH_OF_LABEL2, HEIGHT_OF_LABEL2);
        this.label2.setForeground(Color.green);
        this.label2.setFont(new Font("TimesRoman", Font.PLAIN, SIZE_OF_LABEL));
        add(label2);
        repaint();

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.background != null) {
            g.drawImage(this.background.getImage(),X_OF_WINDOW,Y_OF_WINDOW,WIDTH_OF_WINDOW,HEIGHT_OF_WINDOW, null);
        }
    }



}
