package okhttp;

import com.google.gson.Gson;
import dto.Auth;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests {

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    int i = (int)System.currentTimeMillis()/1000%3600;

    @Test
    public void registrationPos() throws IOException {
        Auth auth = Auth.builder().username("olacola"+i).password("Bo123456").build();

        RequestBody body = RequestBody.create(gson.toJson(auth),JSON);
        Request request = new Request.Builder().url("https://cvbank-main-backend-dev-mkwwqqcvpq-uc.a.run.app/" +
                        "v1/user/registration/usernamepassword").post(body).build();
        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());



    }
}
