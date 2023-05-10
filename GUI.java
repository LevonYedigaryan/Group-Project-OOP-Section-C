
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;
import java.util.*;

public class GUI extends JFrame implements ActionListener{
    private final int X=1024;
    private final int Y=702;
    private JTextField code=new JTextField("Enter your code");
    private RotorChooser rotorSelectionField;
    private ReflectorChooser reflectorSelectionField;
    private String[] rotorPatterns;
    private String[] reflectorPatterns;

    public GUI() throws IOException, IncorrectFormatException, FileNotFoundException{
        super("Enigma");
        rotorPatterns=load(new FileReader("patterns\\rotors.txt"));
        code.setBackground(new java.awt.Color(230,230,230));
        reflectorPatterns=load(new FileReader("patterns\\reflectors.txt"));
        code.setSize(500, 300);
        rotorSelectionField=new RotorChooser();
        reflectorSelectionField=new ReflectorChooser();
        setSize(X, Y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        add(getChoosePanels(), BorderLayout.WEST);
        add(machineUsagePanel(),BorderLayout.CENTER);
    }

    private JPanel machineUsagePanel(){
        JPanel panel=new JPanel(new GridLayout(2,1));
        JPanel input=new JPanel();
        addBorder(input, "Input Your Code Here");
        panel.add(code);
        panel.add(enigmasPanel());
        return panel;
    }

    private JPanel enigmasPanel(){
        JPanel panel=new JPanel(new GridLayout(1,3));

        JPanel enigma=new JPanel(new BorderLayout());
        addBorder(enigma, "Enigma");
        JButton enigmaButton=new JButton("Enigma",new ImageIcon("icons\\enigma.jpg"));
        enigmaButton.addActionListener(this);
        enigma.add(enigmaButton, BorderLayout.CENTER);

        JPanel enigmaPro=new JPanel(new BorderLayout());
        addBorder(enigmaPro, "EnigmaPro");
        JButton enigmaProButton=new JButton("EnigmaPro",new ImageIcon("icons\\enigmapro.jpg"));
        enigmaProButton.addActionListener(this);
        enigmaPro.add(enigmaProButton, BorderLayout.CENTER);

        JPanel enigmaProMax=new JPanel(new BorderLayout());
        addBorder(enigmaProMax, "EnigmaProMax");
        JButton enigmaProMaxButton=new JButton("EnigmaProMax",new ImageIcon("icons\\enigmapromax.jpg"));
        enigmaProMaxButton.addActionListener(this);
        enigmaProMax.add(enigmaProMaxButton, BorderLayout.CENTER);

        panel.add(enigma);
        panel.add(enigmaPro);
        panel.add(enigmaProMax);
        addBorder(panel, "Choose your machine");
        return panel;
    }

	private static void addBorder(JComponent component, String title) {
		Border etch = BorderFactory.createEtchedBorder((new java.awt.Color(220,220,220)),(new java.awt.Color(200,200,200)));
		Border tb = BorderFactory.createTitledBorder(etch, title);
		component.setBorder(tb);
	}

    public JPanel getChoosePanels() throws IncorrectFormatException{
        JPanel panel=new JPanel(new GridLayout(1,2));
        JPanel reflectorPanel=new JPanel(new GridLayout(2,1));
        reflectorPanel.add(reflectorSelectionField);
        reflectorPanel.add(selectionPanel(reflectorPatterns, "Reflector"));
        JPanel rotorPanel=new JPanel(new GridLayout(2,1));
        rotorPanel.add(rotorSelectionField);
        rotorPanel.add(selectionPanel(rotorPatterns, "Rotor"));
        addBorder(reflectorPanel, "Reflectors");
        addBorder(rotorPanel, "Rotors");
        panel.add(reflectorPanel);
        panel.add(rotorPanel);
        return panel;
    }

    public JPanel selectionPanel(String[] patterns, String type) throws IncorrectFormatException{
        JPanel panel=new JPanel(new BorderLayout());
        JPanel buttons=new JPanel(new GridLayout(patterns.length,1));
        for(int i=0;i<patterns.length;i++){
            JButton button=new JButton(type+" "+(i+1));
            button.setBackground((new java.awt.Color(220,220,220)));
            if(type.equals("Rotor")){
                String pattern=patterns[i];
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent a){
                        rotorSelectionField.display(pattern);
                    }
                });
            }
            else{
                String pattern=patterns[i];
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent a){
                        reflectorSelectionField.display(pattern);
                    }
                });
            }
            buttons.add(button);
        }
        panel.add(buttons, BorderLayout.CENTER);
        return panel;
    }

    private String[] load(Reader r) throws IOException, IncorrectFormatException{
        ArrayList<String> list=new ArrayList<>();
        BufferedReader b = new BufferedReader(r);
        String line;
        while((line=b.readLine())!=null) {
            if (!list.contains(line)){
                list.add(line);
            }
        }
        String[] arr=new String[list.size()];
        for(int i=0;i<list.size();i++){
            arr[i]=list.get(i);
        }
        return arr;
    }

    public  void actionPerformed(ActionEvent a){
        try{
            if(rotorSelectionField.getRotors().length>0 && reflectorSelectionField.getReflector().length()>0){
                if(a.getActionCommand().equals("Enigma")){
                    new CodeWindow(new Enigma(rotorSelectionField.getRotors(), reflectorSelectionField.getReflector()).encode(code.getText()));
                }else if(a.getActionCommand().equals("EnigmaPro")){
                    new ProConfigureWindow(rotorSelectionField.getRotors(), reflectorSelectionField.getReflector(), code.getText());
                }else if(a.getActionCommand().equals("EnigmaProMax")){
                    new EncodeDecode(rotorSelectionField.getRotors(), reflectorSelectionField.getReflector(), code.getText());
                }
            }
        }
        catch(Exception e){
            new EnigmaExceptionHandler(e.getMessage());
        }
    }

    public static void main(String[] args){
        try{
            new GUI();
        }
        catch(Exception e){
            new EnigmaExceptionHandler(e.getMessage());
        }
    }
}