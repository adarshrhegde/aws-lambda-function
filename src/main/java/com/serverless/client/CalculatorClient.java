package com.serverless.client;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class CalculatorClient {

    public static void main(String[] args){

        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource("https://o2y53cz951.execute-api.us-east-1.amazonaws.com/default/lambdaservice-dev-calculator?operand1=2&operand2=5&operator=add");

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);

            System.out.println("Output from Server .... \n");
            System.out.println(output);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}