package com.freshvotes.service;

import com.freshvotes.domain.Feature;
import com.freshvotes.domain.Product;
import com.freshvotes.repositories.FeatureRepository;
import com.freshvotes.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FeatureRepository featureRepository;
    public Feature createFeature(Long productId){
        Feature feature = new Feature();
        Optional<Product> productOpt= productRepository.findById(productId);
        if(productOpt.isPresent()){
            feature.setProduct(productOpt.get());
            feature.setStatus("pending");
            productOpt.get().getFeatures().add(feature);
            return featureRepository.save(feature);
        }
        return feature;
    }
    public Feature findFeatureById(Long featureId){
        return featureRepository.getReferenceById(featureId);
    }
    public Feature saveFeature(Feature feature){
        return featureRepository.save(feature);
    }
}
