package org;

import org.eredlab.g4.rif.server.G4Server;

/**
 * 系统启动
* @author njdt
 * @since 2009-01-13
 */
public class Run {
	public static void main(String[] args) {
		G4Server server = new G4Server(
				"f:\\wh4\\g42\\WebRoot",
				"/njdt",
				8899
				);
		server.stop();
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
