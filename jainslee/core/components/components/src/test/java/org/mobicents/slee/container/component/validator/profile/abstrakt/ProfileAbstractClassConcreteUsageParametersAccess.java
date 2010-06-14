/**
 * Start time:16:31:35 2009-02-14<br>
 * Project: mobicents-jainslee-server-core<br>
 * 
 * @author <a href="mailto:baranowb@gmail.com">baranowb - Bartosz Baranowski
 *         </a>
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
package org.mobicents.slee.container.component.validator.profile.abstrakt;

import javax.slee.CreateException;
import javax.slee.profile.ProfileContext;
import javax.slee.profile.ProfileVerificationException;
import javax.slee.usage.UnrecognizedUsageParameterSetNameException;


/**
 * Start time:16:31:35 2009-02-14<br>
 * Project: mobicents-jainslee-server-core<br>
 * 
 * @author <a href="mailto:baranowb@gmail.com">baranowb - Bartosz Baranowski
 *         </a>
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
public abstract class ProfileAbstractClassConcreteUsageParametersAccess implements javax.slee.profile.Profile,ManagementInterfaceOk, ProfileBaseCMPInterface
{

	
	
	
	
	public ProfileAbstractClassConcreteUsageParametersAccess() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void profileActivate() {
		// TODO Auto-generated method stub
		
	}

	public void profileInitialize() {
		// TODO Auto-generated method stub
		
	}

	public void profileLoad() {
		// TODO Auto-generated method stub
		
	}

	public void profilePassivate() {
		// TODO Auto-generated method stub
		
	}

	public void profilePostCreate() throws CreateException {
		// TODO Auto-generated method stub
		
	}

	public void profileRemove() {
		// TODO Auto-generated method stub
		
	}

	public void profileStore() {
		// TODO Auto-generated method stub
		
	}

	public void profileVerify() throws ProfileVerificationException {
		// TODO Auto-generated method stub
		
	}

	public void setProfileContext(ProfileContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void unsetProfileContext() {
		// TODO Auto-generated method stub
		
	}

	
	// profile LO methods
	
	public void makeDogBark(int xxxx)
	{}
	public void dontLookAtMeImUglyDefinedMethodWithLongNameAndSpankTheCat(java.io.Serializable cheese)
	{}
	
	// Management interface
	public void doSomeTricktMGMTMagic(int xxxx)
	{}
	public void dontLookAtMeImUglyDefinedMethodWithLongName(java.io.Serializable cheese)
	{}
	
	// USAGE
	
	

	public UsageOkInterface getUsageParameterSet(String x) throws UnrecognizedUsageParameterSetNameException
	{
		return null;
	}
	

}
