package org.alfresco.mock;

import static java.lang.Class.forName;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test class for verifying functionality.
 * 
 * @author vige
 */
public class ClasspathTestRunner extends SpringJUnit4ClassRunner {

  static ClassLoader customClassLoader;

  /**
   * Constructs a new classpath test runner.
   *
   * @param clazz the clazz
	 * @return the result
   */
  public ClasspathTestRunner(Class<?> clazz) throws InitializationError {
    super(loadFromCustomClassloader(clazz));
  }

  // Loads a class in the custom classloader
  /**
   * Load from custom classloader.
   *
   * @param clazz the clazz
   * @return the class
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

  // Runs junit tests in a separate thread using the custom class loader
  @Override
  /**
   * Run.
   *
   * @param notifier the notifier
   */
  public void run(final RunNotifier notifier) {
    Runnable runnable = new Runnable() {
      @Override
      /**
       * Run.
       *
       */
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