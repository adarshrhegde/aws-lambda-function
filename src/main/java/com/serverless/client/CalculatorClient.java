package com.serverless.client;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * The client class used to access the API
 */
public class CalculatorClient {

    public static void main(String[] args){

        // Add the API Gateway url below
        String url = "https://o2y53cz951.execute-api.us-east-1.amazonaws.com/default/lambdaservice-dev-calculator";

        calculate(url,10, 5, "add");


        calculate(url,10, 5, "subtract");


        calculate(url,10, 5, "multiply");


        calculate(url,10, 5, "divide");

    }


    /**
     * Performs the REST request
     * @param url
     * @param operand1
     * @param operand2
     * @param operator
     */
    public static void calculate(String url, int operand1, int operand2,
                                String operator) {

        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource(url+"?operand1=" + operand1 + "&operand2=" + operand2 +
                            "&operator=" + operator);

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);

            System.out.println("The result of the arithmetic operation " +operator +
                    " between " + operand1 + " and "
                    + operand2 + " = " + output);

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

}
