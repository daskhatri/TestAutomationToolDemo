package com.finexus.automation.xmlparser;

import java.io.IOException;

public class SystemCommandsOperatoins {

		public static void main(String args[]) {
			
			String command = "curl -X POST http://Auto:1174ecedc83eddf0cb1b8fba9e5a22a2d0@localhost:8080/job/MavenDemoTest/buildWithParameters?token=ztypZvByGW&param=com.finexus.tests.TestDemo"; 
					//"curl -X POST https://postman-echo.com/post --data foo1=bar1&foo2=bar2";
			try {
				Process process = Runtime.getRuntime().exec(command);				
				int termination = process.waitFor();
				if(termination == 0) {
					System.out.println("its completed smoothy");
				}else
					System.out.println("Process of execution is interupted!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
