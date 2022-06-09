FROM node:16.10.0-alpine

WORKDIR /app
COPY package.json ./
RUN yarn
COPY . ./

# Arguments
ARG REACT_APP_API_BASE_URL
ENV REACT_APP_API_BASE_URL=${REACT_APP_API_BASE_URL}

ENTRYPOINT ["yarn","start"]
