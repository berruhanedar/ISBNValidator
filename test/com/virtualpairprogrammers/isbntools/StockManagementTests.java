package com.virtualpairprogrammers.isbntools;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;


public class StockManagementTests {

    ExternalISBNDataService testWebService;
    StockManager stockManager;
    ExternalISBNDataService testDatabaseService;

    @Before
    public void setUp() {
        testWebService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setwebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);
        testDatabaseService = mock(ExternalISBNDataService.class);
    }

    @Test
    public void testCanGetACorrectLocatorCode() {

        /*
        STUB IMPL

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
         */

        // As this point we have actually got a fake or a dummy because there is no implementation here
        // ExternalISBNDataService testWebService = mock(ExternalISBNDataService.class);

        // Now its stub
        when(testWebService.lookUp(anyString())).thenReturn(new Book("0140177396", "Of Mice and Men", "J. Steinbeck"));

        // Use in global var
        // ExternalISBNDataService testDatabaseService = mock(ExternalISBNDataService.class);
        when(testDatabaseService.lookUp(anyString())).thenReturn(null);


        // StockManager stockManager = new StockManager();
        // stockManager.setwebService(testWebService);
        // stockManager.setDatabaseService(testDatabaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);

    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        // We don't need any of this code at all. We managed to remove some of the code duplication
        // ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class); // This line of code will do is it creates for us a dummy class
        // ExternalISBNDataService webService = mock(ExternalISBNDataService.class); // we don't need to override lookup method

        when(testDatabaseService.lookUp("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

        //StockManager stockManager = new StockManager();
        stockManager.setwebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDatabaseService, times(1)).lookUp("0140177396");
        // verify(databaseService).lookUp("0140177396");
        verify(testWebService, times(0)).lookUp(anyString());
        // verify(webService, never()).lookUp(anyString());
    }

    @Test
    public void webServiceIsUsedIfDataIsNotPresentInDatabase() {
        // ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class); // This line of code will do is it creates for us a dummy class
        // ExternalISBNDataService webService = mock(ExternalISBNDataService.class); // we don't need to override lookup method

        when(testDatabaseService.lookUp("0140177396")).thenReturn(null);
        when(testWebService.lookUp("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));

        StockManager stockManager = new StockManager();
        stockManager.setwebService(testWebService);
        stockManager.setDatabaseService(testDatabaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDatabaseService, times(1)).lookUp("0140177396");
        verify(testWebService, times(1)).lookUp("0140177396");
    }

}
