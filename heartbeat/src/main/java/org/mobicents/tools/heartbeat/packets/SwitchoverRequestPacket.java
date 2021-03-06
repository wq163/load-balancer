/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2017, Telestax Inc and individual contributors
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
package org.mobicents.tools.heartbeat.packets;

import org.mobicents.tools.heartbeat.api.Packet;

/**
 * @author Konstantin Nosach (kostyantyn.nosach@telestax.com)
 */
public class SwitchoverRequestPacket implements Packet {

	private String fromJvmRoute;
	private String toJvmRoute;

	public SwitchoverRequestPacket(String fromJvmRoute, String toJvmRoute)
	{
		this.fromJvmRoute = fromJvmRoute;
		this.toJvmRoute = toJvmRoute;
	}

	public String getFromJvmRoute() {
		return fromJvmRoute;
	}

	public void setFromJvmRoute(String fromJvmRoute) {
		this.fromJvmRoute = fromJvmRoute;
	}

	public String getToJvmRoute() {
		return toJvmRoute;
	}

	public void setToJvmRoute(String toJvmRoute) {
		this.toJvmRoute = toJvmRoute;
	}
}
