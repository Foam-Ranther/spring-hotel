import { Hono } from "hono";
import { logger } from "hono/logger";

import { searchHotelsHandler } from "./handlers/handlers.ts";
import { HotelRepository } from "./repository/hotel_repository.ts";
import { createClient } from "redis";

type Variables = {
  hotelRepo: HotelRepository;
  redisClient: any;
};

export const createApp = (hotelRepo: HotelRepository) => {
  const app = new Hono<{ Variables: Variables }>();

  const client = createClient({
    url: Deno.env.get("REDIS_HOST") || "redis://localhost:6379",
  });

  client.connect(); 


  app.use(logger());
  app.use((c, next) => {
    c.set("hotelRepo", hotelRepo);
    c.set("redisClient", client);
    return next();
  });


  app.get("/api/search/hotels", searchHotelsHandler); 
  return app;
};
