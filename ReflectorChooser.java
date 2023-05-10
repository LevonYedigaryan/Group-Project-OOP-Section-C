import java.awt.Color;
import javax.swing.JPanel;

public class ReflectorChooser extends JPanel{
    private final int X=20;
	private final int Y=20;
    private String reflector="";
    
    protected void paintComponent(java.awt.Graphics g){
        g.setColor((new java.awt.Color(230,230,230)));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        String[] splitted=reflector.split(":");
        g.drawString(splitted[0], X, Y);
    }

    public String getReflector(){
        return reflector;
    }

    public void display(String reflector){
        this.reflector=reflector;
        repaint();
    }
}
