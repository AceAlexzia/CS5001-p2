package impl;

import interfaces.IProductRecord;
import interfaces.IVendingMachineProduct;

/**
 * This class represents products that can be stocked and sold in a vending machine in a specific lane.
 *
 */
public class VendingMachineProduct implements IVendingMachineProduct {

    VendingMachineProduct(String laneCode, String description) {
        this.laneCode = laneCode;
        this.description = description;
    }

    @Override
    public String getLaneCode() {
        // TODO Auto-generated method stub
        return laneCode;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return description;
    }
    private String laneCode;
    private String description;
}
