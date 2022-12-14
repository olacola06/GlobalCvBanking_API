package okhttp;

import com.google.gson.Gson;
import dto.*;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationTests {

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    int i = (int)System.currentTimeMillis()/1000%3600;
    Logger logger = LoggerFactory.getLogger(RegistrationTests.class);

    @Test
    public void registrationPos() throws IOException {
        AuthProfile authPro = AuthProfile.builder().publicId("").name("Yossi").lastName("")
                .email("").house(0).phone("").position("").street("").zip("").city("")
                .companyName("").companyPhone("").companyWebsite("")
                .country("").role("SEEKER").build();

        Auth auth = Auth.builder().username("Dolma"+i+"@gmail.com").password("Bo123456").profile(authPro)
                .build();
        logger.info("User to be registered with details->>"+auth.getUsername()+", "+auth.getPassword());

        RequestBody body = RequestBody.create(gson.toJson(auth),JSON);
        Request request = new Request.Builder().url("https://cvbank-main-backend-dev-mkwwqqcvpq-uc.a.run.app/" +
                        "v1/user/registration/usernamepassword").post(body).build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);

        AuthResponse authRes = gson.fromJson(response.body().string(), AuthResponse.class);
        System.out.println(authRes.getMessage().toString());
    }
    @Test
    public void registrationWrongData() throws IOException {
        AuthProfile authPro = AuthProfile.builder().name("Yossi").role("SEEKER").build();

        Auth auth = Auth.builder().username("Dolma"+i+"gmail.com").password("").profile(authPro).build();
        RequestBody body = RequestBody.create(gson.toJson(auth),JSON);
        Request request = new Request.Builder().url("https://cvbank-main-backend-dev-mkwwqqcvpq-uc.a.run.app/" +
                "v1/user/registration/usernamepassword").post(body).build();
        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),400);

        ErrorRegistDto responseError = gson.fromJson(response.body().string(), ErrorRegistDto.class);
        System.out.println(responseError.getError().toString());
        System.out.println(responseError.getMessage().toString());

    }
}
