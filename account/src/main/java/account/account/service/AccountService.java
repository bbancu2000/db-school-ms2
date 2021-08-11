package account.account.service;

import account.account.domain.Account;
import account.account.repository.AccountRepository;
import okhttp3.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }


    public String invokeEndpoint() throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8082/all")
                .method("GET", null)
                .addHeader("Authorization", "Basic dXNlcjE6dXNlcjFwYXNz")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        return response.body().string();
    }


    public void invokeEndpointCreateUser(String firstName, String lastName) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n  \"firstName\": \""+firstName+"\",\r\n  \"lastName\": \""+lastName+"\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8082/create")
                .method("POST", body)
                .addHeader("Authorization", "Basic dXNlcjE6dXNlcjFwYXNz")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
    }

    public void invokeEndpointDeleteUser(Long id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://localhost:8082/delete/"+id+"")
                .method("DELETE", body)
                .addHeader("Authorization", "Basic dXNlcjE6dXNlcjFwYXNz")
                .build();
        Response response = client.newCall(request).execute();


    }






}
