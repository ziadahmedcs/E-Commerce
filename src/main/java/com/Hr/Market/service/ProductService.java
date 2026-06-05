package com.Hr.Market.service;

import com.Hr.Market.Entity.Category;
import com.Hr.Market.Entity.Products;
import com.Hr.Market.Repository.CategoryRepository;
import com.Hr.Market.Repository.ProductRepository;
import com.Hr.Market.dto.CategoryDto;
import com.Hr.Market.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository ;

    public Products CreateProduct(ProductDto productDto)
    {
        Category category =
                categoryRepository.findByName(
                        productDto.getCategory());

        if(category == null)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category Not Found"
            );
        }

        Products products = new Products();

        products.setName(productDto.getName());
        products.setPrice(productDto.getPrice());
        products.setQuantity(productDto.getQuantity());
        products.setCategory(category);

        return productRepository.save(products);
    }

    public List<Products> GetProducts() {
        if (productRepository.findAll().size() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Product Founded");
        }
        return productRepository.findAll();
    }

    public Products GetProduct(Long id) {
        Optional<Products> products = productRepository.findById(id);
        if (!products.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product Not Found"
            );
        }
        return products.get();
    }

    public ResponseEntity<?> DeleteProduct(Long id) {
        Optional<Products> products = productRepository.findById(id);
        if (!products.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product Not Found"
            );
        }
        productRepository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "The Product is Deleted");
        response.put("status message", HttpStatus.OK.getReasonPhrase());
        response.put("status", HttpStatus.OK.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public Products UpdateProduct(long id, ProductDto productDto) {
        Optional<Products> products = productRepository.findById(id);
        if (!products.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Product Not Exits");
        }
        Category category = categoryRepository
                .findByName(productDto.getCategory());

        if(category == null)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category Not Found"
            );
        }
        products.get().setName(productDto.getName());
        products.get().setCategory(category);
        products.get().setPrice(productDto.getPrice());
        products.get().setQuantity(productDto.getQuantity());
        productRepository.save(products.get());
        return products.get();
    }

    public List<Products> SearchProducts(String name) {
        List<Products> products = productRepository.findByNameContainingIgnoreCase(name);
        if (products.isEmpty()) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND ,"No Proudct Founded") ;
        }
        return  products ;
    }
    public Category CreateCategory (CategoryDto categoryDto)
    {
        Category category = new Category() ;
        category.setName(categoryDto.getName());
        categoryRepository.save(category) ;
        return category ;
    }

    public List<Category> GetCategories() {
        if (categoryRepository.findAll().size() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Categoris Founded");
        }
        return categoryRepository.findAll();
    }
    public Category GetCategory(long id) {
        Category category = categoryRepository.findById(id).get() ;
        if (category == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Categoris Founded");
        }
        return category ;
    }

    public Category UpdateCategory (long id , CategoryDto categoryDto)
    {
        Category category = categoryRepository.findById(id).get() ;
        if (category == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Categoris Founded");
        }
        category.setName(categoryDto.getName());
        categoryRepository.save(category) ;
        return category ;
    }
    public Map<String,String> DeleteCategory(long id) {
        Category category = categoryRepository.findById(id).get() ;
        if (category == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Categoris Founded");
        }
        categoryRepository.delete(category);
        Map <String,String> response = new HashMap<>() ;
        response.put("message" ,"Category Deleted") ;
        response.put("status" ,HttpStatus.OK.toString()) ;
        return response ;
    }

}
