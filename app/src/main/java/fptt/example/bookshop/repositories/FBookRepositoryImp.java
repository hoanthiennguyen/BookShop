package fptt.example.bookshop.repositories;

import android.content.Context;

import fptt.example.bookshop.model.Book;
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

public class FBookRepositoryImp implements FBookRepository {
    static String token;
    public FBookRepositoryImp(Context context) {
        token = context.getSharedPreferences("MySharedPref", 0).getString("ACCESSTOKEN",null);

    }

    public FBookRepositoryImp() {
    }

    @Override
    public void postClickedBook(long bookId) {
        ClientApi clientApi = new ClientApi();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("bookId", bookId);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
        Call<ResponseBody> call = clientApi.fBookService().addToClickedBooks(token, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {

                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }



    @Override
    public void getClickedBooks( final CallBackData<List<Book>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fBookService().getClickedBooks(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Book>>>() {
                        }.getType();
                        ResponseData<List<Book>> responseData = new Gson().fromJson(result, type);
                        List<Book> list = responseData.getData();
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
    public void search( String search, final CallBackData<List<Book>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fBookService().search(token, search);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Book>>>() {
                        }.getType();
                        ResponseData<List<Book>> responseData = new Gson().fromJson(result, type);
                        List<Book> list = responseData.getData();
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
    public void getAllBookNames(final CallBackData<List<String>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fBookService().getAllBookNames(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<String>>>() {
                        }.getType();
                        ResponseData<List<String>> responseData = new Gson().fromJson(result, type);
                        List<String> list = responseData.getData();
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
    public void getBooksByCategory(String category, int page, final CallBackData<List<Book>> data) {
        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fBookService().getBookByCategory(token,category,page);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<List<Book>>>() {
                        }.getType();
                        ResponseData<List<Book>> responseData = new Gson().fromJson(result, type);
                        List<Book> list = responseData.getData();
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
    public void getBookById( long id, final CallBackData<Book> data) {

        ClientApi clientApi = new ClientApi();
        Call<ResponseBody> call = clientApi.fBookService().getBookById(token, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseData<Book>>() {
                        }.getType();
                        ResponseData<Book> responseData = new Gson().fromJson(result, type);
                        Book book = responseData.getData();
                        if (responseData != null) {
                            data.onSuccess(book);
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
