package org.eredlab.g4.test.systemtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.eredlab.g4.ccl.net.ftp.FTP;
import org.eredlab.g4.ccl.net.ftp.FTPClient;

public class TestFTP2 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws SocketException, IOException {
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("127.0.0.1",21);
		ftpClient.login("anonymous", "");
		ftpClient.changeWorkingDirectory("/");
		InputStream inputStream = new FileInputStream("h:/04.pdf");
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.storeFile("12.pdf", inputStream);
		ftpClient.logout();
		ftpClient.disconnect();
	}

}
