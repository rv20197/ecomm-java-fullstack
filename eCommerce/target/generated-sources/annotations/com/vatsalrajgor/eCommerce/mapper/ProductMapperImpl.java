package com.vatsalrajgor.eCommerce.mapper;

import com.vatsalrajgor.eCommerce.DTO.Product.ProductDTO;
import com.vatsalrajgor.eCommerce.model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-29T07:29:46+0530",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.45.0.v20260101-2150, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setDiscount( product.getDiscount() );
        productDTO.setImage( product.getImage() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setProductId( product.getProductId() );
        productDTO.setProductName( product.getProductName() );
        productDTO.setQuantity( product.getQuantity() );
        productDTO.setSpecialPrice( product.getSpecialPrice() );

        return productDTO;
    }

    @Override
    public Product toEntity(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setDiscount( productDTO.getDiscount() );
        product.setImage( productDTO.getImage() );
        product.setPrice( productDTO.getPrice() );
        product.setProductId( productDTO.getProductId() );
        product.setProductName( productDTO.getProductName() );
        product.setQuantity( productDTO.getQuantity() );
        product.setSpecialPrice( productDTO.getSpecialPrice() );

        return product;
    }
}
