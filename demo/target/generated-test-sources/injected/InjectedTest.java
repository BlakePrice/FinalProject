import java.util.*;
/**
 * Entry point to auto-generated tests (generated by maven-hpi-plugin).
 * If this fails to compile, you are probably using Hudson &lt; 1.327. If so, disable
 * this code generation by configuring maven-hpi-plugin to &lt;disabledTestInjection&gt;true&lt;/disabledTestInjection&gt;.
 */
public class InjectedTest extends junit.framework.TestCase {
  public static junit.framework.Test suite() throws Exception {
    System.out.println("Running tests for "+"io.jenkins.plugins:demo:1.0-SNAPSHOT");
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("basedir","C:\\Users\\Amber\\OneDrive\\Documents\\School (2017-2018)\\sem 2\\CS 498\\Final Project\\FinalProject\\demo");
    parameters.put("artifactId","demo");
    parameters.put("packaging","hpi");
    parameters.put("outputDirectory","C:\\Users\\Amber\\OneDrive\\Documents\\School (2017-2018)\\sem 2\\CS 498\\Final Project\\FinalProject\\demo\\target\\classes");
    parameters.put("testOutputDirectory","C:\\Users\\Amber\\OneDrive\\Documents\\School (2017-2018)\\sem 2\\CS 498\\Final Project\\FinalProject\\demo\\target\\test-classes");
    parameters.put("requirePI","true");
    return org.jvnet.hudson.test.PluginAutomaticTestBuilder.build(parameters);
  }
}
