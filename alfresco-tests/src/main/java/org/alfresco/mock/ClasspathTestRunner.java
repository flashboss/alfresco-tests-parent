package org.alfresco.mock;

import static java.lang.Class.forName;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Custom JUnit test runner that loads test classes using a custom classloader.
 * This runner extends SpringJUnit4ClassRunner to support Spring integration
 * while using ModuleClassLoader to find resources within Alfresco module paths.
 * It enables parallel test execution with module-specific resource loading.
 * 
 * @author vige
 */
public class ClasspathTestRunner extends SpringJUnit4ClassRunner {

  /** Custom classloader used to load test classes. Shared across all tests for parallel execution support. */
  static ClassLoader customClassLoader;

  /**
   * Creates a new ClasspathTestRunner for the given test class.
   * The test class is loaded using the custom ModuleClassLoader.
   * 
   * @param clazz the test class to run
   * @throws InitializationError if the test class cannot be loaded
   */
  public ClasspathTestRunner(Class<?> clazz) throws InitializationError {
    super(loadFromCustomClassloader(clazz));
  }

  /**
   * Loads a class using the custom ModuleClassLoader.
   * The classloader is initialized lazily and reused for parallel test support.
   * 
   * @param clazz the class to load
   * @return the class loaded by the custom classloader
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
   * Runs the JUnit tests in a separate thread using the custom classloader.
   * This ensures that all test code uses the ModuleClassLoader for resource loading.
   * 
   * @param notifier the run notifier to report test events to
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