/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniproject3;

/**
 *
 * @author Shinozaki-Kirishima
 */
public class Products {
    //fields
    private int id;
    private String name;
    private float price;
    private String addDate;
    private byte[] picture;
    
    //constructor
    public Products(int productId,String productName,float productPrice,String productAddDate,byte[] productPicture)
    {
        this.id=productId;
        this.name=productName;
        this.price=productPrice;
        this.addDate=productAddDate;
        this.picture=productPicture;
    }
    //getters 

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getAddDate() {
        return addDate;
    }

    public byte[] getPicture() {
        return picture;
    }
    

}
