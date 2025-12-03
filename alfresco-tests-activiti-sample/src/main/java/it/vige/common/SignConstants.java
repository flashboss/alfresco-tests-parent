package it.vige.common;

/**
 * Constants for digital signature operations.
 * Defines file extensions and mimetypes for signed documents.
 * 
 * @author vige
 */
public interface SignConstants {

	/** File extension for PKCS#7 signed files. */
	String P7M_EXTENSION = "p7m";

	/** MIME type for PKCS#7 signed files. */
	String P7M_MIMETYPE = "application/x-pkcs7-mime";
	
	/** Constant indicating a resource was not found. */
	String NOT_FOUND = "NOT_FOUND";
}
