FROM node:16.15.0-stretch-slim
WORKDIR /usr/src/app
COPY package.json .
RUN npm i -g @angular/cli@~14.1.3
RUN npm i
COPY . .
RUN ng build
CMD ng serve --host 0.0.0.0
