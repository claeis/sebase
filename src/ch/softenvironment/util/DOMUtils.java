package ch.softenvironment.util;
/* 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 */

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * Utility for DOM-handling.
 * Based on JAXP.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2005-10-28 08:15:54 $
 */
public class DOMUtils {
    /**
     * Default Error-Handler for DOM-Parser.
     * @see #readDOM()
     */
    private static class DefaultErrorHandler implements ErrorHandler {
        private String getDetailedMessage(SAXParseException error) {
            return "Row=" + error.getLineNumber() + 
                    "\n SystemId=" + error.getSystemId() + 
                    (error.getPublicId() == null ? "" : "\n PublicId=" + error.getPublicId()) + 
                    "\n errorMessage=" + error.getMessage() + 
                    (error.getCause() == null ? "" : "\n cause=" + error.getCause());
        }
        public void warning(SAXParseException error) {
            Tracer.getInstance().runtimeWarning(DOMUtils.DefaultErrorHandler.class, "warning()", getDetailedMessage(error));
        }
        public void error(SAXParseException error) {
            Tracer.getInstance().runtimeError(DOMUtils.DefaultErrorHandler.class, "error()", getDetailedMessage(error));
        }
        public void fatalError(SAXParseException error) {
            Tracer.getInstance().developerError(DOMUtils.DefaultErrorHandler.class, "fatalError()", getDetailedMessage(error));
        }
    }
    /**
     * Transform a given String to UTF-8 Charset. This might be useful
     * for characters above ASCII-value 0x7e '~' to be written as Text-Node into
     * an XML-Stream which is encoded typically in UTF-8. Otherwise characters like
     * "הצü" might provoque a MALFORMED exception at reading the very same Stream.
     * 
     * Be aware special XML-Characters within a Text-Node are taken care of
     * by Document#createTextNode("&my 'Text' ") resp. instantiated Writers
     * which provide the necessary masking (for e.g. "&amp; &apos;Text...").
     * 
     * Revers transformation for masked XML-Characters or UTF-Characters is
     * dealt by Document-Builder instance (while XML-parsing automatically).
     * 
     * @param origin
     * @return
     * @throws UnsupportedEncodingException
     * @see java.lang.String#getBytes(String)
     * @see org.w3c.dom.Document#createTextNode(java.lang.String)
     */
    public static String encodeUTF8(String origin) throws /*CharacterCodingException*/ UnsupportedEncodingException {
/*
        Charset charset = Charset.forName("UTF-8");
        CharsetEncoder encoder = charset.newEncoder();
        CharsetDecoder decoder = charset.newDecoder();

        // Convert "a string" to Target-Charset bytes in a ByteBuffer
        ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(origin));
        // Convert Target-Charset bytes in a ByteBuffer to a character ByteBuffer
        // The new ByteBuffer is ready to be read.
        CharBuffer cbuf = decoder.decode(bbuf);
        // and then to a string
        return cbuf.toString();
*/
        return new String(origin.getBytes("UTF-8"));
        //decode by: 
        //  new String(encodeUTF8("myString").getBytes(), "UTF-8");
    }
    /**
     * Create a new instance of a DOM-Document builder.
     * @param validating
     * @return
     * @throws ParserConfigurationException
     */
    public static DocumentBuilder createDocumentBuilder(boolean validating) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
Tracer.getInstance().debug("DocumentBuilderFactory=" + factory);
        factory.setValidating(validating);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new DefaultErrorHandler());
Tracer.getInstance().debug("DocumentBuilder=" + builder);       
        return builder;
    }
    /**
     * Transform given XML-instance (data) by given XSLT-Rule-set (templates)
     * and write the result into given writer.
     * 
     * Xalan-Java is an XSLT processor for transforming XML documents into HTML, 
     * text, or other XML document types. It implements XSL Transformations (XSLT) 
     * Version 1.0 and XML Path Language (XPath) Version 1.0.
     * @param xml           (XML-Instance filename for e.g. myData.xml)
     * @param xsl           (XSLT rule filename for e.g. myTransformRules.xsl)
     * @param writer        (for e.g. StringWriter or BufferedWriter)
     * @return StringWriter (containing transformed Stream)
     * @throws TransformerConfigurationException
     * @throws TransformerException
     * @throws DeveloperException (if given files are not readable)
     */
    public static void transform(String xml, String xsl, Writer writer) throws TransformerConfigurationException, TransformerException, DeveloperException {
        File xmlFile = new File(xml);
        File xsltFile = new File(xsl);
        if (xmlFile.canRead() && xsltFile.canRead()) {
            TransformerFactory transFactory = TransformerFactory.newInstance();
            StreamSource xmlSrc = new StreamSource(xmlFile);
            StreamSource xsltSrc = new StreamSource(xsltFile);
            Transformer  transformer = transFactory.newTransformer(xsltSrc);
            StreamResult result = new StreamResult(writer);
            transformer.transform(xmlSrc, result);
        } else {
            throw new DeveloperException(DOMUtils.class, "transform()", "one or both filenames not readable");
        }
    }
    /**
     * Parse given filename (for e.g. MyFile.xml containing DOM-tree) and 
     * return resulting DOM-Document.
     * @param filename
     * @param builder (null => @see #createDocumentBuilder(boolean))
     * @return Document (or null if filename not readable)
     * @throws ParserConfigurationException
     * @throws SAXParseException
     * @throws IOException
     * @throws SAXException
     */
    public static Document read(String filename, DocumentBuilder builder) throws ParserConfigurationException, SAXParseException, IOException, SAXException {
        File file = new File(filename);
        if (file.canRead()) { 
            try {
                DocumentBuilder tmp = builder;
                if (tmp == null) {
                    // create default builder
                    tmp = createDocumentBuilder(false);
                }
                org.w3c.dom.Document document = tmp.parse(file);
                return document;
            } catch (SAXParseException pe) {
                Tracer.getInstance().runtimeError(DOMUtils.class, "readDOM()", filename + " Parse-Error (row=" + pe.getLineNumber() + "): " + pe.getLocalizedMessage());
                throw pe;
            } catch(IOException ioe) {
                Tracer.getInstance().runtimeError(DOMUtils.class, "readDOM()", filename + " IOException: " + ioe.getLocalizedMessage());
                throw ioe;
            } catch(SAXException saxe) {
                Tracer.getInstance().runtimeError(DOMUtils.class, "readDOM()", filename + " SAXException: " + saxe.getLocalizedMessage());
                throw saxe;
            }
        } else {
            Tracer.getInstance().runtimeWarning(DOMUtils.class, "readDOM()", filename + " not existing or readable!");
        }
        
        return null;
    }
    /**
     * Write given DOM-Document into writer.
     * @param document
     * @param writer (for e.g. FileWriter or StringWriter)
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
    public static void writeXML(Document document, Writer writer) throws TransformerConfigurationException, TransformerException {
        Transformer trn = TransformerFactory.newInstance().newTransformer();
        trn.setOutputProperty(OutputKeys.METHOD, "xml");
        trn.setOutputProperty(OutputKeys.ENCODING, "UTF-8");    // default anyway!
//      trn.setOutputProperty(OutputKeys.VERSION, "1.0");       // default for output method "xml"
//      trn.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "publicId");
//      trn.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "systemId");
        
        StreamResult result = new StreamResult(writer);
        trn.transform(new DOMSource(document), result);          
    }
}