package com.piero.web.service;

import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
public class HttpConfig
{
	private final static long TIME_TO_LIVE = 30000;
	
	@Bean
	public HttpComponentsClientHttpRequestFactory getHttpClientFactory()
	{
		return new HttpComponentsClientHttpRequestFactory( this.getHttpClient() );
	}
	
	private HttpClient getHttpClient()
	{
		return HttpClientBuilder.create().setConnectionManager( this.getConnectionManager() ).build();
	}
	
	private PoolingHttpClientConnectionManager getConnectionManager()
	{
		return new PoolingHttpClientConnectionManager( HttpConfig.TIME_TO_LIVE, TimeUnit.MILLISECONDS );
	}
}
