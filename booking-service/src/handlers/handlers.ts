import { Context } from "hono";

export const listBookingsHandler = async (c: Context) => {
  const bookingRepo = c.get("bookingRepo");
  const bookingList = await bookingRepo.listBookings(); 

  return c.json(bookingList);
};

export const bookHotelHandler = async (c: Context) => {
  const bookingRepo = c.get("bookingRepo");
  const user = "user";
  const hotelData = await c.req.json();
  const result = await bookingRepo.bookHotel(user, hotelData);
  console.log("result", result.insertedId);
  return c.json({
    "bookingId": result.insertedId,
    "userId": user,
    "roomId": hotelData.hotel_id,
    "rooms": hotelData.rooms,
  });
};
