package ca.tonsaker.bcstu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.imgscalr.Scalr;

public class JProfilePicture extends JPanel{

	HashMap<String, BufferedImage> profilePictures = new HashMap<String, BufferedImage>();
	BufferedImage profilePic;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3343254904731306886L;

	public JProfilePicture(){
		setPicture(null);
	}
	
	public void setPicture(URL url){
		try {
			if(url == null){
				profilePic = ImageIO.read(new File("res/picoffline.png"));
				repaint();
				return;
			}
			
			BufferedImage img = profilePictures.get(url.getPath());
			if(img != null){
				profilePic = img;
				repaint();
				return;
			}
			
			profilePic = ImageIO.read(url);
			profilePic = Scalr.resize(profilePic, Scalr.Mode.AUTOMATIC, this.getWidth(), this.getHeight());
			profilePictures.put(url.getPath(), profilePic);
		} catch (IOException e) {
			System.err.println("Image page is offline or you are not connected to the internet.");
			try {
				profilePic = ImageIO.read(new File("res/picoffline.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(profilePic, 0, 0, null);
	}

}
