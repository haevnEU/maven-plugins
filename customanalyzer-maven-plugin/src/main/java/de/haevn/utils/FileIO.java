package de.haevn.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
    private FileIO(){}

    public static void write(String data, File file) throws IOException {
        if(!file.exists()){
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write(data);
        }
    }
}
