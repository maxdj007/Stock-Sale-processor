package com.navi.stocksaleprocessor.service.impl;

import com.navi.stocksaleprocessor.service.FileHandler;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileHandlerImpl implements FileHandler {

    private static org.slf4j.Logger logger =
            LoggerFactory.getLogger(FileHandlerImpl.class);

    @Override
    public boolean fileExists(String fileName) {
        return new File(fileName).isFile();
    }

    @Override
    public List<String[]> readData(String name) {
        List<String[]> dataLines = new ArrayList<>();
        File file= new File(name);
        try{
            Scanner inputStream= new Scanner(file);
            while(inputStream.hasNextLine()){
                String data= inputStream.nextLine();
                String[] values = data.split(" ");
                dataLines.add(values);
            }
            inputStream.close();
        }catch (FileNotFoundException e) {
            logger.error("error trying to read file - {}", name, e);
        }
        return dataLines;
    }
}
