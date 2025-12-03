package org.alfresco.mock;

import static java.lang.Class.forName;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Custom JUnit test runner that uses a custom class loader for loading test classes.
 * This allows tests to run with module-specific classpath configurations.
 *
 * @author lucastancapiano
 */
public class ClasspathTestRunner extends SpringJUnit4ClassRunner {

  /** The custom class loader instance. */
  static ClassLoader customClassLoader;

  /**
   * Constructs a new ClasspathTestRunner for the given test class.
   *
   * @param clazz the test class to run
   * @throws InitializationError if the test class cannot be loaded
   */
  public ClasspathTestRunner(Class<?> clazz) throws InitializationError {
    super(loadFromCustomClassloader(clazz));
  }

  /**
   * Loads a class using the custom class loader.
   *
   * @param clazz the class to load
   * @return the loaded class from the custom class loader
   * @throws InitializationError if the class cannot be found
   */
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

  /**
   * Runs the JUnit tests in a separate thread using the custom class loader.
   *
   * @param notifier the run notifier for test events
   */
  @Override
  public void run(final RunNotifier notifier) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
          ClasspathTestRunner.super.run(notifier);
      }
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
