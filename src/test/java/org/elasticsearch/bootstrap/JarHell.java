package org.elasticsearch.bootstrap;

import java.net.URL;

/**
 * Cancel checks for duplicate class files across the classpath to avoid errors during integration tests.
 * 
 * @see https://github.com/elastic/elasticsearch/blob/master/core/src/main/java/org/elasticsearch/bootstrap/JarHell.java
 */
public class JarHell
{
   private JarHell(){}

   public static void checkJarHell() throws Exception{}

   public static void checkJarHell(URL urls[]) throws Exception{}

   public static void checkVersionFormat(String targetVersion){}

   public static void checkJavaVersion(String resource, String targetVersion){}

   public static URL[] parseClassPath()
   {
      return new URL[] {};
   }
}