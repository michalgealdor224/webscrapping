import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;

public class SceneOfResult extends JPanel {




    private ImageIcon background;
    private JLabel label;
    private JLabel label2;
    String nameOfLeague;



    public static void main(String[] args) {


    }


    public SceneOfResult(String numberOfClick,String whichLeague) {
        nameOfLeague = whichLeague;
        String theGroup = null;
        String thePoint = null;
        this.setBounds(0, 0, 700, 600);
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
        this.label.setBounds(100, 100, 400, 50);
        this.label.setForeground(Color.green);
        this.label.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        add(label);
        this.label2.setBounds(100, 200, 400, 50);
        this.label2.setForeground(Color.green);
        this.label2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        add(label2);
        repaint();



    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.background != null) {
            g.drawImage(this.background.getImage(),0,0,700,600, null);
        }
    }



}
