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

        // add request
        calculate(url,10, 5, "add");

        // memory request
        calculate(url,0, 0, "memory");

        // subtract request
        calculate(url,10, 5, "subtract");

        // memory request
        calculate(url,0, 0, "memory");

        // multiply request
        calculate(url,10, 5, "multiply");

        // memory request
        calculate(url,0, 0, "memory");

        // divide request
        calculate(url,10, 5, "divide");

        // memory request
        calculate(url,0, 0, "memory");

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

            if(operator.equals("memory")){
                System.out.println("The last computed result = " + output);

            } else {
                System.out.println("The result of the arithmetic operation " + operator +
                        " between " + operand1 + " and "
                        + operand2 + " = " + output);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }


    }

}
