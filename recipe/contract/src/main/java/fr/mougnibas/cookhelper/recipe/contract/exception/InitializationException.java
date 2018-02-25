/*
  © Copyright 2017-2018 Yoann MOUGNIBAS
  
  This file is part of Cook-Helper.
  
  Cook-Helper is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
  
  Cook-Helper is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  
  You should have received a copy of the GNU General Public License
  along with Cook-Helper. If not, see <http://www.gnu.org/licenses/>
 */

package fr.mougnibas.cookhelper.recipe.contract.exception;

/**
 * An initialization exception.
 * 
 * @author Yoann
 */
public class InitializationException extends RuntimeException {

  /**
   * Generated serial version.
   */
  private static final long serialVersionUID = 8948461475060100808L;

  /**
   * Default constructor.
   */
  public InitializationException() {
  }

  /**
   * Initialize the exception.
   * 
   * @param message
   *          Message exception.
   */
  public InitializationException(String message) {
    super(message);
  }

  /**
   * Initialize the exception.
   * 
   * @param cause
   *          Cause exception.
   */
  public InitializationException(Throwable cause) {
    super(cause);
  }

  /**
   * Initialize the exception.
   * 
   * @param message
   *          Message exception.
   * @param cause
   *          Cause exception.
   */
  public InitializationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Initialize the exception.
   * 
   * @param message
   *          Message exception.
   * @param cause
   *          Cause exception.
   * @param enableSuppression
   *          Enable suppression ?
   * @param writableStackTrace
   *          Writable stack trace ?
   */
  public InitializationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
