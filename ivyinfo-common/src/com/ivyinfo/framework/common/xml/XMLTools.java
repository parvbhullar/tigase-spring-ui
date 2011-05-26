package com.ivyinfo.framework.common.xml;

import org.w3c.dom.*;
import org.xml.sax.*;

import java.io.*;
import java.util.HashMap;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public abstract class XMLTools {
    // ------------------------------------------------------------ Private data

    protected Element root = null;
    
    protected Document xmldoc = null;

    // ---------------------------------------------------------- Public methods

    /**
     * Parse the XML doc the XML parser for a VCard or ICalendar object.
     * @param xmlStream the input stream in XML format
     */
    public XMLTools(InputStream xmlStream) throws SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setCoalescing(false);
        factory.setIgnoringElementContentWhitespace(true);
        Document doc = null;

        try {

            //
            // Parse the xml document
            //
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(xmlStream);

        }catch (ParserConfigurationException pce) {
            throw new SAXException(pce.getMessage());
        }

        //
        // Getting the root element
        //
        root=doc.getDocumentElement();
    }
    
    public XMLTools(String logicType, String fileName) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setCoalescing(false);
        factory.setIgnoringElementContentWhitespace(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            xmldoc = builder.newDocument();//下面是建立XML文档内容的过程，
            
        }catch (ParserConfigurationException pce) {
        	throw new Exception(pce.getMessage());
        }
    }
    
    /**
     * 查找节点，返回符合条件的节点集。
     * @param express
     * @param source
     * @return
     */
    public static NodeList selectNodes(String express, Object source){
        NodeList result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        try {
            result=(NodeList) xpath.evaluate(express, source, XPathConstants.NODESET);
        } catch (XPathExpressionException e){
            e.printStackTrace();
        }
        
        return result;
    }

    /**
     * 返回该节点的String内容
     */
    public String getNodeContent(Node child) {
        NodeList childNodes=child.getChildNodes();
        for (int i=0; i<childNodes.getLength(); i++) {
            Node childNode=childNodes.item(i);
            if (childNode instanceof Text) {
                return (childNode.getNodeValue());
            }
        }
        return ("");
    }
    /**
     * 获取该节点中CDATA的字符串内容，并前后去除空格
     * @param child
     * @return
     */
    public String getNodeCDATAContent(Node child) {
        NodeList childNodes=child.getChildNodes();
        for (int i=0; i<childNodes.getLength(); i++) {
            Node childNode=childNodes.item(i);
            if (childNode.getNodeType() == Node.CDATA_SECTION_NODE) {
                return (childNode.getNodeValue());
            }
        }
        return ("");
    }

    /**
     * Return an Integer with the node text content
     */
    public Integer getIntWrapNodeContent(Node child) {
        NodeList childNodes=child.getChildNodes();
        for (int i=0; i<childNodes.getLength(); i++) {
            Node childNode=childNodes.item(i);
            if (childNode instanceof Text) {
                return new Integer(childNode.getNodeValue());
            }
        }
        return null;
    }

    /**
     * Return an int with the node text content
     */
    public int getIntNodeContent(Node child) {
        Integer content = getIntWrapNodeContent(child);
        if (content == null) {
            return 0;
        }
        return content.intValue();
    }

    /**
     * Return a Short with the node text content
     */
    public Short getShortWrapNodeContent(Node child) {
        NodeList childNodes=child.getChildNodes();
        for (int i=0; i<childNodes.getLength(); i++) {
            Node childNode=childNodes.item(i);
            if (childNode instanceof Text) {
                return new Short(childNode.getNodeValue());
            }
        }
        return null;
    }

    /**
     * Return a short with the node text content
     */
    public short getShortNodeContent(Node child) {
        Short content = getShortWrapNodeContent(child);
        if (content == null) {
            return 0;
        }
        return content.shortValue();
    }
    /**
     * 根据当前节点获取节点的属性名、值
     * @param child
     * @return
     */
    public HashMap getPropertyFromTag(Node child) {
        NamedNodeMap attributes= child.getAttributes();
        Node attribute;
        HashMap nodeProperty = new HashMap();
        for (int i=0;i<attributes.getLength();i++) {
            if (attributes.item(i) instanceof Attr) {
                attribute=attributes.item(i);
                nodeProperty.put(attribute.getNodeName(), attribute.getNodeValue());
            }
        }
        return nodeProperty;
    }
    
    public  void saveXml(String fileName, Document doc){//将Document输出到文件
        TransformerFactory transFactory=TransformerFactory.newInstance();
        try{
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty("encoding", "UTF-8");
            transformer.setOutputProperty("indent", "yes");
            DOMSource source=new DOMSource();
            source.setNode(doc);
            StreamResult result=new StreamResult();
            result.setOutputStream(new FileOutputStream(fileName+".xml"));
            
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e){
            e.printStackTrace();
        } catch (TransformerException e){
            e.printStackTrace();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }   
    }
    
	public static StringBuffer createTagFromString(String tag, String propertyValue) {
        StringBuffer output = new StringBuffer();
        if (propertyValue != null) {
            if (propertyValue.length() > 0) {
                output.append('<').append(tag).append('>');
//                output.append(StringTools.escapeBasicXml(new String(Base64.encode(propertyValue.getBytes()))));
                output.append(propertyValue);
                output.append("</").append(tag).append('>');
            } else {
            	output.append('<').append(tag).append('>');
                output.append("</").append(tag).append(">");
            }
        }
        return output;
    }
	
	public static String getXMLValue(String xml, String nodeName) throws Exception{
		try{
			String startTag = "<"+nodeName+">";
			if(xml.indexOf(startTag)>-1){
				String endTag = "</"+nodeName+">";
				int sTagNum = xml.indexOf(startTag);
				int eTagNum = xml.indexOf(endTag);
				return xml.substring(sTagNum+startTag.length(), eTagNum);
			}else{
				return "";
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return "";
		}
	}
}
