package com.rockettravel.app.services;

import com.rockettravel.app.model.CashRegistry;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class ServiceMangerTest {

    @Test
    public void checkPutOperation_OnValidInput_UpdateTheCount() {

        Reader reader = new StringReader("put 1 2 3 4 5\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry(1,2,3,4,5);

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

    }


    @Test
    public void checkPutOperation_OnInvalidInput_NoUpdate() {

        // CASE 1: LESS NUMBER OF INPUT
        Reader reader = new StringReader("put 1 2 3\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

        // CASE 2: MORE NUMBER OF INPUT
        reader = new StringReader("put 1 2 3 4 5 6 7\nquit");

        serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

        // CASE 3: NEGATIVE INPUT
        reader = new StringReader("put 1 2 3 -4 5\nquit");

        serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

        // CASE 4: INPUT IS NOT NUMBER
        reader = new StringReader("put a b ? ## 2\nquit");

        serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

    }

    @Test
    public void checkTakeOperation_OnInsufficientFund_NoUpdate() {

        Reader reader = new StringReader("put 0 0 1 0 0\ntake 1 2 3 4 5\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry(0,0,1,0,0);

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

    }

    @Test
    public void checkTakeOperation_OnSufficientFund_UpdateTheCount() {

        Reader reader = new StringReader("put 10 9 8 7 6\ntake 1 2 3 4 5\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry(9,7,5,3,1);

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

    }

    @Test
    public void checkTakeOperation_OnInvalidInput_NoUpdate() {

        // CASE 1: LESS NUMBER OF INPUT
        Reader reader = new StringReader("put 5 6 7 8 9\ntake 1 2 3\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry(5,6,7,8,9);

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

        // CASE 2: MORE NUMBER OF INPUT
        reader = new StringReader("put 5 6 7 8 9\ntake 1 2 3 4 5 6 7\nquit");

        serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

        // CASE 3: ON NEGATIVE INPUT
        reader = new StringReader("put 5 6 7 8 9\ntake 1 -2 3 4 5\nquit");

        serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

        // CASE 4: INPUT IS NOT NUMBER
        reader = new StringReader("put 5 6 7 8 9\ntake abc a ? 4 5\nquit");

        serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

    }

    @Test
    public void checkShowOperation_OnValidInputFormat() {
        Reader reader = new StringReader("show\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());
    }

    @Test
    public void checkShowOperation_OnInvalidInputFormat() {
        Reader reader = new StringReader("show 100\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());
    }

    @Test
    public void checkQuitOperation_OnInvalidInputFormat() {
        Reader reader = new StringReader("quit 100\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());
    }

    @Test
    public void checkChangeOperation_OnSufficientChange_CaseI_UpdateTheCount() {
        Reader reader = new StringReader("put 1 0 3 4 0\nchange 11\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry(1, 0, 2, 1, 0);

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());
    }

    @Test
    public void checkChangeOperation_OnSufficientChange_CaseII_UpdateTheCount() {

        // TO MAKE CHANGE FOR $8 WHEN THERE IS $13 IN REGISTRY (Two $5 and three $3)

        Reader reader = new StringReader("put 0 0 2 0 3\nchange 8\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry(0, 0, 1, 0, 0);

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());
    }

    @Test
    public void checkChangeOperation_OnSufficientChange_CaseIII_UpdateTheCount() {

        // FUND IN REGISTRY IS EQUAL TO CHANGE REQUIRED

        Reader reader = new StringReader("put 1 1 1 1 1\nchange 38\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry(0, 0, 0, 0, 0);

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());
    }

    @Test
    public void checkChangeOperation_OnInsufficientFund_NoUpdate() {

        Reader reader = new StringReader("put 1 1 1 1 1\nchange 39\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry(1,1,1,1,1);

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

    }

    @Test
    public void checkChangeOperation_WhenChangeIsNotPossible_NoUpdate() {

        // TO MAKE CHANGE FOR $8 WHEN THERE IS $13 IN REGISTRY (one $10 and three $3)

        Reader reader = new StringReader("put 0 1 0 0 3\nchange 8\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry(0, 1, 0, 0, 3);

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

    }

    @Test
    public void checkChangeOperation_OnInvalidInputFormat_NoUpdate() {

        // CASE 1: WHEN AMOUNT IS MISSING

        Reader reader = new StringReader("put 1 2 3 4 5\nchange\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry(1, 2, 3, 4, 5);

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

        // CASE 2: MORE THAN ONE NUMBER FOLLOWED BY CHANGE COMMAND

        reader = new StringReader("put 1 2 3 4 5\nchange 1 2\nquit");

        serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

        // CASE 3: NEGATIVE AMOUNT

        reader = new StringReader("put 1 2 3 4 5\nchange -1\nquit");

        serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());


        // CASE 4: INPUT IS NOT NUMBER

        reader = new StringReader("put 1 2 3 4 5\nchange abc\nquit");

        serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

        reader = new StringReader("put 1 2 3 4 5\nchange #\nquit");

        serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());

    }

    @Test
    public void checkInvalidOperation_NoUpdate() {
        Reader reader = new StringReader("random\nquit");

        ServiceManager serviceManager = new ServiceManager(reader);
        serviceManager.runApplication();

        CashRegistry expectedOutput = new CashRegistry();

        assertEquals(expectedOutput.toString(), serviceManager.getCashRegistry().toString());
    }

}
