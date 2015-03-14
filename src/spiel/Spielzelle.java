package spiel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Spielzelle extends JPanel {
	
	public enum Typ {Leer, Stuhl, Tisch};

	private Typ t;
	private BufferedImage i;
	private Stuhl st;
	private Tisch ti;
	private final Map<String, BufferedImage> cache = new TreeMap<>();
	 
	public Spielzelle(Typ t) {
	    setTyp(t);
	}

	@Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        if(i == null) { //i==null? Macht das beim Kartenlegen später Probleme?
            i = loadImage(this.t);
        }
        gr.drawImage(i, 0, 0, getWidth(), getHeight(), null);
    }
	
	private synchronized BufferedImage loadImage(Typ ty) {
		String key = null;
		BufferedImage bi = null;
		if(ty.equals(Typ.Stuhl)) {
			if(this.getSt().getGast()!=null) {
				key = "./gast_"+this.getSt().getGast().land+"_"+this.getSt().getGast().geschlecht+".jpg";
			} else {
				key = "./stuhl_leer.jpg";
			}
		} else if(ty.equals(Typ.Tisch)) {
			if(this.getTi().getLand()!=null) {
				key = "./tisch_"+this.getTi().getLand().land+".jpg";
			} else {
				key = "./tisch_leer.jpg";
			}
		}
		if(!ty.equals(Typ.Leer)) {
			bi = cache.get(key);
	        if(bi == null) {
	            try {
	                URL url = new URL(BaseURL.getJarBase(Spielfeld.class), key);
	                bi = ImageIO.read(url);
	            } catch (MalformedURLException e) {
	            } catch (IOException e) {
	            }
	            cache.put(key, bi);
	        }
		}
		return bi;
    }
	
	public Typ getTyp() {
	    return t;
	}
	 
	public void setTyp(Typ t) {
	    if(t != this.t) {
	    	i = null;
	    }
	    this.t = t;
	}
	 
	public Stuhl getSt() {
		return st;
	}

	public void setSt(Stuhl st) {
		this.st = st;
	}

	public Tisch getTi() {
		return ti;
	}

	public void setTi(Tisch ti) {
		this.ti = ti;
	}
}