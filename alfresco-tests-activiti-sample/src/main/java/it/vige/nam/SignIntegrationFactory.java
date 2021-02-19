package it.vige.nam;

import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.transport.http.HTTPConduit;

import it.vige.nam.stub.MockSignIntegration;
import it.vige.nam.stub.SignIntegration;

public class SignIntegrationFactory {

	private static SignIntegration signIntegration;

	public void setSignIntegration(SignIntegration signIntegration) {
			SignIntegrationFactory.signIntegration = new MockSignIntegration();
	}

	public SignIntegrationFactory() {
	}

	public static synchronized SignIntegration getSignIntegration() {
		return signIntegration;
	}

	public static void configureTLS(org.apache.cxf.endpoint.Client client) {
		try {
			HTTPConduit httpConduit = (HTTPConduit) client.getConduit();

			TLSClientParameters tlsParams = new TLSClientParameters();
			tlsParams.setDisableCNCheck(true);

			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
				}

				@Override
				public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };
			tlsParams.setTrustManagers(trustAllCerts);

			httpConduit.setTlsClientParameters(tlsParams);
			httpConduit.getClient().setReceiveTimeout(600000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}