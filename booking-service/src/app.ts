import { Hono } from "hono";
import { logger } from "hono/logger";

import { bookHotelHandler, listBookingsHandler } from "./handlers/handlers.ts";
import { BookingRepository } from "./repository/booking_respository.ts";
import { authMiddleware } from "./middlewares/jwt_middleware.ts";


type Variables = {
  bookingRepo: BookingRepository;
};

export const createApp = (bookingRepo: BookingRepository) => {
  const app = new Hono<{ Variables: Variables }>(); 

  app.use(logger()); 
  app.use((c, next) => {
    c.set("bookingRepo", bookingRepo);
    return next();  
  } )

  app.get("api/bookings", authMiddleware,listBookingsHandler); 
  app.post("api/bookings", bookHotelHandler); 
  return app; 
}