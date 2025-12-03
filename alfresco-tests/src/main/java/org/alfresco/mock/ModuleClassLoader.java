package org.alfresco.mock;

import static java.lang.Thread.currentThread;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

/**
 * Custom class loader for loading Alfresco module resources.
 * Searches for resources in module-specific paths when not found in the standard classpath.
 *
 * @author lucastancapiano
 */
public class ModuleClassLoader extends ClassLoader {

    /** The path separator. */
    private static final String SEPARATOR = "/";

    /** The module path for compiled classes. */
    private static final String MODULE_PATH = "target/classes/alfresco/module";

    /** The module path for test classes. */
    private static final String MODULE_PATH_TEST = "target/test-classes/alfresco/module";

    /** The logger instance. */
    private Logger logger = getLogger(getClass());

    /**
     * Constructs a new ModuleClassLoader using the current thread's context class loader as parent.
     */
    public ModuleClassLoader() {
        super(currentThread()
                .getContextClassLoader());
    }

    /**
     * Gets a resource by name, searching in module paths if not found in the standard classpath.
     *
     * @param name the resource name
     * @return the URL of the resource, or null if not found
     */
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

    /**
     * Searches for a file in the specified module path.
     *
     * @param modulePath the module path to search in
     * @param searchTerm the file name to search for
     * @return the URL of the found file, or null if not found
     */
    public URL find(String modulePath, String searchTerm) {
        URL pathResult = null;
        URI directoryPath = new File(modulePath).toURI();
        try {
            Path pathRoot = Paths.get(directoryPath);
            pathResult = findFirstFileContainingTerm(pathRoot, searchTerm).toUri().toURL();
        } catch (Exception e) {
            logger.warn(searchTerm + " not found in " + modulePath);
        }
        return pathResult;
    }

    /**
     * Finds the first file containing the search term in the given directory tree.
     *
     * @param rootPath the root path to start the search
     * @param searchTerm the term to search for in file names
     * @return the path of the first matching file, or null if not found
     * @throws IOException if an I/O error occurs
     */
    private Path findFirstFileContainingTerm(Path rootPath, final String searchTerm) throws IOException {
        final List<Path> foundPaths = new ArrayList<>();

        Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String fileName = file.getFileName().toString();
                if (fileName.contains(searchTerm)) {
                    foundPaths.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        if (!foundPaths.isEmpty()) {
            return foundPaths.get(0);
        } else {
            return null;
        }
    }
}
