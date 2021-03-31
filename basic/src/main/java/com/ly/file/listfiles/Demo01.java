package com.ly.file.listfiles;

import java.io.File;

import static com.ly.file.listfiles.FileConstants.PATHNAME;

/**
 * liyang 2021-03-31
 *
 * try to find all the files and directories in a given directory
 *
 * reference: https://www.geeksforgeeks.org/file-listfiles-method-in-java-with-examples/
 */
public class Demo01 {
    public static void main(String[] args) {
        try {
            /**
             * Create a file object
             */
            File f = new File(PATHNAME);
            System.out.println(f.getPath());

            /**
             * Get all the names of the files present in the given directory
             */
            File[] files = f.listFiles();

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
