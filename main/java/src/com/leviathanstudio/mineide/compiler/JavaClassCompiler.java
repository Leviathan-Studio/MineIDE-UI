package com.leviathanstudio.mineide.compiler;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class JavaClassCompiler
{
    // compiles all the Java files in a directory that is supplied
    static public void compile(String srcDir) throws IOException
    {
        File dir = new File(srcDir);
        File[] javaFiles = dir.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File file, String name)
            {
                return name.endsWith(".java");
            }
        });
        
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(diagnostics, Locale.US, Charset.forName("UTF-8"));
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(javaFiles));
        javaCompiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits).call();
        
        for(Diagnostic<?> diagnostic : diagnostics.getDiagnostics())
        {
            System.out.format("Error on line %d in %d%n", diagnostic.getLineNumber(), diagnostic.getSource().toString());
        }
        
        fileManager.close();
        return;
    }
}
