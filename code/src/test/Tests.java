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
     * encountering {@code LaneCodeAlreadyInUseException}.
     * </p>
     *
     * <p>
     * Specifically, it registers five unique {@code VendingMachineProduct} instances
     * and asserts that the vending machine reports having five products afterward.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a product with a duplicate lane code is registered,
     *         this exception is <b>expected</b> in this test case.
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
    /**
     * Tests the successful count number of products after register in the vending machine.
     * <p>
     * This test verifies that products can be correctly counted without
     * encountering any exceptions, and that the total number of registered
     * products matches the expected value.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a product with a duplicate lane code is registered,
     *         though this exception is not expected in this test case.
     */
    @Test
    public void countNumberOfProductsTestSuccess() throws LaneCodeAlreadyInUseException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.registerProduct(product3);
        final int expected = 3;
        assertEquals(expected, vendingMachine.getNumberOfProducts());
    }
    /**
     * Tests the successful unregistration of multiple products in the vending machine.
     * <p>
     * This test verifies that products can be correctly unregistered without
     * encountering any exceptions, and that the total number of registered
     * products matches the expected value.
     * </p>
     *
     * <p>
     * Specifically, it unregisters five unique {@code VendingMachineProduct} instances
     * and asserts that the vending machine reports having 0 products afterward.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a product with a duplicate lane code is registered,
     *         though this exception is not expected in this test case.
     *
     * @throws LaneCodeNotRegisteredException if a product with a product is not registered,
     *          though this exception is not expected in this test case.
     */
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
    /**
     * Tests the unsuccessful unregistration of a product in the vending machine.
     * <p>
     * This test verifies that products with the same LenCode but different name cannot be unregistered and
     * encountering {@code  LaneCodeNotRegisteredException}.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a product with a duplicate lane code is registered,
     *         this exception is not expected in this test case.
     *
     * @throws LaneCodeNotRegisteredException if a product with a product is not registered,
     *          though this exception is <b>expected</b> in this test case.
     */
    @Test
    public void unRegisterProductTestFailWithoutTheSameLaneCode() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.unregisterProduct(product3);
    }
    /**
     * Tests the unsuccessful unregistration of a product in the vending machine.
     * <p>
     * This test verifies that products with unregistered LenCode cannot be unregistered and
     * encountering {@code  LaneCodeNotRegisteredException}.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a product with a duplicate lane code is registered,
     *         this exception is not expected in this test case.
     *
     * @throws LaneCodeNotRegisteredException if a product with a product is not registered,
     *          though this exception is <b>expected</b> in this test case.
     */
    @Test
    public void unRegisterProductTestFailWithTheSameLaneCode() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.unregisterProduct(product6);
    }
    /**
     * Tests the successful addition of items to a registered product in the vending machine.
     * <p>
     * This test ensures that when valid lane codes are provided, items are correctly added to
     * the product’s stock count. It verifies that the number of items in lane code "00" matches
     * the expected count after multiple additions.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a duplicate lane code is registered (not expected).
     * @throws LaneCodeNotRegisteredException if an invalid lane code is used (not expected in this test).
     */
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
    /**
     * Tests the unsuccessful addition of an item using an unregistered lane code.
     * <p>
     * This test verifies that attempting to add an item with a lane code ("06") that is not
     * registered in the vending machine triggers an appropriate exception.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a duplicate lane code is registered (not expected).
     * @throws LaneCodeNotRegisteredException expected when adding an item with an unregistered lane code.
     */
    @Test
    public void addItemTestFail() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.addItem("06");
    }

    /**
     * Tests that the sale count increases correctly when items are purchased successfully.
     * <p>
     * This test registers a product, adds two items, and performs two purchases.
     * It asserts that the vending machine correctly tracks the number of sales.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a duplicate lane code is registered (not expected).
     * @throws LaneCodeNotRegisteredException if a product is not registered (not expected).
     * @throws ProductUnavailableException if a product is unavailable for purchase (not expected).
     */
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
    /**
     * Tests the unsuccessful retrieval of a product sale count using an invalid lane code.
     * <p>
     * This test verifies that querying sales information for a non-existent product ("10")
     * should trigger an exception or error.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a duplicate lane code is registered (not expected).
     * @throws LaneCodeNotRegisteredException if a product is not registered (expected in this case).
     * @throws ProductUnavailableException if the product is unavailable (not expected).
     */
    @Test
    public void buyItemSaleCountTestFail() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.addItem("00");
        vendingMachine.addItem("00");
        vendingMachine.buyItem("00");
        vendingMachine.buyItem("00");
        vendingMachine.getNumberOfSales("10");
    }
    /**
     * Tests that the total item count decreases correctly after purchases.
     * <p>
     * This test registers a product, adds items, and buys all of them.
     * It asserts that the total number of items remaining is zero.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a duplicate lane code is registered (not expected).
     * @throws LaneCodeNotRegisteredException if a product is not registered (not expected).
     * @throws ProductUnavailableException if the product is unavailable (not expected).
     */
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
    /**
     * Tests that the number of items decreases correctly after a purchase.
     * <p>
     * This test ensures that when one item is bought, the remaining quantity in that lane code
     * is decremented by one.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a duplicate lane code is registered (not expected).
     * @throws LaneCodeNotRegisteredException if a product is not registered (not expected).
     * @throws ProductUnavailableException if the product is unavailable (not expected).
     */
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
    /**
     * Tests that the total number of items is correctly updated after a purchase.
     * <p>
     * This test verifies that the overall count of items in the vending machine decreases
     * when a product is purchased.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a duplicate lane code is registered (not expected).
     * @throws LaneCodeNotRegisteredException if a product is not registered (not expected).
     * @throws ProductUnavailableException if the product is unavailable (not expected).
     */
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
    /**
     * Tests the unsuccessful purchase of an item using an invalid lane code.
     * <p>
     * This test verifies that attempting to buy an item with an unregistered code ("09")
     * results in an exception being thrown.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a duplicate lane code is registered (not expected).
     * @throws LaneCodeNotRegisteredException expected when attempting to buy from an unregistered code.
     * @throws ProductUnavailableException if the product is unavailable (not expected).
     */
    @Test
    public void buyItemTestFailWrongCode() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.addItem("00");
        vendingMachine.addItem("00");
        vendingMachine.buyItem("09");
    }
    /**
     * Tests the unsuccessful purchase of an item when it is out of stock.
     * <p>
     * This test verifies that purchasing more items than are available triggers
     * a {@code ProductUnavailableException}.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a duplicate lane code is registered (not expected).
     * @throws LaneCodeNotRegisteredException if a product is not registered (not expected).
     * @throws ProductUnavailableException expected when the product is out of stock.
     */
    @Test
    public void buyItemTestFailOutOfStock() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.addItem("00");
        vendingMachine.buyItem("00");
        vendingMachine.buyItem("00");
    }
    /**
     * Tests the correct retrieval of the number of items for a specific lane code.
     * <p>
     * This test ensures that the vending machine correctly counts the number of items
     * available for a given lane code ("01").
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a duplicate lane code is registered (not expected).
     * @throws LaneCodeNotRegisteredException if the lane code is invalid (not expected).
     * @throws ProductUnavailableException if the product is unavailable (not expected).
     */
    @Test
    public void getNumberOfItemByCodeTestSuccess() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.registerProduct(product2);
        vendingMachine.registerProduct(product3);
        vendingMachine.addItem("00");
        vendingMachine.addItem("01");
        vendingMachine.addItem("01");
        vendingMachine.addItem("01");
        vendingMachine.addItem("01");
        final int expected = 4;
        assertEquals(expected, vendingMachine.getNumberOfItems("01"));
    }
    /**
     * Tests the unsuccessful retrieval of the number of items using an invalid lane code.
     * <p>
     * This test checks that requesting the number of items for a non-existent lane code ("05")
     * triggers an appropriate exception.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a duplicate lane code is registered (not expected).
     * @throws LaneCodeNotRegisteredException expected when an invalid lane code is queried.
     * @throws ProductUnavailableException if the product is unavailable (not expected).
     */
    @Test
    public void getNumberOfItemByCodeTestFail() throws LaneCodeAlreadyInUseException, LaneCodeNotRegisteredException, ProductUnavailableException {
        vendingMachine.registerProduct(product1);
        vendingMachine.addItem("00");
        vendingMachine.buyItem("00");
        vendingMachine.getNumberOfItems("05");
    }
    /**
     * Tests the total count of all items across all products in the vending machine.
     * <p>
     * This test verifies that the vending machine correctly reports the combined total
     * number of items for all registered products.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a duplicate lane code is registered (not expected).
     * @throws LaneCodeNotRegisteredException if a product is not registered (not expected).
     * @throws ProductUnavailableException if a product is unavailable (not expected).
     */
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
    /**
     * Tests retrieval of the most popular product based on sales count.
     * <p>
     * This test ensures that the vending machine correctly identifies and returns
     * the product with the highest number of sales.
     * </p>
     *
     * @throws LaneCodeAlreadyInUseException if a duplicate lane code is registered (not expected).
     * @throws LaneCodeNotRegisteredException if a product is not registered (not expected).
     * @throws ProductUnavailableException if a product is unavailable (not expected).
     */
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
    /**
     * Tests that the factory successfully creates a non-null vending machine product.
     * <p>
     * This verifies that the {@code Factory} can instantiate a valid {@code IVendingMachineProduct}
     * object with a given lane code and name.
     * </p>
     */
    @Test
    public void vendingMachineProductNotNull() {
        IVendingMachineProduct vendingMachineProduct = Factory.getInstance().makeVendingMachineProduct("A1", "Haggis Crisps");
        assertNotNull(vendingMachineProduct);
    }
    /**
     * Tests that the factory successfully creates a non-null vending machine instance.
     * <p>
     * This verifies that the {@code Factory} correctly returns a valid {@code IVendingMachine} object.
     * </p>
     */
    @Test
    public void vendingMachineNull() {
        IVendingMachine vendingMachine1 = Factory.getInstance().makeVendingMachine();
        assertNotNull(vendingMachine1);
    }
    /**
     * Tests that the factory successfully creates a non-null product record.
     * <p>
     * This verifies that the {@code Factory} can create a {@code IProductRecord} object
     * when provided with a valid {@code IVendingMachineProduct}.
     * </p>
     */
    @Test
    public void productNotNull() {
        IVendingMachineProduct vendingMachineProduct = Factory.getInstance().makeVendingMachineProduct("A1", "Haggis Crisps");
        IProductRecord productRecord = Factory.getInstance().makeProductRecord(vendingMachineProduct);
        assertNotNull(productRecord);
    }
}
