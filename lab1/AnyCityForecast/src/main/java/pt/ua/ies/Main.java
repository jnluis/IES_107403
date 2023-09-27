package pt.ua.ies;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);
    Toolkit toolkit;
    Timer timer;

    public Main() {
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 0, //initial delay
                20 * 1000); //subsequent rate, 20 seconds
    }

    class RemindTask extends TimerTask {
        public void run() {
            long time = System.currentTimeMillis();
            if (time - scheduledExecutionTime() > 5) {
                return;
            }
            toolkit.beep();
            // get a retrofit instance, loaded with the GSon lib to convert JSON into objects
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.ipma.pt/open-data/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // create a typed interface to use the remote API (a client)
            IpmaService service = retrofit.create(IpmaService.class);
            // prepare the call to remote endpoint
            Call<IpmaCityForecast> newCallSync = service.getCities();

            HashMap<String, Integer> cities = new HashMap<>();
            try {
                Response<IpmaCityForecast> apiResponse = newCallSync.execute();
                IpmaCityForecast forecast2 = apiResponse.body();
                if (forecast2 != null) {
                    for (CityForecast city : forecast2.getData()) {
                        cities.put(city.getLocal(), city.getGlobalID());
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                //Get random indexes of cities
                int index = (int) (Math.random() * cities.size());
                String city = (String) cities.keySet().toArray()[index];
                int id = cities.get(city);
                Call<IpmaCityForecast> callSync = service.getForecastForACity(id);
                Response<IpmaCityForecast> apiResponse = callSync.execute();
                IpmaCityForecast forecast = apiResponse.body();

                if (forecast != null) {
                    logger.info("Forecast != null");
                    System.out.printf("Tempo para as coordenadas %sº , %sº \n", forecast.getData().listIterator().next().getLatitude(), forecast.getData().listIterator().next().getLongitude());
                    logger.info("Tempo para as coordenadas %sº , %sº \n", forecast.getData().listIterator().next().getLatitude(), forecast.getData().listIterator().next().getLongitude());
                    System.out.println();
                    for (int i = 0; i < forecast.getData().size(); i++) {
                        System.out.print("------------ Data ------------------\n");
                        System.out.printf("---------- %s --------------\n", forecast.getData().get(i).getForecastDate());
                        logger.info("Data já foi impressa");
                        System.out.printf("Probabilidade de chuva: %s \n", forecast.getData().get(i).getPrecipitaProb());
                        System.out.printf("Amplitude térmica para o dia: %.2f \n", Double.parseDouble(forecast.getData().get(i).getTMax()) - Double.parseDouble(forecast.getData().get(i).getTMin()));
                        System.out.println();
                        logger.info("Todas as informações deste dia impressas com sucesso");
                    }
                } else {
                    System.out.println("No results for this request!"); // Provavelmente com a introdução do logger, já não seria necessária esta linha
                    logger.info("No results for this request!");
                }
            } catch (Exception ex) {
                logger.error("Error log message");
                System.err.println("Ocorreu um erro");
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
