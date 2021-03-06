package org.asteriskjava.pbx.internal.asterisk.wrap.events;

import org.apache.log4j.Logger;
import org.asteriskjava.pbx.internal.asterisk.InvalidChannelName;
import org.asteriskjava.pbx.internal.core.ChannelEventHelper;

public class AgentCalledEvent extends ChannelEventHelper
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AgentCalledEvent.class);

	private String queue;

	private String agentInterface;

	public AgentCalledEvent(final org.asteriskjava.manager.event.AgentCalledEvent event) throws InvalidChannelName
	{
		super(event.getChannelCalling(), event.getUniqueId(), event.getCallerIdNum(), event.getCallerIdName());

		agentInterface = event.getAgentCalled();
		queue = event.getQueue();
	}

	public String getQueueName()
	{
		return queue;
	}

	public String getAgentInterface()
	{
		return agentInterface;
	}

}
