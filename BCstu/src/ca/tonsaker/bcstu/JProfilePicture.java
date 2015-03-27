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

/**
 * A profile picture displayer for JComponents.
 * 
 * @author Markus Tonsaker
 *
 */
public class JProfilePicture extends JPanel{

	HashMap<String, BufferedImage> profilePictures = new HashMap<String, BufferedImage>();
	BufferedImage profilePic;
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 3343254904731306886L;

	/**
	 * Creates an object of JProfilePicture and defaults the picture to the <i>res/picoffline.png</i>.
	 */
	public JProfilePicture(){
		setPicture(null);
	}
	
	/**
	 * Changes the current displayed profile picture to the given <b>url</b>.
	 * <P>
	 * If the url is <b>null</b>, then the displayed profile picture will be <i>res/picoffline.png</i>
	 * 
	 * @param url - the url to the PNG of the image.  If null then the displayed profile picture will be <i>res/picoffline.png</i>
	 */
	public void setPicture(URL url){
		try {
			//URL is null? Then display the default offline picture. Then return.
			if(url == null){
				profilePic = ImageIO.read(new File("res/picoffline.png"));
				repaint();
				return;
			}
			
			//URL is not null? Then get the PNG from the HashMap. Does the image exist in the cache? Display it and return.
			//(Prevents having to reload the image from the website.)
			BufferedImage img = profilePictures.get(url.getPath());
			if(img != null){
				profilePic = img;
				repaint();
				return;
			}
			
			//Image does not exist in cache (HashMap). Download it from the website, resize it, and store it in the cache (HashMap).
			//Then display it.
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
