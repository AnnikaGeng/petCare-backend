#!/bin/bash
cd /home/ec2-user
aws s3 cp s3://mybucketforpet/petCare-0.0.1-SNAPSHOT.jar
java -jar petCare-0.0.1-SNAPSHOT.jar
