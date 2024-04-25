package it.vige.ws.utils;

import org.alfresco.service.namespace.QName;

/**
 * Classe che contiene tutte le constanti che servono per mappare il content Model personalizzato di Alfresco:
 * http://www.regione.lombardia.it/content/model/atti/1.0
 * Si trovano anche le query. 
 * @author sourcesense
 *
 */
public class Constant {

	public static String CRL_MODEL_URI = "http://www.regione.lombardia.it/content/model/atti/1.0";
	public static final QName TYPE_LEGISLATURA =  QName.createQName(CRL_MODEL_URI, "legislaturaAnagrafica");
	public static final QName TYPE_CONSIGLIERE_ANAGRAFICA =  QName.createQName(CRL_MODEL_URI, "consigliereAnagrafica");
	public static final QName TYPE_GRUPPO_CONSILIARE_ANAGRAFICA =  QName.createQName(CRL_MODEL_URI, "gruppoConsiliareAnagrafica");
	public static final QName TYPE_COMMISSIONE_ANAGRAFICA =  QName.createQName(CRL_MODEL_URI, "commissioneAnagrafica");	
	
	public static final QName PROP_NUMERO_ORDINAMENTO_COMMISSIONE_ANAGRAFICA = QName.createQName(CRL_MODEL_URI, "numeroOrdinamentoCommissioneAnagrafica");
	public static final QName PROP_IDENTIFICATORE_COMMISSIONE_ANAGRAFICA = QName.createQName(CRL_MODEL_URI, "idCommissioneAnagrafica");
	
	public static final QName PROP_DATA_INIZIO_LEGISLATURA = QName.createQName(CRL_MODEL_URI, "dataInizioLegislatura");
	public static final QName PROP_DATA_FINE_LEGISLATURA = QName.createQName(CRL_MODEL_URI, "dataFineLegislatura");
	public static final QName PROP_ID_ANAGRAFICA = QName.createQName(CRL_MODEL_URI, "idAnagrafica");
	
	public static final QName PROP_NOME_CONSIGLIERE_ANAGRAFICA = QName.createQName(CRL_MODEL_URI, "nomeConsigliereAnagrafica");
	public static final QName PROP_COGNOME_CONSIGLIERE_ANAGRAFICA = QName.createQName(CRL_MODEL_URI, "cognomeConsigliereAnagrafica");
	public static final QName PROP_LEGISLATURA_CONSIGLIERE_ANAGRAFICA = QName.createQName(CRL_MODEL_URI, "legislaturaConsigliereAnagrafica");
	public static final QName PROP_GRUPPO_CONSIGLIERE_ANAGRAFICA = QName.createQName(CRL_MODEL_URI, "gruppoConsigliereAnagrafica");
	public static final QName PROP_CODICE_GRUPPO_CONSIGLIERE_ANAGRAFICA = QName.createQName(CRL_MODEL_URI, "codiceGruppoConsigliereAnagrafica");
	
	public static final QName PROP_CODICE_GRUPPO_CONSILIARE_ANAGRAFICA = QName.createQName(CRL_MODEL_URI, "codiceGruppoConsiliareAnagrafica");
	public static final QName PROP_NOME_GRUPPO_CONSILIARE_ANAGRAFICA = QName.createQName(CRL_MODEL_URI, "nomeGruppoConsiliareAnagrafica");	
	
	public static final String GROUP_COMMISSIONI = "GROUP_Commissioni";
	
    public static String QUERY_CURRENT_LEGISLATURE = "SELECT id_legislatura, num_legislatura, durata_legislatura_da, durata_legislatura_a FROM legislature WHERE attiva = '1'";
    public static String QUERY_LEGISLATURE = "SELECT id_legislatura, num_legislatura, durata_legislatura_da, durata_legislatura_a FROM legislature";
    public static String COLUMN_LEGISLATURE_ID = "id_legislatura";
    public static String COLUMN_LEGISLATURE_NUMBER = "num_legislatura";
    public static String COLUMN_LEGISLATURE_FROM = "durata_legislatura_da";
    public static String COLUMN_LEGISLATURE_TO = "durata_legislatura_a";
    
    public static String QUERY_CURRENT_GRUPPI_CONSILIARI = "SELECT id_gruppo, codice_gruppo, nome_gruppo FROM gruppi_politici WHERE attivo = '1' AND deleted = 0";
    public static String COLUMN_GRUPPI_CONSILIARI_ID = "id_gruppo";
    public static String COLUMN_GRUPPI_CONSILIARI_CODICE = "codice_gruppo";
    public static String COLUMN_GRUPPI_CONSILIARI_NOME = "nome_gruppo";

    public static String QUERY_ALL_COUNCILORS_FILTERED_BY_LEGISLATURE
                         = "SELECT DISTINCT ll.num_legislatura, " +
                        		 		   "pp.id_persona, " +
                                           "pp.cognome, " +
                                           "pp.nome, " +
                                           "gg.nome_gruppo, " +
                                           "gg.codice_gruppo, " +
										   "oo.id_organo, " +
                                           "oo.deleted, " +
                                           "( " +
                                             "STUFF( " +
                                                    "( " +
                                                       "SELECT '#' + oo.nome_organo + '!' + ISNULL(convert(varchar(max),oo.ordinamento) + '!' ,'') + ISNULL(convert(varchar(max),oo.id_organo) + '!' ,'') + ISNULL(convert(varchar(max),oo.deleted) ,'') " +
                                                       "FROM persona ppo " +
                                                       "INNER JOIN join_persona_organo_carica AS jpoc " +
                                                               "ON ppo.id_persona = jpoc.id_persona " +
                                                       "INNER JOIN legislature ll " +
                                                               "ON ll.id_legislatura = jpoc.id_legislatura " +
                                                       "INNER JOIN organi oo " +
                                                               "ON oo.id_organo = jpoc.id_organo " +
                                                       "WHERE ppo.deleted = 0 " +
                                                         "AND oo.deleted = 0 " +
                                                         "AND (oo.id_tipo_organo = 2 OR oo.id_tipo_organo = 5) " +
                                                         "AND jpoc.deleted = 0 " +
                                                         "AND jpoc.id_legislatura = ? " +
                                                         "AND jpoc.data_fine is null " +
                                                         "AND ppo.id_persona = pp.id_persona " +
                                                       "ORDER BY oo.nome_organo " +
                                                       "FOR XML PATH(''), TYPE " +
                                                     ").value('.','varchar(max)') " +
                                             ",1,1, '') " +
                                           ") as Organi " +
                           "FROM persona AS pp " +
                           "INNER JOIN join_persona_gruppi_politici jpg " +
                                   "ON pp.id_persona = jpg.id_persona " +
                           "INNER JOIN gruppi_politici gg " +
                                   "ON gg.id_gruppo = jpg.id_gruppo " +
                           "INNER JOIN join_persona_organo_carica AS jpoc " +
                                   "ON pp.id_persona = jpoc.id_persona " +
                           "INNER JOIN legislature ll " +
                                   "ON ll.id_legislatura = jpoc.id_legislatura " +
                           "INNER JOIN organi oo " +
                                   "ON oo.id_organo = jpoc.id_organo " +
                           "WHERE pp.deleted = 0 " +
                             "AND jpoc.deleted = 0 " +
                             "AND pp.deleted = 0 " +
                             "AND jpg.deleted = 0 " +
                             "AND gg.deleted = 0 " +
                             "AND oo.deleted = 0 " +
                             "AND jpg.data_fine is null " +
                             "AND jpoc.data_fine is null " +
                             "AND ll.id_legislatura = ? " +
                           "ORDER BY cognome, nome";
    public static String COLUMN_NOME = "nome";
    public static String COLUMN_ID_PERSONA = "id_persona";
    public static String COLUMN_COGNOME = "cognome";
    public static String COLUMN_NOME_GRUPPO = "nome_gruppo";
    public static String COLUMN_CODICE_GRUPPO = "codice_gruppo";
    public static String COLUMN_NUMERO_LEGISLATURA = "num_legislatura";
    public static String COLUMN_ORGANI = "Organi";
    
    public static String QUERY_CURRENT_GRUPPI_CONSILIARI_MIGRAZIONE = "SELECT gp.id_gruppo, gp.codice_gruppo, gp.nome_gruppo FROM gruppi_politici gp, join_gruppi_politici_legislature gpl WHERE gp.deleted = 0 AND gpl.deleted = 0 AND gpl.id_legislatura = 24 AND gp.id_gruppo = gpl.id_gruppo";
    
    public static String QUERY_ALL_COUNCILORS_FILTERED_BY_LEGISLATURE_MIGRAZIONE = "SELECT DISTINCT " +
	"ll.num_legislatura, " +
	"pp.id_persona, " +
	"pp.cognome, " +
	"pp.nome, " +
	"gg.nome_gruppo, " +
	"gg.codice_gruppo, " +
	"( " +
		"STUFF( " +
			"( " +
				"SELECT DISTINCT " +
					"'#' + oo.nome_organo + '!' + ISNULL( " +
						"convert(varchar(max), oo.ordinamento), " +
						"'' " +
					") " +
				"FROM " +
					"persona ppo " +
				"INNER JOIN join_persona_organo_carica AS jpoc ON ppo.id_persona = jpoc.id_persona " +
				"INNER JOIN legislature ll ON ll.id_legislatura = jpoc.id_legislatura " +
				"INNER JOIN organi oo ON oo.id_organo = jpoc.id_organo " +
				"WHERE " +
					"ppo.deleted = 0 " +
				"AND oo.deleted = 0 " +
				"AND oo.id_tipo_organo = 2 " +
				"AND jpoc.deleted = 0 " +
				"AND jpoc.id_legislatura = ? " +
				"AND ppo.id_persona = pp.id_persona " +
				"FOR XML PATH (''), " +
				"TYPE ).value('.', 'varchar(max)'), " +
				"1, " +
				"1, " +
				"'' " +
			") " +
		") AS Organi " +
		"FROM " +
			"persona AS pp " +
		"INNER JOIN join_persona_gruppi_politici jpg ON pp.id_persona = jpg.id_persona " +
		"INNER JOIN gruppi_politici gg ON gg.id_gruppo = jpg.id_gruppo " +
		"INNER JOIN join_persona_organo_carica AS jpoc ON pp.id_persona = jpoc.id_persona " +
		"INNER JOIN legislature ll ON ll.id_legislatura = jpoc.id_legislatura " +
		"INNER JOIN organi oo ON oo.id_organo = jpoc.id_organo " +
		"WHERE " +
			"pp.deleted = 0 " +
		"AND jpoc.deleted = 0 " +
		"AND pp.deleted = 0 " +
		"AND jpg.deleted = 0 " +
		"AND gg.deleted = 0 " +
		"AND oo.deleted = 0 " +
		"AND jpg.data_fine = ( " +
			"SELECT " +
				"MAX (jpg2.data_fine) " +
			"FROM " +
				"persona AS pp2 " +
			"INNER JOIN join_persona_gruppi_politici jpg2 ON pp2.id_persona = jpg2.id_persona " +
			"INNER JOIN gruppi_politici gg2 ON gg2.id_gruppo = jpg2.id_gruppo " +
			"INNER JOIN join_persona_organo_carica AS jpoc2 ON pp2.id_persona = jpoc2.id_persona " +
			"INNER JOIN legislature ll2 ON ll2.id_legislatura = jpoc2.id_legislatura " +
			"INNER JOIN organi oo2 ON oo2.id_organo = jpoc2.id_organo " +
			"WHERE " +
				"pp.id_persona = pp2.id_persona " +
			"AND pp2.deleted = 0 " +
			"AND jpoc2.deleted = 0 " +
			"AND pp2.deleted = 0 " +
			"AND jpg2.deleted = 0 " +
			"AND gg2.deleted = 0 " +
			"AND oo2.deleted = 0 " +
			"AND ll2.id_legislatura = ? " +
		") " + 
		"AND ll.id_legislatura = 24 " +
		"ORDER BY " +
			"cognome, " +
			"nome";
}
