package com.todoitem.client.service.impl;

import com.todoitem.client.exception.ConnectingException;
import com.todoitem.client.service.MainServerReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
public class MainServerReaderImpl implements MainServerReader {
    private final static Logger LOGGER = LoggerFactory.getLogger(MainServerReaderImpl.class);

    @Override
    public Response getResponseFromMainServer() throws ConnectingException {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(PATH);
        Response response = null;
        try {
            response = webTarget.request().accept(MediaType.APPLICATION_JSON).get();
        } catch (Exception e) {
            throw new ConnectingException("Internal Server Error. There was an error connecting.");
        }
        LOGGER.info("Response received. Response details: " + response);
        return response;
    }
}
