import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class EnigmaExceptionHandler extends JFrame{
    public final int X=500;
    public final int Y=475;

    EnigmaExceptionHandler(String message){
        super("Error ucurred:");
        setSize(X, Y);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
        ImageIcon error=new ImageIcon("icons\\sadLlama.jpg");
        JButton llama=new JButton("Error", error);
        JTextField warn=new JTextField(message);
        add(llama, BorderLayout.CENTER);
        add(warn, BorderLayout.SOUTH);
    }
}
