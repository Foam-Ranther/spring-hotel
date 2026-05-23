import { createApp } from "./src/app.ts";
import db from "./src/database/db.ts";
import { Hotel, HotelRepository } from "./src/repository/hotel_repository.ts";

const main = async () => {

  const hotels = [
  {
    "name": "Grand Palace Hotel",
    "roomsAvailable": 12,
    "city": "Bengaluru"
  },
  {
    "name": "Ocean View Resort",
    "roomsAvailable": 5,
    "city": "Goa"
  },
  {
    "name": "Mountain Breeze Inn",
    "roomsAvailable": 8,
    "city": "Manali"
  }, 
]

const hotelCollection = db.collection<Hotel>("booking");
for (const hotel of hotels) {
  await hotelCollection.updateOne(
    { name: hotel.name, city: hotel.city },
    { $setOnInsert: hotel },
    { upsert: true }
  );
}
  const hotelRepo = new HotelRepository(hotelCollection);
  const app = createApp(hotelRepo);
  Deno.serve({ port: 3001 }, app.fetch);
};

main();
