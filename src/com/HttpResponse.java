package com;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import static com.Util.getNewHttpClient;

@SuppressWarnings({"unused", "WeakerAccess"})
public class HttpResponse {
    private static transient HttpClient httpClient = getNewHttpClient(null);

    public final int status;
    public final String url;
    public final JsonObject response;
    public final String responseString;

    private HttpResponse(int status, String url, JsonObject response, String responseString) {
        this.status = status;
        this.url = url;
        this.response = response;
        this.responseString = responseString;
    }

    public static class RequestBuilder {
        private transient String _url = null;
        private String _requestEntity = null;
        private transient JsonObject _requestObject = null;
        private transient ArrayList<RequestHeader> _requestHeaders = new ArrayList<>();
        private transient ArrayList<RequestParameter> _requestParams = new ArrayList<>();
        private Integer _timeout;

        public RequestBuilder(String url) {
            this._url = url;
        }

        public HttpResponse.RequestBuilder setStringContent(String entity) {
            this._requestEntity = entity;
            return this;
        }

        public HttpResponse.RequestBuilder setJsonStringContent(JsonObject object) {
            this._requestObject = object;
            return this;
        }

        public HttpResponse.RequestBuilder setURL(String url) {
            this._url = url;
            return this;
        }

        public HttpResponse.RequestBuilder addHeader(String key, String value) {
            return this.addHeader(new HttpResponse.RequestHeader(key, value));
        }

        public HttpResponse.RequestBuilder addHeader(HttpResponse.RequestHeader header) {
            return this.addHeader(new HttpResponse.RequestHeader[] {header});
        }

        public HttpResponse.RequestBuilder addHeader(HttpResponse.RequestHeader[] headers) {
            Collections.addAll(_requestHeaders, headers);
            return this;
        }

        public HttpResponse.RequestBuilder addParameter(String key, String value) {
            return this.addParameter(new HttpResponse.RequestParameter(key, value));
        }

        public HttpResponse.RequestBuilder addParameter(HttpResponse.RequestParameter param) {
            return this.addParameter(new HttpResponse.RequestParameter[] {param});
        }

        public HttpResponse.RequestBuilder addParameter(HttpResponse.RequestParameter[] params) {
            Collections.addAll(_requestParams, params);
            return this;
        }

        public HttpResponse.RequestBuilder setTimeout(Integer timeout){
            _timeout = timeout;
            return this;
        }

        public HttpResponse.RequestBuilder setAuthentication(String username, String password){
            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username,password);
            provider.setCredentials(AuthScope.ANY,credentials);
            httpClient = getNewHttpClient(provider);
            return this;
        }

        public String getURL() {
            return this._url;
        }

        public JsonObject getJSONObject() {
            return this._requestObject;
        }

        public HttpResponse GET() {
            HttpGet request;
            MainScreen.getInstance().addLogEntry("Sending HttpResponse GET to " + (_url.contains("password") ? _url.substring(0,_url.indexOf("password")) : _url));

            // Add parameters
            if(_requestParams.size() > 0) {
                MainScreen.getInstance().addLogEntry("\tWith request parameters:");
                try {
                    URIBuilder builder = new URIBuilder(_url);
                    for(RequestParameter param : _requestParams) {
                        builder.addParameter(param._key, param._value);
                        MainScreen.getInstance().addLogEntry("\t\tKey: \"" + param._key + "\", Value: \"" + param._value + "\"");
                    }

                    request = new HttpGet(builder.build());
                } catch(Exception e) {
                    request = new HttpGet(_url);
                    MainScreen.getInstance().addLogEntry("Failed to add request parameters (" + e.getMessage() + ")");
                }
            } else {
                request = new HttpGet(_url);
            }

            // Add headers
            if(this._requestHeaders.size() > 0) {
                for(HttpResponse.RequestHeader header : _requestHeaders)
                    request.addHeader(header._key, header._value);
            }
            //Add timeout config
            if(_timeout != null) {
                RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(_timeout).setSocketTimeout(_timeout).setConnectionRequestTimeout(_timeout).build();
                request.setConfig(requestConfig);
            }

            org.apache.http.HttpResponse response = null;
            try {
                response = HttpResponse.httpClient.execute(request);
                return handleResponse(response);
            } catch(IOException e) {
                MainScreen.getInstance().addLogEntry("Failed to execute HTTP GET HttpResponse: " + _url + "Error: " + e.getMessage());
            }finally{
                request.releaseConnection();
            }
            return new HttpResponse(-1, this._url, null,null);
        }

        @SuppressWarnings("Duplicates")
        public HttpResponse POST() {
            HttpPost request;
            // Add parameters
            if(_requestParams.size() > 0) {
                try {
                    URIBuilder builder = new URIBuilder(_url);
                    for(RequestParameter param : _requestParams)
                        builder.addParameter(param._key, param._value);

                    request = new HttpPost(builder.build());
                } catch(Exception e) {
                    request = new HttpPost(_url);
                    MainScreen.getInstance().addLogEntry("Failed to add request parameters (" + e.getMessage() + ")");
                }
            } else {
                request = new HttpPost(_url);
            }

            // Set JSON entity
            if(this._requestObject != null)
                this._requestEntity = _requestObject.toString();

            // Set entity if needed
            if(this._requestEntity != null) {
                try {
                    request.setEntity(new StringEntity(this._requestEntity, ContentType.APPLICATION_JSON));
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid POST entity (" + e.getMessage() + ")");
                }
            }

            // Add headers
            if(this._requestHeaders.size() > 0) {
                for(HttpResponse.RequestHeader header : _requestHeaders)
                    request.addHeader(header._key, header._value);
            }

            //Add timeout config
            if(_timeout != null) {
                RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(_timeout).setSocketTimeout(_timeout).setConnectionRequestTimeout(_timeout).build();
                request.setConfig(requestConfig);
            }

            org.apache.http.HttpResponse response = null;
            try {
                do {
                    response = HttpResponse.httpClient.execute(request);
                }while(response.getStatusLine().getStatusCode() == 429);
                return handleResponse(response);
            } catch(IOException e) {
                MainScreen.getInstance().addLogEntry("Failed to execute HTTP POST HttpResponse: " + e.getMessage());
            }finally{
                request.releaseConnection();
            }

            return new HttpResponse(-1, this._url, null, null);
        }

        private HttpResponse handleResponse(org.apache.http.HttpResponse response) {
            int status = -1;
            JsonObject json = null;
            StringBuilder responseString = new StringBuilder();
            BufferedReader br;

            if(response != null) {
                status = response.getStatusLine().getStatusCode();
                try {
                    br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    String line;
                    while((line = br.readLine()) != null)
                        responseString.append(line);
                    json = HttpResponse.parseJSON(responseString.toString());
                    if(json != null)
                        responseString = null;
                } catch (IOException e) {
                    MainScreen.getInstance().addLogEntry("Failed to parse HTTP HttpResponse (IOException: " + e.getMessage() + ")");
                }
            } else {
                return new HttpResponse(status, this._url, null,null);
            }

            return new HttpResponse(status, this._url, json,responseString != null ? responseString.toString() : null);
        }
    }

    public static class RequestHeader {
        private final String _key;
        private final String _value;

        public RequestHeader(String key, String value) {
            this._key = key;
            this._value = value;
        }
    }

    public static class RequestParameter {
        private final String _key;
        private final String _value;

        public RequestParameter(String key, String value) {
            this._key = key;
            this._value = value;
        }
    }

    @SuppressWarnings("Duplicates")
    private static JsonObject parseJSON(String response) {
        BufferedReader br;

        try {
            if(response.length() <= 0)
                throw new Exception("JSON string length must be greater than 0");

            JsonElement jsonElement = (new JsonParser()).parse(response);

            if(jsonElement.isJsonObject()) {
                return jsonElement.getAsJsonObject();
            } else if(jsonElement.isJsonArray()) {
                // If it is a JSON array, nest inside a JSON object
                JsonObject obj = new JsonObject();
                obj.add("array", jsonElement.getAsJsonArray());
                return obj;
            } else {
                throw new Exception("JSON is not a JSON Object or JSON Array");
            }

        } catch(IOException e) {
            MainScreen.getInstance().addLogEntry("Failed to parse HTTP response JSON (IOException: " + e.getMessage() + ")");
            if(response != null)
                MainScreen.getInstance().addLogEntry("\t(HTTP response: " + response + ")");

            return null;
        } catch(Exception e) {
            MainScreen.getInstance().addLogEntry("Failed to parse HTTP response JSON (" + e.getMessage() + ")");
            if(response != null)
                MainScreen.getInstance().addLogEntry("\t(HTTP response: " + response + ")");

            return null;
        }
    }
}
