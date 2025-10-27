package impl;

import exceptions.ProductUnavailableException;
import interfaces.IVendingMachineProduct;
import interfaces.IProductRecord;

/**
 * This class represents a ProductRecord, recording information relating to a product sold in a vending machine.
 *
 */
public class ProductRecord implements IProductRecord {

    @Override
    public IVendingMachineProduct getProduct() {
        // TODO Auto-generated method stub
        return product;
    }

    @Override
    public int getNumberOfSales() {
        // TODO Auto-generated method stub
        return saleNumber;
    }

    @Override
    public int getNumberAvailable() {
        // TODO Auto-generated method stub
        return availableNumber;
    }

    @Override
    public void addItem() {
        // TODO Auto-generated method stub
        availableNumber++;
    }

    @Override
    public void buyItem() throws ProductUnavailableException {
        // TODO Auto-generated method stub
        if (availableNumber <= 0) {
            throw new ProductUnavailableException();
        }
        availableNumber--;
        saleNumber++;

    }

    public ProductRecord(IVendingMachineProduct product) {
        saleNumber = 0;
        availableNumber = 0;
        this.product = product;
    }
    public void printRecord() {
        System.out.println("Product:" + product.getLaneCode() + ", description:" + product.getDescription() + ", SaleNumber:" + saleNumber + ", availableNumber:" + availableNumber);
    }

    private int saleNumber;
    private int availableNumber;
    private IVendingMachineProduct product;
}
