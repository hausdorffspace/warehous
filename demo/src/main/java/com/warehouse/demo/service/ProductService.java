package com.warehouse.demo.service;


import com.warehouse.demo.model.*;
import com.warehouse.demo.model.request.ProductRequest;
import com.warehouse.demo.model.response.ProductResponse;
import com.warehouse.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductResponse save(ProductRequest productRequest) {

        productRepository.save(Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .weight(productRequest.getWeight())
                .dimension(Dimension.builder()
                        .height(productRequest.getDimension().getHeight())
                        .width(productRequest.getDimension().getWidth())
                        .build())
                .SKU(productRequest.getSku())
                .typeOfProduct(TypeProduct.INDUSTRIAL)   // TODO poprawic
                .producer(Producer.builder()
                        .companyName(productRequest.getProducer().getCompanyName())
                        .build())
                .warehouse(Warehouse.builder()
                        .description(productRequest.getWarehouse().getDescription())
                        .location(productRequest.getWarehouse().getLocation())
                        .build())
                .build());
        return new ProductResponse("hehehehe"); //TODO
    }
}
