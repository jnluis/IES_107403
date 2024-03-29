package pt.ua.ies;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    //todo: should generalize for a city passed as argument
    // private static final int CITY_ID_AVEIRO = 1010500; given code

    // add logger
    private static Logger logger = LogManager.getLogger(WeatherStarter.class);
    public static void  main(String[] args ) {
        if (args.length != 1) {
            System.out.println("Usage: java WeatherStarter <CITY_ID>");
            return;
        }

        // alinea k
        int CITY_ID = Integer.parseInt(args[0]);

        // get a retrofit instance, loaded with the GSon lib to convert JSON into objects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create a typed interface to use the remote API (a client)
        IpmaService service = retrofit.create(IpmaService.class);
        // prepare the call to remote endpoint
        Call<IpmaCityForecast> callSync = service.getForecastForACity(CITY_ID);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null) {
                logger.info("Forecast != null");
                System.out.printf("Tempo para as coordenadas %sº , %sº \n", forecast.getData().listIterator().next().getLatitude(), forecast.getData().listIterator().next().getLongitude());
                logger.info("Tempo para as coordenadas %sº , %sº \n", forecast.getData().listIterator().next().getLatitude(), forecast.getData().listIterator().next().getLongitude());
                System.out.println();
                for(int i=0; i< forecast.getData().size() ; i++){
                    System.out.print("------------ Data ------------------\n");
                    System.out.printf("---------- %s --------------\n",forecast.getData().get(i).getForecastDate());
                    logger.info("Data já foi impressa");
                    System.out.printf("Probabilidade de chuva: %s \n", forecast.getData().get(i).getPrecipitaProb());
                    System.out.printf("Amplitude térmica para o dia: %.2f \n", Double.parseDouble(forecast.getData().get(i).getTMax()) - Double.parseDouble(forecast.getData().get(i).getTMin()));
                    System.out.println();
                    logger.info("Todas as informações deste dia impressas com sucesso");
                }
                // código que vinha inicialmente
                //var firstDay = forecast.getData().listIterator().next();
                // System.out.printf( "max temp for %s is %4.1f %n",
                  //      firstDay.getForecastDate(),
                    //    Double.parseDouble(firstDay.getTMax()));
            } else {
                System.out.println( "No results for this request!"); // Provavelmente com a introdução do logger, já não seria necessária esta linha
                logger.info("No results for this request!");
            }
        } catch (Exception ex) {
            logger.error("Error log message");
            System.err.println("Ocorreu um erro");
        }

    }
}