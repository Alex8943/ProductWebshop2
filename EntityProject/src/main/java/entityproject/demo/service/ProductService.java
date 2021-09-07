package entityproject.demo.service;

import entityproject.demo.Model.Product;
import entityproject.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void create(Product product){
        productRepository.create(product);
    }

    public void delete (long id){
        productRepository.delete(id);
    }

    public void update(Product product){
        productRepository.update(product);
    }

    public List<Product> readAll(){
        List<Product> productList = new ArrayList<>();
        for(Product product : productRepository.readAll()){
            productList.add(product);
        }
        return productList;
    }

    public Product showProductByID(long id){
        return productRepository.getProductById(id);
    }

}
