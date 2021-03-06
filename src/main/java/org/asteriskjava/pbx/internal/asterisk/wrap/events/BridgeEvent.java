package org.asteriskjava.pbx.internal.asterisk.wrap.events;

import org.apache.log4j.Logger;
import org.asteriskjava.pbx.PBXFactory;
import org.asteriskjava.pbx.internal.asterisk.InvalidChannelName;
import org.asteriskjava.pbx.internal.core.AsteriskPBX;
import org.asteriskjava.pbx.internal.core.ChannelProxy;

public class BridgeEvent extends ManagerEvent
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(BridgeEvent.class);

	enum BridgeState
	{
		Link, Unlink
	}

	private final BridgeState bridgeState;
	private final ChannelProxy channel1;
	private final ChannelProxy channel2;

	public BridgeEvent(final org.asteriskjava.manager.event.BridgeEvent event) throws InvalidChannelName
	{
		super(event);
		final AsteriskPBX pbx = (AsteriskPBX) PBXFactory.getActivePBX();

		this.bridgeState = BridgeState.valueOf(event.getBridgeState());
		this.channel1 = pbx.registerChannel(event.getChannel1(), event.getUniqueId1());
		this.channel2 = pbx.registerChannel(event.getChannel2(), event.getUniqueId2());
	}

	public BridgeState getBridgeState()
	{
		return this.bridgeState;
	}

	public ChannelProxy getChannel1()
	{
		return this.channel1;
	}

	public ChannelProxy getChannel2()
	{
		return this.channel2;
	}

	/**
	 * Returns whether the two channels have been linked.
	 * 
	 * @return <code>true</code> the two channels have been linked,
	 *         <code>false</code> if they have been unlinked.
	 * @since 1.0.0
	 */
	public boolean isLink()
	{
		return this.bridgeState == BridgeState.Link;
	}

	/**
	 * Returns whether the two channels have been unlinked.
	 * 
	 * @return <code>true</code> the two channels have been unlinked,
	 *         <code>false</code> if they have been linked.
	 * @since 1.0.0
	 */
	public boolean isUnlink()
	{
		return this.bridgeState == BridgeState.Unlink;
	}

	public String toString()
	{
		return "BridgeEvent: channel1:" + this.channel1 + " channel2:" + this.channel2 + " bridgeState:" + this.bridgeState; //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
	}
}
