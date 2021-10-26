package entityproject.demo.controller;


import entityproject.demo.Model.Product;
import entityproject.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RESTController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<Product> product() {
        return productService.readAll();
    }


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = new Product(product.getProductID(), product.getProductName(), product.getProductPrice());
        productService.create(newProduct);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "/products/" + product.getProductID());
        return new ResponseEntity<>(newProduct, headers, HttpStatus.CREATED);
    }


    @GetMapping("/products/{id}")
    public Product getProductByID(@PathVariable("id") long id) {
        return productService.showProductByID(id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProductByID(@PathVariable("id") long id) {
        productService.delete(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> productUpdate(@PathVariable("id") long id, @RequestBody Product productDetails) {
        Product product = productService.showProductByID(id);

        product.setProductID(productDetails.getProductID());
        product.setProductName(productDetails.getProductName());
        product.setProductPrice(productDetails.getProductPrice());

        final Product updatedProduct = productService.update(product);

        return ResponseEntity.ok(updatedProduct);
    }


}
