
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per signerImage complex type.
 * 
 * @author vige
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * @author vige
 * <pre>
 * &lt;complexType name="signerImage"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fontName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fontSize" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="height" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="imageFilename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="imageURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="signerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="textVisible" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="width" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="x" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="y" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author vige
 * 
 * @author vige
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signerImage", propOrder = { "fieldName", "fontName", "fontSize", "height", "image", "imageFilename",
		"imageURL", "location", "reason", "signerName", "textVisible", "width", "x", "y" })
public class SignerImage {

	protected String fieldName;
	protected String fontName;
	protected int fontSize;
	protected int height;
	protected byte[] image;
	protected String imageFilename;
	protected String imageURL;
	protected String location;
	protected String reason;
	protected String signerName;
	protected boolean textVisible;
	protected int width;
	protected int x;
	protected int y;

	/**
	 * Recupera il valore della proprietà fieldName.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Imposta il valore della proprietà fieldName.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setFieldName(String value) {
		this.fieldName = value;
	}

	/**
	 * Recupera il valore della proprietà fontName.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFontName() {
		return fontName;
	}

	/**
	 * Imposta il valore della proprietà fontName.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setFontName(String value) {
		this.fontName = value;
	}

	/**
	 * Recupera il valore della proprietà fontSize.
	 * 
	 */
	public int getFontSize() {
		return fontSize;
	}

	/**
	 * Imposta il valore della proprietà fontSize.
	 * 
	 */
	public void setFontSize(int value) {
		this.fontSize = value;
	}

	/**
	 * Recupera il valore della proprietà height.
	 * 
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Imposta il valore della proprietà height.
	 * 
	 */
	public void setHeight(int value) {
		this.height = value;
	}

	/**
	 * Recupera il valore della proprietà image.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * Imposta il valore della proprietà image.
	 * 
	 * @param value allowed object is byte[]
	 */
	public void setImage(byte[] value) {
		this.image = value;
	}

	/**
	 * Recupera il valore della proprietà imageFilename.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getImageFilename() {
		return imageFilename;
	}

	/**
	 * Imposta il valore della proprietà imageFilename.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setImageFilename(String value) {
		this.imageFilename = value;
	}

	/**
	 * Recupera il valore della proprietà imageURL.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * Imposta il valore della proprietà imageURL.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setImageURL(String value) {
		this.imageURL = value;
	}

	/**
	 * Recupera il valore della proprietà location.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Imposta il valore della proprietà location.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setLocation(String value) {
		this.location = value;
	}

	/**
	 * Recupera il valore della proprietà reason.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * Imposta il valore della proprietà reason.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setReason(String value) {
		this.reason = value;
	}

	/**
	 * Recupera il valore della proprietà signerName.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSignerName() {
		return signerName;
	}

	/**
	 * Imposta il valore della proprietà signerName.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setSignerName(String value) {
		this.signerName = value;
	}

	/**
	 * Recupera il valore della proprietà textVisible.
	 * 
	 */
	public boolean isTextVisible() {
		return textVisible;
	}

	/**
	 * Imposta il valore della proprietà textVisible.
	 * 
	 */
	public void setTextVisible(boolean value) {
		this.textVisible = value;
	}

	/**
	 * Recupera il valore della proprietà width.
	 * 
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Imposta il valore della proprietà width.
	 * 
	 */
	public void setWidth(int value) {
		this.width = value;
	}

	/**
	 * Recupera il valore della proprietà x.
	 * 
	 */
	public int getX() {
		return x;
	}

	/**
	 * Imposta il valore della proprietà x.
	 * 
	 */
	public void setX(int value) {
		this.x = value;
	}

	/**
	 * Recupera il valore della proprietà y.
	 * 
	 */
	public int getY() {
		return y;
	}

	/**
	 * Imposta il valore della proprietà y.
	 * 
	 */
	public void setY(int value) {
		this.y = value;
	}

}
