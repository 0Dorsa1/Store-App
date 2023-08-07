package com.example.store;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Management {
    private ArrayList<User> allUsers = new ArrayList<>();
    private ArrayList<Category> allCategories=new ArrayList<>(), itemCategoriesWomen =new ArrayList<>(), itemCategoriesMen=new ArrayList<>();
    private static Management system;
    private User currentUser;


    private final User admin=new User(0,"ADMIN","ADMIN","admin.store@gmail.com");

    public static Management getSystem() {
        if (system == null) {
            system = new Management();
        }
        return system;
    }

    {
        allUsers.add(admin);
        allCategories.add(new Category("https://www.pngfind.com/pngs/m/52-524008_women-fashion-model-png-transparent-png.png", "Woman"));
        allCategories.add(new Category("https://w7.pngwing.com/pngs/929/40/png-transparent-male-fashion-model-clothing-man-brad-pitt-celebrities-fashion-shoe.png", "Man"));
        allCategories.add(new Category("https://w7.pngwing.com/pngs/408/335/png-transparent-child-girl-jumping-inflatable-castle-toddler-kid-girl-jumping-wearing-blue-denim-shorts-hand-people-children-thumbnail.png", "Girl"));
        allCategories.add(new Category("https://w7.pngwing.com/pngs/236/568/png-transparent-boy-model-childrens-clothing-model-children-model-tshirt-child-textile.png", "Boy"));

        itemCategoriesWomen.add(new Category("https://www.asket.com/imgproxy/e:1/format:jpeg/width:1080/resize:fit/quality:85/aHR0cHM6Ly9hc2tldC5jZW50cmFjZG4ubmV0L2NsaWVudC9keW5hbWljL2ltYWdlcy85MV85NzM0YTlhMGE5LWFza2V0X3l0ZV93aGl0ZV9zbGlkZV8wMS1vcmlnaW5hbC5qcGc=.jpg","Shirt"));
        itemCategoriesWomen.add(new Category("https://www.target.com.au/medias/static_content/product/images/full/66/07/A1536607.jpg?impolicy=product_portrait_hero","T-Shirt"));
        itemCategoriesWomen.add(new Category("https://cdn11.bigcommerce.com/s-ca7b7/images/stencil/2048x2048/products/2187/8926/4358.52__88017.1623964176.jpg?c=2","Trousers"));
        itemCategoriesWomen.add(new Category("https://www.pngmart.com/files/1/Female-Shoes-PNG-Clipart.png","Shoes"));
        itemCategoriesWomen.add(new Category("https://cutewallpaper.org/cdn-cgi/mirage/dd19f2d06ebc24f541f142b37b4289ffa7de722a7607e39984c5c6dd4ce8defd/1280/24/model-png/london-fashion-week-promotion-models-with-transparent-background-png-image-transparent-png-free-download-on-seekpng.png","Dress"));
        itemCategoriesWomen.add(new Category("https://www.nicepng.com/png/detail/574-5748878_amourette-charm-wired-padded-bra-and-tai-panty.png","Underwear"));
        itemCategoriesWomen.add(new Category("https://p1.hiclipart.com/preview/903/399/606/166-olivia-holt-woman-wearing-sunglasses-png-clipart.jpg","Accessories"));

        itemCategoriesMen.add(new Category("https://shop.disabilityhorizons.com/wp-content/uploads/2021/04/able-label-mens-shirt-hugo-white-600x840.png","Shirt"));
        itemCategoriesMen.add(new Category("https://w7.pngwing.com/pngs/803/523/png-transparent-t-shirt-polo-shirt-clothing-sport-coat-polo-tshirt-blue-white-thumbnail.png","T-Shirt"));
        itemCategoriesMen.add(new Category("https://www.pngmart.com/files/1/Mens-Pant-PNG-HD.png","Trousers"));
        itemCategoriesMen.add(new Category("https://toppng.com/uploads/preview/men-shoes-115309690250belwqtlfd.png","Shoes"));
        itemCategoriesMen.add(new Category("https://cdn.imgbin.com/21/22/19/imgbin-blazer-jacket-sport-coat-suit-made-to-measure-jacket-HeGxqjgRuB0yRGaYT9Hp7S0mV.jpg","Coat"));
        itemCategoriesMen.add(new Category("https://toppng.com/uploads/preview/man-in-suit-png-download-image-men-in-suits-11562930811asge7knhrp.png","Suit"));
        itemCategoriesMen.add(new Category("https://img.lovepik.com/free-png/20210919/lovepik-mens-underwear-png-image_400568633_wh1200.png","Underwear"));
        itemCategoriesMen.add(new Category("https://cudworthenterprises.com/wp-content/uploads/2021/04/454461.jpg","Accessories"));

    }


    public ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public boolean addCategory(Category category){
        return allCategories.add(category);
    }

    public boolean addFemaleCategory(Category category){
        return itemCategoriesWomen.add(category);
    }

    public boolean addMaleCategory(Category category){
        return itemCategoriesMen.add(category);
    }

    public boolean deleteCategory(Category category){
        return allCategories.remove(category);
    }

    public boolean deleteFemaleCategory(Category category){
        return itemCategoriesWomen.remove(category);
    }


    public boolean deleteMaleCategory(Category category){
        return itemCategoriesMen.remove(category);
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public boolean addUser(User user) {
        return allUsers.add(user);
    }

    public User getAdmin() {
        return admin;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Category> getItemCategoriesWomen() {
        return itemCategoriesWomen;
    }

    public void setItemCategoriesWomen(ArrayList<Category> itemCategoriesWomen) {
        this.itemCategoriesWomen = itemCategoriesWomen;
    }

    public ArrayList<Category> getItemCategoriesMen() {
        return itemCategoriesMen;
    }

    public void setItemCategoriesMen(ArrayList<Category> itemCategoriesMen) {
        this.itemCategoriesMen = itemCategoriesMen;
    }
}
