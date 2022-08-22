package restassured;

import dto.Auth;
import dto.AuthProfile;
import dto.AuthResponse;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RegistrationTests extends HelperMethods {
    int i = (int) System.currentTimeMillis()/1000%3600;

    @Test
    public void registrationPos(){
        AuthProfile profile = AuthProfile.builder().name("Oleg").lastName("Fox").position("Chief").build();
        Auth auth = Auth.builder().username("Afr"+1+"@gmail.com").password("Ar12345@").profile(profile).build();
        logger.info("");

        AuthResponse responce = given().body(auth).contentType("application/json")
                .when().post("/v1/user/registration/usernamepassword")
                .then().assertThat().statusCode(200)
                .extract().response().as(AuthResponse.class);
        System.out.println(responce.getMessage().toString());
    }

    @Test
    public void registrationWrongData(){
        AuthProfile profile = AuthProfile.builder().name("Oleg").lastName("Fox").position("Chief").build();
        Auth auth = Auth.builder().username("Afr"+1+"@gmail.com").password("Ar12345@").profile(profile).build();

    }
}
