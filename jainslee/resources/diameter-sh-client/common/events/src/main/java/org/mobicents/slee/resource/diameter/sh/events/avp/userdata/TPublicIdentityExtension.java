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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import net.java.slee.resource.diameter.sh.events.avp.userdata.PublicIdentityExtension;
import net.java.slee.resource.diameter.sh.events.avp.userdata.PublicIdentityExtension2;


/**
 * <p>Java class for tPublicIdentityExtension complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tPublicIdentityExtension">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdentityType" type="{}tIdentityType" minOccurs="0"/>
 *         &lt;element name="WildcardedPSI" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="Extension" type="{}tPublicIdentityExtension2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tPublicIdentityExtension", propOrder = {
    "identityType",
    "wildcardedPSI",
    "extension"
})
public class TPublicIdentityExtension implements PublicIdentityExtension {

    @XmlElement(name = "IdentityType")
    protected Short identityType;
    @XmlElement(name = "WildcardedPSI")
    @XmlSchemaType(name = "anyURI")
    protected String wildcardedPSI;
    @XmlElement(name = "Extension")
    protected TPublicIdentityExtension2 extension;

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.PublicIdentityExtension#getIdentityType()
     */
    public Short getIdentityType() {
        return identityType;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.PublicIdentityExtension#setIdentityType(java.lang.Short)
     */
    public void setIdentityType(Short value) {
        this.identityType = value;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.PublicIdentityExtension#getWildcardedPSI()
     */
    public String getWildcardedPSI() {
        return wildcardedPSI;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.PublicIdentityExtension#setWildcardedPSI(java.lang.String)
     */
    public void setWildcardedPSI(String value) {
        this.wildcardedPSI = value;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.PublicIdentityExtension#getExtension()
     */
    public PublicIdentityExtension2 getExtension() {
        return extension;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.PublicIdentityExtension#setExtension(org.mobicents.slee.resource.diameter.sh.events.avp.userdata.TPublicIdentityExtension2)
     */
    public void setExtension(PublicIdentityExtension2 value) {
        this.extension = (TPublicIdentityExtension2) value;
    }

}
