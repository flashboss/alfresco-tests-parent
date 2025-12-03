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
 * Custom ClassLoader designed to find resources within Alfresco module paths.
 * This classloader extends the standard ClassLoader and adds the ability to
 * search for resources in the target/classes/alfresco/module and
 * target/test-classes/alfresco/module directories.
 * 
 * @author vige
 */
public class ModuleClassLoader extends ClassLoader {

    /** Path separator used in resource names. */
    private static final String SEPARATOR = "/";
    
    /** Path to compiled module classes. */
    private static final String MODULE_PATH = "target/classes/alfresco/module";
    
    /** Path to compiled test module classes. */
    private static final String MODULE_PATH_TEST = "target/test-classes/alfresco/module";

    /** Logger for this class. */
    private Logger logger = getLogger(getClass());

    /**
     * Creates a new ModuleClassLoader using the current thread's context classloader as parent.
     */
    public ModuleClassLoader() {
        super(currentThread()
                .getContextClassLoader());
    }

    /**
     * Gets a resource by name, first checking the parent classloader,
     * then searching in the module paths if not found.
     * 
     * @param name the name of the resource to find
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
     * Searches for a file containing the search term within the specified module path.
     * 
     * @param modulePath the path to search in
     * @param searchTerm the term to search for in file names
     * @return the URL of the first matching file, or null if not found
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
     * Walks the file tree starting from the root path and finds the first file
     * whose name contains the search term.
     * 
     * @param rootPath the root path to start searching from
     * @param searchTerm the term to search for in file names
     * @return the Path of the first matching file, or null if not found
     * @throws IOException if an I/O error occurs while walking the file tree
     */
    private Path findFirstFileContainingTerm(Path rootPath, final String searchTerm) throws IOException {
        final List<Path> foundPaths = new ArrayList<>();

        Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String fileName = file.getFileName().toString();
                if (fileName.contains(searchTerm)) {
                    foundPaths.add(file); // Aggiungi il percorso trovato alla lista
                }
                return FileVisitResult.CONTINUE;
            }
        });

        // Restituisci il primo percorso trovato, se presente
        if (!foundPaths.isEmpty()) {
            return foundPaths.get(0);
        } else {
            return null;
        }
    }
}
