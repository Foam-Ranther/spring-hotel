FROM denoland/deno:2.7.1

WORKDIR /app

COPY . .

RUN deno install

EXPOSE 3001

CMD ["deno", "run", "-A", "main.ts"]