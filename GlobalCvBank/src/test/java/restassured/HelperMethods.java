package restassured;

import com.jayway.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

public class HelperMethods {

    Logger logger = LoggerFactory.getLogger(HelperMethods.class);

    @BeforeSuite
    public void init(){
        RestAssured.baseURI = "https://cvbank-main-backend-dev-mkwwqqcvpq-uc.a.run.app";
    }
}
