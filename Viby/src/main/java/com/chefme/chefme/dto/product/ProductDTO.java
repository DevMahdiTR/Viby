package com.chefme.chefme.dto.product;

import com.chefme.chefme.dto.business.BusinessDTO;
import com.chefme.chefme.model.file.FileData;

public record ProductDTO(

        long id,
        String name ,
        double price,
        long pointToRedeem,
        FileData fileData,
        BusinessDTO businessDTO

) {
}
