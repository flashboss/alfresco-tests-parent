
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
/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
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
		/**
		 * Create an instance of {@link SignCAdESByAttachment }
		 *
		 * @return the result
		 */
		return new SignCAdESByAttachment();
	}

	/**
	 * Create an instance of {@link SignCAdESByAttachmentResponse }
	 * 
	 */
	public SignCAdESByAttachmentResponse createSignCAdESByAttachmentResponse() {
		/**
		 * Create an instance of {@link SignCAdESByAttachmentResponse }
		 *
		 * @return the result
		 */
		return new SignCAdESByAttachmentResponse();
	}

	/**
	 * Create an instance of {@link VerifyCAdESByAttachment }
	 * 
	 */
	public VerifyCAdESByAttachment createVerifyCAdESByAttachment() {
		/**
		 * Create an instance of {@link VerifyCAdESByAttachment }
		 *
		 * @return the result
		 */
		return new VerifyCAdESByAttachment();
	}

	/**
	 * Create an instance of {@link VerifyCAdESByAttachmentResponse }
	 * 
	 */
	public VerifyCAdESByAttachmentResponse createVerifyCAdESByAttachmentResponse() {
		/**
		 * Create an instance of {@link VerifyCAdESByAttachmentResponse }
		 *
		 * @return the result
		 */
		return new VerifyCAdESByAttachmentResponse();
	}

	/**
	 * Create an instance of {@link WsFaultBean }
	 * 
	 */
	public WsFaultBean createWsFaultBean() {
		/**
		 * Create an instance of {@link WsFaultBean }
		 *
		 * @return the result
		 */
		return new WsFaultBean();
	}

	/**
	 * Create an instance of {@link VerifyPreferences }
	 * 
	 */
	public VerifyPreferences createVerifyPreferences() {
		/**
		 * Create an instance of {@link VerifyPreferences }
		 *
		 * @return the result
		 */
		return new VerifyPreferences();
	}

	/**
	 * Create an instance of {@link SignedDocumentReportBean }
	 * 
	 */
	public SignedDocumentReportBean createSignedDocumentReportBean() {
		/**
		 * Create an instance of {@link SignedDocumentReportBean }
		 *
		 * @return the result
		 */
		return new SignedDocumentReportBean();
	}

	/**
	 * Create an instance of {@link NoteReportBean }
	 * 
	 */
	public NoteReportBean createNoteReportBean() {
		/**
		 * Create an instance of {@link NoteReportBean }
		 *
		 * @return the result
		 */
		return new NoteReportBean();
	}

	/**
	 * Create an instance of {@link SignatureReportBean }
	 * 
	 */
	public SignatureReportBean createSignatureReportBean() {
		/**
		 * Create an instance of {@link SignatureReportBean }
		 *
		 * @return the result
		 */
		return new SignatureReportBean();
	}

	/**
	 * Create an instance of {@link TimestampReportBean }
	 * 
	 */
	public TimestampReportBean createTimestampReportBean() {
		/**
		 * Create an instance of {@link TimestampReportBean }
		 *
		 * @return the result
		 */
		return new TimestampReportBean();
	}

	/**
	 * Create an instance of {@link Credentials }
	 * 
	 */
	public Credentials createCredentials() {
		/**
		 * Create an instance of {@link Credentials }
		 *
		 * @return the result
		 */
		return new Credentials();
	}

	/**
	 * Create an instance of {@link CAdESPreferences }
	 * 
	 */
	public CAdESPreferences createCAdESPreferences() {
		/**
		 * Create an instance of {@link CAdESPreferences }
		 *
		 * @return the result
		 */
		return new CAdESPreferences();
	}

	/**
	 * Create an instance of {@link SignPreferences }
	 * 
	 */
	public SignPreferences createSignPreferences() {
		/**
		 * Create an instance of {@link SignPreferences }
		 *
		 * @return the result
		 */
		return new SignPreferences();
	}

	/**
	 * Create an instance of {@link TimeStampPreferences }
	 * 
	 */
	public TimeStampPreferences createTimeStampPreferences() {
		/**
		 * Create an instance of {@link TimeStampPreferences }
		 *
		 * @return the result
		 */
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
	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link SignCAdESByAttachmentResponse }{@code
	 * >}}
	 *
	 * @return the result
	 */
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
	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link VerifyCAdESByAttachmentResponse
	 * }{@code >}}
	 *
	 * @return the result
	 */
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
	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link WsFaultBean
	 * }{@code >}}
	 *
	 * @param value the value
	 * @return the result
	 */
	public JAXBElement<WsFaultBean> createWSException(WsFaultBean value) {
		/**
		 * Create an instance of {@link JAXBElement }{@code <}{@link WsFaultBean
		 * }{@code >}}
		 *
		 * @param value the value
		 * @return the result
		 */
		return new JAXBElement<WsFaultBean>(_WSException_QNAME, WsFaultBean.class, null, value);
	}

}
