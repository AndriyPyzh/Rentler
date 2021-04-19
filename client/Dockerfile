FROM node:alpine
WORKDIR /app/client
COPY ./client ./
RUN npm install --silent
ENTRYPOINT ["npm", "start"]
EXPOSE 3000