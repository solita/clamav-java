Simple [ClamAV](http://www.clamav.net/) Java client. See also [ClamAV REST service](https://github.com/solita/clamav-rest) which builds on top of this.

Travis CI: [![Build Status](https://travis-ci.org/solita/clamav-java.svg?branch=master)](https://travis-ci.org/solita/clamav-java)

# What is provided

Support for basic INSTREAM scanning and PING command. 

Clamd protocol is explained here:
http://linux.die.net/man/8/clamd

A REST style API and server can be found from another repository, [clamav-rest](https://github.com/solita/clamav-rest). 

# Using the client

Code is self explanatory. Something like this is the idea:

```
  ClamAVClient cl = new ClamAVClient("192.168.50.72", 3310);
  byte[] reply;
  try {
    reply = cl.scan(input);
  } catch (Exception e) {
    throw new RuntimeException("Could not scan the input", e);
  }
  if (!ClamAVClient.isCleanReply(reply)) {
   throw new Exception("aaargh. Something was found");
  }
```

# Maven dependency

```
<dependency>
  <groupId>fi.solita.clamav</groupId>
  <artifactId>clamav-client</artifactId>
  <version>1.0.1</version>
</dependency>
```

# Creating the jar

```
mvn install
```

# Testing the client

To run the automated tests you are assumed to run the clamd in a local virtual machine. 
Configuration for [Vagrant](http://www.vagrantup.com/) and [Oracle Virtualbox](https://www.virtualbox.org/) is provided.

To start test server simply

```
cd vagrant
vagrant up clamav
```

This will kick up a CentOS virtual machine and install [ClamAV](http://www.clamav.net/) in it.

Alternatively, you could use Docker image to run ClamAV. Automated tests with Travis CI run using [Docker image for ClamAV](https://hub.docker.com/r/lokori/clamav-java/). The test image runs with artificially low MaxStreamLength setting on purpose.

## Contributors

* [Antti Virtanen](https://github.com/lokori)
* [Ari Ruotsalainen](https://github.com/ruoat)
* [Heikki Hokkanen](https://github.com/hoxu)
* [Henrik Alstad](https://github.com/drogin)


# License

Copyright © 2014 [Solita](http://www.solita.fi)

Distributed under the GNU Lesser General Public License, either version 2.1 of the License, or 
(at your option) any later version.

