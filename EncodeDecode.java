import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.*;

public class EncodeDecode extends JFrame implements ActionListener {
    public final int X=300;
    public final int Y=100;
    private String[] rotors;
    private String reflector;
    private String code;

    public EncodeDecode(String[] rotors, String reflector, String code){
        super("Encode or Decode?");
        this.code=code;
        setSize(X, Y);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
        this.rotors=rotors;
        this.reflector=reflector;
        JPanel panel=new JPanel(new GridLayout(1, 2));
        JButton encode=new JButton("Encode");
        JButton decode=new JButton("Decode");
        encode.setBackground(new java.awt.Color(220,220,220));
        decode.setBackground(new java.awt.Color(220,220,220));
        encode.addActionListener(this);
        decode.addActionListener(this);
        JPanel encodePanel=new JPanel();
        JPanel decodePanel=new JPanel();
        addBorder(encodePanel, "Encode");
        addBorder(decodePanel, "Decode");
        encodePanel.add(encode);
        decodePanel.add(decode);
        panel.add(encodePanel);
        panel.add(decodePanel);
        add(panel);
    }

    public void actionPerformed(ActionEvent a){
        if(a.getActionCommand().equals("Encode")){
            new ProMaxEncodeWindow(rotors, reflector, code);
            setVisible(false);
        }else if(a.getActionCommand().equals("Decode")){
            new ProMaxDecodeWindow(rotors, reflector, code);
            setVisible(false);
        }
    }

    private static void addBorder(JComponent component, String title) {
		Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border tb = BorderFactory.createTitledBorder(etch, title);
		component.setBorder(tb);
	}
}
