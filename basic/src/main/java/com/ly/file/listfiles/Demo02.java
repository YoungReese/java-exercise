package com.ly.file.listfiles;

import java.io.File;
import java.io.FilenameFilter;

import static com.ly.file.listfiles.FileConstants.PATHNAME;

/**
 * liyang 2021-03-31
 *
 * try to find all the files and directories in a given directory whose names start with "e"
 */
public class Demo02 {
    public static void main(String[] args) {
        try {
            /**
             * Create a file object
             */
            File f = new File(PATHNAME);

            /**
             * Create a FilenameFilter
             */
            FilenameFilter filter = (f1, name) -> name.startsWith("e");

            /**
             * Get all the names of the files present in the given directory and whose names start with "12"
             */
            File[] files = f.listFiles(filter);

            /**
             * Display the names of the files
             */
            System.out.println("Files are:");
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i].getName());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
