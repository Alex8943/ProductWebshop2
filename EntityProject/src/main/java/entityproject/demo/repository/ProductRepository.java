package entityproject.demo.repository;

import entityproject.demo.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements ICrudRepository<Product> {

    @Autowired
    JdbcTemplate template;

    @Override
    public void create(Product product) {
        String sql = "INSERT INTO product (productName, productPrice) VALUES (?, ?)";
        template.update(sql, product.getProductName(), product.getProductPrice());
    }

    // Rowmapper An interface used by JdbcTemplate for mapping rows of a ResultSet on a per-row basis.
    // Implementations of this interface perform the actual work of mapping each row to a result object, but don't need to worry about exception handling.
    // SQLExceptions will be caught and handled by the calling JdbcTemplate.
    @Override
    public List<Product> readAll() {
        String sql = "SELECT * FROM product";
        RowMapper<Product> productList = new BeanPropertyRowMapper<>(Product.class);
        return template.query(sql, productList);
    }

    @Override
    public Product getProductById(long id) {
        String sql = "select * from product where productID = ?";
        RowMapper<Product> productList = new BeanPropertyRowMapper<>(Product.class);
        return template.queryForObject(sql, productList, id);
    }

    @Override
    public void update(Product product) {
        String sql = "update product set productName = ?, productPrice = ? where productID = ?";
        template.update(sql, product.getProductName(), product.getProductPrice(), product.getProductID());
    }

    @Override
    public void delete(long id) {
        String sql = "delete from product where productID = ?";
        template.update(sql, id);
    }
}
