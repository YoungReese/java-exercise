package com.ly.file.listfiles;

import java.io.File;
import java.io.FileFilter;

import static com.ly.file.listfiles.FileConstants.PATHNAME;

/**
 * liyang 2021-03-31
 *
 * try to find all the files and directories in a given directory which are text files
 */
public class Demo03 {
    public static void main(String args[]) {
        try {
            /**
             * Create a file object
             */
            File f = new File(PATHNAME + "/e-books");

            /**
             * Create a FileFilter
             */
            FileFilter filter = f1 -> f1.getName().endsWith("pdf");

            /**
             * Get all the names of the files present in the given directory which are text files
             */
            File[] files = f.listFiles(filter);

            System.out.println("Files are:");

            /**
             * Display the names of the files
             */
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i].getName());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
