import com.gmail.kurmazpavel.*;
import com.gmail.kurmazpavel.beans.dto.*;
import com.gmail.kurmazpavel.impl.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class Test {
    private static CatalogService catalogService = new CatalogServiceImpl();
    private static DiscountService discountService = new DiscountServiceImpl();
    private static UserService userService = new UserServiceImpl();
    private static OrderService orderService = new OrderServiceImpl();
    private static RoleService roleService = new RoleServiceImpl();

    public static void main(String[] args) {
        //creating a role for user (save mapping)
        RoleDTO role = new RoleDTO();
        role.setRole("User");
        roleService.create(role);

        makeGoods(30);
        makeDiscounts();
        applyDiscount();
        listDiscounts(10);
        listDiscounts(20);
        listDiscounts(30);
        listDiscounts(0);
        UserDTO user = createUser();
        userApplyDiscount(user, "Basic");
        createOrders(user);
        printResult(user);
    }

    //create 30 items with random price
    private static void makeGoods(int count) {
        for (int i = 1; i <= count; i++) {
            double cost = (int)(Math.random()*400 + 101);
            CatalogDTO catalogDTO = new CatalogDTO();
            catalogDTO.setPrice(cost);
            catalogDTO.setName("Random item #" + i);
            catalogDTO.setAmount((int) cost);
            catalogService.create(catalogDTO);
        }
        System.out.println("Goods created");
    }

    //creating 3 discount types with unique names
    private static void makeDiscounts() {
        for (int i = 1; i < 4; i++) {
            DiscountDTO discountDTO = new DiscountDTO();
            discountDTO.setPercent(i*10);
            String name;
            switch (i){
                case (1):
                    name="Basic";
                    break;
                case (2):
                    name = "Advanced";
                    break;
                case (3):
                    name = "Super";
                    break;
                default:
                    name = "default";
                    break;
            }
            discountDTO.setName(name);
            discountDTO.setLast(LocalDateTime.now());
            discountService.create(discountDTO);
        }
        System.out.println("Discount created");
    }

    //each item gets it`s own discount based on it`s price
    private static void applyDiscount() {
        List<CatalogDTO> itemList = catalogService.getAll();
        DiscountDTO basic = discountService.readByName("Basic");
        DiscountDTO advanced = discountService.readByName("Advanced");
        DiscountDTO superb = discountService.readByName("Super");
        for (CatalogDTO item: itemList) {
            if ((item.getPrice() <=299) && (item.getPrice() >=200))
                discountService.applyDiscount(item.getID(), basic.getId());
            else if ((item.getPrice() <=399) && (item.getPrice() >=300))
                discountService.applyDiscount(item.getID(), advanced.getId());
            else if ((item.getPrice() <=500) && (item.getPrice() >=400))
                discountService.applyDiscount(item.getID(), superb.getId());
        }
        System.out.println("Discounts applied");
    }

    //listing all items based on their discount (incl. 0%)
    private static void listDiscounts(int percent) {
        List<CatalogDTO> items = discountService.getByDiscount(percent);
        System.out.println("Items with discount " + percent +"%");
        for (CatalogDTO item: items) {
            System.out.println(item.getName());
        }
    }

    //creating a random user
    private static UserDTO createUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setCarma("weak");
        userDTO.setDisabled(0);
        userDTO.setEmail("test");
        userDTO.setLogin("test");
        userDTO.setPassword("");
        userDTO.setPhone("test");
        userDTO.setRolesId(roleService.readByRole("User").getId());
        return userService.create(userDTO);
    }

    //adding a random user discount
    private static void userApplyDiscount(UserDTO userDTO, String name) {
        DiscountDTO discountDTO = discountService.readByName(name);
        discountService.update(discountDTO.getId(), userDTO.getId());
    }

    //making orders for user
    private static void createOrders(UserDTO user) {
        List<CatalogDTO> items = catalogService.getByPrice(250.0, 450.0);
        while (orderService.getById(user.getId()).size() < 4) {
            int id = new Random().nextInt(items.size() - 1);
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setUserId(user.getId());
            orderDTO.setCreated(LocalDateTime.now());
            orderDTO.setItemId(items.get(id).getID());
            long quantity = catalogService.count(250.0, 450.0);
            orderDTO.setQuantity((int) quantity);
            orderService.simpleCreate(orderDTO);
            System.out.println("order created");
        }
    }

    //calculating price and printing results
    private static void printResult(UserDTO userDTO) {
        List<OrderDTO> orders = orderService.getById(userDTO.getId());
        int percent = userService.getDiscount(userDTO).getPercent();
        for (OrderDTO order : orders) {
            double price = catalogService.read(order.getItemId()).getPrice();
            List<DiscountDTO> discounts = catalogService.getDiscounts(order.getItemId());
            double itemDiscount = 0;
            for (DiscountDTO discount: discounts) {
                if (itemDiscount == 0)
                    itemDiscount = discount.getPercent();
                else
                    itemDiscount *= discount.getPercent();
            }
            System.out.println(
                    "User: " + userDTO.getLogin()
                    + " Item: " + catalogService.read(order.getItemId()).getName()
                    + " Quantity: " + order.getQuantity()
                    + " Price: " + price
                    + " Item total discount: " + itemDiscount + "%"
                    + " Price incl discount: " + price * (100-itemDiscount)/100
                    + " User discount: " + percent + "%"
                    + " Final price per item: " +  price * (100 - percent - itemDiscount)/100
                    + " Total price: " + order.getQuantity() * price * (100 - percent - itemDiscount)/100
            );
        }
    }
}
