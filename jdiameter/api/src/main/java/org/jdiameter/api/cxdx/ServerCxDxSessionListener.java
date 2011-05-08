/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jdiameter.api.cxdx;

import org.jdiameter.api.IllegalDiameterStateException;
import org.jdiameter.api.InternalException;
import org.jdiameter.api.OverloadException;
import org.jdiameter.api.RouteException;
import org.jdiameter.api.app.AppAnswerEvent;
import org.jdiameter.api.app.AppRequestEvent;
import org.jdiameter.api.app.AppSession;
import org.jdiameter.api.cxdx.events.JLocationInfoRequest;
import org.jdiameter.api.cxdx.events.JMultimediaAuthRequest;
import org.jdiameter.api.cxdx.events.JPushProfileAnswer;
import org.jdiameter.api.cxdx.events.JPushProfileRequest;
import org.jdiameter.api.cxdx.events.JRegistrationTerminationAnswer;
import org.jdiameter.api.cxdx.events.JRegistrationTerminationRequest;
import org.jdiameter.api.cxdx.events.JServerAssignmentRequest;
import org.jdiameter.api.cxdx.events.JUserAuthorizationRequest;

/**
 * Start time:14:17:50 2009-08-17<br>
 * Project: diameter-parent<br>
 * 
 * @author <a href="mailto:baranowb@gmail.com">Bartosz Baranowski </a>
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
public interface ServerCxDxSessionListener {

  /**
   * Notifies this ClientCxDxSessionListener that the ClientCxDxSession has recived not CxDx message, usually some extension.
   * @param session parent application session (FSM)
   * @param request request object
   * @param answer answer object
   * @throws InternalException The InternalException signals that internal error is occurred.
   * @throws IllegalDiameterStateException The IllegalStateException signals that session has incorrect state (invalid).
   * @throws RouteException The NoRouteException signals that no route exist for a given realm.
   * @throws OverloadException The OverloadException signals that destination host is overloaded.
   */
  void doOtherEvent(AppSession session, AppRequestEvent request, AppAnswerEvent answer) throws InternalException, IllegalDiameterStateException, RouteException, OverloadException;

  void doUserAuthorizationRequest(ServerCxDxSession session,JUserAuthorizationRequest request) throws InternalException, IllegalDiameterStateException, RouteException, OverloadException;

  void doServerAssignmentRequest(ServerCxDxSession session,JServerAssignmentRequest request) throws InternalException, IllegalDiameterStateException, RouteException, OverloadException;

  void doRegistrationTerminationAnswer(ServerCxDxSession session,JRegistrationTerminationRequest request,JRegistrationTerminationAnswer answer) throws InternalException, IllegalDiameterStateException, RouteException, OverloadException;

  void doLocationInformationRequest(ServerCxDxSession session,JLocationInfoRequest request) throws InternalException, IllegalDiameterStateException, RouteException, OverloadException;

  void doPushProfileAnswer(ServerCxDxSession session,JPushProfileRequest request,JPushProfileAnswer answer) throws InternalException, IllegalDiameterStateException, RouteException, OverloadException;

  void doMultimediaAuthRequest(ServerCxDxSession session,JMultimediaAuthRequest request) throws InternalException, IllegalDiameterStateException, RouteException, OverloadException;
}