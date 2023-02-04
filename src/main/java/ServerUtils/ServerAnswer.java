package ServerUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

/**
 * класс формирования ответа сервера клиенту в виде JSON
 */
public class ServerAnswer {
    public ServerAnswer() {
    }

    /**
     * метод создания JSON ответа сервера из categoriesMap
     */
    public String jsonServerResult(Map<String, Object> categoriesMap) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        return gson.toJson(categoriesMap);
    }
}
