package es.florida.model;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Card {

	private int id; //index btn
	private String name;
	private String base64;
	private ImageIcon imgIcon;
	private JButton btnImg;
	private boolean turned = false;
	private boolean clikable = true;
	
	public Card() {
		
	}

	public Card(int id, String name, String base64, ImageIcon imgIcon, JButton btnImg, boolean turned, boolean clikable) {
		this.id = id;
		this.name = name;
		this.base64 = base64;
		this.imgIcon = imgIcon;
		this.btnImg = btnImg;
		this.turned = turned;
		this.clikable = clikable;
	}

	/**
	 * Metodo donde damos la vuelta a la carta y muestra la imagen
	 */
	public void turnOnCard() {
		btnImg.setIcon(imgIcon);
		turned = true;
	}
	
	/**
	 * Metodo donde damos la vuelta a la carta y oculta la imagen
	 */
	public void turnOffCard() {
		btnImg.setIcon(null);
		turned = false;
	}

	/**
	 * @return the clikable
	 */
	public boolean isClikable() {
		return clikable;
	}

	/**
	 * @param clikable the clikable to set
	 */
	public void setClikable(boolean clikable) {
		this.clikable = clikable;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the base64
	 */
	public String getBase64() {
		return base64;
	}

	/**
	 * @return the imgIcon
	 */
	public ImageIcon getImgIcon() {
		return imgIcon;
	}

	/**
	 * @return the btnImg
	 */
	public JButton getBtnImg() {
		return btnImg;
	}

	/**
	 * @return the turned
	 */
	public boolean isTurned() {
		return turned;
	}

	/**
	 * @param turned the turned to set
	 */
	public void setTurned(boolean turned) {
		this.turned = turned;
	}
	
	
	
}
