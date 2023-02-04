package ServerClient;

import ServerUtils.ClientRequestProcess;
import ServerUtils.ProcessClientRequest;
import ServerUtils.ReadCategories;
import ServerUtils.ServerAnswer;
import UtilClasses.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * класс сервера, который осуществляет обработку запроса пользователя
 * и возвращает результат в виде JSON пользователю
 */
public class Main {
    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8989)) {
            System.out.println("Server started");
            Map<String, Integer> mapSum = new LinkedHashMap<>();
            Map<String, String> categoriesMap = new ReadCategories().readCategories();
            Map<String, Object> mapSumGson = new LinkedHashMap<>();

            RequestClass requestClass;
            Collection<RequestClass> requestCollect = new ArrayList<>();
            RequestCollection requestCollection;

            File binFile = new File("RequestCollection.bin");
            if (binFile.exists()) {
                requestCollection = RequestCollection.loadFromBinFile(binFile);
                Collection<RequestClass> requestList = requestCollection.getRequestCollection();

                for (RequestClass rq : requestList) {
                    ClientRequest clientRequest = new ClientRequest(rq.getRequestTitle(), rq.getRequestDate(),
                            rq.getRequestSum());
                    new ClientRequestProcess().mapSumForm(clientRequest, mapSum, categoriesMap);
                    requestClass = new ServerUtils.RequestClass().requestClassInit(clientRequest);
                    new ServerUtils.RequestClass().RequestCollectionInit(requestClass, requestCollect);
                }

                System.out.println("TOTAL SUM: " + mapSum);
            }

            while (true) {
                try (Socket clientSrv = server.accept();
                     PrintWriter writer = new PrintWriter(clientSrv.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSrv.getInputStream()))) {

                    writer.println("New connection accepted!");
                    writer.flush();

                    String jsonRequest = reader.readLine();
                    if (jsonRequest == null || jsonRequest.equals("")) {
                        continue;
                    } else {
                        jsonRequest = jsonRequest.replace("\t", "\n");
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("Client request:");
                        System.out.println(jsonRequest);
                        System.out.println("-------------------------------------------------------------------------");
                    }

                    ClientRequest clientRequest = new ProcessClientRequest().processClientRequest(jsonRequest);

                    requestClass = new ServerUtils.RequestClass().requestClassInit(clientRequest);
                    requestCollection = new ServerUtils.RequestClass()
                            .RequestCollectionInit(requestClass, requestCollect);

                    requestCollection.saveBin(binFile);
                    new ClientRequestProcess().mapSumForm(clientRequest, mapSum, categoriesMap);
                    //Формирование maxCategory
                    MaxCategory maxCategory = new MaxCategory().maxCategorySearch(mapSum);
                    mapSumGson.put("maxCategory", maxCategory);
                    //Формирование maxYearCategory
                    maxYearCategory maxYearCategory = UtilClasses.maxYearCategory
                            .maxYearCategorySearch(requestCollection, categoriesMap);
                    mapSumGson.put("maxYearCategory", maxYearCategory);
                    //Формирование maxMonthCategory
                    maxMonthCategory maxMonthCategory = UtilClasses.maxMonthCategory
                            .maxMonthCategorySearch(requestCollection, categoriesMap);
                    mapSumGson.put("maxMonthCategory", maxMonthCategory);
                    //Формирование maxDayCategory
                    maxDayCategory maxDayCategory = UtilClasses.maxDayCategory
                            .maxDayCategorySearch(requestCollection, categoriesMap);
                    mapSumGson.put("maxDayCategory", maxDayCategory);

                    //Вывод результата работы сервера в Json
                    writer.print(new ServerAnswer().jsonServerResult(mapSumGson));
                    writer.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("Server initialization error");
            e.printStackTrace();
        }
    }
}