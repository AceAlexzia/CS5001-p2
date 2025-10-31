package test;

import exceptions.LaneCodeAlreadyInUseException;
import exceptions.LaneCodeNotRegisteredException;
import exceptions.ProductUnavailableException;
import impl.ProductRecord;
import impl.VendingMachine;
import impl.VendingMachineProduct;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import impl.Factory;
import interfaces.IVendingMachineProduct;
import interfaces.IVendingMachine;
import interfaces.IProductRecord;

/**
 * This is a JUnit test class for the Vending Machine.
 */
public class Tests {

    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of IVendingMachineProduct.
     */
    private IVendingMachine vendingMachine;
    private IVendingMachineProduct product1;
    private IVendingMachineProduct product2;
    private IVendingMachineProduct product3;
    private IVendingMachineProduct product4;
    private IVendingMachineProduct product5;
    private IVendingMachineProduct product6;

    /**
     * Sets up the test environment before each test case is executed.
     * <p>
     * This method initializes a new instance of the {@code VendingMachine} and
     * several {@code VendingMachineProduct} objects with sample data. It uses
     * the {@code Factory} singleton to create these instances. Each product is
     * assigned a unique slot code and name, except {@code product6}, which
     * intentionally shares the same slot code ("00") as {@code product1} for
     * testing duplicate slot behavior.
     * </p>
     *
     * <p>
     * This method is annotated with {@link org.junit.jupiter.api.BeforeEach},
     * meaning it runs automatically before every test method in the test class
     * to ensure a consistent and isolated test setup.
     * </p>
     */
    @BeforeEach
    public void setup() {
        vendingMachine = Factory.getInstance().makeVendingMachine();
        product1 = Factory.getInstance().makeVendingMachineProduct("00", "Testo");
        product2 = Factory.getInstance().makeVendingMachineProduct("01", "Lays Larb");
        product3 = Factory.getInstance().makeVendingMachineProduct("02", "Saba Recommended");
        product4 = Factory.getInstance().makeVendingMachineProduct("03", "Soba Stare");
        product5 = Factory.getInstance().makeVendingMachineProduct("04", "Sprite");
        product6 = Factory.getInstance().makeVendingMachineProduct("00", "Tanuki");
    }
    /**
     * Tests the successful registration of multiple products in the vending machine.
     * <p>
     * This test verifies that products can be correctly registered without
     * encountering any exceptions, and that the total number of registered
     * products matches the expected value.
     * </p>
     *
     * <p>
     * Specifically, it registers five unique {@code VendingMachineProduct} instances
     * and asserts that the vending machine reports having five products afterward.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a product with a duplicate lane code is registered,
     *         though this exception is not expected in this test case.
     */
    @Test
    public void registerProductTestSuccess() throws LaneCodeAlreadyInUseException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.registerProduct(product3);
        vendingMachine.registerProduct(product4);
        vendingMachine.registerProduct(product5);
        final int expected = 5;
        assertEquals(expected, vendingMachine.getNumberOfProducts());
    }
    /**
     * Tests the unsuccessful registration of multiple products in the vending machine.
     * <p>
     * This test verifies that products cannot be correctly registered with
     * encountering LaneCodeAlreadyInUseException,
     * </p>
     *
     * <p>
     * Specifically, it registers five unique {@code VendingMachineProduct} instances
     * and asserts that the vending machine reports having five products afterward.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a product with a duplicate lane code is registered,
     *         though this exception is not expected in this test case.
     */
    @Test
    public void registerProductTestFail() throws LaneCodeAlreadyInUseException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.registerProduct(product6);
        vendingMachine.registerProduct(product3);
        vendingMachine.registerProduct(product4);
        vendingMachine.registerProduct(product5);
    }
    @Test
    public void countNumberOfProductsTestSuccess() throws LaneCodeAlreadyInUseException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.registerProduct(product3);
        final int expected = 3;
        assertEquals(expected, vendingMachine.getNumberOfProducts());
    }
    @Test
    public void unRegisterProductTestSuccess() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.registerProduct(product3);
        vendingMachine.registerProduct(product4);
        vendingMachine.registerProduct(product5);

        vendingMachine.unregisterProduct(product1);
        vendingMachine.unregisterProduct(product2);
        vendingMachine.unregisterProduct(product3);
        vendingMachine.unregisterProduct(product4);
        vendingMachine.unregisterProduct(product5);
        final int expected = 0;
        assertEquals(expected, vendingMachine.getNumberOfProducts());
    }
    @Test
    public void unRegisterProductTestFailWithoutTheSameLaneCode() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.unregisterProduct(product3);
    }
    @Test
    public void unRegisterProductTestFailWithTheSameLaneCode() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.unregisterProduct(product6);
    }
    @Test
    public void addItemTestSuccess() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.addItem("00");
        vendingMachine.addItem("00");
        vendingMachine.addItem("01");
        final int expected = 2;
        assertEquals(expected, vendingMachine.getNumberOfItems("00"));

    }
    @Test
    public void addItemTestFail() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.addItem("06");
    }
    @Test
    public void buyItemSaleCountTestSuccess() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.addItem("00");
        vendingMachine.addItem("00");
        vendingMachine.buyItem("00");
        vendingMachine.buyItem("00");
        final int expected = 2;
        assertEquals(expected, vendingMachine.getNumberOfSales("00"));
    }
    @Test
    public void buyItemSaleCountTestFail() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.addItem("00");
        vendingMachine.addItem("00");
        vendingMachine.buyItem("00");
        vendingMachine.buyItem("00");
        vendingMachine.getNumberOfSales("10");
    }

    @Test
    public void buyItemDecreaseCountTestSuccess() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.addItem("00");
        vendingMachine.addItem("00");
        vendingMachine.buyItem("00");
        vendingMachine.buyItem("00");
        final int expected = 0;
        assertEquals(expected, vendingMachine.getTotalNumberOfItems());
    }
    @Test
    public void buyItemTestSuccess() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.addItem("00");
        vendingMachine.addItem("00");
        final int expected = 1;
        vendingMachine.buyItem("00");
        assertEquals(expected, vendingMachine.getNumberOfItems("00"));
    }
    @Test
    public void countAfterBuyTotalItemTestSuccess() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.addItem("00");
        vendingMachine.addItem("00");
        vendingMachine.addItem("01");
        vendingMachine.buyItem("00");
        final int expected = 2;
        assertEquals(expected, vendingMachine.getTotalNumberOfItems());
    }
    @Test
    public void buyItemTestFailWrongCode() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.addItem("00");
        vendingMachine.addItem("00");
        vendingMachine.buyItem("09");
    }
    @Test
    public void buyItemTestFailOutOfStock() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.addItem("00");
        vendingMachine.buyItem("00");
        vendingMachine.buyItem("00");
    }
    @Test
    public void getNumberOfItemByCodeTestSuccess() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.registerProduct(product3);
        vendingMachine.addItem("00");
        vendingMachine.buyItem("01");
        vendingMachine.buyItem("01");
        vendingMachine.getNumberOfItems("02");
        vendingMachine.getNumberOfItems("02");
        vendingMachine.getNumberOfItems("02");
        final int expected = 3;
        assertEquals(expected, vendingMachine.getNumberOfItems("02"));
    }
    @Test
    public void getNumberOfItemByCodeTestFail() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.addItem("00");
        vendingMachine.buyItem("00");
        vendingMachine.getNumberOfItems("05");
    }

    @Test
    public void getNumberOfAllItemSuccessTest() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.registerProduct(product3);
        vendingMachine.addItem("00");
        vendingMachine.addItem("00");
        vendingMachine.addItem("00");
        vendingMachine.addItem("01");
        vendingMachine.addItem("02");
        final int expected = 5;
        assertEquals(expected, vendingMachine.getTotalNumberOfItems());
    }
    @Test
    public void getMostPopularSuccess() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.registerProduct(product3);
        vendingMachine.addItem("00");
        vendingMachine.addItem("01");
        vendingMachine.addItem("02");
        vendingMachine.addItem("02");
        vendingMachine.addItem("02");
        vendingMachine.buyItem("01");
        vendingMachine.buyItem("02");
        vendingMachine.buyItem("02");
        vendingMachine.buyItem("02");
        final String expected = "02";
        assertEquals(expected, vendingMachine.getMostPopular().getLaneCode());
    }
    @Test
    public void vendingMachineProductNotNull() {
        IVendingMachineProduct vendingMachineProduct = Factory.getInstance().makeVendingMachineProduct("A1", "Haggis Crisps");
        assertNotNull(vendingMachineProduct);
    }
    @Test
    public void vendingMachineNull() {
        IVendingMachine vendingMachine1 = Factory.getInstance().makeVendingMachine();
        assertNotNull(vendingMachine1);
    }

    @Test
    public void productNotNull() {
        IVendingMachineProduct vendingMachineProduct = Factory.getInstance().makeVendingMachineProduct("A1", "Haggis Crisps");
        IProductRecord productRecord = Factory.getInstance().makeProductRecord(vendingMachineProduct);
        assertNotNull(productRecord);
    }
}
