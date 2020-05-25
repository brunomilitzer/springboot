package br.com.brunomilitzer.trainings.springboot;

import br.com.brunomilitzer.trainings.springboot.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ProductRestControllerMVCTest extends RestControllerMVCTests {

    private static final long PRODUCT_ID = 1L;
    private static final String PRODUCT_NAME = "Galaxy S20+";
    private static final String PRODUCT_DESCRIPTION = "Samsung Galaxy S20+";
    private static final double PRODUCT_PRICE = 1129.00;
    private static final long serialVersionUID = 5507153417883287679L;

    @Test
    public void testFindAll() throws Exception {
        final Product product = this.buildProduct();

        final List<Product> products = Collections.singletonList(product);
        when(this.getProductRepository().findAll()).thenReturn(products);

        this.getMockMvc().perform(get(PRODUCT_URL_PATH).contextPath(CONTEXT_PATH)).andExpect(status().isOk())
                .andExpect(content().json(this.objectWritter().writeValueAsString(products)));
    }

    @Test
    public void testFindById() throws Exception {
        final Product product = this.buildProduct();

        when(this.getProductRepository().findById(any())).thenReturn(Optional.of(product));

        this.getMockMvc().perform(get(PRODUCT_URL_PATH + "/" + PRODUCT_ID).contextPath(CONTEXT_PATH)).andExpect(status().isOk())
                .andExpect(content().json(this.objectWritter().writeValueAsString(product)));
    }

    @Test
    public void testCreateProduct() throws Exception {
        final Product product = this.buildProduct();
        when(this.getProductRepository().save(any())).thenReturn(product);

        this.getMockMvc().perform(post(PRODUCT_URL_PATH).contextPath(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON).content(this.objectWritter()
                .writeValueAsString(product))).andExpect(status().isOk()).andExpect(content().json(this.objectWritter().writeValueAsString(product)));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        final Product product = this.buildProduct();
        product.setPrice(2200D);
        when(this.getProductRepository().save(any())).thenReturn(product);

        this.getMockMvc().perform(put(PRODUCT_URL_PATH).contextPath(CONTEXT_PATH).contentType(MediaType.APPLICATION_JSON).content(this.objectWritter()
                .writeValueAsString(product))).andExpect(status().isOk()).andExpect(content().json(this.objectWritter().writeValueAsString(product)));
    }

    @Test
    public void deleteProduct() throws Exception {
        doNothing().when(this.getProductRepository()).deleteById(PRODUCT_ID);

        this.getMockMvc().perform(delete(PRODUCT_URL_PATH + "/" + PRODUCT_ID).contextPath(CONTEXT_PATH)).andExpect(status().isOk());
    }

    private ObjectWriter objectWritter() {
        return new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    private Product buildProduct() {
        final Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setName(PRODUCT_NAME);
        product.setDescription(PRODUCT_DESCRIPTION);
        product.setPrice(PRODUCT_PRICE);
        return product;
    }
}
