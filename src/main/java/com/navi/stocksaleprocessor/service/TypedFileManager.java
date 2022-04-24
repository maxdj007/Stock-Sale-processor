package com.navi.stocksaleprocessor.service;

import java.util.List;

public interface TypedFileManager <T> {

    List<T> readFromFile(String name);

}
