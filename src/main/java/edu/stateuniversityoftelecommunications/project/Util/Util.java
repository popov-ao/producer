package edu.stateuniversityoftelecommunications.project.Util;

import edu.diploma.avro.schema.product.Product;
import edu.stateuniversityoftelecommunications.project.domain.ProductEntity;

public class Util {

    public static ProductEntity getProductEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .productName(valueOf(product.getProductName()))
                .productSku(valueOf(product.getProductSku()))
                .quantity(product.getQuantity())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static String valueOf(CharSequence sequence) {
        return sequence == null ?  null : String.valueOf(sequence);
    }

}
