FROM denoland/deno:2.7.1

WORKDIR /app

COPY . .

# Cache dependencies
RUN deno install

CMD ["deno", "run", "--allow-net", "--allow-env", "--allow-read", "main.js"]