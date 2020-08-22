package com.sample;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.api.task.TaskService;

public class BindFragmentModelWorkItemHandler implements WorkItemHandler{

	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		// TODO Auto-generated method stub
		System.out.println("Aborting");
	}

	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		// TODO Auto-generated method stub
		
		Object ci = workItem.getParameter("input_cInfo");
		Object fragId = workItem.getParameter("FragmentIdentity");
		System.out.println("This is the ID of the fragment to be invoked: " + (String)fragId);
		StartProcess start = new StartProcess();
		String messageFromFragment = start.startProcess(ci, fragId);
		
		System.out.println("This is the message from the fragment invoked: " + messageFromFragment);
		//workItem.getProcessInstanceId();
		Map<String, Object> results = new HashMap<String, Object>();
		results.put("result", messageFromFragment);
		//workItem.getResults().put("messageFromFragment", messageFromFragment);
		//notify manager that workItem has completed
	     manager.completeWorkItem(workItem.getId(), results);
	    
	}

}
