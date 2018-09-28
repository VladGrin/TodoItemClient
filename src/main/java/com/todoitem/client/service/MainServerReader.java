package com.todoitem.client.service;

import com.todoitem.client.exception.ConnectionException;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;


@Service
public interface MainServerReader {

    String PATH = "http://localhost:9000/users";

    Response getResponseFromMainServer() throws ConnectionException;
}
