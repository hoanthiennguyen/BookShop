package fptt.example.bookshop;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApiAuthenticationClient {

    private String baseUrl;
    private String username;
    private String password;
    private String urlResource;
    private String httpMethod;
    private String urlPath;
    private String lastResponse;
    private String payload;
    private HashMap<String, String> parameters;
    private Map<String, List<String>> headerFields;

    public ApiAuthenticationClient(String baseUrl, String username, String password) {
        setBaseUrl(baseUrl);
        this.username = username;
        this.password = password;
        this.urlResource = "";
        this.urlPath = "";
        this.httpMethod = "GET";
        parameters = new HashMap<>();
        lastResponse = "";
        payload = "";
        headerFields = new HashMap<>();
        System.setProperty("jsse.enableSNIExtension", "false");
    }

    public ApiAuthenticationClient setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        if (!baseUrl.substring(baseUrl.length() - 1).equals("/")) {
            this.baseUrl += "/";
        }
        return this;
    }

    public ApiAuthenticationClient setUrlResource(String urlResource) {
        this.urlResource = urlResource;
        return this;
    }

    public final ApiAuthenticationClient setUrlPath(String urlPath) {
        this.urlPath = urlPath;
        return this;
    }

    public ApiAuthenticationClient setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
        return this;
    }

    public String getLastResponse() {
        return lastResponse;
    }

    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    public ApiAuthenticationClient setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    public ApiAuthenticationClient setParameter(String key, String value) {
        this.parameters.put(key, value);
        return this;
    }

    public ApiAuthenticationClient clearParameters() {
        this.parameters.clear();
        return this;
    }

    public ApiAuthenticationClient removeParameter(String key) {
        this.parameters.remove(key);
        return this;
    }

    public ApiAuthenticationClient clearAll() {
        parameters.clear();
        baseUrl = "";
        this.username = "";
        this.password = "";
        this.urlResource = "";
        this.urlPath = "";
        this.httpMethod = "";
        lastResponse = "";
        payload = "";
        headerFields.clear();
        return this;
    }

    public JSONObject getLastResponseAsJsonObject() {
        try {
            return new JSONObject(String.valueOf(lastResponse));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getLastResponseAsJsonArray () {
        try {
            return new JSONObject(String.valueOf(lastResponse));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getPayloadAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = parameters.entrySet().iterator();
        int count = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (count > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(pair.getKey()).append("=").append(pair.getValue());
            it.remove();
            count++;
        }
        return stringBuilder.toString();
    }

    public String execute() {
        String line;
        StringBuilder ouputStringBuilder = new StringBuilder();
        try {

            StringBuilder urlString = new StringBuilder(baseUrl + urlResource);

            if (!urlPath.equals("")) {
                urlString.append("/" + urlPath);
            }
            System.out.println("httpMethod "+httpMethod);
            if (parameters.size() > 0 && httpMethod.equals("GET")) {
                payload = getPayloadAsString();
                urlString.append("?" + payload);
            }else {
                httpMethod = "POST";
            }

            URL url = new URL(urlString.toString());

            String encoding = username + ":" + password;
            System.out.println("username"+username+"password"+password);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(httpMethod);
            connection.setRequestProperty("Authorization", "Bearer " + encoding);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "text/plain");

            if (httpMethod.equals("POST") || httpMethod.equals("PUT")) {
                payload = getPayloadAsString();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                try {
                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                    writer.write(payload);
                    System.out.println("payload "+payload);
                    headerFields = connection.getHeaderFields();

                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    while ((line = br.readLine()) != null) {
                        ouputStringBuilder.append(line);
                    }
                } catch (Exception e) {
                    System.out.println("fail");
                    e.printStackTrace();
                    connection.disconnect();
                }
            } else {
                InputStream content = (InputStream) connection.getInputStream();

                headerFields = connection.getHeaderFields();

                BufferedReader in = new BufferedReader(new InputStreamReader(content));
                while ((line = in.readLine()) != null) {
                    ouputStringBuilder.append(line);
                }
            }

        } catch (Exception e) {
            System.out.println("fail1");
            e.printStackTrace();
        }
        if (!ouputStringBuilder.toString().equals("")) {
            lastResponse = ouputStringBuilder.toString();
        }
        return ouputStringBuilder.toString();
    }
}
