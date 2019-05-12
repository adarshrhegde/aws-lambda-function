package com.serverless;


import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class JavaLambda implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

        private static final Logger LOG = Logger.getLogger(JavaLambda.class);

        /**
         * The handler for the calculator function
         * @param input
         * @param context
         * @return
         */
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
                .setObjectBody(result)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
                .build();
                }


}
