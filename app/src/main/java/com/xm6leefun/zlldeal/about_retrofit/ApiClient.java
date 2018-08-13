package com.xm6leefun.zlldeal.about_retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 创建人：zll .
 * 创建时间：2018/3/30 0030.
 * 作用：
 */

public class ApiClient  {
    //TODO 修改了服务器地址
//    public static final String BASE_URL_TEST = "http://api.beybow.com?api_act=";
//    public static final String BASE_URL_TEST = "http://192.168.1.21:8080";
    public static final String BASE_URL_TEST = "http://192.168.1.20:8080";
//    public static final String BASE_URL_TEST = "http://116.62.240.103:8080";



    //构造方法私有
    private static final int DEFAULT_TIMEOUT = 5;

    /* 超时时间*/
    private ApiService apiService;

    private Retrofit retrofit;
    private ApiClient() {

        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new LogInterceptor());
//        httpClientBuilder.addInterceptor(new GzipRequestInterceptor());
//        httpClientBuilder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//
//            }
//        });

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL_TEST)
                .build();
        apiService = retrofit.create(ApiService.class);

    }

    private class LogInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
//            Request.Builder builder = chain.request().newBuilder();
//            Request requst = builder.addHeader("Content-type", "application/x-www-from-urlencoded").build();
////            Request requst = builder.addHeader("Content-type", "application/json").build();
//            return chain.proceed(requst);
            Request request = chain.request();
            long t1 = System.nanoTime();
            okhttp3.Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }

    static class GzipRequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
                return chain.proceed(originalRequest);
            }

            Request compressedRequest = originalRequest.newBuilder()
                    .header("Content-Encoding", "gzip")
                    .method(originalRequest.method(), gzip(originalRequest.body()))
                    .build();
            return chain.proceed(compressedRequest);
        }

        private RequestBody gzip(final RequestBody body) {
            return new RequestBody() {
                @Override
                public MediaType contentType() {
                    return body.contentType();
                }

                @Override
                public long contentLength() {
                    return -1; // 无法知道压缩后的数据大小
                }
                @Override
                public void writeTo(BufferedSink sink) throws IOException {
                    BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                    body.writeTo(gzipSink);
                    gzipSink.close();
                }
            };
        }
    }



    public static ApiService getApiService() {
        return ApiClientHolder.INSTANCE.apiService;
    }


    private static class ApiClientHolder {
        public static ApiClient INSTANCE = new ApiClient();
    }
    public Gson buildGson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory())
//                .registerTypeAdapter(String.class, new IntegerDefault0Adapter())
//                .registerTypeAdapter(String.class, new IntegerDefault0Adapter())
                .create();
        return gson;
    }
    public static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != String.class) {
                return null;
            }
            return (TypeAdapter<T>) new StringAdapter();
        }
    }
    public static class StringAdapter extends TypeAdapter<String> {
        public String read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }
        public void write(JsonWriter writer, String value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }
//    private APIservice mMovieService;
//
//    public ApiClient(Context context) {
//        super(context);
//        mMovieService = mRetrofit.create(APIservice.class);
//    }
//
//    @Override
//    protected String getBaseUrl() {
//        return APIservice.BASE_URL;
//    }
//
//    private static class SingletonHolder {
//        public static final ApiClient INSTANCE = new ApiClient(MyApplication.get());
//    }
//
//    public static ApiClient getInstance() {
//        return SingletonHolder.INSTANCE;
//    }
//
//    public void getLogin(String tag,  String mobile, String pwd,Subscriber<DataLogins> subscriber){
//        Subscription subscription = mMovieService.api_login(mobile,pwd)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
////                .map(new PostSubscriber<DataLogins>())
////                .compose(ArtistUtils.<List<Movie>>applySchedulers())
////                .subscribe(subscriber);
//        addSubscription(tag,subscription);
//    }
}
