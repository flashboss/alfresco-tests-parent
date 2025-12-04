
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per pAdESPreferences complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * <pre>
 * &lt;complexType name="pAdESPreferences"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://service.ws.nam/}signPreferences"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="encryptInAnyCase" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="encryptionPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="page" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="signerImage" type="{http://service.ws.nam/}signerImage" minOccurs="0"/&gt;
 *         &lt;element name="signerImageReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pAdESPreferences", propOrder = { "encryptInAnyCase", "encryptionPassword", "page", "signerImage",
		"signerImageReference" })
/**
 * PAdESPreferences implementation for testing purposes.
 *
 * @author vige
 */
public class PAdESPreferences extends SignPreferences {

	/** The encrypt in any case. */
	protected boolean encryptInAnyCase;
	/** The encryption password. */
	protected String encryptionPassword;
	/** The page. */
	protected int page;
	/** The signer image. */
	protected SignerImage signerImage;
	/** The signer image reference. */
	protected String signerImageReference;

	/**
	 * Recupera il valore della proprietà encryptInAnyCase.
	 * 
	 */
	public boolean isEncryptInAnyCase() {
		return encryptInAnyCase;
	}

	/**
	 * Imposta il valore della proprietà encryptInAnyCase.
	 * 
	 */
	public void setEncryptInAnyCase(boolean value) {
		this.encryptInAnyCase = value;
	}

	/**
	 * Recupera il valore della proprietà encryptionPassword.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEncryptionPassword() {
		return encryptionPassword;
	}

	/**
	 * Imposta il valore della proprietà encryptionPassword.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setEncryptionPassword(String value) {
		this.encryptionPassword = value;
	}

	/**
	 * Recupera il valore della proprietà page.
	 * 
	 */
	public int getPage() {
		return page;
	}

	/**
	 * Imposta il valore della proprietà page.
	 * 
	 */
	public void setPage(int value) {
		this.page = value;
	}

	/**
	 * Recupera il valore della proprietà signerImage.
	 * 
	 * @return possible object is {@link SignerImage }
	 * 
	 */
	public SignerImage getSignerImage() {
		return signerImage;
	}

	/**
	 * Imposta il valore della proprietà signerImage.
	 * 
	 * @param value allowed object is {@link SignerImage }
	 * 
	 */
	public void setSignerImage(SignerImage value) {
		this.signerImage = value;
	}

	/**
	 * Recupera il valore della proprietà signerImageReference.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSignerImageReference() {
		return signerImageReference;
	}

	/**
	 * Imposta il valore della proprietà signerImageReference.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setSignerImageReference(String value) {
		this.signerImageReference = value;
	}

}
