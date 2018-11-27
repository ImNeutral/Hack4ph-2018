package app.hackathon.suki.suki.network;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SukiServiceGenerator {

    public static final String API_BASE_URL = BaseUrlHelper.BASE_URL;
    public static OkHttpClient client;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static String sBasicCredentials;


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {

        return createService(serviceClass, null, null);
    }

//    Create service with Auth header
    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        if (username != null && password != null) {

            final String credentials = Credentials.basic(username, password);

            final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
            sBasicCredentials = basic;

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", credentials)
                            .addHeader("Accept", "application/json")
                            .addHeader("Content-Type","application/json")
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }else{
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Content-Type","application/json")
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

//    Create service with Auth header using Credentials.Basic returned by API
    public static <S> S createService(Class<S> serviceClass, String credentials) {
        if (credentials!=null) {

            final String basicCred = credentials;
            sBasicCredentials = credentials;
            
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basicCred)
                            .addHeader("Accept", "application/json")
                            .addHeader("X-Requested-With", "XMLHttpRequest")
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });


        }

        client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

}

