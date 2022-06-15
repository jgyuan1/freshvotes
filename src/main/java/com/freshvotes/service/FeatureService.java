package com.freshvotes.service;

import com.freshvotes.domain.Feature;
import com.freshvotes.domain.Product;
import com.freshvotes.domain.User;
import com.freshvotes.repositories.FeatureRepository;
import com.freshvotes.repositories.ProductRepository;
import com.freshvotes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private UserRepository userRepository;

    public Feature createFeature(Long productId, User user){
        Feature feature = new Feature();
        System.out.println("UserName = " + user.getName() + "created a new feature, and setting feature.user.id = "+ user.getId());
        Optional<Product> productOpt= productRepository.findById(productId);
        if(productOpt.isPresent()){
            Product product = productOpt.get();
            feature.setProduct(product);
            feature.setStatus("pending");
            feature.setUser(user);
            feature=featureRepository.save(feature);
//            product.getFeatures().add(feature);
//            productRepository.save(product);
//            user.getFeatures().add(feature);
//            userRepository.save(user);
            return feature;
        }
        System.out.println("UserName = " + user.getName() + "created a new feature without status, and setting feature.user.id = "+ user.getId());
        return feature;
    }
    public Feature findFeatureById(Long featureId){
        return featureRepository.getReferenceById(featureId);
    }
    public Feature saveFeature(Feature feature){
        return featureRepository.save(feature);
    }
}
