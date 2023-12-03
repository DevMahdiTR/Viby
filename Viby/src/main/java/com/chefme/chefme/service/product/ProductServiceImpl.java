package com.chefme.chefme.service.product;


import com.chefme.chefme.dto.product.ProductDTO;
import com.chefme.chefme.dto.product.ProductDTOMapper;
import com.chefme.chefme.exceptions.ResourceNotFoundException;
import com.chefme.chefme.model.business.Business;
import com.chefme.chefme.model.file.FileData;
import com.chefme.chefme.model.product.Product;
import com.chefme.chefme.repository.ProductRepository;
import com.chefme.chefme.responses.ResponseHandler;
import com.chefme.chefme.service.file.FileService;
import com.chefme.chefme.service.user.UserEntityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl  implements  ProductService{

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;
    private final UserEntityService userEntityService;
    private final FileService fileService;

    public ProductServiceImpl(ProductRepository productRepository, ProductDTOMapper productDTOMapper, UserEntityService userEntityService, FileService fileService) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
        this.userEntityService = userEntityService;
        this.fileService = fileService;
    }


    @Override
    public ResponseEntity<Object> addProduct(@NotNull UserDetails userDetails, @NotNull MultipartFile file, @NotNull String productJson) throws IOException {
        Business existingUser = (Business) userEntityService.getUserEntityByEmail(userDetails.getUsername());
        Product newproduct = new ObjectMapper().readValue(productJson,Product.class);
        FileData image = fileService.processUploadedFile(file);
        newproduct.setFileData(image);
        newproduct.setBusiness(existingUser);
        final Product finalProduct = productRepository.save(newproduct);
        final ProductDTO product = productDTOMapper.apply(finalProduct);
        return ResponseHandler.generateResponse(product , HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateProductById(UserDetails userDetails,long productId, MultipartFile file, @NotNull String productJson) throws IOException {
        Business existingUser = (Business) userEntityService.getUserEntityByEmail(userDetails.getUsername());
        Product existingProduct = getProductById(userDetails,productId);
        FileData prevImage = existingProduct.getFileData();
        Product productDetails = new ObjectMapper().readValue(productJson ,Product.class);
        productDetails.setId(existingProduct.getId());
        productDetails.setFileData(prevImage);
        productDetails.setBusiness(existingProduct.getBusiness());


        if(file != null)
        {

            FileData newImage = fileService.processUploadedFile(file);
            productDetails.setFileData(newImage);
        }

        Product newProduct = productRepository.save(productDetails);
        ProductDTO product = productDTOMapper.apply(newProduct);
        fileService.deleteFileFromFileSystem(prevImage);

        return ResponseHandler.generateResponse(product , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteProductById(UserDetails userDetails,long productId) throws IOException {
        Business existingUser = (Business) userEntityService.getUserEntityByEmail(userDetails.getUsername());
        Product existingProduct = getProductById(userDetails,productId);
        productRepository.deleteProductByIdOfBusinessById(productId , existingUser.getId());
        fileService.deleteFileFromFileSystem(existingProduct.getFileData());
        final String successResponse = String.format("The Product with ID : %d deleted successfully.", productId);
        return ResponseHandler.generateResponse(successResponse , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> fetchProductById(UserDetails userDetails,long productId) {
        Business existingUser = (Business) userEntityService.getUserEntityByEmail(userDetails.getUsername());
        Product existingCoupon = getProductById(userDetails,productId);
        ProductDTO product = productDTOMapper.apply(existingCoupon);
        return ResponseHandler.generateResponse(product , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> fetchProductsByIdInBusinessById(@NotNull UserDetails userDetails , final int pageNumber) {
        Business existingUser = (Business) userEntityService.getUserEntityByEmail(userDetails.getUsername());
        Pageable pageable = PageRequest.of(pageNumber - 1 , 10);
        List<Product> productList = productRepository.fetchAllProductOfBusinessById(existingUser.getId() , pageable);

        if(productList.isEmpty() && pageNumber > 1)
        {
            return fetchProductsByIdInBusinessById(userDetails , 1);
        }
        List<ProductDTO> products = productList.stream().map(productDTOMapper).toList();
        final int totalPages = (int)Math.ceil(productRepository.getCountOfProductInBusinessById(existingUser.getId()) / 10.0);

        return ResponseHandler.generateResponse(products,HttpStatus.OK , products.size() , totalPages);

    }

    @Override
    public Product getProductById(@NotNull  final UserDetails userDetails,long productId) {
        Business existingUser = (Business) userEntityService.getUserEntityByEmail(userDetails.getUsername());
        return productRepository.fetchProductByIdOfBusiness(productId , existingUser.getId()).orElseThrow(
                () -> new ResourceNotFoundException("This Product could not be found in our system.")
        );
    }
}
