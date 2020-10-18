package testdataobjects;

import lombok.Getter;

import java.util.LinkedHashMap;

/*
 * Created by : vedikagupta
 * Date : 03/10/20
 */
@Getter
public class CheckoutTestDataObject {
    private String email;
    private String password;
    private String productCategory;
    private String subCategory;
    private String subSubCategory;
    private String productName;
    private String colour;
    private String size;

    public CheckoutTestDataObject(LinkedHashMap data) {
        this.email = data.get("Email").toString();
        this.password = data.get("Password").toString();
        this.productCategory = data.get("ProductCategory").toString();
        this.subCategory = data.get("SubCategory").toString();
        this.subSubCategory = data.get("SubSubCategory").toString();
        this.productName = data.get("ProductName").toString();
        this.colour = data.get("Colour").toString();
        this.size = data.get("Size").toString();
    }


}