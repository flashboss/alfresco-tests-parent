package org.alfresco.mock.test;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.repo.content.encoding.ContentCharsetFinder;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.MimetypeService;

public class MockMimetypeService implements MimetypeService, Serializable {

	@Override
	public String getExtension(String mimetype) {
		if (mimetype.indexOf(".") >= 0)
			return mimetype.substring(mimetype.lastIndexOf("."));
		else
			return null;
	}

	@Override
	public String getMimetype(String extension) {
		if (extension != null && extension.equals("acp"))
			return MimetypeMap.MIMETYPE_ACP;
		else if (extension != null && extension.equals("zip"))
			return MimetypeMap.MIMETYPE_ZIP;
		else
			return null;
	}

	@Override
	public Map<String, String> getDisplaysByExtension() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getDisplaysByMimetype() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getExtensionsByMimetype() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getMimetypesByExtension() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isText(String mimetype) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getMimetypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String guessMimetype(String filename) {
		return getMimetype(filename);
	}

	@Override
	public String guessMimetype(String filename, ContentReader reader) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String guessMimetype(String filename, InputStream input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMimetypeIfNotMatches(ContentReader reader) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContentCharsetFinder getContentCharsetFinder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getMimetypes(String extension) {
		// TODO Auto-generated method stub
		return null;
	}

}
