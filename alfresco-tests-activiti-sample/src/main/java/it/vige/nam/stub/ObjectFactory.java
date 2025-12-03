
package it.vige.nam.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the it.vige.nam.stub package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _SignCAdESByAttachment_QNAME = new QName("http://service.ws.nam/",
			"signCAdESByAttachment");
	private final static QName _SignCAdESByAttachmentResponse_QNAME = new QName("http://service.ws.nam/",
			"signCAdESByAttachmentResponse");
	private final static QName _VerifyCAdESByAttachment_QNAME = new QName("http://service.ws.nam/",
			"verifyCAdESByAttachment");
	private final static QName _VerifyCAdESByAttachmentResponse_QNAME = new QName("http://service.ws.nam/",
			"verifyCAdESByAttachmentResponse");
	private final static QName _WSException_QNAME = new QName("http://service.ws.nam/", "WSException");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema
	 * derived classes for package: it.vige.nam.stub
	 *
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link SignCAdESByAttachment }
	 *
	 */
	public SignCAdESByAttachment createSignCAdESByAttachment() {
		return new SignCAdESByAttachment();
	}

	/**
	 * Create an instance of {@link SignCAdESByAttachmentResponse }
	 *
	 */
	public SignCAdESByAttachmentResponse createSignCAdESByAttachmentResponse() {
		return new SignCAdESByAttachmentResponse();
	}

	/**
	 * Create an instance of {@link VerifyCAdESByAttachment }
	 *
	 */
	public VerifyCAdESByAttachment createVerifyCAdESByAttachment() {
		return new VerifyCAdESByAttachment();
	}

	/**
	 * Create an instance of {@link VerifyCAdESByAttachmentResponse }
	 *
	 */
	public VerifyCAdESByAttachmentResponse createVerifyCAdESByAttachmentResponse() {
		return new VerifyCAdESByAttachmentResponse();
	}

	/**
	 * Create an instance of {@link WsFaultBean }
	 *
	 */
	public WsFaultBean createWsFaultBean() {
		return new WsFaultBean();
	}

	/**
	 * Create an instance of {@link VerifyPreferences }
	 *
	 */
	public VerifyPreferences createVerifyPreferences() {
		return new VerifyPreferences();
	}

	/**
	 * Create an instance of {@link SignedDocumentReportBean }
	 *
	 */
	public SignedDocumentReportBean createSignedDocumentReportBean() {
		return new SignedDocumentReportBean();
	}

	/**
	 * Create an instance of {@link NoteReportBean }
	 *
	 */
	public NoteReportBean createNoteReportBean() {
		return new NoteReportBean();
	}

	/**
	 * Create an instance of {@link SignatureReportBean }
	 *
	 */
	public SignatureReportBean createSignatureReportBean() {
		return new SignatureReportBean();
	}

	/**
	 * Create an instance of {@link TimestampReportBean }
	 *
	 */
	public TimestampReportBean createTimestampReportBean() {
		return new TimestampReportBean();
	}

	/**
	 * Create an instance of {@link Credentials }
	 *
	 */
	public Credentials createCredentials() {
		return new Credentials();
	}

	/**
	 * Create an instance of {@link CAdESPreferences }
	 *
	 */
	public CAdESPreferences createCAdESPreferences() {
		return new CAdESPreferences();
	}

	/**
	 * Create an instance of {@link SignPreferences }
	 *
	 */
	public SignPreferences createSignPreferences() {
		return new SignPreferences();
	}

	/**
	 * Create an instance of {@link TimeStampPreferences }
	 *
	 */
	public TimeStampPreferences createTimeStampPreferences() {
		return new TimeStampPreferences();
	}

	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link SignCAdESByAttachment }{@code >}}
	 *
	 */
	@XmlElementDecl(namespace = "http://service.ws.nam/", name = "signCAdESByAttachment")
	public JAXBElement<SignCAdESByAttachment> createSignCAdESByAttachment(SignCAdESByAttachment value) {
		return new JAXBElement<SignCAdESByAttachment>(_SignCAdESByAttachment_QNAME, SignCAdESByAttachment.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link SignCAdESByAttachmentResponse }{@code >}}
	 *
	 */
	@XmlElementDecl(namespace = "http://service.ws.nam/", name = "signCAdESByAttachmentResponse")
	public JAXBElement<SignCAdESByAttachmentResponse> createSignCAdESByAttachmentResponse(
			SignCAdESByAttachmentResponse value) {
		return new JAXBElement<SignCAdESByAttachmentResponse>(_SignCAdESByAttachmentResponse_QNAME,
				SignCAdESByAttachmentResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link VerifyCAdESByAttachment }{@code >}}
	 *
	 */
	@XmlElementDecl(namespace = "http://service.ws.nam/", name = "verifyCAdESByAttachment")
	public JAXBElement<VerifyCAdESByAttachment> createVerifyCAdESByAttachment(VerifyCAdESByAttachment value) {
		return new JAXBElement<VerifyCAdESByAttachment>(_VerifyCAdESByAttachment_QNAME, VerifyCAdESByAttachment.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link VerifyCAdESByAttachmentResponse }{@code >}}
	 *
	 */
	@XmlElementDecl(namespace = "http://service.ws.nam/", name = "verifyCAdESByAttachmentResponse")
	public JAXBElement<VerifyCAdESByAttachmentResponse> createVerifyCAdESByAttachmentResponse(
			VerifyCAdESByAttachmentResponse value) {
		return new JAXBElement<VerifyCAdESByAttachmentResponse>(_VerifyCAdESByAttachmentResponse_QNAME,
				VerifyCAdESByAttachmentResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link WsFaultBean
	 * }{@code >}}
	 *
	 */
	@XmlElementDecl(namespace = "http://service.ws.nam/", name = "WSException")
	public JAXBElement<WsFaultBean> createWSException(WsFaultBean value) {
		return new JAXBElement<WsFaultBean>(_WSException_QNAME, WsFaultBean.class, null, value);
	}

}
