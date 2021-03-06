package com.umitunal.services;

import com.umitunal.domain.Product;
import com.umitunal.domain.ProductComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component
public class MongoDataService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void initDataService() {
        List<Product> products = this.prepareProductList(10000);
        this.mongoTemplate.insert(products,Product.class);
    }
    
    public List<Product> prepareProductList(int size){
        List<Product> products = new ArrayList<Product>();

        for (int i = 0; i< size; i++) {
            products.add(this.prepareProduct());
        }

        return  products;
    }

    private Product prepareProduct() {
        Product product = new Product();
        product.setCreatedAt(new Date());
        product.setTitle("Title2");
        product.setDescription("Product Description" + UUID.randomUUID().toString());
        product.setPrice(10.0);
        product.setKeywordList(this.getKeywordList());
        product.setProductCommentList(this.getProductCommentList(3));
        return product;
    }

    public List<ProductComment> getProductCommentList(int size) {
        List<ProductComment> productComments = new ArrayList<ProductComment>();
        for (int i= 1; i<=size; i++){
            ProductComment productComment = new ProductComment();
            productComment.setPostedAt(new Date());
            productComment.setAuthor("Author : " + UUID.randomUUID().toString());
            productComment.setEmail("Email" + UUID.randomUUID().toString());

            productComments.add(productComment);
        }
        return productComments;
    }


    public List<String> getKeywordList() {
        List<String> keywordList = new ArrayList<String>();
        for (int i = 0; i<=3;i++){
            keywordList.add(UUID.randomUUID().toString());
        }
        return keywordList;
    }


}