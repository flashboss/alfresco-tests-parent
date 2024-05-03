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
            pathResult = findFirstFileContainingTerm(pathRoot, searchTerm).toUri().toURL();
        } catch (Exception e) {
            logger.warn(searchTerm + " not found in " + modulePath);
        }
        return pathResult;
    }

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
