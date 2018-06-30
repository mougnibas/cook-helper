package fr.mougnibas.cookhelper.shoplist.contract.exception;

/**
 * A fetch exception.
 * 
 * @author Yoann
 */
public class RecipeNotFoundException extends RuntimeException {

  /**
   * Generated serial number.
   */
  private static final long serialVersionUID = -8190324223597224863L;

  /**
   * Default constructor.
   */
  public RecipeNotFoundException() {
  }

  /**
   * Initialize the exception.
   * 
   * @param message
   *          Message exception.
   */
  public RecipeNotFoundException(String message) {
    super(message);
  }

  /**
   * Initialize the exception.
   * 
   * @param cause
   *          Cause exception.
   */
  public RecipeNotFoundException(Throwable cause) {
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
  public RecipeNotFoundException(String message, Throwable cause) {
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
  public RecipeNotFoundException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
