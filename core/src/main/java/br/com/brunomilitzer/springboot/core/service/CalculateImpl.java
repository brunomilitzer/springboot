package br.com.brunomilitzer.springboot.core.service;

import org.springframework.stereotype.Service;

@Service
public class CalculateImpl implements CalculateService {

    @Override
    public Integer sum(final int num1, final int num2) {
        return num1 + num2;
    }
}
