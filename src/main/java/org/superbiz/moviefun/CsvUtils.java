package org.superbiz.moviefun;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvUtils {

    public static String readFile(String path) {
//        ClassLoader cl = CsvUtils.class.getClassLoader();
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        InputStream is = cl.getSystemResourceAsStream(path);

//        try {
//            Scanner scanner = new Scanner(new File(path)).useDelimiter("\\A");
        System.out.println("path name   : " + path);
        System.out.println("inputStream : " + is);
            Scanner scanner = new Scanner(is);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()) {
                return scanner.next();
            } else {
                return "";
            }

//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static <T> List<T> readFromCsv(ObjectReader objectReader, String path) {
        try {
            List<T> results = new ArrayList<>();

            MappingIterator<T> iterator = objectReader.readValues(readFile(path));

            while (iterator.hasNext()) {
                results.add(iterator.nextValue());
            }

            return results;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
