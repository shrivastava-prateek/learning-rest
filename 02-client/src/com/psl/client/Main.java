package com.psl.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class Main {
	public static void main(String[] args){
		Client client = Client.create();
		WebResource resource;
		resource =  client.resource("http://localhost:8181/01-hello-world/hello/greet");
		String result=resource.get(String.class);
         System.out.println(result);
	}

       
}
