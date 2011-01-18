package net.java.slee.resource.diameter.ro;

import java.io.IOException;

import net.java.slee.resource.diameter.Validator;
import net.java.slee.resource.diameter.base.CreateActivityException;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.ro.events.RoCreditControlAnswer;
import net.java.slee.resource.diameter.ro.events.RoCreditControlRequest;

/**
 * The SBB interface for the Diameter Ro Resource Adaptor.
 * 
 * This API can be used in either an asynchronous or synchronous manner.
 * 
 * To send messages asynchronously, create a RoClientSessionActivity using one of the createRoClientSessionActivity() methods.
 * 
 * To send messages synchronously, use the following methods:
 * <ul>eventCreditControlRequest(CreditControlRequest)</ul>
 * <ul>initialCreditControlRequest(CreditControlRequest)</ul>
 * <ul>updateCreditControlRequest(CreditControlRequest)</ul>
 * <ul>terminationCreditControlRequest(CreditControlRequest)</ul>
 * 
 * The Credit-Control-Request messages must be created using the RoMessageFactory returned from getRoMessageFactory().
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface RoProvider {

  /**
   * Return a message factory to be used to create concrete implementations of credit control messages.
   * 
   * @return
   */
  public RoMessageFactory getRoMessageFactory();

  /**
   * Return a avp factory to be used to create concrete implementations of credit control AVPs.
   * 
   * @return
   */
  public RoAvpFactory getRoAvpFactory();

  /**
   * Create a new activity to send and receive Diameter messages.
   * 
   * @return a DiameterActivity
   * @throws CreateActivityException if the RA could not create the activity for any reason
   */
  public RoClientSessionActivity createRoClientSessionActivity() throws CreateActivityException;

  /**
   * Create a new activity to send and receive Diameter messages.
   * 
   * @param destinationHost a destination host to automatically put in all messages
   * @param destinationRealm a destination realm to automatically put in all messages 
   * @return a DiameterActivity 
   * @throws CreateActivityException if the RA could not create the activity for any reason
   */
  public RoClientSessionActivity createRoClientSessionActivity(DiameterIdentity destinationHost, DiameterIdentity destinationRealm) throws CreateActivityException;

  /**
   * Send a Credit-Control-Request message to the appropriate peers, and block until the response is received then return it.
   * 
   * @param ccr the CreditControlRequest to send
   * @return the answer received 
   * @throws IOException if an error occurred sending the request to the peer
   */
  public RoCreditControlAnswer sendRoCreditControlRequest(RoCreditControlRequest ccr) throws IOException;

  /**
   * Return the number of peers this Diameter resource adaptor is connected to.
   * 
   * @return connected peer count
   */
  int getPeerCount();

  /**
   * Returns array containing identities of connected peers. 
   * 
   * @return
   */
  DiameterIdentity[] getConnectedPeers();

  Validator getValidator();

}
