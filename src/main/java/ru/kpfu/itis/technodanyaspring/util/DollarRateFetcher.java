package ru.kpfu.itis.technodanyaspring.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class DollarRateFetcher {
    private static final String API_ACCESS_KEY = "xmEfF8nFmpP5vJmp6TOMxKrC3a7q3jAH";
    private static final String BASE_URL = "https://api.apilayer.com/fixer/latest";

    public static double fetchDollarRate() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(BASE_URL + "?base=USD" + "&symbols=RUB")
                .addHeader("apikey", API_ACCESS_KEY)
                .get()
                .build();

        try {
            try (Response response = client.newCall(request).execute()) {
                String jsonData = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonData);

                if (jsonObject.getBoolean("success")) {
                    JSONObject rates = jsonObject.getJSONObject("rates");
                    return rates.getDouble("RUB");
                } else {
                    System.out.println("Ошибка при получении курса доллара: " + jsonObject.getString("error"));
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return 0;
    }
}
