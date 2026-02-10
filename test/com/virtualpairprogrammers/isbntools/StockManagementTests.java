package com.virtualpairprogrammers.isbntools;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;


public class StockManagementTests {

    @Test
    public void testCanGetACorrectLocatorCode() {

        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
            @Override
            public Book lookUp(String isbn) {
                return new Book(isbn, "Of Mice and Men", "J. Steinbeck");
            }
        };

        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookUp(String isbn) {
                return null;
            }
        };


        StockManager stockManager = new StockManager();
        stockManager.setwebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);

    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class); // This line of code will do is it creates for us a dummy class
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class); // we don't need to override lookup method

        when(databaseService.lookUp("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

        StockManager stockManager = new StockManager();
        stockManager.setwebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService, times(1)).lookUp("0140177396");
        // verify(databaseService).lookUp("0140177396");
        verify(webService, times(0)).lookUp(anyString());
        // verify(webService, never()).lookUp(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
        ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class); // This line of code will do is it creates for us a dummy class
        ExternalISBNDataService webService = mock(ExternalISBNDataService.class); // we don't need to override lookup method

        when(databaseService.lookUp("0140177396")).thenReturn(null);
        when(webService.lookUp("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

        StockManager stockManager = new StockManager();
        stockManager.setwebService(webService);
        stockManager.setDatabaseService(databaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService, times(1)).lookUp("0140177396");
        verify(webService, times(1)).lookUp("0140177396");
    }

}
