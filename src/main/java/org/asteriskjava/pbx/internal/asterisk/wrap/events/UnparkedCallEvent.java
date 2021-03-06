package org.asteriskjava.pbx.internal.asterisk.wrap.events;

import org.apache.log4j.Logger;
import org.asteriskjava.pbx.Channel;
import org.asteriskjava.pbx.PBXFactory;
import org.asteriskjava.pbx.internal.asterisk.InvalidChannelName;
import org.asteriskjava.pbx.internal.core.AsteriskPBX;
import org.asteriskjava.pbx.internal.core.ChannelImpl;

public class UnparkedCallEvent extends AbstractParkedCallEvent
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(UnparkedCallEvent.class);

	private final Channel fromChannel;

	public UnparkedCallEvent(final org.asteriskjava.manager.event.UnparkedCallEvent event) throws InvalidChannelName
	{
		super(event);
		final AsteriskPBX pbx = (AsteriskPBX) PBXFactory.getActivePBX();

		this.fromChannel = pbx.registerChannel(event.getParkerDialString(), ChannelImpl.UNKNOWN_UNIQUE_ID);
	}

	public Channel getFromChannel()
	{
		return this.fromChannel;
	}

}
