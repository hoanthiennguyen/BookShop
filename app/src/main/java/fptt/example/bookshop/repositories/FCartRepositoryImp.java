package fptt.example.bookshop.repositories;

import android.content.Context;

import fptt.example.bookshop.model.Bill;
import fptt.example.bookshop.model.CartBook;
import fptt.example.bookshop.model.MakePaymentRequest;
import fptt.example.bookshop.utils.CallBackData;
import fptt.example.bookshop.utils.ClientApi;
import fptt.example.bookshop.utils.ResponseData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
FCartRepositoryImp implements FCartRepository {
    static String token;
    public FCartRepositoryImp() {

    }
    public FCartRepositoryImp(Context context) {
        token = context.getSharedPreferences("MySharedPref", 0).getString("ACCESSTOKEN",null);

    }

    @Override
    public void addToCart(long id, int quantity, final CallBackData<List<CartBook>> data) {
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("quantity", quantity);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> call = clientApi.fCartService().postAddToCart(token, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<CartBook>>>() {
                        }.getType();
                        ResponseData<List<CartBook>> responseData = new Gson().fromJson(result, type);
                        List<CartBook> list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    data.onFail("Bạn cần đăng nhập lại");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void getAllInCart( final CallBackData<List<CartBook>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fCartService().getAllInCart(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<CartBook>>>() {
                        }.getType();
                        ResponseData<List<CartBook>> responseData = new Gson().fromJson(result, type);
                        List<CartBook> list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    data.onFail(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void payment(MakePaymentRequest request,  final CallBackData<Bill> data) {

        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject = new JSONObject();


        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> call = clientApi.fCartService().payment(token, request);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<Bill>>() {
                        }.getType();
                        ResponseData<Bill> responseData = new Gson().fromJson(result, type);
                        Bill bill = responseData.getData();
                        data.onSuccess(bill);
                    } catch (Exception e) {
                        e.printStackTrace();
                        data.onFail("Thanh toán thất bại");
                    }
                } else {
                    data.onFail("Thanh toán thất bại");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                data.onFail("Thanh toán thất bại");
            }
        });

    }


    @Override
    public void editCart(long id, int quantity, final CallBackData<List<CartBook>> data) {
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("quantity", quantity);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> call = clientApi.fCartService().editCart(token, id, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<CartBook>>>() {
                        }.getType();
                        ResponseData<List<CartBook>> responseData = new Gson().fromJson(result, type);
                        List<CartBook> list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    data.onFail("Bạn cần đăng nhập lại");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    @Override
    public void delete(long id, final CallBackData<List<CartBook>> data) {
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject = new JSONObject();
        Call<ResponseBody> call = clientApi.fCartService().deleteItemInCart(token, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<CartBook>>>() {
                        }.getType();
                        ResponseData<List<CartBook>> responseData = new Gson().fromJson(result, type);
                        List<CartBook> list = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(list);
                        } else {
                            data.onFail("Lỗi server");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    data.onFail("Bạn cần đăng nhập lại");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
