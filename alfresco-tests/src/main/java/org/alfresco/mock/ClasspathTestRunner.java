package org.alfresco.mock;

import static java.lang.Class.forName;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Mock implementation of the ClasspathTestRunner class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class ClasspathTestRunner extends SpringJUnit4ClassRunner {

  static ClassLoader customClassLoader;

  public ClasspathTestRunner(Class<?> clazz) throws InitializationError {
    super(loadFromCustomClassloader(clazz));
  }

  // Loads a class in the custom classloader
  private static Class<?> loadFromCustomClassloader(Class<?> clazz) throws InitializationError {
    try {
      // Only load once to support parallel tests
      if (customClassLoader == null) {
        
        customClassLoader = new ModuleClassLoader();
      }
      return forName(clazz.getName(), true, customClassLoader);
    } catch (ClassNotFoundException e) {
      throw new InitializationError(e);
    }
  }

  // Runs junit tests in a separate thread using the custom class loader
  /**
   * {@inheritDoc}
   */
  @Override
  public void run(final RunNotifier notifier) {
    Runnable runnable = () -> {
      super.run(notifier);
    };
    Thread thread = new Thread(runnable);
    thread.setContextClassLoader(customClassLoader);
    thread.start();
    try {
      thread.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}