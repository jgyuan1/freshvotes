package com.freshvotes.web;

import com.freshvotes.domain.Feature;
import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import com.freshvotes.security.CustomSecurityUser;
import com.freshvotes.service.FeatureService;
import com.freshvotes.service.ProductService;
import com.freshvotes.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {
    Logger log = LoggerFactory.getLogger(FeatureController.class);
    @Autowired
    private FeatureService featureService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @PostMapping("")
    public String createFeature(@AuthenticationPrincipal CustomSecurityUser customSecurityUser, @PathVariable Long productId) {

        User user = userService.getUserByUserName(customSecurityUser.getUsername());

        Feature feature = featureService.createFeature(productId,user);
//        System.out.println("UserName = " + user.getName() + "are creating feature, and setting feature.user.id = "+ feature.getUser().getId());
        return "redirect:/products/" + productId + "/features/" + feature.getId();
    }
    @PostMapping("{featureId}")
    public String updateFeature(@PathVariable Long productId, @PathVariable Long featureId, @AuthenticationPrincipal CustomSecurityUser customSecurityUser,@ModelAttribute Feature feature) {

        Product product = productService.findProductById(productId);
        feature.setProduct(product);
        User user = userService.getUserByUserName(customSecurityUser.getUsername());
        feature.setUser(user);
        System.out.println("Posting and Saving: productId = " + productId + ", featureId = " + featureId + ", product = " + product + ", feature = " + feature);
        featureService.saveFeature(feature);
        String encodedProductName;
        try {
            encodedProductName = URLEncoder.encode(feature.getProduct().getName(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("unable to encode the URL string:" + feature.getProduct().getName() + ", redirecting to dashboard instead of the intended product user view page");
            return "redirect:/dashboard";
        }
        return "redirect:/p/" + encodedProductName;
    }


    @GetMapping("{featureId}")
    public String getFeature(@PathVariable Long productId, @PathVariable Long featureId, @AuthenticationPrincipal CustomSecurityUser customSecurityUser, ModelMap model) {

        User user = userService.getUserByUserName(customSecurityUser.getUsername());
        Product product = productService.findProductById(productId);
        Feature feature = featureService.findFeatureById(featureId);
        System.out.println("found Product:" + product.toString());
        System.out.println("found Feature:" + feature.toString());
        System.out.println("User:" + user.toString()+"are getting or editing feature created by" + "UserName:" +  feature.getUser().getName());
//      model.put("product", product);
        model.put("feature",feature);
        model.put("user", user);
        return "feature";
    }

}
