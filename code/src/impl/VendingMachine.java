package impl;


import exceptions.LaneCodeAlreadyInUseException;
import exceptions.LaneCodeNotRegisteredException;
import exceptions.ProductUnavailableException;
import interfaces.IProductRecord;
import interfaces.IVendingMachineProduct;
import interfaces.IVendingMachine;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This class represents a simple vending machine which can stock and sell products.
 *
 */
public class VendingMachine implements IVendingMachine {

    @Override
    public void registerProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeAlreadyInUseException {
        // TODO Auto-generated method stub
        Enumeration<IVendingMachineProduct> keys = registerProduct.keys();
        while (keys.hasMoreElements()) {
            IVendingMachineProduct product = keys.nextElement();
            IProductRecord record = registerProduct.get(product);

            if (vendingMachineProduct.getLaneCode().equals(product.getLaneCode())) {
                throw new LaneCodeAlreadyInUseException();
            }
        }
        IProductRecord productRecord = Factory.getInstance().makeProductRecord(vendingMachineProduct);
        IVendingMachineProduct newProduct = Factory.getInstance().makeVendingMachineProduct(vendingMachineProduct.getLaneCode(), vendingMachineProduct.getDescription());
        registerProduct.put(newProduct, productRecord);
    }

    @Override
    public void unregisterProduct(IVendingMachineProduct vendingMachineProduct) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        IVendingMachineProduct checkProduct = null;
        Enumeration<IVendingMachineProduct> keys = registerProduct.keys();

        while (keys.hasMoreElements()) {
            IVendingMachineProduct product = keys.nextElement();
            IProductRecord record = registerProduct.get(product);

            if (vendingMachineProduct.getLaneCode().equals(product.getLaneCode())) {
                checkProduct = product;
                break;
            }
        }
        if (checkProduct == null) {
            throw new LaneCodeNotRegisteredException();
        }
        registerProduct.remove(checkProduct);
    }

    @Override
    public void addItem(String laneCode) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        IVendingMachineProduct checkProduct = null;
        Enumeration<IVendingMachineProduct> keys = registerProduct.keys();
        while (keys.hasMoreElements()) {
            IVendingMachineProduct product = keys.nextElement();
            IProductRecord record = registerProduct.get(product);

            if (laneCode.equals(product.getLaneCode())) {
                checkProduct = product;
                break;
            }
        }
        if (checkProduct == null) {
            throw new LaneCodeNotRegisteredException();
        }
        // Add Item in ProductRecord
        registerProduct.get(checkProduct).addItem();
    }

    @Override
    public void buyItem(String laneCode) throws ProductUnavailableException, LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        IVendingMachineProduct checkProduct = null;
        Enumeration<IVendingMachineProduct> keys = registerProduct.keys();
        while (keys.hasMoreElements()) {
            IVendingMachineProduct product = keys.nextElement();
            IProductRecord record = registerProduct.get(product);

            if (laneCode.equals(product.getLaneCode())) {
                checkProduct = product;
                if (record.getNumberAvailable() > 0) {
                    record.buyItem();
                }
                else {
                    throw new ProductUnavailableException();
                }
                break;
            }
        }
        if (checkProduct == null) {
            throw new LaneCodeNotRegisteredException();
        }
    }

    @Override
    public int getNumberOfProducts() {
        // TODO Auto-generated method stub
        return registerProduct.size();
    }

    @Override
    public int getTotalNumberOfItems() {
        // TODO Auto-generated method stub
        int total = 0;
        Enumeration<IVendingMachineProduct> keys = registerProduct.keys();
        while (keys.hasMoreElements()) {
            IVendingMachineProduct product = keys.nextElement();
            IProductRecord record = registerProduct.get(product);
            total += record.getNumberAvailable();
        }
        return total;
    }

    @Override
    public int getNumberOfItems(String laneCode) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        int total = 0;
        IVendingMachineProduct checkProduct = null;
        Enumeration<IVendingMachineProduct> keys = registerProduct.keys();
        while (keys.hasMoreElements()) {
            IVendingMachineProduct product = keys.nextElement();
            IProductRecord record = registerProduct.get(product);
            if (laneCode.equals(product.getLaneCode())) {
                total = record.getNumberAvailable();
                checkProduct = product;
                break;
            }
        }
        if (checkProduct == null) {
            throw new LaneCodeNotRegisteredException();
        }
        return total;
    }

    @Override
    public int getNumberOfSales(String laneCode) throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        int total = 0;
        IVendingMachineProduct checkProduct = null;
        Enumeration<IVendingMachineProduct> keys = registerProduct.keys();
        while (keys.hasMoreElements()) {
            IVendingMachineProduct product = keys.nextElement();
            IProductRecord record = registerProduct.get(product);
            if (laneCode.equals(product.getLaneCode())) {
                total = record.getNumberOfSales();
                checkProduct = product;
                break;
            }
        }
        if (checkProduct == null) {
            throw new LaneCodeNotRegisteredException();
        }
        return total;
    }

    @Override
    public IVendingMachineProduct getMostPopular() throws LaneCodeNotRegisteredException {
        // TODO Auto-generated method stub
        int mostPopularAmount = 0;
        Enumeration<IVendingMachineProduct> keys = registerProduct.keys();
        IVendingMachineProduct mostPopularProduct = null;
        while (keys.hasMoreElements()) {
            IVendingMachineProduct product = keys.nextElement();
            IProductRecord record = registerProduct.get(product);
            if (mostPopularAmount < record.getNumberOfSales()) {
                mostPopularAmount = record.getNumberOfSales();
                mostPopularProduct = product;
            }
        }
        if  (mostPopularProduct == null) {
            throw new LaneCodeNotRegisteredException();
        }
        return mostPopularProduct;
    }

    private Dictionary<IVendingMachineProduct, IProductRecord> registerProduct = new Hashtable<IVendingMachineProduct, IProductRecord>();

}
