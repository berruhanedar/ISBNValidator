package com.virtualpairprogrammers.isbntools;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StockManagementTests {

    @Test
    public void testCanGetACorrectLocatorCode(){

        ExternalISBNDataService testService = new ExternalISBNDataService() {
            @Override
            public Book lookUp(String isbn) {
                return new Book(isbn,"Of Mice and Men","J. Steinbeck");
            }
        };

        StockManager stockManager = new StockManager();
        stockManager.setService(testService);
        String isbn = "0140177396";

        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4",locatorCode);

    }



}
