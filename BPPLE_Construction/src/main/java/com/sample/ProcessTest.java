package com.sample;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.w3c.dom.Document;

/**
 * This is a sample file to launch a process.
 */
public class ProcessTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-process");
        	
        	Map<String, Object> params = new HashMap<String, Object>();
        	String contextInfoPath = "C:\\jBPM\\jbpm-installer-7.3.0.Final\\workspace\\BPPLE_Construction\\src\\main\\resources\\data\\ContextInfo.xml";
        	Document contextInfoDoc = XMLDOMHelper.getDocument(contextInfoPath);
    		ContextInfo contextInfo = new ContextInfo();
    		contextInfo.setContextInfoDoc(contextInfoDoc);
            params.put("contextInfo", contextInfo);
            // start a new process instance
            kSession.startProcess("defaultPackage.StudentEnrolmentReferenceVariant", params);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
