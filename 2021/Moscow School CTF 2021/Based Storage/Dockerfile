FROM node:lts
WORKDIR /app
COPY src/package.json /app/package.json
RUN npm install
COPY src /app
COPY flag.txt /app/flag
USER www-data

ENV NODE_ENV=production
CMD ["npm", "start"]
