FROM node:24-alpine AS dependencies

WORKDIR /workspace

COPY package.json package-lock.json ./
RUN npm ci

FROM dependencies AS build

COPY . .
RUN npm run build

FROM nginx:1.28-alpine AS production

COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /workspace/dist/mad-frontend/browser /usr/share/nginx/html

EXPOSE 80
