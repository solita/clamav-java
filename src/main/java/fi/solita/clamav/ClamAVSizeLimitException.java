package fi.solita.clamav;

/**
 * Thrown if clamd size limit is exceeded during scanning.
 * <p> 
 * See <a href="http://linux.die.net/man/5/clamd.conf">clamd man page</a> for further information.
 */
public class ClamAVSizeLimitException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ClamAVSizeLimitException(String msg) {
		super(msg);
	}
}
