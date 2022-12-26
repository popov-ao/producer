package edu.stateuniversityoftelecommunications.project.Util;

import edu.stateuniversityoftelecommunications.project.domain.ProductEntity;

import java.time.Instant;

import static edu.stateuniversityoftelecommunications.project.Util.TestConstant.MESSAGE_NAME;
import static edu.stateuniversityoftelecommunications.project.Util.TestConstant.PRODUCT_SKU;
import static edu.stateuniversityoftelecommunications.project.Util.TestConstant.QUANTITY_THEN;

public class TestUtil {
    public static ProductEntity newProductEntity() {
        Instant timeNow = Instant.now();
        return ProductEntity.builder()
                .id(1L)
                .productName(MESSAGE_NAME)
                .productSku(PRODUCT_SKU)
                .quantity(QUANTITY_THEN)
                .createdAt(timeNow)
                .updatedAt(timeNow)
                .build();
    }
}
