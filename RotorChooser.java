import java.awt.Color;
import javax.swing.JPanel;
import java.util.*;

public class RotorChooser extends JPanel{
    private final int X=20;
	private final int Y=20;
    private final int STEP=30;
    private ArrayList<String> rotors=new ArrayList<>();

    protected void paintComponent(java.awt.Graphics g){
        g.setColor((new java.awt.Color(230,230,230)));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        for(int i=0;i<rotors.size();i++){
            String[] splitted=rotors.get(i).split(":");
            g.drawString(i+1+") "+splitted[0], X, Y+i*STEP);
        }
    }

    public String[] getRotors(){
        String[] get=new String[rotors.size()];
        for(int i=0;i<rotors.size();i++){
            get[i]=rotors.get(i);
        }
        return get;
    }

    public void display(String pattern){
        if(rotors.contains(pattern)){
            rotors.remove(pattern);
        }
        else{
            rotors.add(pattern);
        }
        repaint();
    }
}