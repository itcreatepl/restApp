package pl.itcreate;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

class UserUtil {
    static void getUsers() {

        final CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://reqres.in/api/users");
        request.addHeader("custom-key", "mkyong");
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            System.out.println(response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);
            String result = EntityUtils.toString(entity);
            int i = result.indexOf("{");
            result = result.substring(i);

            JSONObject obj = new JSONObject(result.trim());
            JSONArray languagesArr = (JSONArray) obj.get("data");
            System.out.println("--------------------");
            for (int i1 = 0; i1 < languagesArr.length() - 1; i1++) {
                JSONObject userData = (JSONObject) languagesArr.get(i1);
                String first_name = userData.get("first_name").toString();
                String last_name = userData.get("last_name").toString();
                String email = userData.get("email").toString();
                System.out.println("First name: " + first_name +
                        " | Last name: "+ last_name +
                        " | Email:" + email);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void getUser(int userID) {

        final CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://reqres.in/api/users/" + userID);

        // add request headers
        request.addHeader("custom-key", "mkyong");
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());
            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println("--------------------");
            System.out.println(headers);
            String result = EntityUtils.toString(entity);
            //System.out.println(result);
            int i = result.indexOf("{");
            result = result.substring(i);

            JSONObject obj = new JSONObject(result.trim());
            JSONObject data = obj.getJSONObject("data");

            System.out.println("First name:" + data.getString("first_name") +
                    " | Last name: " + data.getString("last_name") +
                    " | Email : " + data.getString("email")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void createUser(String name, String job) {
        HttpPost post = new HttpPost("https://reqres.in/api/users");
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("name", name));
        urlParameters.add(new BasicNameValuePair("job", job));
        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            StatusLine statusLine = response.getStatusLine();
            System.out.println("--------------------");
            System.out.println("Response code: " + statusLine.getStatusCode());
            System.out.println(EntityUtils.toString(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loginUser(String email, String password) {
        HttpPost post = new HttpPost("https://reqres.in/api/login");

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("email", email));
        urlParameters.add(new BasicNameValuePair("password", password));

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            HttpEntity entity = response.getEntity();
            StatusLine statusLine = response.getStatusLine();
            System.out.println("--------------------");
            System.out.println("Response code: " + statusLine.getStatusCode());
            String result = EntityUtils.toString(entity);
            JSONObject obj = new JSONObject(result);
            System.out.println(obj.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void deleteUser(int userID) {
        HttpDelete delete = new HttpDelete("https://reqres.in/api/users/" + userID);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(delete)) {
            StatusLine statusLine = response.getStatusLine();
            System.out.println("--------------------");
            System.out.println("Response code: " + statusLine.getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void updateUser(int userID, String name, String job) {

        HttpPut put = new HttpPut("https://reqres.in/api/users/" + userID);

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("name", name));
        urlParameters.add(new BasicNameValuePair("job", job));

        try {
            put.setEntity(new UrlEncodedFormEntity(urlParameters));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(put)) {
            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            StatusLine statusLine = response.getStatusLine();
            System.out.println("--------------------");
            System.out.println("Response code: " + statusLine.getStatusCode());
            System.out.println(EntityUtils.toString(entity));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
