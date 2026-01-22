sudo docker pull mirkogutierrezappx/vecino:latest

sudo docker stop vecino-container 2>/dev/null
sudo docker rm vecimp-container 2>/dev/null

sudo docker build -t vecino .

sudo docker run \
           --restart always \
           -d -p 8080:8080 \
           --env-file .env \
           --network appx \
           --add-host=host.docker.internal:host-gateway \
           --name vecino-container vecino \
        mirkogutierrezappx/vecino:latest
