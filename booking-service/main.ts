import { createApp } from "./src/app.ts";
import db from "./src/database/db.ts";
import { Booking, BookingRepository } from "./src/repository/booking_respository.ts";


const main = () => {
  const bookingCollection = db.collection<Booking>("booking"); 
  const bookingRepo = new BookingRepository(bookingCollection); 
  const app = createApp(bookingRepo); 
  Deno.serve({port: 3001 }, app.fetch); 
}

main(); 