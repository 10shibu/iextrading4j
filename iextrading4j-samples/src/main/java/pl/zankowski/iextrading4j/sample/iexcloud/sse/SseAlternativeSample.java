package pl.zankowski.iextrading4j.sample.iexcloud.sse;

import pl.zankowski.iextrading4j.api.alternative.CryptoDetailedBook;
import pl.zankowski.iextrading4j.api.alternative.Sentiment;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.IEXCloudTokenBuilder;
import pl.zankowski.iextrading4j.client.IEXTradingApiVersion;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.sse.manager.SseRequest;
import pl.zankowski.iextrading4j.client.sse.request.alternative.CryptoBookSseRequestBuilder;
import pl.zankowski.iextrading4j.client.sse.request.alternative.SentimentSseRequestBuilder;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;

public class SseAlternativeSample {

    final IEXCloudClient cloudClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_BETA_SANDBOX,
            new IEXCloudTokenBuilder()
                    .withPublishableToken("Tpk_18dfe6cebb4f41ffb219b9680f9acaf2")
                    .withSecretToken("Tsk_3eedff6f5c284e1a8b9bc16c54dd1af3")
                    .build());

    public static void main(String[] args) throws InterruptedException {
        final SseAlternativeSample alternativeSample = new SseAlternativeSample();

        alternativeSample.sentimentSample();
        alternativeSample.cryptoBookSample();

        new Semaphore(0).acquire();
    }

    private static final Consumer<List<Sentiment>> SENTIMENT_CONSUMER = System.out::println;

    private void sentimentSample() {
        final SseRequest<List<Sentiment>> request = new SentimentSseRequestBuilder()
                .withSymbol("spy")
                .build();

        cloudClient.subscribe(request, SENTIMENT_CONSUMER);
    }

    private static final Consumer<List<CryptoDetailedBook>> CRYPTO_BOOK_CONSUMER = System.out::println;

    private void cryptoBookSample() {
        final SseRequest<List<CryptoDetailedBook>> request = new CryptoBookSseRequestBuilder()
                .withSymbol("btcusd")
                .build();

        cloudClient.subscribe(request, CRYPTO_BOOK_CONSUMER);
    }

}
