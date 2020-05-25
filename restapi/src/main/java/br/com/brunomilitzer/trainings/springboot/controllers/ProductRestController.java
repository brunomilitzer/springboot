package br.com.brunomilitzer.trainings.springboot.controllers;

import br.com.brunomilitzer.trainings.springboot.model.Product;
import br.com.brunomilitzer.trainings.springboot.repository.ProductRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Rest API", version = "v1.0", contact = @Contact(name = "Bruno Militzer", email = "brunomilitzer@gmail.com", url = "http://www.brunomilitzer.com.br")))
@RestController
public class ProductRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

    @Autowired
    private ProductRepository productRepository;

    @Operation(tags = "products", description = "A list of products", deprecated = true)
    @RequestMapping(value = "products", method = RequestMethod.GET)
    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable("product-cache")
    @RequestMapping(value = "products/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("id") final Long id) {
        LOGGER.info("Finding product by ID: " + id);

        return this.productRepository.findById(id).get();
    }

    @RequestMapping(value = "products", method = RequestMethod.POST)
    public Product createProduct(@RequestBody final Product product) {
        return this.productRepository.save(product);
    }

    @RequestMapping(value = "products", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody final Product product) {
        return this.productRepository.save(product);
    }

    @CacheEvict("product-cache")
    @RequestMapping(value = "products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id") final Long id) {
        this.productRepository.deleteById(id);
    }
}
