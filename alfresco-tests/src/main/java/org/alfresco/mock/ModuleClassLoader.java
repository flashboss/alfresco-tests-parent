package org.alfresco.mock;

import static java.lang.Thread.currentThread;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;

public class ModuleClassLoader extends ClassLoader {

    private static final String SEPARATOR = "/";
    private static final String MODULE_PATH = "target/classes/alfresco/module";
    private static final String MODULE_PATH_TEST = "target/test-classes/alfresco/module";

    private Logger logger = getLogger(getClass());

    public ModuleClassLoader() {
        super(currentThread()
                .getContextClassLoader());
    }

    @Override
    public URL getResource(String name) {
        URL url = super.getResource(name);
        if (url == null && name != null && !name.contains(SEPARATOR)) {
            url = find(MODULE_PATH_TEST, name);
            if (url == null)
                url = find(MODULE_PATH, name);
        }
        return url;
    }

    public URL find(String modulePath, String searchTerm) {
        URL pathResult = null;
        URI directoryPath = new File(modulePath).toURI();
        try {
            Path pathRoot = Paths.get(directoryPath);
            pathResult = Files.find(pathRoot, Integer.MAX_VALUE,
                    (path, attr) -> path.getFileName().toString().contains(searchTerm))
                    .findFirst()
                    .orElse(null).toUri().toURL();
        } catch (Exception e) {
            logger.warn(searchTerm + " not found in " + modulePath);
        }
        return pathResult;
    }
}
