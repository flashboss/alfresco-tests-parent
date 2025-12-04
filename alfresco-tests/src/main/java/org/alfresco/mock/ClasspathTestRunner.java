package org.alfresco.mock;

import static java.lang.Class.forName;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Custom JUnit test runner that uses a custom classloader to support
 * loading classes from Alfresco module paths.
 *
 * @author vige
 */
public class ClasspathTestRunner extends SpringJUnit4ClassRunner {

  static ClassLoader customClassLoader;

  /**
   * Constructs a new ClasspathTestRunner.
   *
   * @param clazz the test class
   * @throws InitializationError if initialization fails
   */
  public ClasspathTestRunner(Class<?> clazz) throws InitializationError {
    super(loadFromCustomClassloader(clazz));
  }

  /**
   * Loads a class using the custom classloader.
   *
   * @param clazz the class to load
   * @return the loaded class
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