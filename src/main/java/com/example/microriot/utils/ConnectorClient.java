package com.example.microriot.utils;


import com.example.microriot.model.base.BaseRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
@Log4j2
public class ConnectorClient {

    @Autowired
    private RestTemplate restTemplate;


    public <T> T get(final String  XRiotToken, String path, ParameterizedTypeReference<T> responseType)  {
        ResponseEntity<T> responseEntity = restTemplate.exchange(restTemplate.getUriTemplateHandler().expand("") + path,
                HttpMethod.GET, new HttpEntity<>(buildHeaders(XRiotToken)), responseType);
        log.info(responseEntity.getStatusCodeValue());
        return responseEntity.getBody();

    }
    private HttpHeaders buildHeaders(String XRiotToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         headers.add("X-Riot-Token", XRiotToken);
        return headers;
    }
    public <T> T post(final BaseRequest request, String path, ParameterizedTypeReference<T> responseType)  {
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(restTemplate.getUriTemplateHandler().expand("") + path, HttpMethod.POST,
                    new HttpEntity<>(request, buildHeadersRiot(request)), responseType);
            return responseEntity.getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
    private HttpHeaders buildHeadersRiot(BaseRequest request) {
        // ConfigData.Client.BillConnector billConnector =
        // ConfigLoader.getInstance().configData.getClient().getBillConnector();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // headers.add("X-API-KEY", billConnector.getApiKey());
        return headers;
    }
}
