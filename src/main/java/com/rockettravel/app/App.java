package com.rockettravel.app;

import com.rockettravel.app.services.ServiceManager;

import java.io.InputStreamReader;

public class App
{
    public static void main( String[] args )
    {

        ServiceManager serviceManager = new ServiceManager(new InputStreamReader(System.in));
        serviceManager.runApplication();

    }
}
