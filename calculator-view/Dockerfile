FROM ubuntu:latest

USER root

RUN apt-get update
RUN apt-get install -y nginx

# Replace default Nginx configuration file with provided one
RUN rm -v /etc/nginx/nginx.conf
ADD nginx.conf /etc/nginx/

#Copy static web content
ADD web /usr/share/nginx/html/
ADD web /var/www/html/

# Expose ports
EXPOSE 80

# Start the server during container bootstrap
CMD service nginx start
