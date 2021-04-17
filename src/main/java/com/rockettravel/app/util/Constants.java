package com.rockettravel.app.util;

public class Constants {

    // OPERATION CONSTANTS
    public static final String SHOW_COMMAND = "SHOW";
    public static final String PUT_COMMAND = "PUT";
    public static final String CHANGE_COMMAND = "CHANGE";
    public static final String TAKE_COMMAND = "TAKE";
    public static final String QUIT_COMMAND = "QUIT";

    // MESSAGE
    public static final String EXIT_MESSAGE = "Goodbye!";

    // INPUT INDEX
    public static final int OPERATION_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public static final int TWENTY_BILL_INDEX = 1;
    public static final int TEN_BILL_INDEX = 2;
    public static final int FIVE_BILL_INDEX = 3;
    public static final int TWO_BILL_INDEX = 4;
    public static final int ONE_BILL_INDEX = 5;

    // ERROR TYPES
    public static final String NEGATIVE_INPUT = "Number of bills can't be a negative value";
    public static final String NEGATIVE_AMOUNT = "Amount can't be a negative value";
    public static final String INVALID_OPERATION = "Invalid operation";
    public static final String INVALID_PUT_TAKE_INPUT_FORMAT = "Invalid input format \n Correct format: <Command> <# of 20’s> <# of 10’s> <# of 5’s> <# of 2’s> <# of 1’s>";
    public static final String INVALID_SHOW_QUIT_INPUT_FORMAT = "Invalid input format \n Correct format: <Command>";
    public static final String INVALID_CHANGE_INPUT_FORMAT = "Invalid input format \n Correct format: change <# amount>";
    public static final String INSUFFICIENT_TWENTY_BILLS = "Not enough $20 bills in the register";
    public static final String INSUFFICIENT_TEN_BILLS = "Not enough $10 bills in the register";
    public static final String INSUFFICIENT_FIVE_BILLS = "Not enough $5 bills in the register";
    public static final String INSUFFICIENT_TWO_BILLS = "Not enough $2 bills in the register";
    public static final String INSUFFICIENT_ONE_BILLS = "Not enough $1 bills in the register";
    public static final String INSUFFICIENT_FUND = "Not enough funds in the register";
    public static final String NO_CHANGE_POSSIBLE = "No change can be made";

}
