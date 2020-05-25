package br.com.brunomilitzer.trainings.springboot;

import br.com.brunomilitzer.trainings.springboot.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RestApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProductRestApiTests {

    @Value("${base.url}")
    private String baseUrl;

    @Test
    void testGetProduct() {

        System.out.println(this.baseUrl);
        final RestTemplate restTemplate = new RestTemplate();
        final Product product = restTemplate.getForObject(this.baseUrl + "products/1", Product.class);

        assertNotNull(product);
        assertEquals("Galaxy S20", product.getName());
    }

    @Test
    void testCreateProduct() {

        final Product product = new Product();
        product.setName("Iphone");
        product.setDescription("Iphone 11");
        product.setPrice(1100D);

        final RestTemplate restTemplate = new RestTemplate();
        final Product newProduct = restTemplate.postForObject(this.baseUrl + "products", product, Product.class);

        assertNotNull(newProduct);
        assertNotNull(newProduct.getId());
        assertEquals("Iphone", newProduct.getName());
    }

    @Test
    void testUpdateProduct() {

        final RestTemplate restTemplate = new RestTemplate();
        final Product product = restTemplate.getForObject(this.baseUrl + "products/1", Product.class);

        assertNotNull(product);
        assertNotNull(product.getId());

        product.setName("Galaxy S20+");
        product.setName("Samsung Galaxy S20+");
        product.setPrice(1129D);

        restTemplate.put(this.baseUrl + "products", product);

        assertEquals("Galaxy S20+", product.getName());
        assertEquals("Samsung Galaxy S20+", product.getDescription());
        assertEquals(1129D, product.getPrice());
    }
}
