package org.coolstore.catalog.resource.client;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class InventoryServiceTest {

    @Test
    public void testGetByItemId() {
        get("http://localhost:8080/api/inventory/329299").then().statusCode(200).body("size()", is(1));
    }

}