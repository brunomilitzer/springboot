package br.com.brunomilitzer.trainings.springboot;

import br.com.brunomilitzer.trainings.springboot.repository.ProductRepository;
import br.com.brunomilitzer.trainings.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.Serializable;

public abstract class RestControllerMVCTests implements Serializable {

    private static final long serialVersionUID = 2278765145225109615L;

    protected static final String CONTEXT_PATH = "/restapi";

    protected static final String PRODUCT_URL_PATH = CONTEXT_PATH + "/products";

    protected static final String STUDENT_URL_PATH = CONTEXT_PATH + "/students";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private StudentRepository studentRepository;

    public MockMvc getMockMvc() {
        return this.mockMvc;
    }

    public ProductRepository getProductRepository() {
        return this.productRepository;
    }

    public StudentRepository getStudentRepository() {
        return this.studentRepository;
    }
}
