package impl;


import exceptions.LaneCodeAlreadyInUseException;
import exceptions.LaneCodeNotRegisteredException;
import exceptions.ProductUnavailableException;
import interfaces.IProductRecord;
import interfaces.IVendingMachineProduct;
import interfaces.IVendingMachine;

import java.util.ArrayList;

/**
 * This class represents a simple vending machine which can stock and sell products.
 *
 */
public class VendingMachine implements IVendingMachine {

    @Override
    public void registerProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeAlreadyInUseException {
        // TODO Auto-generated method stub
        for (IVendingMachineProduct currentProduct: allRegisterProduct) {
            if (currentProduct.getLaneCode().equals(vendingMachineProduct.getLaneCode())) {
                throw new LaneCodeAlreadyInUseException();
            }
        }
        allRegisterProduct.add(vendingMachineProduct);
    }

    @Override
    public void unregisterProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        IVendingMachineProduct checkProduct = null;
        for (IVendingMachineProduct currentProduct: allRegisterProduct) {
            if(currentProduct.getLaneCode().equals(vendingMachineProduct.getLaneCode())) {
                checkProduct = currentProduct;
            }
        }
        throw new LaneCodeNotRegisteredException();
        allRegisterProduct.remove(vendingMachineProduct);
    }

    @Override
    public void addItem(String laneCode) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        for (IVendingMachineProduct product: allRegisterProduct) {
            if (!product.getLaneCode().equals(laneCode)) {
                throw new LaneCodeNotRegisteredException();
            }
        }
    }

    @Override
    public void buyItem(String laneCode) throws ProductUnavailableException, LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getNumberOfProducts() {
        // TODO Auto-generated method stub
        return allRegisterProduct.size();
    }

    @Override
    public int getTotalNumberOfItems() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNumberOfItems(String laneCode) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNumberOfSales(String laneCode) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public IVendingMachineProduct getMostPopular() throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        return null;
    }

    private ArrayList<IVendingMachineProduct> allRegisterProduct = new ArrayList<IVendingMachineProduct>();

}
