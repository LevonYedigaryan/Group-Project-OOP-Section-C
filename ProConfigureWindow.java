import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProConfigureWindow extends JFrame implements ActionListener {
    private JTextField code=new JTextField("Enter your configuration");
    private String[] rotors;
    private String reflector;
    private String cypher;
    public final int X=300;
    public final int Y=150;

    public ProConfigureWindow(String[] rotors, String reflector, String cypher){
        super("Frontal Panel");
        this.rotors=rotors;
        this.reflector=reflector;
        this.cypher=cypher;
        setSize(X, Y);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
        code.setBackground(new java.awt.Color(230,230,230));
        JButton button=new JButton("Enter");
        button.addActionListener(this);
        button.setBackground(new java.awt.Color(220,220,220));
        add(button, BorderLayout.SOUTH);
        add(code, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent a){
        if(a.getActionCommand().equals("Enter")){
            try{
                new CodeWindow(new EnigmaPro(rotors,reflector,code.getText()).encode(cypher));
                setVisible(false);
            }
            catch(Exception e){
                new EnigmaExceptionHandler(e.getMessage());
                setVisible(false);
            }
        }
    }
}