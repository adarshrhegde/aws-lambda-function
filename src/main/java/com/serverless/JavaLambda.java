package com.serverless;


import java.util.Base64;
import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class JavaLambda implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

private static final Logger LOG = Logger.getLogger(Handler.class);

@Override
public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

        LOG.info("received: " + input);
        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = mapper.valueToTree(input.get("queryStringParameters"));

        long result = 0;
        switch (node.get("operator").asText()) {
                case "add":
                        result = node.get("operand1").asInt() + node.get("operand2").asInt();
                        break;
                case "subtract":
                        result = node.get("operand1").asInt() - node.get("operand2").asInt();
                        break;
                case "multiply":
                        result = node.get("operand1").asInt() * node.get("operand2").asInt();
                        break;
                case "divide":
                        result = node.get("operand1").asInt() / node.get("operand2").asInt();
                        break;
                        default:
                        result = 0;


        }
        return ApiGatewayResponse.builder()
        .setStatusCode(200)
        .setObjectBody("The result of the arithmetic operation " +node.get("operator").asText() +
                        " between " + node.get("operand1").asText() + " and "
                        + node.get("operand2").asText() + " = " + result)
        //.setObjectBody(Base64.getEncoder().withoutPadding().encodeToString(("The result of the arithmetic operation " +node.get("operator").asText() +
        //        " between " + node.get("operand1").asText() + " and "
        //        + node.get("operand2").asText() + " = " + result).getBytes()))
        .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
        //        .setHeaders(Collections.singletonMap("Content-Type", "application/x-protobuf"))
        //        .setBase64Encoded(true)
        .build();
        }


        }
