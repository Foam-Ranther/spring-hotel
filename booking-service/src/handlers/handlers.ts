import { Context } from "hono";

export const listBookingsHandler = async (c: Context) => {
  const bookingRepo = c.get("bookingRepo");
  const bookingList = await bookingRepo.listBookings();

  return c.json(bookingList);
};

export const bookHotelHandler = async (c: Context) => {
  const bookingRepo = c.get("bookingRepo");
  const redisClient = c.get("redisClient");
  const user = "user";
  const hotelData = await c.req.json();
  const result = await bookingRepo.bookHotel(user, hotelData);
  console.log("result", result.insertedId);
  const bookingData = {
    "bookingId": result.insertedId,
    "userId": user,
    "roomId": hotelData.hotel_id,
    "rooms": hotelData.rooms,
  };
  redisClient.lPush("queue", JSON.stringify(bookingData))
  return c.json(bookingData);
};

export const searchHotelsHandler = async (c: Context) => {
  const bookingRepo = c.get("bookingRepo");
  const redisClient = c.get("redisClient");
  const hotels = await redisClient.get("hotels");
  console.log({ hotels });
  if (hotels) {
    return c.json(JSON.parse(hotels));
  }
  const city = c.req.param("city");
  const result = await bookingRepo.searchHotel(city);
  await redisClient.set("hotels", JSON.stringify(result), { EX: 20 });
  return c.json(result);
};
