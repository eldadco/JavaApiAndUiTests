package Infra.DriverSingleton;

import SeleniumInfra.SeleniumInfraStructure;
import lombok.Getter;

public class DriverSingleton {
    @Getter
    private SeleniumInfraStructure seleniumInfra;
    private static DriverSingleton instance = null;

    private DriverSingleton() {
        this.seleniumInfra = new SeleniumInfraStructure();

    }

    public static DriverSingleton getInstance() {
        if (instance == null)
            instance = new DriverSingleton();

        return instance;
    }


}
