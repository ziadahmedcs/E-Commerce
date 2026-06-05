package com.Hr.Market.Controller;

import com.Hr.Market.Entity.Category;
import com.Hr.Market.Entity.Products;
import com.Hr.Market.dto.CategoryDto;
import com.Hr.Market.dto.ProductDto;
import com.Hr.Market.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Items")
public class ProductController {
    @Autowired
    private ProductService productService  ;
    @PostMapping("/products")
    public Products CreateProduct (@RequestBody ProductDto productDto)
    {
        return  productService.CreateProduct(productDto) ;
    }

    @GetMapping("/products")
    public List<Products> GetProducts ()
    {
        return  productService.GetProducts() ;
    }

    @GetMapping("/products/{id}")
    public Products GetProduct (@PathVariable Long id)
    {
        return  productService.GetProduct(id) ;
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> DeleteProduct (@PathVariable Long id)
    {
        return productService.DeleteProduct(id) ;
    }
    @PutMapping("/products/{id}")
    public Products UpdateProduct (@PathVariable Long id , @RequestBody ProductDto productDto)
    {
        return  productService.UpdateProduct(id , productDto) ;
    }

    @GetMapping("/products/search")
    public List<Products> SearchProduct (@RequestParam String name)
    {
        return  productService.SearchProducts(name);
    }

    @PostMapping("/category")
    public Category CreateCategory (@Valid  @RequestBody CategoryDto categoryDto)
    {
        return productService.CreateCategory(categoryDto);
    }
    @GetMapping("/category")
    public List<Category> GetCategories ()
    {
        return  productService.GetCategories() ;
    }
    @GetMapping("/category/{id}")
    public Category GetCategory (@PathVariable Long id)
    {
        return  productService.GetCategory(id) ;
    }
    @PutMapping("/category/{id}")
    public  Category UpdateCategory (@PathVariable Long id ,@RequestBody CategoryDto categoryDto )
    {
       return productService.UpdateCategory(id , categoryDto) ;
    }
    @DeleteMapping("/category/{id}")
    public Map<String,String> DeleteCategory (@PathVariable Long id)
    {
        return  productService.DeleteCategory(id) ;
    }
}
