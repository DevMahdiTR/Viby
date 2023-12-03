package com.chefme.chefme.controller.product;

import com.chefme.chefme.service.product.ProductService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @PostMapping()
    public ResponseEntity<Object> addProduct(
            @AuthenticationPrincipal @NotNull UserDetails userDetails ,
            @RequestParam(name = "image") @NotNull final MultipartFile image,
            @RequestParam(name = "productJson") @NotNull final String productJson) throws IOException {
        return productService.addProduct(userDetails , image , productJson);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Object> updateProductById(
            @AuthenticationPrincipal @NotNull  final UserDetails userDetails,
            @PathVariable("productId") final long productId,
            @RequestParam(name = "image" , required = false)  MultipartFile image,
            @RequestParam(name = "productJson") @Valid @NotNull final String productJson) throws IOException
    {
        return productService.updateProductById(userDetails , productId , image , productJson);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Object> deleteProductById(
            @AuthenticationPrincipal @NotNull  final UserDetails userDetails,
            @PathVariable("productId") final long productId ) throws IOException {
        return productService.deleteProductById(userDetails , productId);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Object> fetchProductById(
            @AuthenticationPrincipal @NotNull  final UserDetails userDetails,
            @PathVariable("productId") final long productId)
    {
        return productService.fetchProductById(userDetails, productId);
    }

    @GetMapping("/page/{pageNumber}")
    public ResponseEntity<Object> fetchProductsByIdInBusinessById(
            @AuthenticationPrincipal @NotNull  final UserDetails userDetails ,
            @PathVariable("pageNumber") final  int pageNumber)
    {
        return productService.fetchProductsByIdInBusinessById(userDetails, pageNumber);
    }

}
