package spiel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Spielzelle extends JPanel {
	
	public static enum Geschlecht {Mann, Frau};
	public static enum Land {DE, FR, GB, US, TR, IN, CN, RU, ES, CU, CF, IT, JOKER};
	public static enum Typ {Stuhl, Tisch};

	private Land l;
	private Geschlecht g;
	private Typ t;
	private BufferedImage i;
	 
	public Spielzelle() {
		this(null, null, null);
	}
	 
	public Spielzelle(Geschlecht g, Land l, Typ t) {
		setLand(l);
	    setGeschlecht(g);
	    setTyp(t);
	}
	 
	public Geschlecht getGeschlecht() {
		return g;
	}
	 
	public Land getLand() {
	    return l;
	}
	 
	public Typ getTyp() {
	    return t;
	}
	 
	public void setGeschlecht(Geschlecht g) {
		if(g != this.g) {
			i = null;
	    }
	    this.g = g;
	}
	 
	public void setLand(Land l) {
		if(l != this.l) {
			i = null;
	    }
	    this.l = l;
	}
	 
	public void setTyp(Typ t) {
	    if(t != this.t) {
	    	i = null;
	    }
	    this.t = t;
	}
	 
	protected void paintComponent(Graphics gr) {
	    super.paintComponent(gr);
	    if(i == null && l != null && g != null && t != null) {
	    	try {
	    		URL url = null;
	    		if(t.equals(Typ.Stuhl)) {
	    			url = new URL(BaseURL.getJarBase(Spielfeld.class), "demo_"+l.toString()+".jpg");
	    			System.out.println(l.toString());
	    		} else if(t.equals(Typ.Tisch)) {
	    			url = new URL(BaseURL.getJarBase(Spielfeld.class), "demo_"+l.toString()+".jpg");
	    		}
	    		System.out.println(url);
	    		i = ImageIO.read(url);
	    	}catch(MalformedURLException e) {
	    	}catch(IOException e) { }
	    }
	    if(i != null) {
	    	gr.drawImage(i,0,0, getWidth(), getHeight(), null);
	    }
	}
}