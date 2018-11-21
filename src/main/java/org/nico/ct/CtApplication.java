package org.nico.ct;


import org.nico.cat.server.bootstrap.ServerBootStrap;

public class CtApplication {

	public static void main(String[] args) {
		
		ServerBootStrap bootStrap = new ServerBootStrap();
		if(args.length > 0) {
			bootStrap.start(Integer.parseInt(args[0]));
		}else {
			bootStrap.start(8080);	
		}
		
	}
}
