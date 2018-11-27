package app.hackathon.suki.suki.models;

public class Product {
    String productName;
    String productDescription;
    String productPrice;
    String productQty;
    String productVariant;
    String productOrganic;

    public Product(String productName, String productDescription, String productPrice, String productQty, String productVariant, String productOrganic) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQty = productQty;
        this.productVariant = productVariant;
        this.productOrganic = productOrganic;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public String getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(String productVariant) {
        this.productVariant = productVariant;
    }

    public String getProductOrganic() {
        return productOrganic;
    }

    public void setProductOrganic(String productOrganic) {
        this.productOrganic = productOrganic;
    }
}
