package org.alfresco.mock.test;

import java.util.List;

import org.alfresco.service.cmr.avmsync.AVMDifference;
import org.alfresco.service.cmr.avmsync.AVMSyncService;
import org.alfresco.util.NameMatcher;

public class MockAVMSyncService implements AVMSyncService {

	@Override
	public List<AVMDifference> compare(int srcVersion, String srcPath, int dstVersion, String dstPath,
			NameMatcher excluder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AVMDifference> compare(int srcVersion, String srcPath, int dstVersion, String dstPath,
			NameMatcher excluder, boolean expandDirs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(List<AVMDifference> diffList, NameMatcher excluder, boolean ignoreConflicts, boolean ignoreOlder,
			boolean overrideConflicts, boolean overrideOlder, String tag, String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flatten(String layerPath, String underlyingPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetLayer(String layerPath) {
		// TODO Auto-generated method stub
		
	}

}
