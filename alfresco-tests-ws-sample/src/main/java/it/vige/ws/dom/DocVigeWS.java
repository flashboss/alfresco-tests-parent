package it.vige.ws.dom;

import org.joda.time.DateTime;

/**
* Mock implementation of the DocVigeWS class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class DocVigeWS {

/**
* The id pratica.
 */
    private String idPratica;
/**
* The data creazione pratica.
 */
    private DateTime dataCreazionePratica;
/**
* The numero pratica.
 */
    private String numeroPratica;
/**
* The convenzione.
 */
    private String convenzione;
/**
* The descrizione partner.
 */
    private String descrizionePartner;
/**
* The codice fiscale azienda.
 */
    private String codiceFiscaleAzienda;
/**
* The ragione sociale azienda.
 */
    private String ragioneSocialeAzienda;


/**
* Constructs a new DocVigeWS instance.
* @param idPratica the idPratica
* @param dataCreazionePratica the dataCreazionePratica
* @param numeroPratica the numeroPratica
* @param convenzione the convenzione
* @param descrizionePartner the descrizionePartner
* @param codiceFiscaleAzienda the codiceFiscaleAzienda
* @param ragioneSocialeAzienda the ragioneSocialeAzienda
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
* Gets the id pratica.
* @return the result
 */
    public String getIdPratica() {
        return idPratica;
    }

/**
* Gets the numero pratica.
* @return the result
 */
    public String getNumeroPratica() {
        return numeroPratica;
    }

/**
* Gets the convenzione.
* @return the result
 */
    public String getConvenzione() {
        return convenzione;
    }

/**
* Gets the descrizione partner.
* @return the result
 */
    public String getDescrizionePartner() {
        return descrizionePartner;
    }

/**
* Gets the codice fiscale azienda.
* @return the result
 */
    public String getCodiceFiscaleAzienda() {
        return codiceFiscaleAzienda;
    }

/**
* Gets the ragione sociale azienda.
* @return the result
 */
    public String getRagioneSocialeAzienda() {
        return ragioneSocialeAzienda;
    }

/**
* Gets the data creazione pratica.
* @return the result
 */
    public DateTime getDataCreazionePratica() {
        return dataCreazionePratica;
    }
}
