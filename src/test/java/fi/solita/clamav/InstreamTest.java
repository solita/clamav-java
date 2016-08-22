package fi.solita.clamav;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * These tests assume clamd is running and responding in the virtual machine. 
 */
public class InstreamTest {

  private static String CLAMAV_HOST = "localhost";
  
  private byte[] scan(byte[] input) throws UnknownHostException, IOException  {
    ClamAVClient cl = new ClamAVClient(CLAMAV_HOST, 3310);
    return cl.scan(input);
  }
  
  private byte[] scan(InputStream input) throws UnknownHostException, IOException  {
    ClamAVClient cl = new ClamAVClient(CLAMAV_HOST, 3310);
    return cl.scan(input);
  }
  @Test
  public void testRandomBytes() throws UnknownHostException, IOException {
    byte[] r = scan("alsdklaksdla".getBytes("ASCII"));
    assertTrue(ClamAVClient.isCleanReply(r));
  }
  
  @Test
  public void testPositive() throws UnknownHostException, IOException {
    // http://www.eicar.org/86-0-Intended-use.html
    byte[] EICAR = "X5O!P%@AP[4\\PZX54(P^)7CC)7}$EICAR-STANDARD-ANTIVIRUS-TEST-FILE!$H+H*".getBytes("ASCII");
    byte[] r =  scan(EICAR);
    assertFalse(ClamAVClient.isCleanReply(r));
  }

  @Test
  public void testStreamChunkingWorks() throws UnknownHostException, IOException {
      byte[] multipleChunks = new byte[50000];
      byte[] r = scan(multipleChunks);
      assertTrue(ClamAVClient.isCleanReply(r));
  }
  
  @Test
  public void testChunkLimit() throws UnknownHostException, IOException {
      byte[] maximumChunk = new byte[2048];
      byte[] r = scan(maximumChunk);
      assertTrue(ClamAVClient.isCleanReply(r));
  }
  
  @Test
  public void testZeroBytes() throws UnknownHostException, IOException {
      byte[] r = scan(new byte[]{});
      assertTrue(ClamAVClient.isCleanReply(r));
  }

  @Test(expected = ClamAVSizeLimitException.class)
  public void testSizeLimit() throws UnknownHostException, IOException {
	  scan(new SlowInputStream());
  }
}
