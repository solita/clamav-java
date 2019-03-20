package fi.solita.clamav;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * These tests assume clamd is running and responding in the virtual machine. 
 */
public class PingTest {

  @Test
  public void testPingPong() throws IOException  {
    ClamAVClient cl = new ClamAVClient(TestConfiguration.CLAMAV_HOST, TestConfiguration.CLAMAV_PORT);
    assertTrue(cl.ping());
  }
}
