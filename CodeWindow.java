import javax.swing.*;

public class CodeWindow extends JFrame {
    public final int X=300;
    public final int Y=150;

    public CodeWindow(String code){
        super("Your code:");
        setSize(X, Y);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
        JTextArea cypher=new JTextArea(code);
        cypher.setBackground(new java.awt.Color(230,230,230));
        add(cypher);
    }
}
