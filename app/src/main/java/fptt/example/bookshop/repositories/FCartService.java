package fptt.example.bookshop.repositories;

import fptt.example.bookshop.model.MakePaymentRequest;
import fptt.example.bookshop.utils.ConfigApi;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FCartService {
    @POST(ConfigApi.Api.POST_ADD_TO_CART)
    Call<ResponseBody> postAddToCart(@Header("Authorization") String token, @Body RequestBody body);

    @GET(ConfigApi.Api.GET_ALL_IN_CART)
    Call<ResponseBody> getAllInCart(@Header("Authorization") String token);

    @POST(ConfigApi.Api.PAYMENT)
    Call<ResponseBody> payment(@Header("Authorization") String token, @Body MakePaymentRequest body);

    @PUT(ConfigApi.Api.EDIT_CART)
    Call<ResponseBody> editCart(@Header("Authorization") String token, @Path("id") long id,@Body RequestBody body);

    @DELETE(ConfigApi.Api.DELETE_ITEM_IN_CART)
    Call<ResponseBody> deleteItemInCart(@Header("Authorization") String token,  @Path("id") long id);
}
