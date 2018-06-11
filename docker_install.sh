#/bin/bash

# remove all old Docker installations
sudo apt-get remove docker docker-engine docker.io

# install required 3rd party packages
sudo apt-get update
sudo apt-get install apt-transport-https ca-certificates curl gnupg2 software-properties-common

# add docker's pgp
curl -fsSL https://download.docker.com/linux/debian/gpg | sudo apt-key add -

# add Docker's stable repository
sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/debian \
   $(lsb_release -cs) \
   stable"

# update repositories
sudo apt-get update

#install DockerCE packages
sudo apt-get install docker-ce
