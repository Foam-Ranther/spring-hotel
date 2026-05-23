import { Context } from "hono";

export const searchHotelsHandler = async (c: Context) => {
  const city = c.req.query("city");
  const hotelRepo = c.get("hotelRepo");
  const redisClient = c.get("redisClient");
  const hotels = await redisClient.get(city);
  console.log({ hotels });
  if (hotels) {
    console.log("Cache Hit"); 
    return c.json(JSON.parse(hotels));
  }
  console.log("Cache Miss"); 
  const result = await hotelRepo.searchHotel(city);
  await redisClient.set(city, JSON.stringify(result), { EX: 20 });
  return c.json(result);
};
