package fptt.example.bookshop.repositories;


import android.content.Context;

import fptt.example.bookshop.model.Token;
import fptt.example.bookshop.model.User;
import fptt.example.bookshop.utils.CallBackData;
import fptt.example.bookshop.utils.ClientApi;
import fptt.example.bookshop.utils.ResponseData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;


import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAccountRepositoryImp implements FAccountRepository {
    String token ;
    public FAccountRepositoryImp(Context context) {
        token = context.getSharedPreferences("MySharedPref", 0).getString("ACCESSTOKEN",null);

    }

    public FAccountRepositoryImp() {

    }

    @Override
    public void login(String username, String password, final CallBackData<Token> data) {
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> call = clientApi.fAccountService().login(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<Token>() {
                        }.getType();
                        Token responseData = new Gson().fromJson(result, type);

                        if (responseData != null && responseData.getTokenType().equals("Bearer")) {
                            data.onSuccess(responseData);
                        } else {
                            data.onFail("Tên đăng nhập hoặc mật khẩu sai");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                data.onFail("Lỗi server");
            }
        });
    }

    @Override
    public void register(String username, String email, String password, final CallBackData<Token> data) {

        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            jsonObject.put("email", email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> call = clientApi.fAccountService().register(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<Token>() {}.getType();
                        Token responseData = new Gson().fromJson(result, type);

                        if (responseData != null) {
                            data.onSuccess(responseData);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (response.code() == 412) {
                    data.onFail("Tài khoản đã tồn tại");
                } else {
                    data.onFail("Đăng ký không thành công");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void getProfile(final CallBackData<User> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fAccountService().getProfile(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<User>>() {
                        }.getType();
                        ResponseData<User> responseData = new Gson().fromJson(result, type);
                        User user = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(user);
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
    public void updateProfile(User user,final CallBackData<User> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fAccountService().updateProfile(token,user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<User>>() {
                        }.getType();
                        ResponseData<User> responseData = new Gson().fromJson(result, type);
                        User user = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(user);
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
}
