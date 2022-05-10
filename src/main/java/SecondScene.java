import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class SecondScene extends JPanel {
    private ImageIcon background;
    private JTextField textField;
    private JLabel enterNumber;
    private JLabel clickEnter;
    private JComboBox comboBox;
    private JFrame combobox;



    public static void main(String[] args) {

    }

    public SecondScene(String button) {
        this.setBounds(0,0,700,600);
        this.setFocusable(true);
        this.requestFocus();
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setVisible(true);
        AtomicReference<String> chooseOfUser= new AtomicReference<>("0");

            textField = new JTextField("",20);




        System.out.println(chooseOfUser.get());
        new Thread(() -> {
            this.background = new ImageIcon("background.jpg");
            this.enterNumber = new JLabel("enter number"+ " "+ "\n"+"between 1-20:");
            this.enterNumber.setBounds(100,200,400,50);
            this.enterNumber.setForeground(Color.green);
            this.enterNumber.setFont(new Font("TimesRoman",Font.PLAIN,20));
            this.clickEnter = new JLabel("click enter");
            this.clickEnter.setBounds(100,300,400,50);
            this.clickEnter.setForeground(Color.green);
            this.clickEnter.setFont(new Font("TimesRoman",Font.PLAIN,20));
                this.textField.setBounds(100, 250, 50, 50);
                this.add(textField);
            this.add(clickEnter);
            this.add(enterNumber);



            textField.addActionListener((event)-> {
                System.out.println(textField.getText());
                String number = textField.getText();
                remove(textField);
                remove(clickEnter);
                remove(enterNumber);
                System.out.println(button);
                SceneChoose sceneChoose = new SceneChoose(number,button, null);
                add(sceneChoose);
            });

            repaint();
            this.add(clickEnter);
            this.add(enterNumber);
            this.add(textField);

        }).start();

    }
    public String whichNumber () {
        return textField.getText();
    }
    public boolean ifHave (String[] arr,String x) {
        boolean flag =false;
        for (int i=0;i<arr.length; i++) {
            if (arr[i] == x) {
                flag=true;
            }
        }
        return flag;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.background != null) {
            g.drawImage(this.background.getImage(),0,0,700,600, null);
        }
    }
}
