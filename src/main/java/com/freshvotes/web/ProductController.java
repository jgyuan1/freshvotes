package com.freshvotes.web;

import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import com.freshvotes.service.ProductService;
import com.freshvotes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping("/allProducts")
    //parameter "products" in products.html will resolve from model
    public String getAllProducts(@AuthenticationPrincipal User user, ModelMap model) {
        List<Product> allProducts = productService.findAllProductsByUser(user);
//        System.out.println(appProducts);
        model.put("allProducts", allProducts);
        return "allProducts";
    }

    @GetMapping("/products")
    //parameter "product" in product.html will resolve from model
    public String getProducts(ModelMap model) {
        return "product";
    }
    @GetMapping("/products/{productId}")
    public String getProducts(@PathVariable Long productId, ModelMap model) {
//        System.out.println(model);
        Product product = productService.findProductById(productId);
        model.put("product", product);
//        System.out.println(model);
        return "product";
    }

    @PostMapping("/products")
    public String createProduct(@AuthenticationPrincipal User user, HttpServletResponse response) {

        Product product = productService.createANewProduct();
        product.setPublished(Boolean.FALSE);

        String username = user.getUsername();

        com.freshvotes.domain.User userDomain = userService.getUserByUserName(username);
        product.setUser(userDomain);
        Long productId= productService.saveProduct(product);
        System.out.println("The product with Id" + productId + "is saved!");
        return "redirect:/products/"+productId;
    }

//    @PostMapping("/products/{productId}")
//    // parameter product comes from the thymeleaf that binds a product's information with a product
//    public String saveProduct(@PathVariable Long productId, @ModelAttribute Product product) {
//        Product dbProduct = productService.findProductById(productId);
//        dbProduct.setName(product.getName());
//        dbProduct.setPublished(product.getPublished());
//        productService.saveProduct(dbProduct);
//        return "redirect:/products/" + dbProduct.getId();
//    }

    @PostMapping("/products/{productId}")
    // parameter product comes from the thymeleaf that binds a product's information with a product
    public String saveProduct(@PathVariable Long productId, Product product, @AuthenticationPrincipal User user) {
        // because user is lazily loaded, so we need to add the user manually
        product.setUser(user);
        productService.saveProduct(product);
        return "redirect:/products/" + product.getId();
    }
}

