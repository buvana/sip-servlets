/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a full listing
 * of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU General Public License, v. 2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License,
 * v. 2.0 along with this distribution; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */
package org.mobicents.slee.resource.diameter.sh.events.avp.userdata;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import net.java.slee.resource.diameter.sh.events.avp.userdata.ApplicationServer;
import net.java.slee.resource.diameter.sh.events.avp.userdata.Extension;
import net.java.slee.resource.diameter.sh.events.avp.userdata.InitialFilterCriteria;
import net.java.slee.resource.diameter.sh.events.avp.userdata.Trigger;


/**
 * <p>Java class for tInitialFilterCriteria complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tInitialFilterCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Priority" type="{}tPriority"/>
 *         &lt;element name="TriggerPoint" type="{}tTrigger" minOccurs="0"/>
 *         &lt;element name="ApplicationServer" type="{}tApplicationServer"/>
 *         &lt;element name="Extension" type="{}tExtension" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tInitialFilterCriteria", propOrder = {
    "priority",
    "triggerPoint",
    "applicationServer",
    "extension",
    "any"
})
public class TInitialFilterCriteria implements InitialFilterCriteria {

    @XmlElement(name = "Priority")
    protected int priority;
    @XmlElement(name = "TriggerPoint")
    protected TTrigger triggerPoint;
    @XmlElement(name = "ApplicationServer", required = true)
    protected TApplicationServer applicationServer;
    @XmlElement(name = "Extension")
    protected TExtension extension;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.InitialFilterCriteria#getPriority()
     */
    public int getPriority() {
        return priority;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.InitialFilterCriteria#setPriority(int)
     */
    public void setPriority(int value) {
        this.priority = value;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.InitialFilterCriteria#getTriggerPoint()
     */
    public Trigger getTriggerPoint() {
        return triggerPoint;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.InitialFilterCriteria#setTriggerPoint(org.mobicents.slee.resource.diameter.sh.events.avp.userdata.TTrigger)
     */
    public void setTriggerPoint(Trigger value) {
        this.triggerPoint = (TTrigger) value;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.InitialFilterCriteria#getApplicationServer()
     */
    public ApplicationServer getApplicationServer() {
        return applicationServer;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.InitialFilterCriteria#setApplicationServer(org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ApplicationServer)
     */
    public void setApplicationServer(ApplicationServer value) {
        this.applicationServer = (TApplicationServer) value;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.InitialFilterCriteria#getExtension()
     */
    public Extension getExtension() {
        return extension;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.InitialFilterCriteria#setExtension(org.mobicents.slee.resource.diameter.sh.events.avp.userdata.Extension)
     */
    public void setExtension(Extension value) {
        this.extension = (TExtension) value;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.InitialFilterCriteria#getAny()
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

}
