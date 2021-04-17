package com.rockettravel.app.services;

import com.rockettravel.app.model.CashRegistry;
import com.rockettravel.app.util.CashRegistryException;
import com.rockettravel.app.util.Constants;

import java.io.BufferedReader;
import java.io.Reader;

public class ServiceManager {

    final CashRegistry cashRegistry;
    final Reader reader;

    public ServiceManager(Reader reader) {
        this.cashRegistry = new CashRegistry();
        this.reader = reader;
    }

    // FOR TESTING PURPOSE
    public CashRegistry getCashRegistry() {
        return this.cashRegistry;
    }

    public void runApplication() {

        final BufferedReader br = new BufferedReader(reader);

        String operation = "";

        do {
            try {
                final String command = br.readLine().trim();
                final String[] information = command.split(" ");

                operation = information[Constants.OPERATION_INDEX].toUpperCase();

                switch(operation) {
                    case Constants.SHOW_COMMAND:
                        if(information.length != 1) {
                            throw new CashRegistryException(Constants.INVALID_SHOW_QUIT_INPUT_FORMAT);
                        }
                        this.cashRegistry.printInformation();
                        break;

                    case Constants.PUT_COMMAND:
                        performPutOperation(information);
                        this.cashRegistry.printInformation();
                        break;

                    case Constants.TAKE_COMMAND:
                        performTakeOperation(information);
                        this.cashRegistry.printInformation();
                        break;

                    case Constants.CHANGE_COMMAND:
                        performChangeOperation(information);
                        break;

                    case Constants.QUIT_COMMAND:
                        if(information.length != 1) {
                            throw new CashRegistryException(Constants.INVALID_SHOW_QUIT_INPUT_FORMAT);
                        }
                        System.out.println(Constants.EXIT_MESSAGE);
                        break;

                    default:
                        throw new CashRegistryException(Constants.INVALID_OPERATION);

                }

            } catch (Exception ex) {
                System.out.println("ERROR : " + ex.toString());
            }
        } while(!operation.equalsIgnoreCase(Constants.QUIT_COMMAND));

    }

    private int[] inputDataValidation(final String[] information) throws CashRegistryException {

        if(information.length != 6) {
            throw new CashRegistryException(Constants.INVALID_PUT_TAKE_INPUT_FORMAT);
        }

        int twentyBills;
        int tenBills;
        int fiveBills;
        int twoBills;
        int oneBills;

        try {

            twentyBills = Integer.parseInt(information[Constants.TWENTY_BILL_INDEX]);
            tenBills = Integer.parseInt(information[Constants.TEN_BILL_INDEX]);
            fiveBills = Integer.parseInt(information[Constants.FIVE_BILL_INDEX]);
            twoBills = Integer.parseInt(information[Constants.TWO_BILL_INDEX]);
            oneBills = Integer.parseInt(information[Constants.ONE_BILL_INDEX]);

            if(twentyBills < 0 || tenBills < 0 || fiveBills < 0 || twoBills < 0 || oneBills < 0) {
                throw new CashRegistryException(Constants.NEGATIVE_INPUT);
            }

        } catch (CashRegistryException ex) {
            throw new CashRegistryException(ex.getMessage());
        } catch (Exception ex) {
            throw new CashRegistryException(Constants.INVALID_PUT_TAKE_INPUT_FORMAT);
        }

        return new int[]{twentyBills, tenBills, fiveBills, twoBills, oneBills};

    }

    private boolean takeOperationDataValidation(final int[] billsCount) throws CashRegistryException {

        final StringBuilder errorMessage = new StringBuilder();

        if(this.cashRegistry.getTwentyBills() < billsCount[Constants.TWENTY_BILL_INDEX - 1]) {
            errorMessage.append(Constants.INSUFFICIENT_TWENTY_BILLS + "\n");
        }
        if(this.cashRegistry.getTenBills() < billsCount[Constants.TEN_BILL_INDEX - 1]) {
            errorMessage.append(Constants.INSUFFICIENT_TEN_BILLS + "\n");
        }
        if(this.cashRegistry.getFiveBills() < billsCount[Constants.FIVE_BILL_INDEX - 1]) {
            errorMessage.append(Constants.INSUFFICIENT_FIVE_BILLS + "\n");
        }
        if(this.cashRegistry.getTwoBills() < billsCount[Constants.TWO_BILL_INDEX - 1]) {
            errorMessage.append(Constants.INSUFFICIENT_TWO_BILLS + "\n");
        }
        if(this.cashRegistry.getOneBills() < billsCount[Constants.ONE_BILL_INDEX - 1]) {
            errorMessage.append(Constants.INSUFFICIENT_ONE_BILLS + "\n");
        }

        if(errorMessage.length() != 0) {
            throw new CashRegistryException(errorMessage.toString());
        }

        return Boolean.TRUE;

    }

    private void performPutOperation(final String[] information) throws CashRegistryException {

        final int[] billsCount = inputDataValidation(information);

        this.cashRegistry.putTwentyBills(billsCount[Constants.TWENTY_BILL_INDEX - 1]);
        this.cashRegistry.putTenBills(billsCount[Constants.TEN_BILL_INDEX - 1]);
        this.cashRegistry.putFiveBills(billsCount[Constants.FIVE_BILL_INDEX - 1]);
        this.cashRegistry.putTwoBills(billsCount[Constants.TWO_BILL_INDEX - 1]);
        this.cashRegistry.putOneBills(billsCount[Constants.ONE_BILL_INDEX - 1]);

    }

    private void performTakeOperation(final String[] information) throws CashRegistryException {

        final int[] billsCount = inputDataValidation(information);

        if(takeOperationDataValidation(billsCount)) {
            this.cashRegistry.takeTwentyBills(billsCount[Constants.TWENTY_BILL_INDEX - 1]);
            this.cashRegistry.takeTenBills(billsCount[Constants.TEN_BILL_INDEX - 1]);
            this.cashRegistry.takeFiveBills(billsCount[Constants.FIVE_BILL_INDEX - 1]);
            this.cashRegistry.takeTwoBills(billsCount[Constants.TWO_BILL_INDEX - 1]);
            this.cashRegistry.takeOneBills(billsCount[Constants.ONE_BILL_INDEX - 1]);
        }

    }

    private void performChangeOperation(final String[] information) throws CashRegistryException {

        if(information.length != 2) {
            throw new CashRegistryException(Constants.INVALID_CHANGE_INPUT_FORMAT);
        }

        int amount;

        try {
            amount = Integer.parseInt(information[Constants.AMOUNT_INDEX]);
        } catch (Exception ex) {
            throw new CashRegistryException(Constants.INVALID_CHANGE_INPUT_FORMAT);
        }

        if(amount > this.cashRegistry.getTotal()) {
            throw new CashRegistryException(Constants.INSUFFICIENT_FUND);
        }

        if(amount < 0) {
            throw new CashRegistryException(Constants.NEGATIVE_AMOUNT);
        }

        final int[] billsRequired = getChangeDetails(amount);

        if(billsRequired == null) {
            throw new CashRegistryException(Constants.NO_CHANGE_POSSIBLE);
        } else {

            System.out.println(String.format("%d\t%d\t%d\t%d\t%d",
                                              billsRequired[Constants.TWENTY_BILL_INDEX - 1],
                                              billsRequired[Constants.TEN_BILL_INDEX - 1],
                                              billsRequired[Constants.FIVE_BILL_INDEX - 1],
                                              billsRequired[Constants.TWO_BILL_INDEX - 1],
                                              billsRequired[Constants.ONE_BILL_INDEX - 1]));

            this.cashRegistry.takeTwentyBills(billsRequired[Constants.TWENTY_BILL_INDEX - 1]);
            this.cashRegistry.takeTenBills(billsRequired[Constants.TEN_BILL_INDEX - 1]);
            this.cashRegistry.takeFiveBills(billsRequired[Constants.FIVE_BILL_INDEX - 1]);
            this.cashRegistry.takeTwoBills(billsRequired[Constants.TWO_BILL_INDEX - 1]);
            this.cashRegistry.takeOneBills(billsRequired[Constants.ONE_BILL_INDEX - 1]);
        }

    }

    private int[] getChangeDetails(final int amount) {

        /*
            Intuition: 20A + 10B + 5C + 2D + E = amount
            where A, B, C, D, E would be the quantity of each denomination and will range from zero
            to the count available in the registry
        */

        int A, B, C, D, E;

        for(A = Math.min(this.cashRegistry.getTwentyBills(), amount / 20);
            A >= 0; A--) {

            for(B = Math.min(this.cashRegistry.getTenBills(), (amount - 20 * A)/10);
                B >= 0; B--) {

                for(C = Math.min(this.cashRegistry.getFiveBills(), (amount - 20 * A - 10 * B)/5);
                    C >= 0; C--) {

                    for(D = Math.min(this.cashRegistry.getTwoBills(), (amount - 20 * A - 10 * B - 5 * C)/2);
                        D >= 0; D--) {

                        for(E = Math.min(this.cashRegistry.getOneBills(), (amount - 20 * A - 10 * B - 5 * C - 2 * D));
                            E >= 0; E--) {

                            if( 20 * A + 10 * B + 5 * C + 2 * D + E == amount ) {

                                return new int[] {A, B, C, D, E};

                            }

                        }

                    }

                }

            }

        }

        return null;

    }

}
