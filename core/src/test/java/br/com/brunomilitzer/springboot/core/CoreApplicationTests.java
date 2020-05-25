package br.com.brunomilitzer.springboot.core;

import br.com.brunomilitzer.springboot.core.service.CalculateService;
import br.com.brunomilitzer.springboot.core.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CoreApplicationTests {

    @Autowired
    private PaymentService service;

    @Autowired
    private CalculateService calculateService;

    @Test
    void testDependenciesInjection() {
        assertNotNull(this.service);
    }

    @Test
    void testCalculateSumTwoNumbers() {
        assertNotNull(this.calculateService);

        final Integer num1 = 5;
        final Integer num2 = 3;

        final Integer result = this.calculateService.sum(num1, num2);

        assertEquals(result, 8);
    }
}