import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class ProMaxEncodeWindow extends JFrame implements ActionListener {
    private JTextField configuration=new JTextField("Enter your configuration");
    private JTextField rotorNumber=new JTextField("Enter the number of working rotors for each step");
    private String[] rotors;
    private String reflector;
    public final int X=600;
    public final int Y=300;
    private String code;

    public ProMaxEncodeWindow(String[] rotors, String reflector, String code){
        super("Encode parameters");
        this.code=code;
        setSize(X, Y);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
        rotorNumber.setBackground(new java.awt.Color(230,230,230));
        configuration.setBackground(new java.awt.Color(230,230,230));;
        this.rotors=rotors;
        this.reflector=reflector;
        JPanel panel=new JPanel(new GridLayout(2,1));
        panel.add(configuration);
        panel.add(rotorNumber);
        JButton button=new JButton("Enter");
        button.setBackground(new java.awt.Color(220,220,220));
        button.addActionListener(this);
        add(button, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent a){
        if(a.getActionCommand().equals("Enter")){
            try{
                new CodeWindow(new EnigmaProMax(rotors, reflector, configuration.getText(), Integer.parseInt(rotorNumber.getText())).encode(code));
                setVisible(false);
            }
            catch(Exception e){
                new EnigmaExceptionHandler(e.getMessage());
                setVisible(false);
            }
        }
    }
}
