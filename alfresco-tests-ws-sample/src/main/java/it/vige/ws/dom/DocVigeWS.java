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

    private String idPratica;
    private DateTime dataCreazionePratica;
    private String numeroPratica;
    private String convenzione;
    private String descrizionePartner;
    private String codiceFiscaleAzienda;
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

    public String getIdPratica() {
        return idPratica;
    }

    public String getNumeroPratica() {
        return numeroPratica;
    }

    public String getConvenzione() {
        return convenzione;
    }

    public String getDescrizionePartner() {
        return descrizionePartner;
    }

    public String getCodiceFiscaleAzienda() {
        return codiceFiscaleAzienda;
    }

    public String getRagioneSocialeAzienda() {
        return ragioneSocialeAzienda;
    }

    public DateTime getDataCreazionePratica() {
        return dataCreazionePratica;
    }
}
