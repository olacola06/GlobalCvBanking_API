package okhttp;

import com.google.gson.Gson;
import dto.Auth;
import dto.AuthResponse;
import dto.ErrorLoginDto;
import dto.ErrorRegistDto;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class LoginTests {

    static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    Logger logger = LoggerFactory.getLogger(LoginTests.class);
    Properties prop = new Properties();

    @Test
    public void loginPos() throws IOException {
        Auth auth = Auth.builder().username("office@prisma-eo.co.il").password("Bb12345%").build();

        RequestBody  body = RequestBody.create(gson.toJson(auth),JSON);
        Request request = new Request.Builder()
                .url("https://cvbank-main-backend-dev-mkwwqqcvpq-uc.a.run.app/v1/user/login/usernamepassword")
                .post(body).build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);

        AuthResponse authRes = gson.fromJson(response.body().string(),AuthResponse.class);
        String token = authRes.getToken();
        System.out.println(token);

    }
    @Test
    public void loginUnauthorised() throws IOException {
        Auth auth = Auth.builder().username("Dolma-1585@gmail.com").password("Bo123456").build();

        RequestBody body = RequestBody.create(gson.toJson(auth),JSON);
        Request request = new Request.Builder()
                .url("https://cvbank-main-backend-dev-mkwwqqcvpq-uc.a.run.app/v1/user/login/usernamepassword")
                .post(body).build();
        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());

        ErrorLoginDto loginEr = gson.fromJson(response.body().string(),ErrorLoginDto.class);
        logger.info("Status of Error = "+loginEr.withStatus().toString());
        logger.info("Type of Error = "+loginEr.withError().toString());
        logger.info("What went wrong ->>"+loginEr.withMessage().toString());
    }
}
