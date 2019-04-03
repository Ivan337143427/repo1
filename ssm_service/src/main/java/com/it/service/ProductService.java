package com.it.service;

import com.github.pagehelper.PageInfo;
import com.it.domain.Product;

import java.util.List;

public interface ProductService {
    PageInfo findAll(int page, int size);
    void save(Product product);
    void delete(String[] ids);
}
