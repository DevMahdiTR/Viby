package com.chefme.chefme.dto.product;

import com.chefme.chefme.dto.business.BusinessDTO;
import com.chefme.chefme.dto.business.BusinessDTOMapper;
import com.chefme.chefme.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {

    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getPointToRedeem(),
                product.getFileData(),
                new BusinessDTOMapper().apply(product.getBusiness())
        );
    }
}
