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

/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public class ModuleClassLoader extends ClassLoader {

    /** The s e p a r a t o r. */
    private static final String SEPARATOR = "/";
    /** The module path. */
    private static final String MODULE_PATH = "target/classes/alfresco/module";
    /** The module path test. */
    private static final String MODULE_PATH_TEST = "target/test-classes/alfresco/module";

    /** The logger. */
    private Logger logger = getLogger(getClass());

    /**
     * Constructs a new module class loader.
     *
	 * @return the result
     */
    public ModuleClassLoader() {
        super(currentThread()
                .getContextClassLoader());
    }

    @Override
    /**
     * Get resource.
     *
     * @param name the name
     * @return the u r l
     */
    public URL getResource(String name) {
        URL url = super.getResource(name);
        if (url == null && name != null && !name.contains(SEPARATOR)) {
            url = find(MODULE_PATH_TEST, name);
            if (url == null)
                url = find(MODULE_PATH, name);
        }
        return url;
    }

    /**
     * Find.
     *
     * @param modulePath the module path
     * @param searchTerm the search term
     * @return the u r l
     */
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
