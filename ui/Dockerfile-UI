FROM nginx:latest

# We copy the UI
COPY ./static /usr/share/nginx/html
COPY ./cors.conf /etc/nginx/conf.d/cors.conf

# Expose port 80
EXPOSE 80

# COPY nginx-custom.conf /etc/nginx/conf.d/default.conf

# Run the jar file
CMD ["nginx", "-g", "daemon off;"]