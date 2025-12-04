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
 * Custom classloader that searches for resources in Alfresco module paths. Extends the default
 * classloader to find resources in target/classes/alfresco/module and
 * target/test-classes/alfresco/module directories.
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

  /** Constructs a new ModuleClassLoader using the current thread's context classloader. */
  public ModuleClassLoader() {
    super(currentThread().getContextClassLoader());
  }

  /**
   * Get resource.
   *
   * @param name the name
   * @return the result
   */
  @Override
  public URL getResource(String name) {
    URL url = super.getResource(name);
    if (url == null && name != null && !name.contains(SEPARATOR)) {
      url = find(MODULE_PATH_TEST, name);
      if (url == null) url = find(MODULE_PATH, name);
    }
    return url;
  }

  /**
   * Finds a resource in the specified module path.
   *
   * @param modulePath the path to search in
   * @param searchTerm the term to search for
   * @return the URL of the found resource, or null if not found
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
   * Find first file containing term.
   *
   * @param rootPath the root path
   * @param searchTerm the search term
   * @return the result
   */
  private Path findFirstFileContainingTerm(Path rootPath, final String searchTerm)
      throws IOException {
    final List<Path> foundPaths = new ArrayList<>();

    Files.walkFileTree(
        rootPath,
        new SimpleFileVisitor<Path>() {
          /**
           * Visit file.
           *
           * @param file the file
           * @param attrs the attrs
           * @return the result
           */
          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
              throws IOException {
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
