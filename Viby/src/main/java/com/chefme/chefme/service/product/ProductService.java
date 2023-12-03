package com.chefme.chefme.service.product;

import com.chefme.chefme.model.product.Product;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {

    public ResponseEntity<Object> addProduct(@NotNull  final UserDetails userDetails,@NotNull MultipartFile file, @Valid @NotNull final String productJson) throws IOException;
    public ResponseEntity<Object> updateProductById(@NotNull  final UserDetails userDetails, final long productId, MultipartFile file, @Valid @NotNull final String productJson) throws IOException;
    public ResponseEntity<Object> deleteProductById(@NotNull  final UserDetails userDetails,final long productId ) throws IOException;
    public ResponseEntity<Object> fetchProductById(@NotNull  final UserDetails userDetails,final long productId);
    public ResponseEntity<Object> fetchProductsByIdInBusinessById(@NotNull  final UserDetails userDetails , final  int pageNumber);
    public Product getProductById(@NotNull  final UserDetails userDetails,final long productId);

}
