package it.vige.ws.dom;

import org.joda.time.DateTime;

/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public class DocVigeWS {

    /** The id pratica. */
    private String idPratica;
    /** The data creazione pratica. */
    private DateTime dataCreazionePratica;
    /** The numero pratica. */
    private String numeroPratica;
    /** The convenzione. */
    private String convenzione;
    /** The descrizione partner. */
    private String descrizionePartner;
    /** The codice fiscale azienda. */
    private String codiceFiscaleAzienda;
    /** The ragione sociale azienda. */
    private String ragioneSocialeAzienda;


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
    * Get id pratica.
    *
    * @return the string
    */
    public String getIdPratica() {
        return idPratica;
    }

    /**
    * Get numero pratica.
    *
    * @return the string
    */
    public String getNumeroPratica() {
        return numeroPratica;
    }

    /**
    * Get convenzione.
    *
    * @return the string
    */
    public String getConvenzione() {
        return convenzione;
    }

    /**
    * Get descrizione partner.
    *
    * @return the string
    */
    public String getDescrizionePartner() {
        return descrizionePartner;
    }

    /**
    * Get codice fiscale azienda.
    *
    * @return the string
    */
    public String getCodiceFiscaleAzienda() {
        return codiceFiscaleAzienda;
    }

    /**
    * Get ragione sociale azienda.
    *
    * @return the string
    */
    public String getRagioneSocialeAzienda() {
        return ragioneSocialeAzienda;
    }

    /**
    * Get data creazione pratica.
    *
    * @return the date time
    */
    public DateTime getDataCreazionePratica() {
        return dataCreazionePratica;
    }
}
