import { Hono } from "hono";
import { logger } from "hono/logger";

import { bookHotelHandler, listBookingsHandler, searchHotelsHandler } from "./handlers/handlers.ts";
import { BookingRepository } from "./repository/booking_respository.ts";
import { authMiddleware } from "./middlewares/jwt_middleware.ts";
import { createClient, RedisClientType, RedisClusterType } from "redis";

type Variables = {
  bookingRepo: BookingRepository;
  redisClient: any;
};

export const createApp = (bookingRepo: BookingRepository) => {
  const app = new Hono<{ Variables: Variables }>();

  const client = createClient({
    url: "redis://localhost:6379",
  });

  client.connect(); 


  app.use(logger());
  app.use((c, next) => {
    c.set("bookingRepo", bookingRepo);
    c.set("redisClient", client);
    return next();
  });

  app.get("api/bookings", authMiddleware, listBookingsHandler);
  app.post("api/bookings", bookHotelHandler);
  app.get("/api/search/hotels", searchHotelsHandler); 
  return app;
};
