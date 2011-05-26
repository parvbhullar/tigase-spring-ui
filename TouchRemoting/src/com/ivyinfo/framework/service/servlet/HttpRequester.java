package com.ivyinfo.framework.service.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Vector;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**  
 * HTTP请求对象  
 *   
 * @author YYmmiinngg  
 */  
public class HttpRequester {   
    private String defaultContentEncoding;   
    
    public HttpRequester() {   
//        this.defaultContentEncoding = Charset.defaultCharset().name();   
    	this.defaultContentEncoding = "UTF-8";
    }   
    
    /**  
     * 发送GET请求  
     * @param urlString  	URL地址  
     * @return 响应对象  
     * @throws IOException  
     */  
    public HttpRespons sendGet(String urlString,String contents) throws IOException {   
        return this.send(urlString, "GET", null, null,contents);   
    }   
    
    /**  
     * 发送GET请求  
     * @param urlString   	URL地址  
     * @param params   		参数集合  
     * @return 响应对象  
     * @throws IOException  
     */  
    public HttpRespons sendGet(String urlString, Map<String, String> params,String contents)   
            throws IOException {   
        return this.send(urlString, "GET", params, null,contents);   
    }   
    
    /**  
     * 发送GET请求  
     * @param urlString     URL地址  
     * @param params     	参数集合  
     * @param propertys    	请求属性  
     * @return 响应对象  
     * @throws IOException  
     */  
    public HttpRespons sendGet(String urlString, Map<String, String> params,   
            Map<String, String> propertys,String contents) throws IOException {   
        return this.send(urlString, "GET", params, propertys,contents);   
    }   
    
    /**  
     * 发送POST请求  
     * @param urlString   	URL地址  
     * @return 响应对象  
     * @throws IOException  
     */  
    public HttpRespons sendPost(String urlString,String contents) throws IOException {   
        return this.send(urlString, "POST", null, null,contents);   
    }   
    
    /**  
     * 发送POST请求  
     * @param urlString  URL地址  
     * @param params  	 参数集合  
     * @return 响应对象  
     * @throws IOException  
     */  
    public HttpRespons sendPost(String urlString, Map<String, String> params,String contents)   
            throws IOException {   
        return this.send(urlString, "POST", params, null,contents);   
    }   
    
    /**  
     * 发送POST请求  
     * @param urlString   	URL地址  
     * @param params    	参数集合  
     * @param propertys   	请求属性  
     * @return 响应对象  
     * @throws IOException  
     */  
    public HttpRespons sendPost(String urlString, Map<String, String> params,   
            Map<String, String> propertys,String contents) throws IOException {   
        return this.send(urlString, "POST", params, propertys, contents);   
    }   
    
    /**  
     * 发送HTTP请求  
     * @param urlString  
     * @return 响映对象  
     * @throws IOException  
     */  
    private HttpRespons send(String urlString, String method,   
            Map<String, String> parameters, Map<String, String> propertys, String contents)   
            throws IOException {   
        HttpURLConnection urlConnection = null;   
    
        if (method.equalsIgnoreCase("GET") && parameters != null) {   
            StringBuffer param = new StringBuffer();   
            int i = 0;   
            for (String key : parameters.keySet()) {   
                if (i == 0)   
                    param.append("?");   
                else  
                    param.append("&");   
                param.append(key).append("=").append(parameters.get(key));   
                i++;   
            }   
            urlString += param;   
        }   
        URL url = new URL(urlString);   
        urlConnection = (HttpURLConnection) url.openConnection();   
    
        urlConnection.setRequestMethod(method);   
        urlConnection.setDoOutput(true);   
        urlConnection.setDoInput(true);   
        urlConnection.setUseCaches(false);   
    
        if (propertys != null){   
            for (String key : propertys.keySet()) {   
                urlConnection.addRequestProperty(key, propertys.get(key));   
            }   
        }
        if (method.equalsIgnoreCase("POST") && parameters != null) {   
            StringBuffer param = new StringBuffer();   
            for (String key : parameters.keySet()) {   
                param.append("&");   
                param.append(key).append("=").append(parameters.get(key));   
            }   
            urlConnection.getOutputStream().write(param.toString().getBytes());   
            urlConnection.getOutputStream().flush();   
            urlConnection.getOutputStream().close();   
        }   
        if(contents!=null){
        	urlConnection.getOutputStream().write(contents.getBytes());
        }
    
        return this.makeContent(urlString, urlConnection);   
    }   
    
    /**  
     * 得到响应对象  
     * @param urlConnection  
     * @return 响应对象  
     * @throws IOException  
     */  
    private HttpRespons makeContent(String urlString,   
            HttpURLConnection urlConnection) throws IOException {   
        HttpRespons httpResponser = new HttpRespons();   
        try {   
            InputStream in = urlConnection.getInputStream();   
            ReadHeader(in,httpResponser);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in,"UTF-8"));   
            httpResponser.contentCollection = new Vector<String>();   
            StringBuffer temp = new StringBuffer();   
            String line = bufferedReader.readLine();   
            while (line != null) {   
                httpResponser.contentCollection.add(line);   
                temp.append(line).append("\r\n");   
                line = bufferedReader.readLine();   
            }   
            bufferedReader.close();   
    
            String ecod = urlConnection.getContentEncoding();   
            if (ecod == null)   
                ecod = this.defaultContentEncoding;   
    
            httpResponser.urlString = urlString;   
    
            httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();   
            httpResponser.file = urlConnection.getURL().getFile();   
            httpResponser.host = urlConnection.getURL().getHost();   
            httpResponser.path = urlConnection.getURL().getPath();   
            httpResponser.port = urlConnection.getURL().getPort();   
            httpResponser.protocol = urlConnection.getURL().getProtocol();   
            httpResponser.query = urlConnection.getURL().getQuery();   
            httpResponser.ref = urlConnection.getURL().getRef();   
            httpResponser.userInfo = urlConnection.getURL().getUserInfo();   
            
            
            if(httpResponser.content!=null && "".equals(httpResponser.content)){
            	httpResponser.content = new String(temp.toString().getBytes(), ecod);
            }else{
            	httpResponser.content = httpResponser.content;
            }
            System.err.println("get Content:"+httpResponser.content);
            
            httpResponser.contentEncoding = ecod;   
            httpResponser.code = urlConnection.getResponseCode();   
            httpResponser.message = urlConnection.getResponseMessage();   
            httpResponser.contentType = urlConnection.getContentType();   
            httpResponser.method = urlConnection.getRequestMethod();   
            httpResponser.connectTimeout = urlConnection.getConnectTimeout();   
            httpResponser.readTimeout = urlConnection.getReadTimeout();   
    
            return httpResponser;   
        } catch (IOException e) {   
            throw e;   
        } finally {   
            if (urlConnection != null)   
                urlConnection.disconnect();   
        }   
    }   
    
    /**  
     * 默认的响应字符集  
     */  
    public String getDefaultContentEncoding() {   
        return this.defaultContentEncoding;   
    }   
    
    /**  
     * 设置默认的响应字符集  
     */  
    public void setDefaultContentEncoding(String defaultContentEncoding) {   
        this.defaultContentEncoding = defaultContentEncoding;   
    }   
    
    
	private byte crlf13 = (byte) 13; // '\r'

	private byte crlf10 = (byte) 10; // '\n'
	
	private StringBuffer request = new StringBuffer(); // 用于保存所有内容

	
	private boolean beGet = false;

	private String ReadHeader(InputStream input, HttpRespons httpResponser) // 读取头部 并获得大小

	{
		byte[] crlf = new byte[1];
		int crlfNum = 0; // 已经连接的回车换行数 crlfNum=4为头部结束

		try {
			while (input.read(crlf) != -1) // 读取头部
			{
				if (crlf[0] == crlf13 || crlf[0] == crlf10) {
					crlfNum++;
				} else {
					crlfNum = 0;
				} // 不是则清
				request = request.append(new String(crlf, 0, 1)); // byte数组相加
				if (crlfNum == 4)
					break;
			}
		} catch (IOException e) {
			System.out.println("Read Http Header Error!");
			return "";
		}

		String tempStr = (new String(request));//.toUpperCase();
		

		// 这里我只处理了GET与POST方法
		String strMethod = tempStr.substring(0, 4);
		String content = "";
		
		if (strMethod.equals("GET ")) // 前
		{
			beGet = true;
		} else {
			httpResponser.content = tempStr;
		}
		return content;
	}
	
	/**
	 * Base64解码
	 * @param str
	 * @return
	 * @throws IOException 
	 */
	public static String base64Decode(String str) throws IOException{
		BASE64Decoder base64 = new BASE64Decoder();
		if(str!=null && !"".equals(str)){
			return new String(base64.decodeBuffer(str));//decode(str.getBytes()));
		}else{
			return "";
		}		 
	}
	/**
	 * Base64加密
	 * @param str
	 * @return
	 */
	public static String base64Encode(String str){
		BASE64Encoder base64 = new BASE64Encoder();
		if(str!=null && !"".equals(str)){
			return new String(base64.encode(str.getBytes()));
		}else{
			return "";
		}	
	}
	
	public static boolean isBase64(String str){
	    try {
	    	String temp = base64Decode(str);
	    	System.err.println("======str:"+str);
	    	System.err.println("======temp:"+temp);
	            //if(!temp.equals(str))return false;
	    } catch (UnsupportedEncodingException e) {
	            System.out.println("no");
	            return false;
	    } catch (IOException ex){
	    	return false;
	    }
	        return true;
	}
	
	 public static void main(String[] args) {  
		 try{
		 String a = "abc";
		 String ba = base64Encode(a);
		 System.err.println(ba);
		 System.err.println(base64Decode(ba));
		 System.err.println(isBase64(a));
		 System.err.println(isBase64(ba));
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
	 }
    
}
