package com.navi.stocksaleprocessor.service;

import java.util.List;

public interface FileHandler {

    boolean fileExists(String fileName);

    List<String[]> readData(String name);

}
