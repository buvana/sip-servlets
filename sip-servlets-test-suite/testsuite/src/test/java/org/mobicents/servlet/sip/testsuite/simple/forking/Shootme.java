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

package org.mobicents.servlet.sip.testsuite.simple.forking;

import java.util.Hashtable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.sip.Dialog;
import javax.sip.DialogState;
import javax.sip.DialogTerminatedEvent;
import javax.sip.IOExceptionEvent;
import javax.sip.ListeningPoint;
import javax.sip.RequestEvent;
import javax.sip.ResponseEvent;
import javax.sip.ServerTransaction;
import javax.sip.SipListener;
import javax.sip.SipProvider;
import javax.sip.SipStack;
import javax.sip.Transaction;
import javax.sip.TransactionState;
import javax.sip.TransactionTerminatedEvent;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.header.ContactHeader;
import javax.sip.header.HeaderFactory;
import javax.sip.header.ToHeader;
import javax.sip.header.ViaHeader;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import javax.sip.message.Response;

import junit.framework.TestCase;

import org.apache.log4j.Logger;



/**
 * This class is a UAC template. Shootist is the guy that shoots and shootme is
 * the guy that gets shot.
 *
 * @author M. Ranganathan
 */

public class Shootme   implements SipListener {




    private static final String myAddress = "" + System.getProperty("org.mobicents.testsuite.testhostaddr") + "";

    private Hashtable serverTxTable = new Hashtable();

    private SipProvider sipProvider;

    private int myPort ;

    private static String unexpectedException = "Unexpected exception ";

    private static Logger logger = Logger.getLogger(Shootme.class);

    private boolean inviteSeen;


    private boolean byeSeen;

    private boolean ackSeen;


    private SipStack sipStack;

    private int delay;

    private int ringingDelay;

    private boolean sendRinging;

    private static AddressFactory addressFactory;

    private static MessageFactory messageFactory;

    private static HeaderFactory headerFactory;

    private static final String transport = "udp";

    private static Timer timer = new Timer();


    class MyTimerTask extends TimerTask {
        RequestEvent  requestEvent;
        String toTag;
        ServerTransaction serverTx;

        public MyTimerTask(RequestEvent requestEvent,  ServerTransaction tx, String toTag) {
            logger.info("MyTimerTask ");
            this.requestEvent = requestEvent;
            this.toTag = toTag;
            this.serverTx = tx;

        }

        public void run() {
            sendInviteOK(requestEvent,serverTx,toTag);
        }

    }



    public void processRequest(RequestEvent requestEvent) {
        Request request = requestEvent.getRequest();
        ServerTransaction serverTransactionId = requestEvent
                .getServerTransaction();

        logger.info("\n\nRequest " + request.getMethod()
                + " received at " + sipStack.getStackName()
                + " with server transaction id " + serverTransactionId);

        if (request.getMethod().equals(Request.INVITE)) {
            processInvite(requestEvent, serverTransactionId);
        } else if (request.getMethod().equals(Request.ACK)) {
            processAck(requestEvent, serverTransactionId);
        } else if (request.getMethod().equals(Request.BYE)) {
            processBye(requestEvent, serverTransactionId);
        } else if (request.getMethod().equals(Request.CANCEL)) {
            processCancel(requestEvent, serverTransactionId);
        }

    }

    public void processResponse(ResponseEvent responseEvent) {
    }

    /**
     * Process the ACK request. Send the bye and complete the call flow.
     */
    public void processAck(RequestEvent requestEvent,
            ServerTransaction serverTransaction) {
        logger.info("shootme: got an ACK! ");
        logger.info("Dialog = " + requestEvent.getDialog());
        if(requestEvent.getDialog() != null) {
        	logger.info("Dialog State = " + requestEvent.getDialog().getState());
        }

        this.ackSeen = true;
    }

    /**
     * Process the invite request.
     */
    public void processInvite(RequestEvent requestEvent,
            ServerTransaction serverTransaction) {
        SipProvider sipProvider = (SipProvider) requestEvent.getSource();
        Request request = requestEvent.getRequest();
        try {
            logger.info("shootme: got an Invite sending Trying");
            // logger.info("shootme: " + request);

            ServerTransaction st = requestEvent.getServerTransaction();

            if (st == null) {
                logger.info("null server tx -- getting a new one");
                st = sipProvider.getNewServerTransaction(request);
            }

            logger.info("getNewServerTransaction : " + st);

            String txId = ((ViaHeader)request.getHeader(ViaHeader.NAME)).getBranch();
            this.serverTxTable.put(txId, st);

            // Create the 100 Trying response.
            Response response = messageFactory.createResponse(Response.TRYING,
                    request);
                ListeningPoint lp = sipProvider.getListeningPoint(transport);
            int myPort = lp.getPort();

            Address address = addressFactory.createAddress("Shootme <sip:"
                    + myAddress + ":" + myPort + ">");

            // Add a random sleep to stagger the two OK's for the benifit of implementations
            // that may not be too good about handling re-entrancy.
//            int timeToSleep = (int) ( Math.random() * 1000);
//
//            Thread.sleep(timeToSleep);

            st.sendResponse(response);

            Response ringingResponse = messageFactory.createResponse(Response.RINGING,
                    request);
            ContactHeader contactHeader = headerFactory.createContactHeader(address);
            response.addHeader(contactHeader);
            ToHeader toHeader = (ToHeader) ringingResponse.getHeader(ToHeader.NAME);
            String toTag =  "" + System.nanoTime();
            toHeader.setTag(toTag);
            if ( sendRinging ) {
                ringingResponse.addHeader(contactHeader);
                st.sendResponse(ringingResponse);
            }
            Dialog dialog =  st.getDialog();
            dialog.setApplicationData(st);

            this.inviteSeen = true;

            timer.schedule(new MyTimerTask(requestEvent,st,toTag), this.delay);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }

    private void sendInviteOK(RequestEvent requestEvent, ServerTransaction inviteTid, String toTag) {
        try {
            logger.info("sendInviteOK: " + inviteTid);
            if (inviteTid.getState() != TransactionState.COMPLETED) {
                logger.info("shootme: Dialog state before OK: "
                        + inviteTid.getDialog().getState());

                SipProvider sipProvider = (SipProvider) requestEvent.getSource();
                Request request = requestEvent.getRequest();
                Response okResponse = messageFactory.createResponse(Response.OK,
                        request);
                    ListeningPoint lp = sipProvider.getListeningPoint(transport);
                int myPort = lp.getPort();


                ((ToHeader)okResponse.getHeader(ToHeader.NAME)).setTag(toTag);


                Address address = addressFactory.createAddress("Shootme <sip:"
                        + myAddress + ":" + myPort + ">");
                ContactHeader contactHeader = headerFactory
                        .createContactHeader(address);
                okResponse.addHeader(contactHeader);
                inviteTid.sendResponse(okResponse);
                logger.info("shootme: Dialog state after OK: "
                        + inviteTid.getDialog().getState());
                TestCase.assertEquals( DialogState.CONFIRMED , inviteTid.getDialog().getState() );
            } else {
                logger.info("semdInviteOK: inviteTid = " + inviteTid + " state = " + inviteTid.getState());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Process the bye request.
     */
    public void processBye(RequestEvent requestEvent,
            ServerTransaction serverTransactionId) {
        Request request = requestEvent.getRequest();
        try {
            logger.info("shootme:  got a bye sending OK.");
            logger.info("shootme:  dialog = " + requestEvent.getDialog());
            logger.info("shootme:  dialogState = " + requestEvent.getDialog().getState());
            Response response = messageFactory.createResponse(200, request);
            if ( serverTransactionId != null) {
                serverTransactionId.sendResponse(response);
            }
            logger.info("shootme:  dialogState = " + requestEvent.getDialog().getState());

            this.byeSeen = true;


        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);

        }
    }

    public void processCancel(RequestEvent requestEvent,
            ServerTransaction serverTransactionId) {
        Request request = requestEvent.getRequest();
        SipProvider sipProvider = (SipProvider)requestEvent.getSource();
        try {
            logger.info("shootme:  got a cancel. " );
            // Because this is not an In-dialog request, you will get a null server Tx id here.
            if (serverTransactionId == null) {
                serverTransactionId = sipProvider.getNewServerTransaction(request);
            }
            Response response = messageFactory.createResponse(200, request);
            serverTransactionId.sendResponse(response);

            String serverTxId = ((ViaHeader)response.getHeader(ViaHeader.NAME)).getBranch();
            ServerTransaction serverTx = (ServerTransaction) this.serverTxTable.get(serverTxId);
            if ( serverTx != null && (serverTx.getState().equals(TransactionState.TRYING) ||
                    serverTx.getState().equals(TransactionState.PROCEEDING))) {
                Request originalRequest = serverTx.getRequest();
                Response resp = messageFactory.createResponse(Response.REQUEST_TERMINATED,originalRequest);
                serverTx.sendResponse(resp);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);

        }
    }

    public void processTimeout(javax.sip.TimeoutEvent timeoutEvent) {
        Transaction transaction;
        if (timeoutEvent.isServerTransaction()) {
            transaction = timeoutEvent.getServerTransaction();
        } else {
            transaction = timeoutEvent.getClientTransaction();
        }
        logger.info("state = " + transaction.getState());
        logger.info("dialog = " + transaction.getDialog());
        logger.info("dialogState = "
                + transaction.getDialog().getState());
        logger.info("Transaction Time out");
    }

    public SipProvider createProvider() {
        try {

            ListeningPoint lp = sipStack.createListeningPoint(myAddress,
                    myPort, transport);

            sipProvider = sipStack.createSipProvider(lp);
            logger.info("provider " + sipProvider);
            logger.info("sipStack = " + sipStack);
            return sipProvider;
        } catch (Exception ex) {
            logger.error(ex);
            TestCase.fail(unexpectedException);
            return null;

        }

    }

    public Shootme( int myPort, boolean sendRinging, int delay ) {
        this.myPort = myPort;
        this.delay = delay;
        this.sendRinging = sendRinging;

        SipObjects sipObjects = new SipObjects(myPort, "shootme","on", true);
        addressFactory = sipObjects.addressFactory;
        messageFactory = sipObjects.messageFactory;
        headerFactory = sipObjects.headerFactory;
        this.sipStack = sipObjects.sipStack;
    }



    public void processIOException(IOExceptionEvent exceptionEvent) {
        logger.info("IOException");

    }

    public void processTransactionTerminated(
            TransactionTerminatedEvent transactionTerminatedEvent) {
        logger.info("Transaction terminated event recieved");

    }

    public void processDialogTerminated(
            DialogTerminatedEvent dialogTerminatedEvent) {
        logger.info("Dialog terminated event recieved");

    }

    public void checkState() {
        TestCase.assertTrue("Should see invite", inviteSeen);

        TestCase.assertTrue("Should see BYE",byeSeen );

    }

    public boolean checkBye() {
        return this.byeSeen;
    }

    public void stop() {
        this.sipStack.stop();
    }


    /**
     * @return the ackSeen
     *
     * Exactly one Dialog must get an ACK.
     */
    public boolean isAckSeen() {
        return ackSeen;
    }



}
