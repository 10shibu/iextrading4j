package pl.zankowski.iextrading4j.client.sse.request.stocks;

import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.sse.manager.SseRequest;
import pl.zankowski.iextrading4j.client.sse.manager.SseRequestBuilder;
import pl.zankowski.iextrading4j.client.sse.request.AbstractSymbolSseRequestBuilder;

import javax.ws.rs.core.GenericType;
import java.util.List;

public class QuoteSseRequestBuilder extends AbstractSymbolSseRequestBuilder<List<Quote>, QuoteSseRequestBuilder> {

    private QuoteInterval quoteInterval;

    public QuoteSseRequestBuilder withQuoteInterval(final QuoteInterval quoteInterval) {
        this.quoteInterval = quoteInterval;
        return this;
    }

    @Override
    public SseRequest<List<Quote>> build() {
        return SseRequestBuilder.<List<Quote>>builder()
                .withPath("/stocksUS{interval}")
                .addPathParam("interval", quoteInterval.getName())
                .withResponse(new GenericType<List<Quote>>() {})
                .addQueryParam(SYMBOL_PARAM, getSymbol())
                .addQueryParam(NO_SNAPSHOT_PARAM, isNoSnapshot())
                .build();
    }

}
