/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2015, Telestax Inc and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package org.mobicents.tools.smpp.multiplexer;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.rowset.spi.SyncResolver;

import com.cloudhopper.smpp.pdu.Pdu;
/**
 * @author Konstantin Nosach (kostyantyn.nosach@telestax.com)
 */
public class SmppToProviderRoundRobinAlgorithm extends DefaultSmppAlgorithm
{
	protected Iterator<Entry<Long, MClientConnectionImpl>> connectionToProviderIterator = null;

	@Override
	public void processSubmitToNode(ConcurrentHashMap<Long, MServerConnectionImpl> connectionsToNodes, Long serverSessionId, Pdu packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized void processSubmitToProvider(ConcurrentHashMap<Long, MClientConnectionImpl> connectionsToProviders, Long sessionId, Pdu packet) 
	{
		if(connectionToProviderIterator==null)
			connectionToProviderIterator = connectionsToProviders.entrySet().iterator();
		Entry<Long, MClientConnectionImpl> pair = null;
		while(connectionToProviderIterator.hasNext())
		{
			pair = connectionToProviderIterator.next();
			if(connectionsToProviders.containsKey(pair.getKey()))
				pair.getValue().sendSmppRequest(sessionId,packet);
			return;
		}
		connectionToProviderIterator = connectionsToProviders.entrySet().iterator();
		if(connectionToProviderIterator.hasNext())
		{
			pair = connectionToProviderIterator.next();
			pair.getValue().sendSmppRequest(sessionId,packet);
			return;
		}
		else
			throw new RuntimeException("LB does not have connected Providers, but trying send them request");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configurationChanged() {
		// TODO Auto-generated method stub
		
	}

}