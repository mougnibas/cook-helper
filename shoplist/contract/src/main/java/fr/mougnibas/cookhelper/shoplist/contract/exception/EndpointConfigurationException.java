package fr.mougnibas.cookhelper.shoplist.contract.exception;

/**
 * An endpoint configuration exception.
 * 
 * @author Yoann
 */
public class EndpointConfigurationException extends RuntimeException {

  /**
   * Generated serial version.
   */
  private static final long serialVersionUID = 8429322531565154679L;

  /**
   * Default constructor.
   */
  public EndpointConfigurationException() {
  }

  /**
   * Initialize the exception.
   * 
   * @param message Message exception.
   */
  public EndpointConfigurationException(String message) {
    super(message);
  }

  /**
   * Initialize the exception.
   * 
   * @param cause Cause exception.
   */
  public EndpointConfigurationException(Throwable cause) {
    super(cause);
  }

  /**
   * Initialize the exception.
   * 
   * @param message Message exception.
   * @param cause   Cause exception.
   */
  public EndpointConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Initialize the exception.
   * 
   * @param message            Message exception.
   * @param cause              Cause exception.
   * @param enableSuppression  Enable suppression ?
   * @param writableStackTrace Writable stack trace ?
   */
  public EndpointConfigurationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
