package com.freshvotes.web;

import com.freshvotes.domain.Feature;
import com.freshvotes.domain.Product;
import com.freshvotes.service.FeatureService;
import com.freshvotes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {
    @Autowired
    private FeatureService featureService;
    @Autowired
    private ProductService productService;

    @PostMapping("")
    public String createFeature(@PathVariable Long productId) {

        Feature feature = featureService.createFeature(productId);
        return "redirect:/products/" + productId + "/features/" + feature.getId();
    }
    @PostMapping("{featureId}")
    public String updateFeature(@PathVariable Long productId, @PathVariable Long featureId, Feature feature) {

        Product product = productService.findProductById(productId);
        feature.setProduct(product);
        System.out.println("Posting and Saving: productId = " + productId + ", featureId = " + featureId + ", product = " + product + ", feature = " + feature);
        featureService.saveFeature(feature);
        return "redirect:/products/" + productId+ "/features/" + featureId;
    }


    @GetMapping("{featureId}")
    public String getFeature(@PathVariable Long productId, @PathVariable Long featureId, ModelMap model) {
        Product product = productService.findProductById(productId);
        Feature feature = featureService.findFeatureById(featureId);
        System.out.println("found Product:" + product.toString());
        System.out.println("found Feature:" + feature.toString());
//        model.put("product", product);
        model.put("feature",feature);
        return "feature";
    }
}
