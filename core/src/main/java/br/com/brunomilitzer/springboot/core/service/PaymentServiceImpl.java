package br.com.brunomilitzer.springboot.core.service;

import br.com.brunomilitzer.springboot.core.dao.PaymentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentDAO dao;

    public PaymentDAO getDao() {
        return this.dao;
    }

    @Autowired
    public void setDao(final PaymentDAO dao) {
        this.dao = dao;
    }
}
