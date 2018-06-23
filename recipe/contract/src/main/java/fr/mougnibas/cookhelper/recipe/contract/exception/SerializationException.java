package fr.mougnibas.cookhelper.recipe.contract.exception;

/**
 * A serialization exception.
 * 
 * @author Yoann
 */
public class SerializationException extends RuntimeException {

  /**
   * Generated serial version.
   */
  private static final long serialVersionUID = -8226788705117355607L;

  /**
   * Default constructor.
   */
  public SerializationException() {
  }

  /**
   * Initialize the exception.
   * 
   * @param message
   *          Message exception.
   */
  public SerializationException(String message) {
    super(message);
  }

  /**
   * Initialize the exception.
   * 
   * @param cause
   *          Cause exception.
   */
  public SerializationException(Throwable cause) {
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
  public SerializationException(String message, Throwable cause) {
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
  public SerializationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
