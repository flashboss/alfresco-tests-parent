package org.alfresco.mock.test.activiti;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.subethamail.smtp.MessageContext;
import org.subethamail.smtp.MessageHandler;
import org.subethamail.smtp.MessageHandlerFactory;
import org.subethamail.smtp.RejectException;

/**
 * A factory of messages. It is used by the internal mail server
 * 
 * @author lucastancapiano
 *
 */
public class MockMessageHandlerFactory implements MessageHandlerFactory {

	/** The logger. */
	private Logger logger = getLogger(getClass());

	/**
	 * Create.
	 *
	 * @param ctx the ctx
	 * @return the message handler
	 */
	public MessageHandler create(MessageContext ctx) {
		return new Handler(ctx);
	}

	/**
	 * Print the message and its properties
	 * 
	 * @author lucastancapiano
	 *
	 */
	class Handler implements MessageHandler {
		MessageContext ctx;

		/**
		 * Constructs a new handler.
		 *
		 * @param ctx the ctx
		 * @return the result
		 */
		public Handler(MessageContext ctx) {
			this.ctx = ctx;
		}

		/**
		 * Prints the from mail
		 * 
		 * @param from the from
		 */
		public void from(String from) throws RejectException {
			logger.info("FROM:" + from);
		}

		/**
		 * Prints the recipient mail
		 * 
		 * @param recipient the recipient
		 */
		public void recipient(String recipient) throws RejectException {
			logger.info("RECIPIENT:" + recipient);
		}

		/**
		 * Prints the body of the message
		 * 
		 * @param data the data
		 */
		public void data(InputStream data) throws IOException {
			logger.info("MAIL DATA");
			logger.info("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
			logger.info(this.convertStreamToString(data));
			logger.info("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
		}

		/**
		 * Prints if the message is created
		 */
		public void done() {
			logger.info("Finished");
		}

		/**
		 * Utility convert a stream in a string
		 * 
		 * @param is
		 *           The stream to convert
		 * @return The converted string
		 */
		public String convertStreamToString(InputStream is) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();

			/** The line. */
			String line = null;
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				logger.error("activiti diagram", e);
			}
			return sb.toString();
		}

	}
}