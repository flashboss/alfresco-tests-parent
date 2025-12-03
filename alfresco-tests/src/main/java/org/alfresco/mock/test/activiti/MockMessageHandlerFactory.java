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

	private Logger logger = getLogger(getClass());

	/**
	 * Creates a new message handler.
	 *
	 * @param ctx the message context
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
		 * Constructs a new Handler instance.
		 *
		 * @param ctx the message context
		 */
		public Handler(MessageContext ctx) {
			this.ctx = ctx;
		}

		/**
		 * Prints the from mail.
		 *
		 * @param from the from address
		 * @throws RejectException if an error occurs
		 */
		public void from(String from) throws RejectException {
			logger.info("FROM:" + from);
		}

		/**
		 * Prints the recipient mail.
		 *
		 * @param recipient the recipient address
		 * @throws RejectException if an error occurs
		 */
		public void recipient(String recipient) throws RejectException {
			logger.info("RECIPIENT:" + recipient);
		}

		/**
		 * Prints the body of the message.
		 *
		 * @param data the data input stream
		 * @return the message content as string
		 * @throws IOException if an error occurs
		 */
		public String data(InputStream data) throws IOException {
			String result = this.convertStreamToString(data);
			logger.info("MAIL DATA");
			logger.info("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
			logger.info(result);
			logger.info("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");
			return result;
		}

		/**
		 * Prints if the message is created.
		 */
		public void done() {
			logger.info("Finished");
		}

		/**
		 * Utility convert a stream in a string
		 * 
		 * @param is
		 *            The stream to convert
		 * @return The converted string
		 */
		public String convertStreamToString(InputStream is) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();

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