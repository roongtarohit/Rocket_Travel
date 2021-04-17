package com.rockettravel.app.model;

import com.rockettravel.app.util.CashRegistryException;
import com.rockettravel.app.util.Constants;

public class CashRegistry {

    private int twentyBills;
    private int tenBills;
    private int fiveBills;
    private int twoBills;
    private int oneBills;

    public CashRegistry() {
        this.twentyBills = 0;
        this.tenBills = 0;
        this.fiveBills = 0;
        this.twoBills = 0;
        this.oneBills = 0;
    }

    public CashRegistry(final int twentyBills, final int tenBills,
                        final int fiveBills, final int twoBills, final int oneBills) {
        this.twentyBills = twentyBills;
        this.tenBills = tenBills;
        this.fiveBills = fiveBills;
        this.twoBills = twoBills;
        this.oneBills = oneBills;
    }

    @Override
    public String toString() {
        return "CashRegistry{" +
                "twentyBills=" + twentyBills +
                ", tenBills=" + tenBills +
                ", fiveBills=" + fiveBills +
                ", twoBills=" + twoBills +
                ", oneBills=" + oneBills +
                '}';
    }

    public int getTwentyBills() {
        return twentyBills;
    }

    public void setTwentyBills(int twentyBills) {
        this.twentyBills = twentyBills;
    }

    public int getTenBills() {
        return tenBills;
    }

    public void setTenBills(int tenBills) {
        this.tenBills = tenBills;
    }

    public int getFiveBills() {
        return fiveBills;
    }

    public void setFiveBills(int fiveBills) {
        this.fiveBills = fiveBills;
    }

    public int getTwoBills() {
        return twoBills;
    }

    public void setTwoBills(int twoBills) {
        this.twoBills = twoBills;
    }

    public int getOneBills() {
        return oneBills;
    }

    public void setOneBills(int oneBills) {
        this.oneBills = oneBills;
    }

    public long getTotal() {

        return 20 * this.twentyBills + 10 * this.tenBills
                + 5 * this.fiveBills + 2 * this.twoBills + this.oneBills;

    }

    public void putTwentyBills(int count) {
        this.twentyBills += count;
    }

    public void putTenBills(int count) {
        this.tenBills += count;
    }

    public void putFiveBills(int count) {
        this.fiveBills += count;
    }

    public void putTwoBills(int count) {
        this.twoBills += count;
    }

    public void putOneBills(int count) {
        this.oneBills += count;
    }

    public void takeTwentyBills(int count) throws CashRegistryException {
        if(count > this.getTwentyBills()) {
            throw new CashRegistryException(Constants.INSUFFICIENT_TWENTY_BILLS);
        }
        this.twentyBills -= count;
    }

    public void takeTenBills(int count) throws CashRegistryException {
        if(count > this.getTenBills()) {
            throw new CashRegistryException(Constants.INSUFFICIENT_TEN_BILLS);
        }
        this.tenBills -= count;
    }

    public void takeFiveBills(int count) throws CashRegistryException {
        if(count > this.getFiveBills()) {
            throw new CashRegistryException(Constants.INSUFFICIENT_FIVE_BILLS);
        }
        this.fiveBills -= count;
    }

    public void takeTwoBills(int count) throws CashRegistryException {
        if(count > this.getTwoBills()) {
            throw new CashRegistryException(Constants.INSUFFICIENT_TWO_BILLS);
        }
        this.twoBills -= count;
    }

    public void takeOneBills(int count) throws CashRegistryException {
        if(count > this.getOneBills()) {
            throw new CashRegistryException(Constants.INSUFFICIENT_ONE_BILLS);
        }
        this.oneBills -= count;
    }

    public void printInformation() {

        System.out.println(String.format("$%d\t%d\t%d\t%d\t%d\t%d",
                getTotal(), this.twentyBills, this.tenBills, this.fiveBills, this.twoBills, this.oneBills));

    }
}
