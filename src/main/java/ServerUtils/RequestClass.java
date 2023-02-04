package ServerUtils;

import ServerClient.ClientRequest;
import UtilClasses.RequestCollection;

import java.util.Collection;

/**
 * класс для сериализации классов UtilClasses.RequestClass и RequestCollection
 */
public class RequestClass {
    public RequestClass() {
    }

    /**
     * метод сериализации UtilClasses.RequestClass
     */
    public UtilClasses.RequestClass requestClassInit(ClientRequest clientRequest) {
        String[] date = clientRequest.getDate().split("\\.");

        return new UtilClasses.RequestClass(clientRequest.getTitle(), clientRequest.getDate(), clientRequest.getSum(),
                date[0], date[1], date[2]);
    }

    /**
     * метод сериализации RequestCollection
     */
    public RequestCollection RequestCollectionInit(UtilClasses.RequestClass requestClass,
                                                   Collection<UtilClasses.RequestClass> requestCollect) {
        requestCollect.add(requestClass);
        return new RequestCollection(requestCollect);
    }
}
