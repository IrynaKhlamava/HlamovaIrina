package com.company.injection;

import com.company.injection.exeptions.FindClassesException;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ClassScanner {

    private final String CLASS_EXTENSION = ".class";
    private final Set<Class<?>> foundClasses;
    private String packageName = "";

    public ClassScanner() {
        this.foundClasses = new HashSet<>();
    }

    public Set<Class<?>> findClasses(Class<?> startClass) {
        String directory = getDirectory(startClass);
        File file = new File(directory);
        if (!file.isDirectory()) {
            throw new RuntimeException("Invalid directory" + directory);
        }
        try {
            for (File innerFile : Objects.requireNonNull(file.listFiles())) {
                scanDirectory(innerFile);
            }
        } catch (ClassNotFoundException e) {
            throw new FindClassesException("Find class failed", e.getCause());
        }
        return foundClasses;
    }

    private String getDirectory(Class<?> clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
    }

    private void scanDirectory(File file) throws ClassNotFoundException {
        String currPackageName = "";
        if (file.isDirectory()) {
            packageName += file.getName() + ".";
            for (File innerFile : Objects.requireNonNull(file.listFiles())) {
                scanDirectory(innerFile);
            }
        }
        if (!file.getName().endsWith(CLASS_EXTENSION)) {
            currPackageName = file.getName() + ".";
            packageName = packageName.replace(currPackageName, "");
            return;
        }
        String className = packageName + file.getName().replace(CLASS_EXTENSION, "");
        foundClasses.add(Class.forName(className));
    }
}
