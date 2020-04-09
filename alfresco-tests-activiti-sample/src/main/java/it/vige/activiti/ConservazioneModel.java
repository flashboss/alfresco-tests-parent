package it.vige.activiti;

import org.alfresco.service.namespace.QName;

public interface ConservazioneModel {
	static final String MCC_CONSERVAZIONE_URI = "http://www.mcc.it/model/content/conservazione/1.0";
	static final QName ASPECT_HASHABLE = QName.createQName(MCC_CONSERVAZIONE_URI, "hashable");
	static final QName TYPE_IPDA = QName.createQName(MCC_CONSERVAZIONE_URI, "IPdA");
	static final QName TYPE_PDA = QName.createQName(MCC_CONSERVAZIONE_URI, "PdA");
	static final QName PROP_PDA_ID = QName.createQName(MCC_CONSERVAZIONE_URI, "pdaId");
	static final QName PROP_PDA_ID_COUNTER = QName.createQName(MCC_CONSERVAZIONE_URI, "pdaIdCounter");
	static final QName PROP_IPDA_ID = QName.createQName(MCC_CONSERVAZIONE_URI, "ipdaId");
}