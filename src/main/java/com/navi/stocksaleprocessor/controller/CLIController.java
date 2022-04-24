package com.navi.stocksaleprocessor.controller;

import com.navi.stocksaleprocessor.service.TransactionProcessorService;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CLIController implements CommandLineRunner {

    private static org.slf4j.Logger logger =
            LoggerFactory.getLogger(CLIController.class);

    @Autowired
    private TransactionProcessorService transactionProcessorService;

    @Override
    public void run(String... args) throws Exception {
        if(args.length < 1){
            logger.error("File was not given in the input");
        } else {
            transactionProcessorService.processTransactionsFromFile(args[0]);
        }
    }
}