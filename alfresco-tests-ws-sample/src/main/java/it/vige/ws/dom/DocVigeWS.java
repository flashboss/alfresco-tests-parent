package it.vige.ws.dom;

import org.joda.time.DateTime;

/**
 * Domain object representing a Vige WebService document/practice.
 * Contains metadata about a practice including identifiers, dates, and company information.
 *
 * @author lucastancapiano
 */
public class DocVigeWS {

    /** The practice identifier. */
    private String idPratica;

    /** The practice creation date. */
    private DateTime dataCreazionePratica;

    /** The practice number. */
    private String numeroPratica;

    /** The agreement/convention description. */
    private String convenzione;

    /** The partner description. */
    private String descrizionePartner;

    /** The company fiscal code. */
    private String codiceFiscaleAzienda;

    /** The company business name. */
    private String ragioneSocialeAzienda;

    /**
     * Constructs a new DocVigeWS instance with all required fields.
     *
     * @param idPratica the practice identifier
     * @param dataCreazionePratica the practice creation date
     * @param numeroPratica the practice number
     * @param convenzione the agreement/convention description
     * @param descrizionePartner the partner description
     * @param codiceFiscaleAzienda the company fiscal code
     * @param ragioneSocialeAzienda the company business name
     */
    public DocVigeWS(String idPratica, DateTime dataCreazionePratica, String numeroPratica, String convenzione, String descrizionePartner, String codiceFiscaleAzienda, String ragioneSocialeAzienda) {
        this.idPratica = idPratica;
        this.numeroPratica = numeroPratica;
        this.convenzione = convenzione;
        this.descrizionePartner = descrizionePartner;
        this.codiceFiscaleAzienda = codiceFiscaleAzienda;
        this.ragioneSocialeAzienda = ragioneSocialeAzienda;
        this.dataCreazionePratica = dataCreazionePratica;
    }

    /**
     * Gets the practice identifier.
     *
     * @return the practice identifier
     */
    public String getIdPratica() {
        return idPratica;
    }

    /**
     * Gets the practice number.
     *
     * @return the practice number
     */
    public String getNumeroPratica() {
        return numeroPratica;
    }

    /**
     * Gets the agreement/convention description.
     *
     * @return the agreement/convention description
     */
    public String getConvenzione() {
        return convenzione;
    }

    /**
     * Gets the partner description.
     *
     * @return the partner description
     */
    public String getDescrizionePartner() {
        return descrizionePartner;
    }

    /**
     * Gets the company fiscal code.
     *
     * @return the company fiscal code
     */
    public String getCodiceFiscaleAzienda() {
        return codiceFiscaleAzienda;
    }

    /**
     * Gets the company business name.
     *
     * @return the company business name
     */
    public String getRagioneSocialeAzienda() {
        return ragioneSocialeAzienda;
    }

    /**
     * Gets the practice creation date.
     *
     * @return the practice creation date
     */
    public DateTime getDataCreazionePratica() {
        return dataCreazionePratica;
    }
}
