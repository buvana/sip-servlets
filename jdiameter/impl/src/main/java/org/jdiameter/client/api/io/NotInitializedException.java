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

package org.jdiameter.client.api.io;

/**
 * Signals that an exception has occurred in a during start
 * transport element. An <code>NotInitializedException</code> is thrown to indicate that
 * transport element is not  configured.
 */
public class NotInitializedException extends Exception{

    /**
     * Create instance of class
     */
    public NotInitializedException() {
        super();
    }

    /**
     * Create instance of class with predefined parameters
     * @param message error message
     */
    public NotInitializedException(String message) {
        super(message);
    }

    /**
     * Create instance of class with predefined parameters
     * @param message error message
     * @param cause error cause
     */
    public NotInitializedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create instance of class with predefined parameters
     * @param cause error cause
     */
    public NotInitializedException(Throwable cause) {
        super(cause);
    }
}