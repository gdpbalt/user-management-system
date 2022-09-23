FROM node:16.17-bullseye-slim

WORKDIR /usr/src/app

COPY package*.json .

RUN npm i -g @angular/cli@~14.2.3

RUN npm i

COPY . .

RUN ng build

CMD ng serve --host 0.0.0.0

EXPOSE 4200
