package com.sysc4806.mini_shopify;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ownerDashboardPageTest {

    @Test
    public void testPageElementsExist() {
        String headerId = "welcome-msg";
        String storeIdInput = "storeId";
        String storeNameInput = "storeName";
        String storeOwnerInput = "storeOwner";
        String storeCategoryInput = "storeCategory";
        String storeDescriptionInput = "storeDescription";
        String saveButton = "Save Changes";
        String deleteButton = "delete-store-btn";

        assertNotNull(headerId, "Header should exist");
        assertNotNull(storeIdInput, "Store ID input should exist");
        assertNotNull(storeNameInput, "Store Name input should exist");
        assertNotNull(storeOwnerInput, "Store Owner input should exist");
        assertNotNull(storeCategoryInput, "Store Category input should exist");
        assertNotNull(storeDescriptionInput, "Store Description input should exist");
        assertNotNull(saveButton, "Save button should exist");
        assertNotNull(deleteButton, "Delete button should exist");
    }

}
