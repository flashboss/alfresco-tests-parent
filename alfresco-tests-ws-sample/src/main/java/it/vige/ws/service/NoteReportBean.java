package it.vige.ws.service;

/**
 * Bean class representing a note in a signature verification report.
 * Contains information about the type and description of the note.
 *
 * @author lucastancapiano
 */
public class NoteReportBean {

	/**
	 * Gets the type of the note.
	 * Type 3 typically indicates an error.
	 *
	 * @return the note type code
	 */
	public int getType() {
		return 3;
	}

	/**
	 * Gets the description of the note.
	 *
	 * @return the note description
	 */
	public String getDescription() {
		return "";
	}
}
