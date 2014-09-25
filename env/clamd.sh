#!/bin/bash
set -eu

# EPEL
rpm -Uvh http://dl.fedoraproject.org/pub/epel/6/x86_64/epel-release-6-8.noarch.rpm

# ClamAV
yum install -y clamav clamd

# take off firewall (local virtual machine - ok)
iptables -F

# listen to our local IP, not only on localhost
echo 'TCPAddr 192.168.50.72' >> /etc/clamd.conf

service clamd restart

